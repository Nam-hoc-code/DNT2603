package Bai4.Models;

public class Sach extends TaiLieu{
    private String tenTacGia;
    private String soTrang;

    public Sach() {}

    public Sach( String maTaiLieu, String tenNhaXuatBan, String soBanPhatHanh, String tenTacGia, String soTrang) {
        super(maTaiLieu,tenNhaXuatBan,soBanPhatHanh);
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    public void printInformation(){
        System.out.println("Tên tác giả : " + tenTacGia);
        System.out.println("Số trang : " + soTrang);
    }
}
