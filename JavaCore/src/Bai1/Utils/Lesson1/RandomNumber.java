package Bai1.Utils.Lesson1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RandomNumber {
//    Exercise 4: Random Number
//    Question 1:
//    In ngẫu nhiên ra 1 số nguyên
//

    public void randomNumbe1r() {
        Random ramdom = new Random();

        int i = ramdom.nextInt(100);
        System.out.println(i);

    }

    //
//    Question 2:
//    In ngẫu nhiên ra 1 số thực
//
    public void randomNumbe2r() {
        Random random = new Random();

        float f = random.nextFloat();
        System.out.println(f);

    }
//
//    Question 3:
//    Khai báo 1 array bao gồm các tên của các bạn trong lớp, sau đó in ngẫu nhiên ra tên của 1 bạn
//
//
    public void randomNumbe3r() {
        ArrayList<String> hocsinh = new ArrayList<>();
        hocsinh.add("Nam1");
        hocsinh.add("Nam2");
        hocsinh.add("Nam3");
        hocsinh.add("Nam4");

        Random random = new Random();
        int hs = random.nextInt(hocsinh.size());
        System.out.println(hocsinh.get(hs));
    }



//    Question 4:
//    Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 24-07-1995 tới ngày 20-12- 1995
//
    public void randomNumbe4r() {
        Random random = new Random();

        int minDay = (int) LocalDate.of(1995,7,24).toEpochDay();
        int maxDay = (int) LocalDate.of(1995,12,20).toEpochDay();

        long ramdomInt = random.nextInt(maxDay - minDay + 1) + minDay;

        LocalDate localDate = LocalDate.ofEpochDay(ramdomInt);
        System.out.println(localDate);
    }

//
//    Question 5:
//    Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 1 năm trở lại đây
//
    public void randomNumbe5r() {
        Random random = new Random();

        int minDay = (int) LocalDate.of(2025,8,24).toEpochDay();
        int maxDay = (int) LocalDate.of(2026,8,24).toEpochDay();

        long ramdomInt = random.nextInt(maxDay - minDay + 1) + minDay;
        LocalDate localDate = LocalDate.ofEpochDay(ramdomInt);
        System.out.println(localDate);

    }
//
//    Question 6:
//    Lấy ngẫu nhiên 1 ngày trong quá khứ.
//
    public void randomNumber6() {
        Random random = new Random();

        Date date = new Date();

        int randomDays = random.nextInt(365) + 1;

        date.setTime(date.getTime() - (long) randomDays * 24 * 60 * 60 * 1000);

        System.out.println(date);
    }
//
//    Question 7:
//    Lấy ngẫu nhiên 1 số có 3 chữ số.
    public void randomNumbe7r() {
        Random random = new Random();

        int ramDomInt = random.nextInt(100,999);
        System.out.println(ramDomInt);
    }

}
