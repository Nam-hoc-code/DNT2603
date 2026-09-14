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
public class TapChi extends TaiLieu {
    private String soPhatHanh;
    private Integer thangPhatHanh;

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Số phát hành : " + soPhatHanh);
        System.out.println("Tháng phát hành : " + thangPhatHanh);
    }
}
