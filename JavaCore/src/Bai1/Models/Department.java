package Bai1.Models;

import Bai1.Enums.DepartmentName;

public class Department {
    private int departmentId ;
    private DepartmentName departmentName ;
    private String location;

    public Department() {
        this.departmentName = DepartmentName.Waiting;
    }

    public Department( DepartmentName departmentName) {
        this.departmentName =  departmentName;
    }

    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public String getLocation() {
        return location;
    }

    public DepartmentName getDepartmentName() {
        return departmentName;
    }
    public void printInformation() {
        System.out.println("Department Id: " + departmentId);
        System.out.println("Department Name: " + departmentName);
    }

    public String toString() {
        return  "Department Id: " + departmentId + "Department Name: " + departmentName;
    }
}
