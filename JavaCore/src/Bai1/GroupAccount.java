package Bai1;

import java.util.Date;

public class GroupAccount {
    private Long groupId ;
    private Long accountId ;
    private Date joinDate ;

    public GroupAccount(Long groupId, Long accountId, Date joinDate) {
        this.groupId = groupId;
        this.accountId = accountId;
        this.joinDate = joinDate;
    }
    public void printInfromation() {
        System.out.println("Department Id : " + groupId);
        System.out.println("Account Id : " + accountId);
        System.out.println("Join Date : " + joinDate);
    }
}
