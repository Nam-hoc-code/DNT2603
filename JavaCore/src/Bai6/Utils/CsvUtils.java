package Bai6.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine đọc file CSV DÙNG CHUNG bằng generic.
 *
 * <p>Phương thức {@link #importCsv(String, String, CsvMapper)} nhận vào một
 * {@code CsvMapper<T>} để không phụ thuộc vào loại đối tượng:
 * chỉ cần viết mapper mới (parse/saveBatch) là import được entity mới,
 * còn toàn bộ logic đọc file, bỏ dòng trống, gom lỗi, ghi file {@code *_errors.csv}
 * chỉ có MỘT lần trong này.
 *
 * <p>TỐI ƯU: đọc + validate TOÀN BỘ file trước, rồi lưu tất cả MỘT LẦN qua
 * JDBC batch ({@code CsvMapper.saveBatch}) thay vì INSERT từng dòng.
 */
public class CsvUtils {

    /**
     * Import file CSV cho bất kỳ loại đối tượng T nào.
     *
     * @param path          đường dẫn file csv cần import
     * @param defaultHeader header mặc định dùng khi file không có dòng header
     * @param mapper        ánh xạ dòng CSV -> T + lưu N dòng T xuống DB (generic, batch)
     * @return message kết quả (số dòng thành công, số dòng lỗi + đường dẫn file lỗi)
     */
    public static <T> String importCsv(String path, String defaultHeader, CsvMapper<T> mapper) {
        File file = new File(path);
        // kiểm tra ban đầu
        if (!file.exists()) {
            return "File không tồn tại";
        }
        if (!path.toLowerCase().endsWith(".csv")) {
            return "Định dạng file không phù hợp cần .csv";
        }

        List<String> listErrors = new ArrayList<>();
        int insertedCount = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String headerLine = reader.readLine(); // dòng header (tiêu đề cột)

            // PHA 1: đọc + validate toàn bộ file (chưa lưu gì xuống DB)
            List<Row<T>> rows = new ArrayList<>();
            int validCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // bỏ qua dòng trống
                }
                Row<T> row = new Row<>(line);
                try {
                    row.entity = mapper.parse(line.split(",", -1)); // -1: giữ cột rỗng cuối dòng
                    row.batchIndex = validCount++; // vị trí của dòng này trong batch
                } catch (IllegalArgumentException e) {
                    row.error = e.getMessage(); // record lỗi -> gom lý do
                }
                rows.add(row);
            }

            // PHA 2: lưu TOÀN BỘ dòng hợp lệ MỘT LẦN qua JDBC batch
            int[] batchResults = null;
            if (validCount > 0) {
                List<T> entities = new ArrayList<>(validCount);
                for (Row<T> row : rows) {
                    if (row.error == null) {
                        entities.add(row.entity);
                    }
                }
                batchResults = mapper.saveBatch(entities);
            }

            // đối chiếu kết quả batch theo TỪNG dòng (giữ đúng thứ tự trong file)
            for (Row<T> row : rows) {
                if (row.error != null) {
                    listErrors.add(row.line + ", " + row.error);
                    continue;
                }
                boolean ok = batchResults != null
                        && row.batchIndex < batchResults.length
                        && batchResults[row.batchIndex] != Statement.EXECUTE_FAILED;
                if (ok) {
                    insertedCount++;
                } else {
                    listErrors.add(row.line + ", Không thể thêm vào DB (vi phạm ràng buộc)");
                }
            }

            // ghi danh sách lỗi ra file csv (header + errors) bằng BufferedWriter
            if (!listErrors.isEmpty()) {
                String errorPath = path.substring(0, path.toLowerCase().lastIndexOf(".csv")) + "_errors.csv";
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(errorPath), StandardCharsets.UTF_8))) {
                    writer.write((headerLine == null ? defaultHeader : headerLine) + ",error");
                    writer.newLine();
                    for (String error : listErrors) {
                        writer.write(error);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "Thực hiện lưu thành công " + insertedCount + " dòng, " + listErrors.size()
                        + " dòng lỗi. File lỗi: " + errorPath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Thực hiện lưu thành công " + insertedCount + " dòng";
    }

    /**
     * Một dòng dữ liệu trong file CSV: giữ nguyên nội dung gốc + kết quả parse
     * để ghi file lỗi theo ĐÚNG THỨ TỰ dòng như trong file.
     */
    private static class Row<T> {
        final String line;   // nội dung gốc của dòng
        T entity;            // đối tượng đã parse (null nếu dòng lỗi)
        String error;        // lý do dòng lỗi (null nếu parse OK)
        int batchIndex;      // vị trí trong mảng kết quả batch (chỉ dùng với dòng hợp lệ)

        Row(String line) {
            this.line = line;
        }
    }
}
