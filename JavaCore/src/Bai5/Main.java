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
        IQLAccount qlAccount = new QLAccount();
        IQLDepartment qlDepartment = new QLDepartment();
        IQLPosition qlPosition = new QLPosition();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== QUẢN LÝ NHÂN VIÊN ==========");
            System.out.println("1. Hiển thị toàn bộ Account.");
            System.out.println("2. Hiển thị toàn bộ Department.");
            System.out.println("3. Hiển thị toàn bộ Position.");
            System.out.println("4. Thoát.");

            int choice = CheckInput.nhapSoTrongKhoang(sc, "Mời bạn chọn: ", 1, 4);

            switch (choice) {
                case 1:
                    qlAccount.hienThiTatCa();
                    break;
                case 2:
                    qlDepartment.hienThiTatCa();
                    break;
                case 3:
                    qlPosition.hienThiTatCa();
                    break;
                case 4:
                    System.out.println("Tạm biệt!");
                    return;
            }
        }
    }
}