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



    public Account( String userName, String fullName,String email, Department department, Position position,Date createDate) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.department = new Department();
        this.position = new Position();
        this.createDate = createDate;
    }

    public Account(Long accountId, String userName, String fullName, Date createDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
        this.department = new Department();
        this.position = new Position();
        this.createDate = createDate;
    }


    public Account(Long accountId, String userName, String fullName,Department department, Position position, Date createDate) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
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
