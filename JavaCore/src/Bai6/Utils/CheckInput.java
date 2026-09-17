package Bai6.Utils;

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
}