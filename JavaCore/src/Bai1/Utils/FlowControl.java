package Bai1.Utils;

import Bai1.Models.Account;
import Bai1.Models.Department;
import Bai1.Models.GroupAccount;
import Bai1.Models.Position;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlowControl {
    //    Exercise 1: Flow Control - Tiếp tục bài tập Day 01
//    IF
//    Question 1:
//    Kiểm tra account thứ 2
//    Nếu không có phòng ban (tức là department == null) thì sẽ in ra text "Nhân viên này chưa có phòng ban"
//    Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"
    public void question1If(ArrayList<Account> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getDepartment().getDepartmentName() == null && i == 1) {
                System.out.println("Nhân viên này chưa có phòng ban");
            } else {
                System.out.println("Phong ban nhân viên nay là " + accounts.get(i).getDepartment().getDepartmentName());
            }

        }
    }

    //    Question 2:
//    Kiểm tra account thứ 2
//    Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
//    Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
//    Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
//    Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
    public void question2If(ArrayList<Account> accounts, ArrayList<GroupAccount> groupAccounts) {
        if (accounts.get(1) != null) { // lấy account ở vị trí 2
            int sum = 0;
            for (GroupAccount groupAccount : groupAccounts) {
                if (groupAccount.getAccount().getAccountId().equals(accounts.get(1).getAccountId())) {
                    sum++;
                }
            }
            if (sum == 0) {
                System.out.println("Nhân viên chưa có group");
            } else if (sum == 1 || sum == 2) {
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
            } else if (sum == 3) {
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
            } else {
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
            }
        } else {
            System.out.println("Tài khoản ko tồn tại");
        }
    }


//Question 3:
//Sử dụng toán tử ternary để làm Question 1

    public String question3If(ArrayList<Account> accounts) {
        String departmentUser2nd = String.valueOf(accounts.get(1).getDepartment().getDepartmentName());

        return (departmentUser2nd == null) ? "Nhân viên này chưa có phòng ban" : "Phong ban nhân viên nay là " + accounts.get(1).getDepartment().getDepartmentName();
    }

//Question 4:
//Sử dụng toán tử ternary để làm yêu cầu sau:
//Kiểm tra Position của account thứ 1
//Nếu Position = Dev thì in ra text "Đây là Developer"
//Nếu không phải thì in ra text "Người này không phải là Developer"

    public String question4If(ArrayList<Account> accounts) {
        String positionName = String.valueOf(accounts.get(1).getPosition().getPositionName());

        return (positionName == "Dev") ? "Đây là Devoloper" : "Người này không phải là Devoloper";
    }


//SWITCH CASE
//Question 5:
//Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau:
//Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
//Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
//Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
//Còn lại in ra "Nhóm có nhiều thành viên"

    public String question5SwitchCase(ArrayList<GroupAccount> groupAccounts) {
        int sum = 0;
        for (GroupAccount groupAccount : groupAccounts) {
            if (groupAccount.getAccount().getAccountId().equals(1L)) {
                sum++;
            }
        }

        switch (sum) {
            case 0 : return  "Nhóm ko có thành viên";

            case 1:
                return "Nhóm có một thành viên";
            case 2:
                return "Nhóm có hai thành viên";
            case 3:
                return "Nhóm có ba thành viên";

            default: return "Nhóm có nhiều thành viên";
        }

    }

//Question 6:
//Sử dụng switch case để làm lại Question 2

    public String question6SwitchCase(ArrayList<Account> accounts, ArrayList<GroupAccount> groupAccounts) {
        if (accounts.get(1) != null) { // lấy account ở vị trí 2
            int sum = 0;
            for (GroupAccount groupAccount : groupAccounts) {
                if (groupAccount.getAccount().getAccountId().equals(accounts.get(1).getAccountId())) {
                    sum++;
                }
            }

            switch (sum) {
                case 0:
                    return "Ko tham gia bất kỳ nhóm  nào";

                case 1, 2:
                    return "Group của nhân viên này là Java Fresher, C# Fresher";

                case 3:
                    return "Nhân viên này là người quan trọng, tham gia nhiều group";

                default:
                    return "Nhân viên này là người hóng chuyện, tham gia tất cả các group";
            }
        }

        return null;
    }

    //
//Question 7:
//Sử dụng switch case để làm lại Question 4
    public String question7SwitchCase(ArrayList<Account> accounts) {
        String positionName = String.valueOf(accounts.get(1).getPosition().getPositionName());

        switch (positionName) {
            case "Dev":
                return "Đây là Devoloper";

            default:
                return "Người này không phải là Devoloper";

        }
    }

