package Bai6.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer idDepartment;
    private Integer idManager;
    private String departmentName;
    private Integer numberPeople;
}