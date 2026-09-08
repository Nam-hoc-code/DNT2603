package Bai4.Models;

import java.time.LocalDate;

public class TapChi extends TaiLieu {
    private String soPhatHanh;
    private LocalDate thoiGianPhatHanh;

    public TapChi(String maTaiLieu,  String tenNhaXuatBan, String soBanPhatHanh, String soPhatHanh, LocalDate thoiGianPhatHanh) {
        super(maTaiLieu,tenNhaXuatBan,soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thoiGianPhatHanh = thoiGianPhatHanh;
    }

    public String getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(String soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
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
        System.out.println("Số phát hành : " + soPhatHanh);
        System.out.println("Thời gian phát hành : " + thoiGianPhatHanh);
    }
}
