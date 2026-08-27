package Bai1.Utils.Lesson2;

import Bai1.Models.Account;

public class BoxingUnboxing {
//    Exercise 3: Boxing & Unboxing
//    Question 1:
//    Khởi tạo lương có datatype là Integer có giá trị bằng 5000.
//    Sau đó convert lương ra float và hiển thị lương lên màn hình (với số float có 2 số sau dấu thập phân).
//
    public void question1BoxingUnboxing (Account account) {
        account.setSalary2(100000);

        System.out.println( "Salary after convert : " + (float)account.getSalary2());
    }
//
//    Question 2:
//    Khai báo 1 String có value = "1234567"
//    Hãy convert String đó ra số int
//
    public void question2BoxingUnboxing (String str) {
        int i = Integer.parseInt(str);
        System.out.println("str convert to int : " + i);
    }
//
//    Question 3:
//    Khởi tạo 1 số Integer có value là chữ "1234567"
//    Sau đó convert số trên thành datatype int
    public void question3BoxingUnboxing (Account account) {
        Integer IntergerObject = Integer.parseInt("1234568");
        int i = IntergerObject;
        System.out.println("IntergerObject convert to int : " + i);
    }

}
