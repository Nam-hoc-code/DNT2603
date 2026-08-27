package Bai1.Utils.Lesson2;

import Bai1.Models.Group;

import java.util.ArrayList;
import java.util.Scanner;

public class StringExercise {

    //    Exercise 4: String
//    Question 1:
//    Nhập một xâu kí tự, đếm số lượng các từ trong xâu kí tự đó (các từ có thể cách nhau bằng nhiều khoảng trắng );
//
    public int question1String(Scanner sc) {
        String str;
        while (true) {
            System.out.println("Nhập vào một chuỗi ký tự:");
            str = sc.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Không được nhập chuỗi rỗng. Nhập lại!");
        }

        String words = str.replaceAll("\\s+", " ");

        return words.length();
    }

    //
//    Question 2:
//    Nhập hai xâu kí tự s1, s2 nối xâu kí tự s2 vào sau xâu s1;
//
    public String question2String(Scanner sc) {
        String str1;
        String str2;

        while (true) {
            System.out.println("Nhập chuỗi a:");

            str1 = sc.nextLine().trim();

            if (!str1.isEmpty()) {
                break;
            }

            System.out.println("Chuỗi không được rỗng. Nhập lại!");
        }

        while (true) {
            System.out.println("Nhập chuỗi b:");

            str2 = sc.nextLine().trim();

            if (!str2.isEmpty()) {
                break;
            }

            System.out.println("Chuỗi không được rỗng. Nhập lại!");
        }

        return str1 + str2;
    }

    //
//    Question 3:
//    Viết chương trình để người dùng nhập vào tên và kiểm tra, nếu tên chữ viết hoa chữ cái đầu thì viết hoa lên.
//
    public String question3String(Scanner sc) {

        String userName;

        while (true) {
            System.out.println("Nhập giá trị một chuỗi UserName:");

            userName = sc.nextLine().trim();

            if (!userName.isEmpty()) {
                break;
            }

            System.out.println("UserName không được để trống!");
        }

        return Character.toUpperCase(userName.charAt(0))
                + userName.substring(1);
    }

