package Bai6.Utils;

import java.util.List;

/**
 * Interface GENERIC: ánh xạ 1 dòng CSV (String[]) -> đối tượng T bất kỳ.
 *
 * <p>Khai báo generic {@code CsvMapper<T>} để CÙNG một logic đọc/ghi file CSV
 * trong {@link CsvUtils} có thể dùng lại cho mọi entity (Account, Department, ...).
 *
 * <ul>
 *   <li>{@link #parse(String[])}: kiểm tra + chuyển đổi 1 dòng.
 *       Nếu dòng không hợp lệ -> ném {@link IllegalArgumentException} kèm LÝ DO;
 *       engine sẽ ghi lý do đó vào file lỗi.</li>
 *   <li>{@link #saveBatch(List)}: lưu N đối tượng đã parse xuống DB bằng JDBC
 *       batch (addBatch/executeBatch) -> trả mảng kết quả TỪNG dòng:
 *       {@code >= 0} / {@code SUCCESS_NO_INFO} = thành công,
 *       {@code Statement.EXECUTE_FAILED} = dòng lỗi (vi phạm ràng buộc).</li>
 * </ul>
 *
 * @param <T> loại đối tượng mà mapper này xử lý (vd: T = Account, T = Department)
 */
public interface CsvMapper<T> {

    // Parse + validate 1 dòng CSV -> đối tượng T; không hợp lệ thì ném IllegalArgumentException(lý do)
    T parse(String[] values);

    // Lưu MỘT LẦN toàn bộ danh sách T xuống DB bằng JDBC batch -> mảng kết quả từng dòng
    int[] saveBatch(List<T> entities);
}