//
//FOREACH
//Question 8:
//In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ


    public void question8ForEach(ArrayList<Account> accounts) {
        System.out.println("+-----+--------------------+--------------------+--------------------+");
        System.out.println("|   ID|               Email|            FullName|          Department|");
        for (Account account : accounts) {
            for (Account accountUser : accounts) {
                System.out.printf("|%-5d|%-20s|%-20s|%-20s|\n", accountUser.getAccountId(), accountUser.getEmail(), accountUser.getUserName(), String.valueOf(accountUser.getDepartment().getDepartmentName()));
            }

        }
    }

//Question 9:
//In ra thông tin các phòng ban bao gồm: id và name

    public void question9ForEach(ArrayList<Department> departments) {
        System.out.println("+-----+---------------+");
        for (Department department : departments) {
            System.out.printf("|%-5i|%-15s", department.getDepartmentId(), department.getDepartmentName());
        }
    }

//FOR
//Question 10:
//In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của
//họ theo định dạng như sau:
//Thông tin account thứ 1 là:
//Email: NguyenVanA@gmail.com
//Full name: Nguyễn Văn A
//Phòng ban: Sale
//Thông tin account thứ 2 là:
//Email: NguyenVanB@gmail.com
//Full name: Nguyễn Văn B
//Phòng ban: Markettin

    public void question10For(ArrayList<Account> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("Thông tin account " + accounts.get(i).getAccountId() + " là : ");
            System.out.println("Email : " + accounts.get(i).getEmail());
            System.out.println("Full Name : " + accounts.get(i).getFullName());
            System.out.println("Phòng ban : " + accounts.get(i).getDepartment().getDepartmentName());
        }
    }

//Question 11:
//In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
//Thông tin department thứ 1 là:
//Id: 1
//Name: Sale
//Thông tin department thứ 2 là:
//Id: 2
//Name: Marketing
//

    public void question11For(ArrayList<Department> departments) {
        for (int i = 0; i < departments.size(); i++) {
            System.out.println("Thông tin department thứ " + (departments.get(i).getDepartmentId()) + 1 + " là : ");
            System.out.println("Id : " + departments.get(i).getDepartmentId() + 1);
            System.out.println("Name : " + departments.get(i).getDepartmentName());
        }
    }

//Question 12:
//Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10

    public void question12For(ArrayList<Account> accounts) {
        if (accounts.size() <= 1) {
            return;
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Thông tin account " + accounts.get(i).getAccountId() + " là : ");
            System.out.println("Email : " + accounts.get(i).getEmail());
            System.out.println("Full Name : " + accounts.get(i).getFullName());
            System.out.println("Phòng ban : " + accounts.get(i).getDepartment().getDepartmentName());
        }
    }

//Question 13:
//In ra thông tin tất cả các account ngoại trừ account thứ 2


    public void question13For(ArrayList<Department> departments) {
        for (int i = 0; i < departments.size(); i++) {
            if (i == 2) {
                continue;
            } else {
                System.out.println("Thông tin department thứ " + (departments.get(i).getDepartmentId()) + 1 + " là : ");
                System.out.println("Id : " + departments.get(i).getDepartmentId() + 1);
                System.out.println("Name : " + departments.get(i).getDepartmentName());
            }
        }
    }

//Question 14:
//In ra thông tin tất cả các account có id < 4

    public void question14For(ArrayList<Account> accounts) {

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() < 4) {
                System.out.println("Thông tin account " + accounts.get(i).getAccountId() + " là : ");
                System.out.println("Email : " + accounts.get(i).getEmail());
                System.out.println("Full Name : " + accounts.get(i).getFullName());
                System.out.println("Phòng ban : " + accounts.get(i).getDepartment().getDepartmentName());
            }

        }
    }
//
//Question 15:
//In ra các số chẵn nhỏ hơn hoặc bằng 20

    public void question15For() {
        System.out.println("Số chắn trong khoảng 1 -> 20 : ");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.print(" " + i);
            }
        }
    }

//WHILE
//Question 16:
//Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với
//lệnh break, continue

    public void question16While() {
        int i = 1;
        System.out.println("Số chẵn trong khoảng 1 -> 20");
        while (i <= 20) {
            if (i % 2 == 0) {
                System.out.print(" " + i);
            }
        }
    }

//DO-WHILE
//Question 17:
//Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với
//lệnh break, continue

    public void question17DoWhile() {
        int i = 1;
        System.out.println("Số chẵn trong khoảng 1 -> 20");
        do {
            if (i % 2 == 0) {
                System.out.print(" " + i);
            }
        } while (i <= 20);
    }

}