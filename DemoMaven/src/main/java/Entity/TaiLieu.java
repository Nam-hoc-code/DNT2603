package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiLieu {
    private String maTaiLieu;
    private String tenNhaXuatBan;
    private String soBanPhatHanh;

    public void printInformation() {
        System.out.println("Mã tài liệu : " + maTaiLieu);
        System.out.println("Tên nhà xuất bản : " + tenNhaXuatBan);
        System.out.println("Số bản phát hành : " + soBanPhatHanh);
    }
}
