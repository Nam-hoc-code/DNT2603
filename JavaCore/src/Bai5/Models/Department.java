package Bai5.Models;

public class Department {
    private Integer idDepartment;
    private Integer idManager;
    private String departmentName;
    private Integer numberPeople;

    public Department() {}

    public Department(Integer idDepartment, Integer idManager, String departmentName, Integer numberPeople) {
        this.idDepartment = idDepartment;
        this.idManager = idManager;
        this.departmentName = departmentName;
        this.numberPeople = numberPeople;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", idManager=" + idManager +
                ", departmentName='" + departmentName + '\'' +
                ", numberPeople=" + numberPeople +
                '}';
    }
}