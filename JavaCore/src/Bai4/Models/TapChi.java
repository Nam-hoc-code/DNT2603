package Bai4.Models;

public class TapChi extends TaiLieu {
    private String soPhatHanh;
    private Integer thangPhatHanh;

    public TapChi(String maTaiLieu, String tenNhaXuatBan, String soBanPhatHanh, String soPhatHanh, Integer thangPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    public String getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(String soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public Integer getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(Integer thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Số phát hành : " + soPhatHanh);
        System.out.println("Tháng phát hành : " + thangPhatHanh);
    }
}