    //
//            Question 4:
//    Viết chương trình để người dùng nhập vào tên in từng ký tự trong tên của người dùng ra
//    VD:
//    Người dùng nhập vào "Nam", hệ thống sẽ in ra
//        "Ký tự thứ 1 là: N"
//                "Ký tự thứ 1 là: A"
//                "Ký tự thứ 1 là: M"
//
    public void question4String(Scanner sc) {
        String str;

        while (true) {
            System.out.println("Nhập vào giá trị một chuỗi:");

            str = sc.nextLine().trim();

            if (!str.isEmpty()) {
                break;
            }

            System.out.println("Không được nhập chuỗi rỗng. Nhập lại!");
        }

        for (int i = 0; i < str.length(); i++) {
            System.out.println(
                    "Ký tự thứ " + (i + 1) + " là: " + str.charAt(i)
            );
        }
    }

//
//    Question 5:
//    Viết chương trình để người dùng nhập vào họ, sau đó yêu cầu người dùng nhập vào tên và hệ thống sẽ in ra họ và tên đầy đủ.
//
    public void  question5String(Scanner sc) {
        String strHo;
        String strTen;
        while (true) {
            strHo = sc.nextLine().trim();

            if (!strHo.isEmpty()) {
                break;
            }
            else {
                System.out.println("Nhập lại giá trị Họ ko đc trống");
            }


        }

        while (true) {
            strTen = sc.nextLine().trim();
            if (!strTen.isEmpty()) {
                break;
            }
            else {

                System.out.println("Giá trị tên ko đc trống ");
            }

        }

        System.out.println("Họ và tên : " + strHo + strTen );
    }
//
//    Question 6:
//    Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và sau đó hệ thống sẽ tách ra họ, tên , tên đệm
//    VD:
//    Người dùng nhập vào "Nguyễn Văn Nam"
//    Hệ thống sẽ in ra
//    "Họ là: Nguyễn"
//            "Tên đệm là: Văn"
//            "Tên là: Nam"
//
    public void question6String(Scanner sc) {
        String strHoTen;
        while (true) {
            strHoTen = sc.nextLine().replaceAll("\\s+", " "); // \\s+ : thay thế cho toàn

            if ( !strHoTen.isEmpty()) {
                break;
            }
            else {
                System.out.println("Họ tên ko được bỏ trống");
            }
        }
        String[] arrayString = strHoTen.split(" ");

        for (String string : arrayString) {
            System.out.println(string);
        }


    }
//
//    Question 7:
//    Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và chuẩn hóa họ và tên của họ như sau:
//    a) Xóa dấu cách ở đầu và cuối và giữa của chuỗi người dùng nhập vào
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "nguyễn văn nam"
//    b) Viết hoa chữ cái mỗi từ của người dùng
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "Nguyễn Văn Nam"
//
    public void  question7String(Scanner sc) {
        String strHoTen;

        while (true) {
            strHoTen = sc.nextLine().trim().replaceAll("\\s+"," "); // bỏ "   " -> " "

            if ( !strHoTen.isEmpty()) {
                break;
            }
            else {
                System.out.println("giá trị đầu vào ko được trống !");
            }
        }

        String[] arrayString = strHoTen.split(" ");
        String strResult =null;
        for (String string : arrayString) {
            string = Character.toUpperCase(string.charAt(0)) + string.substring(1);
            strResult =  strResult + string;
        }

        System.out.println(" Họ và tên" +  strResult);


    }

//
//    Question 8:
//    In ra tất cả các group có chứa chữ "Java"
//
    public  void  question8String(ArrayList<Group> groups) {
        for (Group group : groups) {
           if (  group.getGroupName().toString().contains("Java") ) {
               System.out.println("Tên nhóm chứa chuỗi Java" + group.getGroupName().toString());
           }
        }
    }
//
//    Question 9:
//    In ra tất cả các group "Java"
//
    public  void  question9String(ArrayList<Group> groups) {
        for (Group group : groups) {
            if( group.getGroupName().toString().equals("Java") ) {
                System.out.println("Tên nhóm Java" + group.getGroupName().toString());

            }
        }
    }
//
//    Question 10:
//    Kiểm tra 2 chuỗi có là đảo ngược của nhau hay không.
//    Nếu có xuất ra “OK” ngược lại “KO”.
//    Ví dụ “word” và “drow” là 2 chuỗi đảo ngược nhau.
//
    public  String  question10String(Scanner sc) {
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        int left = str1.length()-1, right = 0;

        if( str1.length() != str2.length() ) {
             return "KO";
        }
        while (left >= 0 ) {
            if ( str1.charAt(left) == str2.charAt(left) ) {
                left --;
                right ++;
            }
            else {
                return "KO";
            }
        }
        return "OK";
    }
//
//    Question 11: Count special Character
//    Tìm số lần xuất hiện ký tự "a" trong chuỗi
//
    public  int  question11String(Scanner sc) {
        String str1 = sc.nextLine();
        int sum = 0;
        for (int i = 0; i < str1.length() ; i++) {
            if ( str1.charAt(i) == 'a' ) {
                sum ++;
            }
        }
        return sum;
    }
//
//    Question 12: Reverse String
//    Đảo ngược chuỗi sử dụng vòng lặp
//
    public  void  question12String(Scanner sc) {
        String str1 = sc.nextLine();
        char[] charArray = str1.toCharArray();
        int left = 0, right = charArray.length-1;
        while (left <= right ) {
            char temp =  charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
        }

        System.out.println(new String (charArray));
    }
//
//    Question 13:
//    String not contains digit
//    Kiểm tra một chuỗi có chứa chữ số hay không, nếu có in ra false ngược lại true.
//    Ví dụ:
//            "abc" => true
//            "1abc", "abc1", "123", "a1bc", null => false
//
        public void question13String(Scanner sc) {
            String str1 = sc.nextLine();

            for (int i = 0; i < str1.length() ; i++) {
                char ch = str1.charAt(i);
                if ( ch > '0' && ch < '9' ) {
                    System.out.println(false);
                    break;
                }
            }
            System.out.println(true);
        }
//
//    Question 14: Replace character
//    Cho một chuỗi str, chuyển các ký tự được chỉ định sang một ký tự khác cho trước.
//    Ví dụ:
//            "VTI Academy" chuyển ký tự 'e' sang '*' kết quả " VTI Acad*my"
//
public  void  question14String(Scanner sc) {
    System.out.println("Nhập chuỗi : ");
    String str1 = sc.nextLine();
    System.out.println( "Nhập ký tự bạn muốn đc đổi : " );
    char kyTuBiDoi = sc.next().charAt(0);
    System.out.println("Nhập ký tự bạn muốn đỏi thành");
    char kyTuSeDoi = sc.next().charAt(0);

    char[] charArray = str1.toCharArray();

    int left = 0, right = charArray.length-1;

    for (int i = 0; i < charArray.length ; i++) {
        if ( charArray[i] == kyTuBiDoi ) {
            charArray[i] = kyTuSeDoi;
        }
    }

    System.out.println(new String (charArray));
}
//
//    Question 15: Revert string by word
//    Đảo ngược các ký tự của chuỗi cách nhau bởi dấu cách mà không dùng thư viện.
//    Ví dụ: " I am developer " => "developer am I".
//    Các ký tự bên trong chỉ cách nhau đúng một dấu khoảng cách.
//    Gợi ý: Các bạn cần loại bỏ dấu cách ở đầu và cuối câu, thao tác cắt chuỗi theo dấu cách
//
    public void question15String(Scanner sc) {
        String str1 = sc.nextLine();

        while ( true ) {
            str1 = sc.nextLine().trim().replaceAll("\\s+"," ");

            if ( !str1.isEmpty()) {
                break;
            }
            else {
                System.out.println("Giá trị đầu vào ko đc để trống");
            }

        }
        String stringResult = "";
        String[] arrayString = str1.split(" ");
        for (int i = arrayString.length-1; i >= 0 ; i--) {
            stringResult = stringResult + arrayString[i] + " ";
        }
        System.out.println( stringResult);


    }

//
//    Question 16:
//    Cho một chuỗi str và số nguyên n >= 0. Chia chuỗi str ra làm các phần bằng nhau với n ký tự.
//    Nếu chuỗi không chia được thì xuất ra màn hình “KO”.
//
    public void question16String(Scanner sc) {
        String str1 = sc.nextLine();
        int n = sc.nextInt();

        if (str1.length() % n != 0) {
            System.out.println("KO");
        }

        for (int i = 0; i < str1.length() ; i+=n) {
            System.out.println(str1.substring(i, i+n));
        }
    }

    public static void main(String[] args) {
        StringExercise stringExercise = new StringExercise();

        Scanner sc = new Scanner(System.in);

        System.out.println(stringExercise.question3String(sc));
    }
}