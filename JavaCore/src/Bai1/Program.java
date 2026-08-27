package Bai1;

import Bai1.Enums.CategoryName;
import Bai1.Enums.DepartmentName;
import Bai1.Enums.PositionName;
import Bai1.Enums.TypeName;
import Bai1.Models.*;
import Bai1.Utils.Lesson1.InputFromConsole;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Program {
    public static void main(String[] args) {

        Department department = new Department( DepartmentName.Sale);
        Position position = new Position(PositionName.Fullstack);

        Account user1 = new Account(1L,"Nam","Lê Thanh Nam",new Date(2024,7,7));
        Account user2 = new Account(2L,"A","Nguyen A",new Date(2026,8,8));
        Account user3 = new Account(3L,"B","Le B",department,position,new Date(2026,8,23));

        // Mock database
        ArrayList<Account> database = new ArrayList<>();

        database.add(user1);
        database.add(user3);
        database.add(user2);

        // Bài 2

        CategoryQuestion category1 = new CategoryQuestion(1, CategoryName.SQL);
        CategoryQuestion category2 = new CategoryQuestion(2,CategoryName.Java);

        TypeQuestion typeQuestion1 = new TypeQuestion(1,TypeName.MultipleChoice);

        Question question1 = new Question(1,"OPP là gì ? ",category1,typeQuestion1,user1,new  Date(2024,7,7));

        Answer ans1 = new Answer(1L," OOP là Doraemon?",question1,Boolean.FALSE);
        Answer ans2 = new Answer(2L,"OPP là một phương thức lập trình mô phỏng các sự vật, sự việc trong cuộc sống",question1,Boolean.TRUE);
        Answer ans3 = new Answer(3L,"OPP là ..... ",question1,Boolean.FALSE);



        Exam exam1 = new Exam(1,"004", "A half of semester exam ",new Date());

        System.out.println("User : ");
        user2.printInformation();
        user1.printInformation();
        System.out.println();

        System.out.println("Question : ");
        question1.printInformation();
        System.out.println();

        System.out.println("Exam : ");
        exam1.printInformation();
        System.out.println();

        System.out.println("Answer : ");
        ans1.printInformation();
        ans2.printInformation();
        ans3.printInformation();

        Scanner sc = new Scanner(System.in);
        InputFromConsole inputFromConsole = new InputFromConsole();
        inputFromConsole.input7(sc);
        sc.close();

    }
}
