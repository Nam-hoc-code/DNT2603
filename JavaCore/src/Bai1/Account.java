package Bai1;

import java.util.Date;
import java.util.Scanner;

public class Account {
    private Long accountId;
    private String userName;
    private String fullName;
    private String email;
    private Department department;
    private Position position;
    private Date createDate;


    public Long getAccountId() {
        return accountId;
    }
    public Account(Long accountId, String userName, String fullName) {
        this.accountId = accountId;
        this.userName = userName;
        this.fullName = fullName;
    }



    public void printInformation() {
        System.out.println("Id tài khoản : " + accountId);
        System.out.println("Tên tài khoản : " + userName);
        System.out.println("Tên người dùng tài khoản : " + fullName);
        System.out.println("Email : " + email);
        System.out.println("Id phòng ban : " + department.getDepartmentId());
        System.out.println("Id vị trí : " + position.getPositionId());
        System.out.println("Ngày tạo : " + createDate);
    }


}
