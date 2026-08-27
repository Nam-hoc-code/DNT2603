package Bai1.Utils.Lesson1;

import Bai1.Models.Exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateFormat {

    //    Exercise 3: Date Format
//    Question 1:
//    In ra thông tin Exam thứ 1 và property create date sẽ được format theo định
//    dạng vietnamese
//
    public void question8DateFormat(ArrayList<Exam> exams) {
        if (exams.get(0) != null) {
            System.out.println("+-----+------------------+----------+------+");
            System.out.println("|   ID|             Title|  Duration|  Code|");
            Exam exam1st = exams.get(0);
            System.out.printf("|%-7d|%-20s|%-3d|%-5s|",exam1st.getExamID(),exam1st.getTitle(),exam1st.getDuration(),exam1st.getCode());
        } else {
            System.out.println("Exam thứ 1 không tồn tại");
        }
    }


//
//    Question 2:
//    In ra thông tin: Exam đã tạo ngày nào theo định dạng
//    Năm – tháng – ngày – giờ – phút – giây
//

    public void question9DateFormat(ArrayList<Exam> exams) {
        System.out.println("+-------+--------------------+");
        System.out.println("| examId|          createDate|");
        for (Exam exam : exams) {
            String pattern = "dd/MM/yyyy HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String date = sdf.format(exam.getCreationDate());
            System.out.printf("|%-7d|%-20s|",exam.getExamID(),date);

        }
    }

    //
//    Question 3:
//    Chỉ in ra năm của create date property trong Question 2
//
    public void question10DateFormat(ArrayList<Exam> exams) {
        System.out.println("+-------+--------------------+");
        System.out.println("| examId|                Year|");
        for (Exam exam : exams) {
            String pattern = "yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String date = sdf.format(exam.getCreationDate());
            System.out.printf("|%-7d|%-20s|",exam.getExamID(),date);

        }
    }

    //
//    Question 4:
//    Chỉ in ra tháng và năm của create date property trong Question 2
//
    public void question11DateFormat(ArrayList<Exam> exams) {
        System.out.println("+-------+--------------------+");
        System.out.println("| examId|          createDate|");
        for (Exam exam : exams) {
            String pattern = "MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String date = sdf.format(exam.getCreationDate());
            System.out.printf("|%-7d|%-20s|",exam.getExamID(),date);

        }
    }
    //
//    Question 5:
//    Chỉ in ra "MM-DD" của create date trong Question 2
    public void question12DateFormat(ArrayList<Exam> exams) {
        System.out.println("+-------+--------------------+");
        System.out.println("| examId|          createDate|");
        for (Exam exam : exams) {
            String pattern = "MM/dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String date = sdf.format(exam.getCreationDate());
            System.out.printf("|%-7d|%-20s|",exam.getExamID(),date);

        }
    }





}
