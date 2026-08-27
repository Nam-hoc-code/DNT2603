package Bai1.Utils.Lesson1;

import Bai1.Models.Account;

import java.util.ArrayList;

public class Method {
//    Question 1:
//    Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10
    public void method1 () {
        System.out.println("Các số nguên chẵn nhỏ hơn 10 ");
        for (int i = 1; i < 10; i++) {
            if( i % 2 == 0 ) {
                System.out.print(", " +  i);
            }
        }
    }

//    Question 2:
//    Tạo method để in thông tin các account
    public void method2 (ArrayList<Account> accounts) {
        System.out.println("+-----+---------------+----------------------+-----------------------+");
        System.out.println("|  ID |         Email |             FullName |            Department | ");

        for (Account accountUser : accounts) {
            System.out.printf("|%-5d|%-15s|%-30s|%-15s|\n", accountUser.getAccountId(), accountUser.getEmail(), accountUser.getFullName(), String.valueOf(accountUser.getDepartment().getDepartmentName()));
        }
    }
//    Question 3:
//    Tạo method để in ra các số nguyên dương nhỏ hơn 10
    public void method3 () {
        System.out.println("Các số nguyên dương nhỏ < 10 : ");
        for ( int i = 1; i < 10; i++) {
            System.out.println(", " + i);
        }
    }
}
