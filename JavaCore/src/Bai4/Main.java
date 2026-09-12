//package Bai4;
//
//import Bai4.Backend.QuanLySach;
//import Bai4.Models.Bao;
//import Bai4.Models.Sach;
//import Bai4.Models.TaiLieu;
//import Bai4.Models.TapChi;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        ArrayList<TaiLieu> taiLieuArrayList = new ArrayList<TaiLieu>();
//        QuanLySach quanLySach = new QuanLySach();
//        Scanner sc = new Scanner(System.in);
//
//        taiLieuArrayList.add(new Sach("S001", "Kim Đồng", "1000", "Nguyễn Nhật Ánh", "320"));
//        taiLieuArrayList.add(new TapChi("T001", "Lao Động", "500", "12", LocalDate.of(2024, 5, 20)));
//        taiLieuArrayList.add(new Bao("B001", "Tuổi Trẻ", "2000", LocalDate.of(2024, 5, 22)));
//
//        while (true) {
//            System.out.println("========== QUẢN LÝ THƯ VIỆN ==========");
//            System.out.println("1. Thêm mới tài liệu.");
//            System.out.println("2. Xóa tài liệu theo mã tài liệu.");
//            System.out.println("3. Hiển thị thông tin tài liệu.");
//            System.out.println("4. Tìm kiếm tài liệu theo loại.");
//            System.out.println("5. Thoát khỏi chương trình.");
//            System.out.print("Mời bạn chọn chức năng: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Chọn loại tài liệu bạn muốn thêm: ");
//                    System.out.println("1. Sách.");
//                    System.out.println("2. Tạp chí.");
//                    System.out.println("3. Báo.");
//                    System.out.print("Mời bạn chọn: ");
//                    int type = sc.nextInt();
//                    sc.nextLine();
//                    TaiLieu taiLieu = quanLySach.themMoiTaiLieu(sc, type);
//                    if (taiLieu != null) {
//                        taiLieuArrayList.add(taiLieu);
//                        System.out.println("Thêm tài liệu thành công!");
//                    } else {
//                        System.out.println("Loại tài liệu không hợp lệ!");
//                    }
//                    break;
//
//                case 2:
//                    System.out.println(quanLySach.xoaTaiLieu(sc, taiLieuArrayList));
//                    break;
//
//                case 3:
//                    quanLySach.hienThiThongTinTaiLieu(taiLieuArrayList);
//                    break;
//
//                case 4:
//                    quanLySach.timKiemTheoLoaiTaiLieu(taiLieuArrayList, sc);
//                    break;
//
//                case 5:
//                    System.out.println("Chương trình kết thúc. Tạm biệt!");
//                    return;
//
//                default:
//                    System.out.println("Chức năng không hợp lệ, vui lòng chọn lại!");
//                    break;
//            }
//            System.out.println();
//        }
//    }
//}
public static void main(String[] args) {
    Integer i =  null;

    String s = String.valueOf(i);
    if ( s instanceof String) {
        System.out.println("Nó là String");
    }


}