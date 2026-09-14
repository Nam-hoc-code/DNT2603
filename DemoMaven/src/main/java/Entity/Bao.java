package Entity;

import java.time.LocalDate;

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
public class Bao extends TaiLieu {
    private LocalDate thoiGianPhatHanh;

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Thời gian phát hành : " + thoiGianPhatHanh);
    }
}
