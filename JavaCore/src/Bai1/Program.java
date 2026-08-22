package Bai1;

import Bai1.Enums.CategoryName;
import Bai1.Enums.DepartmentName;
import Bai1.Enums.TypeName;

public class Program {
    public static void main(String[] args) {

        Account user1 = new Account(1L,"Nam","Lê Thanh Nam");
        Account user2 = new Account(2L,"A","Nguyen A");

        CategoryQuestion category1 = new CategoryQuestion(1, CategoryName.SQL);
        CategoryQuestion category2 = new CategoryQuestion(2,CategoryName.Java);

        Question question1 = new Question(1,"OPP là gì ? ",new TypeQuestion(1, TypeName.Essay));

        Answer ans1 = new Answer(1L," OOP là Doraemon?",Boolean.FALSE);
        Answer ans2 = new Answer(2L,"OPP là một phương thức lập trình mô phỏng các sự vật, sự việc trong cuộc sống",Boolean.TRUE);
        Answer ans3 = new Answer(3L,"OPP là ..... ",Boolean.FALSE);
        Department department = new Department(1, DepartmentName.Sale);

        

        Exam exam1 = new Exam(1,"004", "A half of semester exam ");

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


    }
}
