package Bai6.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer idAccount;
    private String name;
    private String location;
    private String accountName;
    private String password;
    // Khóa ngoại là ĐỐI TƯỢNG phù hợp (object reference) thay vì id thô:
    // - position   : tương ứng cột id_position, luôn giữ luôn tên vị trí (positionName)
    // - department : tương ứng cột id_department, luôn giữ luôn tên phòng ban (departmentName)
    private Position position;
    private Department department;
}