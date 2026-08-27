package Bai1.Utils.Lesson2;

import Bai1.Models.Account;

import java.util.Arrays;
import java.util.Scanner;

public class DatatypeCasting {

//    Exercise 1: Datatype Casting
//    Question 1:
//    Khai báo 2 số lương có kiểu dữ liệu là float.
//    Khởi tạo Lương của Account 1 là 5240.5 $
//    Khởi tạo Lương của Account 2 là 10970.055$
//    Khai báo 1 số int để làm tròn Lương của Account 1 và in số int đó ra
//    Khai báo 1 số int để làm tròn Lương của Account 2 và in số int đó ra

    public void question1DatatypeCasting(float salary1, float salary2) {
        System.out.println("salary 1 : " + (int) salary1 + " $");
        System.out.println("salary 1 : " + (int) salary2 + " $");
    }

    //
//    Question 2:
//    Lấy ngẫu nhiên 1 số có 5 chữ số (những số dưới 5 chữ số thì sẽ thêm có số 0 ở đầu cho  đủ 5 chữ số)
//
    public String question2DatatypeCasting(Scanner sc) {
        System.out.println("Nhập giá trị số nguyên : ");
        Integer i = null;
        while (true) {
            if (sc.hasNextInt()) {
                i = sc.nextInt(); //
                break;
            } else {
                System.out.println("Nhập lại giá trị i") ;
                sc.nextLine(); // bỏ giá trị sai
            }
        } // nhập vào giá trị int

        if (i.toString().length() >= 5) { // lớn hơn hoặc đã đủ
            return  i.toString();
        } else {
            String str = String.format("%05d", i); // % : format, 0 : số zero, 5 : là độ dài của string, d : số int thêm vào
            return str;
        }

    }


//
//    Question 3:
//    Lấy 2 số cuối của số ở Question 2 và in ra.
//    Gợi ý:
//    Cách 1: convert số có 5 chữ số ra String, sau đó lấy 2 số cuối
//    Cách 2: chia lấy dư số đó cho 100
//

    public void question3DatatypeCasting(Scanner sc) {
        String str = question2DatatypeCasting(sc);
        str.substring(str.length()-1, str.length()-2);
        System.out.println(str);

    }
//
//    Question 4:
//    Viết 1 method nhập vào 2 số nguyên a và b và trả về thương của chúng.

    public float question4DatatypeCasting(Scanner sc) {
        Integer number1;
        Integer number2;

        while (true) {
            if (sc.hasNextInt()) {
                number1 = sc.nextInt();
                number2 = sc.nextInt();
                break;
            }
            else {
                System.out.println("Số nhập vào ko đáp ứng yêu cầu");
                sc.nextLine();
            }
        }

        return (float) number1/number2;
    }

    public static void main(String[] args) {
        //        DatatypeCasting obj = new DatatypeCasting();
        //        Account a1 = new Account();
        //        Account a2 = new Account();
        //
        //        a1.setSalary1(5240.5f);
        //        a2.setSalary1(5240.5f);
//        //        obj.question1DatatypeCasting(a1.getSalary1(),a2.getSalary1());
//                String a = new String("  ff   ff    ");
//        String strWithOutWhiteSpace = a.replace("\\s+", " ").trim();
//        String[] arrayList =  strWithOutWhiteSpace.split(" ");
//        for (String string : arrayList) {
//            System.out.println( string) ;
//        };
        char a = 'a';
        char b = 'a';
        System.out.println(a==b);
    }

}
