package Bai1.Models;

import java.util.Date;

public class Account {
    private Long accountId;
    private String userName;
    private String fullName;
    private String email;
    private Department department;
    private Position position;
    private Date createDate;
    private float salary1; // câu 1 bài 1
    private Integer salary2; // câu 1 bài 3

    public Account() {
        position = new Position();
        department = new Department();
    }

    public Account( String userName, String fullName,String email, Department department, Position position,Date createDate) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.department = new Department();
        this.position = new Position();
        this.createDate = createDate;
    } // right

    public Account(Long accountId, String userName, String fullName, Date createDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
        this.department = new Department();
        this.position = new Position();
        this.createDate = createDate;
    }

    public Account(Long accountId, String userName, String fullName,Department department,Position position , Date createDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public void setEmail(String email) {}

    public float getSalary1() { // just homework
        return salary1;
    }

    public Integer getSalary2() { // just homework
        return salary2;
    }

    public void setSalary1(float salary) {
        this.salary1 = salary;
    }

    public void setSalary2(Integer salary2) {
        this.salary2 = salary2;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }
    public String getFullName() {
        return fullName;
    }
    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }


    public void printInformation() {
        System.out.println("Id tài khoản : " + accountId);
        System.out.println("Tên tài khoản : " + userName);
        System.out.println("Tên người dùng tài khoản : " + fullName);
        System.out.println("Email : " + email);
        System.out.println("Tên phòng ban : " + department.getDepartmentName());
        System.out.println("Id vị trí : " + position.getPositionId());
        System.out.println("Ngày tạo : " + createDate);
    }


}
