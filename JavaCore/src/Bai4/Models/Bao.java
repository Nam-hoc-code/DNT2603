package Bai4.Models;

import java.time.LocalDate;

public class Bao extends TaiLieu {
    private LocalDate thoiGianPhatHanh;

    public Bao(String maTaiLieu,  String tenNhaXuatBan, String soBanPhatHanh, LocalDate thoiGianPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.thoiGianPhatHanh = thoiGianPhatHanh;
    }
    public LocalDate getThoiGianPhatHanh() {
        return thoiGianPhatHanh;
    }

    public void setThoiGianPhatHanh(LocalDate thoiGianPhatHanh) {
        this.thoiGianPhatHanh = thoiGianPhatHanh;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Thời gian phát hành : " + thoiGianPhatHanh);
    }
}
