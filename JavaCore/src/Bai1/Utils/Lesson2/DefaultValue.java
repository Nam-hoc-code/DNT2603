package Bai1.Utils.Lesson2;

import Bai1.Models.Account;

import java.util.ArrayList;
import java.util.Date;

public class DefaultValue {

//    Exercise 2: Default value
//    Question 1:
//    Không sử dụng data đã insert từ bài trước, tạo 1 array Account và khởi tạo 5 phần tử theo cú pháp (sử dụng vòng for để khởi tạo):
//            ∙ Email: "Email 1"
//            ∙ Username: "User name 1"
//            ∙ FullName: "Full name 1"
//            ∙ CreateDate: now
    public void question1DefaultValue(ArrayList<Account> accounts) {
        for (int i = 0; i < 5; i++) {
            Account a = new Account();
            a.setUserName("User Name 1");
            a.setFullName("Full Name 1");
            a.setEmail("Email 1");
            a.setCreateDate(new Date());
            accounts.add(a);
        }

    }

    public void main (String[] args) {
        DefaultValue obj = new DefaultValue();
        ArrayList<Account> accounts = new ArrayList<>();
        obj.question1DefaultValue(accounts);

        for(Account a : accounts){
            a.printInformation();
        }
    }

}
