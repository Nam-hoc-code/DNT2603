package Bai1.Utils.Lesson1;

import Bai1.Models.Account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Systemoutprintf {
    //    Question 1:
//    Khai báo 1 số nguyên = 5 và sử dụng lệnh System out printf để in ra số
//    nguyên đó
    public void question1Systemoutprintf() {
        int i = 5;
        System.out.println(i);
    }

//    Question 2:
//    Khai báo 1 số nguyên = 100 000 000 và sử dụng lệnh System out printf để in
//    ra số nguyên đó thành định dạng như sau: 100,000,000

    public void question2Systemoutprintf() {
        int i = 100000000;
        System.out.printf(" %,d", i);
        System.out.println();
    }
//
//    Question 3:
//    Khai báo 1 số thực = 5,567098 và sử dụng lệnh System out printf để in ra số
//    thực đó chỉ bao gồm 4 số đằng sau

    public void question3Systemoutprintf() {
        float f = 5.567098f;
        System.out.printf("%.4f", f);
    }

//    Question 4:
//    Khai báo Họ và tên của 1 học sinh và in ra họ và tên học sinh đó theo định
//    dạng như sau:
//    Họ và tên: "Nguyễn Văn A" thì sẽ in ra trên console như sau:
//    Tên tôi là "Nguyễn Văn A" và tôi đang độc thân.

    public void question4Systemoutprintf(Scanner sc) {
        System.out.println("Nhập tên của bạn : ");
        String name = sc.nextLine();
        System.out.printf("Tên tôi là " + "'" + name + "'" + " và tôi đang độc thân. ");
    }

//    Question 5:
//    Lấy thời gian bây giờ và in ra theo định dạng sau:
//            24/04/2020 11h:16p:20s

    public void question5Systemoutprintf(Scanner sc) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(new Date().getTime());
        System.out.println(dateString);
    }

    //    Question 6:
//    In ra thông tin account (như Question 8 phần FOREACH) theo định dạng
//    table (giống trong Database)
    public void question6Systemoutprintf(ArrayList<Account> accounts) {
        System.out.println("+-----+---------------+----------------------+-----------------------+");
        System.out.println("|  ID |         Email |             FullName |            Department | ");

        for (Account accountUser : accounts) {
            System.out.printf("|%-5d|%-15s|%-30s|%-15s|\n", accountUser.getAccountId(), accountUser.getEmail(), accountUser.getFullName(), String.valueOf(accountUser.getDepartment().getDepartmentName()));
        }

    }


}
