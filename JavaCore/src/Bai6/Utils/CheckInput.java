package Bai6.Utils;

import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Backend.reponsitory.Implement.QLTVResponsitory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CheckInput {

    public static int nhapSoNguyen(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ, vui lòng nhập số nguyên!");
            }
        }
    }

    public static int nhapSoTrongKhoang(Scanner sc, String message, int min, int max) {
        while (true) {
            int value = nhapSoNguyen(sc, message);
            if (value < min || value > max) {
                System.out.println("Vui lòng nhập trong khoảng " + min + " - " + max + "!");
                continue;
            }
            return value;
        }
    }

    public static String nhapChuoiKhongRong(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Không được để trống, vui lòng nhập lại!");
                continue;
            }
            return value;
        }
    }

    public static boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmailHopLe(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public static LocalDate nhapNgay(Scanner sc, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print(message);
                return LocalDate.parse(sc.nextLine().trim(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ, nhập theo định dạng dd/MM/yyyy!");
            }
        }
    }

    public static Boolean checkExistUserName(int userId, String userName) {
        IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();
        return qlTVResponsitory.isNameDuplicated(userId,userName);
    }

    public static Boolean checkOldName(int userId, String userName) {
        IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();
        return qlTVResponsitory.isOldName(userId,userName);
    }

    // ===== Các hàm validate chuẩn cho đầu vào (dùng chung ở Thêm & Import CSV) =====

    // Tên người dùng: 2 - 30 ký tự
    public static boolean isTenHopLe(String input) {
        if (input == null) {
            return false;
        }
        int length = input.trim().length();
        return length >= 2 && length <= 30;
    }

    // Nơi ở: không trống, tối đa 50 ký tự
    public static boolean isNoiOiHopLe(String input) {
        if (input == null) {
            return false;
        }
        int length = input.trim().length();
        return length >= 1 && length <= 50;
    }

    // Tên tài khoản: 6 - 20 ký tự
    public static boolean isAccountNameHopLe(String input) {
        if (input == null) {
            return false;
        }
        int length = input.trim().length();
        return length >= 6 && length <= 20;
    }

    // Mật khẩu: tối thiểu 8 ký tự + chữ thường + chữ hoa + số + ký tự đặc biệt (@$!%*?&)
    public static boolean isPasswordHopLe(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    // Kiểm tra tên đã tồn tại ở BẤT KỲ account nào (dùng khi thêm mới) -> true nếu trùng
    public static Boolean checkNameExist(String name) {
        IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();
        return qlTVResponsitory.isNameExist(name);
    }

    // Kiểm tra account_name đã tồn tại ở BẤT KỲ account nào (dùng khi thêm mới) -> true nếu trùng
    public static Boolean checkAccountNameExist(String accountName) {
        IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();
        return qlTVResponsitory.isAccountNameExist(accountName);
    }

    public static Boolean checkExistIdUserName (int id) {
        IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();
        if (qlTVResponsitory.getUserName(id) == null ) {
            return true;
        }
        return false;
    }
}