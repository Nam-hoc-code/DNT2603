package Bai1;

import java.util.Date;

public class Account {
    private Long accountId ;
    private String userName ;
    private String fullName ;
    private String email ;
    private int departmentId;
    private int positionId;
    private Date createDate;

    public Account(Long accountId,String userName,String fullName) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
    }


    public void printInformation() {
        System.out.println("Id tài khoản : " + accountId);
        System.out.println("Tên tài khoản : " + userName);
        System.out.println("Tên người dùng tài khoản : " + fullName);
        System.out.println("Email : " + email);
        System.out.println("Id phòng ban : " + departmentId);
        System.out.println("Id vị trí : " + positionId);
        System.out.println("Ngày tạo : " + createDate);
    }
