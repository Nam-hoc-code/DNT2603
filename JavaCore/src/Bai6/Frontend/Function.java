package Bai6.Frontend;

import Bai6.Utils.CheckInput;
import Bai6.Backend.controller.QLTVController;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;

import java.util.List;
import java.util.Scanner;

/**
 * Frontend: tầng DUY NHẤT lo phần giao diện người dùng
 * (nhập liệu, validate, in kết quả, hiển thị menu chọn).
 * Dữ liệu được lấy từ Repository đi lên qua Controller dưới dạng List/boolean.
 */
public class Function {

    QLTVController qlTVController = new QLTVController();

    // Một Scanner dùng chung cho toàn bộ chương trình.
    // LƯU Ý: không được gọi close() vì nó sẽ đóng luôn System.in gây lỗi NoSuchElementException.
    Scanner sc = new Scanner(System.in);

    // ===== In bảng danh sách account (logic in chuyển từ Repository về Frontend) =====
    private void printAccounts(List<Account> accounts) {
        // Đường kẻ ngang phân cách
        String line = "+------+--------------------+--------------------+------------------+----------+-----------------+---------------------+";
        // In header bảng
        System.out.println(line);
        System.out.printf("| %-4s | %-18s | %-18s | %-16s | %-8s | %-15s | %-19s |%n",
                "ID", "Ten", "Noi o", "Tai khoan", "Mat khau", "Vi tri", "Phong ban");
        System.out.println(line);
        // In từng dòng dữ liệu trong danh sách trả về từ Repository
        for (Account acc : accounts) {
            System.out.printf("| %-4s | %-18s | %-18s | %-16s | %-8s | %-15s | %-19s |%n",
                    toDisplay(acc.getIdAccount()),
                    toDisplay(acc.getName()),
                    toDisplay(acc.getLocation()),
                    toDisplay(acc.getAccountName()),
                    toDisplay(acc.getPassword()),
                    positionName(acc),
                    departmentName(acc));
        }
        System.out.println(line);
    }

    // Lấy tên vị trí từ đối tượng Position trong Account (null-safe)
    private String positionName(Account acc) {
        return acc.getPosition() != null ? toDisplay(acc.getPosition().getPositionName()) : "Trống";
    }

    // Lấy tên phòng ban từ đối tượng Department trong Account (null-safe)
    private String departmentName(Account acc) {
        return acc.getDepartment() != null ? toDisplay(acc.getDepartment().getDepartmentName()) : "Trống";
    }

    // Giá trị null trong DB -> hiển thị "Trống"
    private String toDisplay(Object value) {
        return value != null ? String.valueOf(value) : "Trống";
    }

    // ===== 1. Hiển thị toàn bộ account =====
    public void hienThi() {
        // Repository trả List<Account> qua Controller, Frontend chỉ việc in
        List<Account> accounts = qlTVController.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        printAccounts(accounts);
    }

    // ===== 2. Thêm account =====
    public void them() {
        // --- Nhập + validate các trường cơ bản ---
        String name = CheckInput.nhapChuoiKhongRong(sc, "Nhap ten: ");
        String location = CheckInput.nhapChuoiKhongRong(sc, "Nhap noi o: ");
        String accountName = nhapTenTaiKhoan(); // validate: 6 - 20 ký tự
        String password = nhapMatKhau();        // validate: tối thiểu 6 ký tự

        // --- Chọn vị trí & phòng ban LẤY TỪ DB (không hardcode) ---
        int idPosition = chonViTri();
        int idDepartment = chonPhongBan();
        if (idPosition < 0 || idDepartment < 0) { // danh sách trong DB rỗng nên không chọn được
            System.out.println("Khong the them! Danh sach vi tri/phong ban trong DB dang trong.");
            return;
        }

        // --- Đóng gói entity rồi chuyển xuống Repository qua Controller ---
        // Khóa ngoại truyền dạng đối tượng Position / Department (chỉ cần id là đủ để lưu DB)
        Account account = new Account(null, name, location, accountName, password,
                new Position(idPosition, null),
                new Department(idDepartment, null, null, null));
        boolean success = qlTVController.themAccount(account);
        if (success) {
            System.out.println("Da them account moi!");
        } else {
            System.out.println("Them that bai! Vi tri hoac phong ban khong ton tai.");
        }
    }

    // ===== 3. Sửa tên account =====
    public void sua() {
        // validate id: phải là số nguyên > 0
        int id = CheckInput.nhapSoNguyen(sc, "Nhap id Account ban muon sua ten: ");
        if (id <= 0) {
            System.out.println("ID phai la so nguyen duong!");
            return;
        }
        String newName = CheckInput.nhapChuoiKhongRong(sc, "Nhap ten ban muon sua: ");
        boolean success = qlTVController.suaAccount(id, newName);
        if (success) {
            System.out.println("Da cap nhat ten tai khoan co id " + id + "!");
        } else {
            System.out.println("Cap nhat khong thanh cong! Khong tim thay account co id " + id + ".");
        }
    }

