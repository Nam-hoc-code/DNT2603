package Bai5;

import Bai5.Backend.IQLAccount;
import Bai5.Backend.IQLDepartment;
import Bai5.Backend.IQLPosition;
import Bai5.Backend.QLAccount;
import Bai5.Backend.QLDepartment;
import Bai5.Backend.QLPosition;
import Bai5.Utils.CheckInput;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IQLAccount qlAccount = new QLAccount(sc);
        IQLDepartment qlDepartment = new QLDepartment(sc);
        IQLPosition qlPosition = new QLPosition(sc);

        while (true) {
            System.out.println("\n========== QUẢN LÝ NHÂN VIÊN ==========");
            System.out.println("1. Hiển thị toàn bộ Account.");
            System.out.println("2. Xóa Account.");
            System.out.println("3. Sửa tên Account.");
            System.out.println("4. Thêm Account.");
            System.out.println("5. Hiển thị toàn bộ Department.");
            System.out.println("6. Xóa Department.");
            System.out.println("7. Sửa tên Department.");
            System.out.println("8. Thêm Department.");
            System.out.println("9. Hiển thị toàn bộ Position.");
            System.out.println("10. Xóa Position.");
            System.out.println("11. Thoát.");

            int choice = CheckInput.nhapSoTrongKhoang(sc, "Mời bạn chọn: ", 1, 11);

            switch (choice) {
                case 1:
                    qlAccount.hienThiTatCa();
                    break;
                case 2:
                    qlAccount.xoaTaiKhoan();
                    break;
                case 3:
                    qlAccount.suaTaiKhoan();
                    break;
                case 4:
                    qlAccount.themTaiKhoan();
                    break;
                case 5:
                    qlDepartment.hienThiTatCa();
                    break;
                case 6:
                    qlDepartment.xoaPhong();
                    break;
                case 7:
                    qlDepartment.suaPhong();
                    break;
                case 8:
                    qlDepartment.themPhong();
                    break;
                case 9:
                    qlPosition.hienThiTatCa();
                    break;
                case 10:
                    qlPosition.xoaViTri();
                    break;
                case 11:
                    System.out.println("Tạm biệt!");
                    return;
            }
        }
    }
}