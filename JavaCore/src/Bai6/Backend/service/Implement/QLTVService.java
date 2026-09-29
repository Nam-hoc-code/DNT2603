package Bai6.Backend.service.Implement;

import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Backend.reponsitory.Implement.QLTVResponsitory;
import Bai6.Backend.service.IQLTVService;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;
import Bai6.Utils.CheckInput;
import Bai6.Utils.CsvMapper;
import Bai6.Utils.CsvUtils;

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

    // Header mặc định cho file csv account (dùng khi file không có dòng header)
    private static final String ACCOUNT_HEADER = "name,location,account_name,password,id_position,id_department";
    // Header mặc định cho file csv department
    private static final String DEPARTMENT_HEADER = "department_name,number_people,id_manager";

    @Override
    public String importCsv(String path) {
        // GENERIC: chỉ cung cấp mapper; toàn bộ đọc file / gom lỗi / ghi file lỗi do CsvUtils lo
        return CsvUtils.importCsv(path, ACCOUNT_HEADER, accountCsvMapper());
    }

    @Override
    public String importDepartmentCsv(String path) {
        // GENERIC: cùng engine CsvUtils, chỉ thay mapper là import được entity khác
        return CsvUtils.importCsv(path, DEPARTMENT_HEADER, departmentCsvMapper());
    }

    // Mapper GENERIC cho Account: parse/validate 1 dòng CSV -> Account, saveBatch -> repository.themBatch
    private CsvMapper<Account> accountCsvMapper() {
        // Nạp 1 LẦN dữ liệu từ DB vào bộ nhớ: tập id vị trí / phòng ban + tập name / account_name
        // (thay vì query từng dòng -> tránh mở kết nối hàng nghìn lần khi import file lớn)
        Set<Integer> positionIds = new HashSet<>();
        Set<Integer> departmentIds = new HashSet<>();
        Set<String> existingNames = new HashSet<>();
        Set<String> existingAccountNames = new HashSet<>();
        for (Position p : qlTVResponsitory.getPositions()) {
            positionIds.add(p.getIdPosition());
        }
        for (Department d : qlTVResponsitory.getDepartments()) {
            departmentIds.add(d.getIdDepartment());
        }
        for (Account acc : qlTVResponsitory.hienThi()) {
            existingNames.add(acc.getName());
            existingAccountNames.add(acc.getAccountName());
        }
        // bắt trùng ngay TRONG file (DB không có UNIQUE constraint ở name/account_name)
        Set<String> seenNames = new HashSet<>();
        Set<String> seenAccountNames = new HashSet<>();

        return new CsvMapper<Account>() {
            @Override
            public Account parse(String[] values) {
                if (values.length < 6) {
                    throw new IllegalArgumentException("Thiếu cột dữ liệu");
                }
                String name = values[0].trim();
                String location = values[1].trim();
                String accountName = values[2].trim();
                String password = values[3].trim();

                if (!CheckInput.isTenHopLe(name)) {
                    throw new IllegalArgumentException("Tên phải từ 2 - 30 ký tự");
                }
                if (!seenNames.add(name)) {
                    throw new IllegalArgumentException("Tên đã tồn tại trong file CSV này");
                }
                if (existingNames.contains(name)) {
                    throw new IllegalArgumentException("Tên đã tồn tại trong DB");
                }
                if (!CheckInput.isNoiOiHopLe(location)) {
                    throw new IllegalArgumentException("Nơi ở không được quá 50 ký tự");
                }
                if (!CheckInput.isAccountNameHopLe(accountName)) {
                    throw new IllegalArgumentException("Tên tài khoản phải từ 6 - 20 ký tự");
                }
                if (!seenAccountNames.add(accountName)) {
                    throw new IllegalArgumentException("Tên tài khoản đã tồn tại trong file CSV này");
                }
                if (existingAccountNames.contains(accountName)) {
                    throw new IllegalArgumentException("Tên tài khoản đã tồn tại trong DB");
                }
                if (!CheckInput.isPasswordHopLe(password)) {
                    throw new IllegalArgumentException("Mật khẩu phải tối thiểu 8 ký tự, gồm chữ thường, chữ hoa, số và ký tự đặc biệt");
                }

                int idPosition;
                int idDepartment;
                try {
                    idPosition = Integer.parseInt(values[4].trim());
                    idDepartment = Integer.parseInt(values[5].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Vị trí/phòng ban phải là số");
                }
                if (!positionIds.contains(idPosition)) {
                    throw new IllegalArgumentException("Vị trí id " + idPosition + " không tồn tại trong DB");
                }
                if (!departmentIds.contains(idDepartment)) {
                    throw new IllegalArgumentException("Phòng ban id " + idDepartment + " không tồn tại trong DB");
                }

                return new Account(null, name, location, accountName, password,
                        new Position(idPosition), new Department(idDepartment));
            }

            @Override
            public int[] saveBatch(List<Account> accounts) {
                // batch insert: toàn bộ dòng hợp lệ được gửi xuống DB trong 1 lần
                return qlTVResponsitory.themBatch(accounts);
            }
        };
    }

    // Mapper GENERIC cho Department: parse/validate 1 dòng CSV -> Department, saveBatch -> repository.themDepartmentBatch
    private CsvMapper<Department> departmentCsvMapper() {
        // Nạp 1 LẦN từ DB vào bộ nhớ: tập id account (ứng viên làm quản lý) + tập tên phòng ban
        Set<Integer> managerIds = new HashSet<>();
        Set<String> existingDepartmentNames = new HashSet<>();
        for (Account acc : qlTVResponsitory.hienThi()) {
            managerIds.add(acc.getIdAccount());
        }
        for (Department d : qlTVResponsitory.getDepartments()) {
            existingDepartmentNames.add(d.getDepartmentName());
        }
        // bắt trùng ngay TRONG file (DB không có UNIQUE constraint ở department_name)
        Set<String> seenDepartmentNames = new HashSet<>();

        return new CsvMapper<Department>() {
            @Override
            public Department parse(String[] values) {
                if (values.length < 3) {
                    throw new IllegalArgumentException("Thiếu cột dữ liệu");
                }
                String departmentName = values[0].trim();
                String numberPeople = values[1].trim();
                String idManager = values[2].trim();

                // department_name: 1 - 100 ký tự, không trùng
                if (!CheckInput.isTenPhongBanHopLe(departmentName)) {
                    throw new IllegalArgumentException("Tên phòng ban phải từ 1 - 100 ký tự");
                }
                if (!seenDepartmentNames.add(departmentName)) {
                    throw new IllegalArgumentException("Tên phòng ban đã tồn tại trong file CSV này");
                }
                if (existingDepartmentNames.contains(departmentName)) {
                    throw new IllegalArgumentException("Tên phòng ban đã tồn tại trong DB");
                }

                // number_people: để trống -> null, ngược lại phải là số nguyên >= 0
                Integer numberOfPeople = null;
                if (!numberPeople.isEmpty()) {
                    try {
                        numberOfPeople = Integer.parseInt(numberPeople);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Số người phải là số nguyên");
                    }
                    if (numberOfPeople < 0) {
                        throw new IllegalArgumentException("Số người không được là số âm");
                    }
                }

                // id_manager: để trống -> null (chưa có quản lý), ngược lại phải tồn tại trong bảng account
                Integer idManagerValue = null;
                if (!idManager.isEmpty()) {
                    try {
                        idManagerValue = Integer.parseInt(idManager);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Quản lý phải là id số");
                    }
                    if (!managerIds.contains(idManagerValue)) {
                        throw new IllegalArgumentException("Quản lý id " + idManagerValue + " không tồn tại trong DB");
                    }
                }

                return new Department(null, idManagerValue, departmentName, numberOfPeople);
            }

            @Override
            public int[] saveBatch(List<Department> departments) {
                // batch insert: toàn bộ dòng hợp lệ được gửi xuống DB trong 1 lần
                return qlTVResponsitory.themDepartmentBatch(departments);
            }
        };
    }
}