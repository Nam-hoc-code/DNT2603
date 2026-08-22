package Bai1;

import Bai1.Enums.GroupName;

import java.util.Date;

public class Group {
    private Long groupId ;
    private GroupName groupName ;
    private Account account ; // id người tạo
    private Date createDate ;

    public Group(Long groupId, GroupName groupName, Account account, Date createDate) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.account = account;
        this.createDate = createDate;
    }
    public void printInfromation() {
        System.out.println("Department Id: " + groupId);
        System.out.println("Department Name: " + groupName);
        System.out.println("Account Id: " + account.getAccountId());
        System.out.println("Create Date: " + createDate);
    }
}
