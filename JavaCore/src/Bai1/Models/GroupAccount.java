package Bai1.Models;

import java.util.Date;

public class GroupAccount {
    private Group group ; //groupId
    private Account account ;
    private Date joinDate ;

    public GroupAccount(Group group, Account account, Date joinDate) {
        this.group = group ;
        this.account = account;
        this.joinDate = joinDate;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public void setAccount(Account account) {this.account = account;}

    public Account getAccount() {
        return account;
    }

    public void printInfromation() {
        System.out.println("Department Id : " + group.getGroupId());
        System.out.println("Account Id : " + account.getAccountId());
        System.out.println("Join Date : " + joinDate);
    }
}
