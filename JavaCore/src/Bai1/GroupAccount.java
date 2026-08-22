package Bai1;

import java.util.Date;

public class GroupAccount {
    private Long groupId ;
    private Account account ;
    private Date joinDate ;

    public GroupAccount(Long groupId, Account account, Date joinDate) {
        this.groupId = groupId;
        this.account = account;
        this.joinDate = joinDate;
    }
    public void printInfromation() {
        System.out.println("Department Id : " + groupId);
        System.out.println("Account Id : " + account.getAccountId());
        System.out.println("Join Date : " + joinDate);
    }
}
