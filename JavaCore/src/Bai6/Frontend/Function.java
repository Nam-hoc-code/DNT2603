package Bai6.Frontend;

import Bai1.Models.Account;
import Bai5.Utils.CheckInput;
import Bai6.Backend.controller.QLTVController;


import java.util.List;
import java.util.Scanner;

public class Function {
    QLTVController qlTVController = new QLTVController();
    Scanner sc = new Scanner(System.in);
    public void hienThi() {
       qlTVController.getAccounts();
    };

    public void them() {
        qlTVController.themAccount();
    };

    public void sua() {
        qlTVController.suaAccount();
    };

    public void xoa() {
        qlTVController.xoaAccount();
    };

    public void timKiem() {
        qlTVController.timKiemAccount();
    };

    public void menu() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ NHÂN VIÊN ==========");
            System.out.println("1. Hiển thị toàn bộ Account.");
            System.out.println("2. Thêm Account.");
            System.out.println("3. Sửa Tên Account.");
            System.out.println("4. Xóa Account.");
            System.out.println("5. Tìm kiếm Account.");
            System.out.println("6. Thoát.");

            int choice = CheckInput.nhapSoTrongKhoang(sc, "Mời bạn chọn: ", 1, 11);

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
                    System.out.println("Tạm biệt!");
                    return;
            }
        }
    }
}