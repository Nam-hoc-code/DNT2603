package Bai4.Models;

public class TaiLieu {
    private String maTaiLieu;
    private String tenNhaXuatBan;
    private String soBanPhatHanh;

    public TaiLieu() {}

    public TaiLieu(String maTaiLieu,  String tenNhaXuatBan, String soBanPhatHanh) {
        this.maTaiLieu = maTaiLieu;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public String getSoBanPhatHanh() {
        return soBanPhatHanh;
    }

    public void setSoBanPhatHanh(String soBanPhatHanh) {
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public void printInformation(){
        System.out.println("Mã tài liệu : " + maTaiLieu);
        System.out.println("Tên nhà xuất bản : " +  tenNhaXuatBan);
        System.out.println("Số bản phát hành : " + soBanPhatHanh);
    }
}
