package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Sach extends TaiLieu {
    private String tenTacGia;
    private String soTrang;

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Tên tác giả : " + tenTacGia);
        System.out.println("Số trang : " + soTrang);
    }
}
