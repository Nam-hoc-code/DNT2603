package Bai1;

import Bai1.Enums.DepartmentName;

public class Department {
    private int departmentId ;
    private DepartmentName departmentName ;

    public Department(int departmentId, DepartmentName departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    public void printInformation() {
        System.out.println("Department Id: " + departmentId);
        System.out.println("Department Name: " + departmentName);
    }
}
