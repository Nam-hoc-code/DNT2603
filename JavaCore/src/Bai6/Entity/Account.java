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
    private Integer idPosition;
    private Integer idDepartment;
}