    // ===== 4. Xóa account =====
    public void xoa() {
        // validate id: phải là số nguyên > 0
        int id = CheckInput.nhapSoNguyen(sc, "Nhap id account muon xoa: ");
        if (id <= 0) {
            System.out.println("ID phai la so nguyen duong!");
            return;
        }
        boolean success = qlTVController.xoaAccount(id);
        if (success) {
            System.out.println("Da xoa account co id " + id + "!");
        } else {
            System.out.println("Khong the xoa! Account khong ton tai hoac dang la quan ly cua mot phong ban.");
        }
    }

    // ===== 5. Tìm kiếm account theo tên =====
    public void timKiem() {
        // validate: từ khóa không được để trống
        String keyword = CheckInput.nhapChuoiKhongRong(sc, "Nhap ten account ban muon tim: ");
        List<Account> accounts = qlTVController.timKiemAccount(keyword);
        if (accounts.isEmpty()) {
            System.out.println("Khong tim thay account nao chua tu khoa \"" + keyword + "\".");
            return;
        }
        printAccounts(accounts);
    }

    // ===== Menu chính =====
    public void menu() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ NHÂN VIÊN ==========");
            System.out.println("1. Hien thi toan bo Account.");
            System.out.println("2. Them Account.");
            System.out.println("3. Sua Ten Account.");
            System.out.println("4. Xoa Account.");
            System.out.println("5. Tim kiem Account.");
            System.out.println("6. Thoat.");

            int choice = CheckInput.nhapSoTrongKhoang(sc, "Moi ban chon: ", 1, 6);

            switch (choice) {
                case 1:
                    this.hienThi();
                    break;
                case 2:
                    this.them();
                    break;
                case 3:
                    this.sua();
                    break;
                case 4:
                    this.xoa();
                    break;
                case 5:
                    this.timKiem();
                    break;
                case 6:
                    System.out.println("Tam biet!");
                    return;
            }
        }
    }

    // ===== Các hàm nhập + validate riêng =====

    // Tên tài khoản phải có độ dài 6 - 20 ký tự
    private String nhapTenTaiKhoan() {
        while (true) {
            String value = CheckInput.nhapChuoiKhongRong(sc, "Nhap ten tai khoan (6-20 ky tu): ");
            if (value.length() >= 6 && value.length() <= 20) {
                return value;
            }
            System.out.println("Ten tai khoan phai tu 6 - 20 ky tu, nhap lai!");
        }
    }

    // Mật khẩu phải đủ mạnh: tối thiểu 8 ký tự, có chữ thường + chữ hoa + số + ký tự đặc biệt (@$!%*?&)
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private String nhapMatKhau() {
        while (true) {
            String value = CheckInput.nhapChuoiKhongRong(sc, "Nhap mat khau: ");
            if (value.matches(PASSWORD_REGEX)) {
                return value;
            }
            System.out.println("Mat khau phai co toi thieu 8 ky tu, gom chữ thuong, CHU HOA, chu so va ky tu dac biet (@$!%*?&)!");
        }
    }

    // Hiển thị menu chọn vị trí, danh sách LẤY TỪ DB qua getPositions()
    private int chonViTri() {
        List<Position> positions = qlTVController.getPositions();
        if (positions.isEmpty()) {
            return -1; // DB không có dữ liệu vị trí
        }
        System.out.println("--- Chon vi tri (lay tu DB) ---");
        for (int i = 0; i < positions.size(); i++) {
            System.out.println((i + 1) + ". " + positions.get(i).getPositionName());
        }
        // validate: chỉ được chọn từ 1 -> số lượng vị trí có trong DB
        int choice = CheckInput.nhapSoTrongKhoang(sc, "Moi chon vi tri: ", 1, positions.size());
        return positions.get(choice - 1).getIdPosition(); // map số chọn -> id thật trong DB
    }

    // Hiển thị menu chọn phòng ban, danh sách LẤY TỪ DB qua getDepartments()
    private int chonPhongBan() {
        List<Department> departments = qlTVController.getDepartments();
        if (departments.isEmpty()) {
            return -1; // DB không có dữ liệu phòng ban
        }
        System.out.println("--- Chon phong ban (lay tu DB) ---");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDepartmentName());
        }
        // validate: chỉ được chọn từ 1 -> số lượng phòng ban có trong DB
        int choice = CheckInput.nhapSoTrongKhoang(sc, "Moi chon phong ban: ", 1, departments.size());
        return departments.get(choice - 1).getIdDepartment(); // map số chọn -> id thật trong DB
    }
}