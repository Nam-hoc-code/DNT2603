package Bai4.Backend;

import Bai4.Models.Bao;
import Bai4.Models.Sach;
import Bai4.Models.TaiLieu;
import Bai4.Models.TapChi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class QuanLySach {
//    a) Thêm mới tài liêu: Sách, tạp chí, báo.
    public TaiLieu themMoiTaiLieu(Scanner sc, int luaChon) {
        switch(luaChon) {
            case 1:
                System.out.println("Nhập mã sách : ");
                String maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                String tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                String soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập tên tác giả : ");
                String tenTacGia = sc.nextLine();
                System.out.println("Nhập số trang của sách : ");
                String soTrang = sc.nextLine();
                TaiLieu sach = new Sach(maTaiLieu,tenNhaXuatBan,soBanPhatHanh,tenTacGia,soTrang);
                return sach;


            case 2 :
                System.out.println("Nhập mã tạp chí : ");
                maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập số phát hành : ");
                String soPhatHanh = sc.nextLine();
                System.out.println("Nhập thời gian phát hành (dd/MM/yyyy) : ");
                String pattern = "dd/MM/yyyy";
                LocalDate thoiGianPhatHanh = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern(pattern));
                TaiLieu tapChi = new TapChi(maTaiLieu,tenNhaXuatBan,soBanPhatHanh,soPhatHanh,thoiGianPhatHanh);
                return tapChi;


            case 3 :
                System.out.println("Nhập mã báo : ");
                maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập thời gian phát hành (dd/MM/yyyy) : ");
                pattern = "dd/MM/yyyy";
                thoiGianPhatHanh = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern(pattern));
                TaiLieu bao = new Bao(maTaiLieu,tenNhaXuatBan,soBanPhatHanh,thoiGianPhatHanh);
                return bao;


                default : return null;
        }

    }
    public String xoaTaiLieu( Scanner sc, ArrayList<TaiLieu> taiLieuArrayList) {
        System.out.println("Nhập vào mã tài liệu bạn muốn : ");
        String maTaiLieu = sc.nextLine();
        Iterator<TaiLieu> iterator = taiLieuArrayList.iterator();
        while (iterator.hasNext()) {
            TaiLieu taiLieu = iterator.next();
            if (taiLieu.getMaTaiLieu().equals(maTaiLieu)) {
                iterator.remove();
                return "Xóa tài liệu thành công";
            }
        }
        return "Không tìm thấy";

    }

    public void hienThiThongTinTaiLieu(ArrayList<TaiLieu> taiLieuArrayList) {

        System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");
        System.out.println("|   Mã tài liệu | Tên nhà xuất bản   |    Số bản phát hành|       Số Phát Hành | Thời gian phát hành |        Tên Tác Giả | Số Trang |");
        System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");

        for (TaiLieu taiLieu : taiLieuArrayList) {

            // Các thuộc tính dùng chung
            String maTaiLieu = taiLieu.getMaTaiLieu();
            String tenNhaXuatBan = taiLieu.getTenNhaXuatBan();
            String soBanPhatHanh = taiLieu.getSoBanPhatHanh();

            // Các thuộc tính riêng
            String soPhatHanh = "";
            String thoiGianPhatHanh = "";
            String tenTacGia = "";
            String soTrang = "";

            // Kiểm tra loại tài liệu
            if (taiLieu instanceof Sach) {

                Sach sach = (Sach) taiLieu;

                tenTacGia = sach.getTenTacGia() != null
                        ? sach.getTenTacGia()
                        : "";

                soTrang = sach.getSoTrang() != null
                        ? sach.getSoTrang()
                        : "";

            } else if (taiLieu instanceof TapChi) {

                TapChi tapChi = (TapChi) taiLieu;

                soPhatHanh = tapChi.getSoPhatHanh() != null
                        ? tapChi.getSoPhatHanh()
                        : "";

                thoiGianPhatHanh = tapChi.getThoiGianPhatHanh() != null
                        ? tapChi.getThoiGianPhatHanh().toString()
                        : "";

            } else if (taiLieu instanceof Bao) {

                Bao bao = (Bao) taiLieu;

                thoiGianPhatHanh = bao.getThoiGianPhatHanh() != null
                        ? bao.getThoiGianPhatHanh().toString()
                        : "";
            }

            System.out.printf(
                    "| %-13s | %-18s | %-18s | %-18s | %-19s | %-18s | %-8s |%n",
                    maTaiLieu,
                    tenNhaXuatBan,
                    soBanPhatHanh,
                    soPhatHanh,
                    thoiGianPhatHanh,
                    tenTacGia,
                    soTrang
            );
        }

        System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");
    }

    public void timKiemTheoLoaiTaiLieu(ArrayList<TaiLieu> taiLieuArrayList,Scanner sc) {
        System.out.println("Nhập vào thể loại bạn muốn tìm : ");
        String theLoai = sc.nextLine();
        theLoai = theLoai.toLowerCase();

        if (theLoai.contains("sach")) {
            for (TaiLieu taiLieu : taiLieuArrayList) {
                if (taiLieu instanceof Sach) {
                    taiLieu.printInformation();
                }
            }
        }

        else  if (theLoai.contains("tapchi")) {
            for (TaiLieu taiLieu : taiLieuArrayList) {
                if (taiLieu instanceof TapChi) {
                    taiLieu.printInformation();
                }
            }
        } else if (theLoai.contains("bao")) {
            for (TaiLieu taiLieu : taiLieuArrayList) {
                if (taiLieu instanceof Bao) {
                    taiLieu.printInformation();
                }
            }
        } else {
            for (TaiLieu taiLieu : taiLieuArrayList) {

                    taiLieu.printInformation();

            }
        }

    }
}
