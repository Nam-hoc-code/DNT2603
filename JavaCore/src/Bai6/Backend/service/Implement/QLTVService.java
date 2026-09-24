package Bai6.Backend.service.Implement;

import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Backend.reponsitory.Implement.QLTVResponsitory;
import Bai6.Backend.service.IQLTVService;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;
import Bai6.Utils.CheckInput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service: tầng trung gian, chỉ truyền dữ liệu giữa Controller và Repository.
 * Không chứa logic hiển thị, không nhập liệu.
 */
public class QLTVService implements IQLTVService {

    IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();

    @Override
    public List<Account> hienThi() {
        return qlTVResponsitory.hienThi();
    }

    @Override
    public List<Account> timKiem(String keyword) {
        return qlTVResponsitory.timKiem(keyword);
    }

    @Override
    public boolean them(Account account) {
        return qlTVResponsitory.them(account);
    }

    @Override
    public boolean sua(int idAccount, String newName) {
        return qlTVResponsitory.sua(idAccount, newName);
    }

    @Override
    public boolean xoa(int idAccount) {
        return qlTVResponsitory.xoa(idAccount);
    }

    @Override
    public List<Position> getPositions() {
        return qlTVResponsitory.getPositions();
    }

    @Override
    public List<Department> getDepartments() {
        return qlTVResponsitory.getDepartments();
    }

    @Override
    public String importCsv(String path) {
        File file = new File(path);
        // kiểm tra ban đầu
        if (!file.exists()) {
            return "File không tồn tại";
        }
        if (!path.toLowerCase().endsWith(".csv")) {
            return "Định dạng file không phù hợp cần .csv";
        }

        ArrayList<String> listErrors = new ArrayList<>();
        int insertedCount = 0;

        // tập id vị trí / phòng ban ĐANG có trong DB để kiểm tra dòng nằm trong khoảng cho phép
        Set<Integer> positionIds = new HashSet<>();
        for (Position p : qlTVResponsitory.getPositions()) {
            positionIds.add(p.getIdPosition());
        }
        Set<Integer> departmentIds = new HashSet<>();
        for (Department d : qlTVResponsitory.getDepartments()) {
            departmentIds.add(d.getIdDepartment());
        }

        // tách dữ lệu để add vào đối tượng
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String headerLine = reader.readLine(); // dòng header (tiêu đề cột)
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // bỏ qua dòng trống
                }
                String[] values = line.split(","); // dữ liệu của một line
                if (values.length < 6) {
                    listErrors.add(line + ", Thiếu cột dữ liệu");
                    continue;
                }

                String name = values[0].trim();
                String location = values[1].trim();
                String accountName = values[2].trim();
                String password = values[3].trim();

                // name: 2 - 30 ký tự, không trùng
                if (!CheckInput.isTenHopLe(name)) {
                    listErrors.add(line + ", Tên phải từ 2 - 30 ký tự");
                    continue;
                }
                if (qlTVResponsitory.isNameExist(name)) {
                    listErrors.add(line + ", Tên đã tồn tại trong DB");
                    continue;
                }
                // location: tối đa 50 ký tự
                if (!CheckInput.isNoiOiHopLe(location)) {
                    listErrors.add(line + ", Nơi ở không được quá 50 ký tự");
                    continue;
                }
                // account_name: 6 - 20 ký tự, không trùng
                if (!CheckInput.isAccountNameHopLe(accountName)) {
                    listErrors.add(line + ", Tên tài khoản phải từ 6 - 20 ký tự");
                    continue;
                }
                if (qlTVResponsitory.isAccountNameExist(accountName)) {
                    listErrors.add(line + ", Tên tài khoản đã tồn tại trong DB");
                    continue;
                }
                // password: tối thiểu 8 ký tự + đủ mạnh
                if (!CheckInput.isPasswordHopLe(password)) {
                    listErrors.add(line + ", Mật khẩu phải tối thiểu 8 ký tự, gồm chữ thường, chữ hoa, số và ký tự đặc biệt");
                    continue;
                }

                int idPosition;
                int idDepartment;
                try {
                    idPosition = Integer.parseInt(values[4].trim());
                    idDepartment = Integer.parseInt(values[5].trim());
                } catch (NumberFormatException e) {
                    listErrors.add(line + ", Vị trí/phòng ban phải là số");
                    continue;
                }
                // id_position, id_department: phải nằm trong khoảng id ĐANG có trong DB
                if (!positionIds.contains(idPosition)) {
                    listErrors.add(line + ", Vị trí id " + idPosition + " không tồn tại trong DB");
                    continue;
                }
                if (!departmentIds.contains(idDepartment)) {
                    listErrors.add(line + ", Phòng ban id " + idDepartment + " không tồn tại trong DB");
                    continue;
                }

                Account account = new Account();
                account.setName(name);
                account.setLocation(location);
                account.setAccountName(accountName);
                account.setPassword(password);
                account.setPosition(new Position(idPosition));
                account.setDepartment(new Department(idDepartment));
                if (qlTVResponsitory.them(account)) {
                    insertedCount++;
                } else {
                    listErrors.add(line + ", Không thể thêm vào DB (vi phạm ràng buộc)");
                }
            }

            // ghi danh sách lỗi ra file csv dùng BufferedWriter
            if (!listErrors.isEmpty()) {
                String errorPath = path.substring(0, path.toLowerCase().lastIndexOf(".csv")) + "_errors.csv";
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(errorPath), StandardCharsets.UTF_8))) {
                    writer.write((headerLine == null ? "name,location,account_name,password,id_position,id_department" : headerLine) + ",error");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thực hiện lưu thành công " + insertedCount + " dòng";
    }
}