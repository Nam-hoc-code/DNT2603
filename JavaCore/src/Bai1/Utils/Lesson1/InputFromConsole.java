package Bai1.Utils.Lesson1;

import Bai1.Enums.DepartmentName;
import Bai1.Enums.GroupName;
import Bai1.Enums.PositionName;
import Bai1.Models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class InputFromConsole {
    //    Question 1:
//    Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình.
//
    public void input1(Scanner sc) {
        System.out.println("Nhập số nguyên a : ");
        int a = sc.nextInt();
        System.out.println("Nhập số nguyên b : ");
        int b = sc.nextInt();
        System.out.println("Nhập số nguyên c : ");

        int c = sc.nextInt();

        System.out.println(a + b + c);
    }

    //
//            Question 2:
//    Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình.
//
    public void input2(Scanner sc) {
        System.out.println("Nhập số thực a : ");
        float a = sc.nextInt();
        System.out.println("Nhập số thực b : ");
        float b = sc.nextInt();
        System.out.println(a + b);
    }

    //
//            Question 3:
//    Viết lệnh cho phép người dùng nhập họ và tên.
//
    public void input3(Scanner sc) {
        System.out.println("Nhập tên của bạn : ");
        String name = sc.nextLine();
        System.out.println(name);
    }

    //
//    Question 4:
//    Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ.
//
    public void input4(Scanner sc) {

        System.out.print("Nhập ngày sinh của bạn vd : 6/3/2003: ");
        String birthday = sc.nextLine();

        System.out.println("Ngày sinh của bạn là: " + birthday);
    }

    //
//            Question 5:
//    Viết lệnh cho phép người dùng tạo account (viết thành method)
//    Đối với property Position, Người dùng nhập vào 1 2 3 4 5 và vào
//    chương trình sẽ chuyển thành Position.Dev, Position.Test, Position.ScrumMaster, Position.PM.
//
    public Account input5(Scanner sc) {
        System.out.println("------ Chức năng tạo account ------ ");
        System.out.println();
        System.out.println("Nhập UserName : ");
        String name = sc.nextLine();
        System.out.println("Nhập FullName : ");
        String fullName = sc.nextLine();
        System.out.println("Nhập Email : ");
        String email = sc.nextLine();

        System.out.println("Chọn phòng ban của bạn : ");
        System.out.println("1. Development ");
        System.out.println("2. Test ");
        System.out.println("3. ScrumMaster ");
        System.out.println("4. PM ");


        Position position = new Position();

        switch (sc.nextInt()) {
            case 1:
                position.setPositionName(PositionName.Development);
                break;
            case 2:
                position.setPositionName(PositionName.Test);
                break;
            case 3:
                position.setPositionName(PositionName.ScrumMaster);
                break;
            case 4:
                position.setPositionName(PositionName.PM);
        }

        Account account = new Account(name, fullName, email, new Department(), position, new Date());

        return account;

    }

    //
//            Question 6:
//    Viết lệnh cho phép người dùng tạo department (viết thành method)
//
    public void input6(Scanner sc) { // Em đang dùng enum để tọa các tên phòng cố định nên không thể tạo một đối tượng mới với tên mới nhập từ console
        System.out.println("------ Chức năng tạo department ------ ");
        System.out.println();
        Department department = new Department();
        System.out.println("Chọn phòng bạn của bạn muốn tạo : ");
        System.out.println("1. Sale ");
        System.out.println("2. Marketing ");
        System.out.println("3. Development ");

        switch (sc.nextInt()) {
            case 1:
                department.setDepartmentName(DepartmentName.Sale);
                break;
            case 2:
                department.setDepartmentName(DepartmentName.Marketing);
                break;
            case 3:
                department.setDepartmentName(DepartmentName.Development);
        }

        System.out.println("Phòng đã tạo thành công!");
    }

    //
//    Question 7:
//    Nhập số chẵn từ console
//
    public void input7(Scanner sc) {
        System.out.println("Nhập giá trị số nguyên là số chẵn : ");
        int i = sc.nextInt();
        while (i % 2 != 0) {
            System.out.println("Vui lòng nhập vào giá trị là một số chẵn : ");
            i = sc.nextInt();
        }
        System.out.println("Số chẵn là : " + i);
    }


    //
//    Question 8:
//    Viết chương trình thực hiện theo flow sau:
//    Bước 1:
//    Chương trình in ra text "mời bạn nhập vào chức năng muốn sử dụng"
//    Bước 2:
//    Nếu người dùng nhập vào 1 thì sẽ thực hiện tạo account
//    Nếu người dùng nhập vào 2 thì sẽ thực hiện chức năng tạo
//            department
//    Nếu người dùng nhập vào số khác thì in ra text "Mời bạn nhập
//    lại" và quay trở lại bước 1
    public void input8(Scanner sc) {
        System.out.println("Mời bạn nhập vào chức bạn muốn sử dụng : ");
        System.out.println("1. Tạo account ");
        System.out.println("2. Tạo department ");
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    input5(sc);
                    break;
                case 2:
                    input6(sc);
                    break;
                case 3:
                    return;

            }
        }
    }


    //    Question 9:
//    Viết method cho phép người dùng thêm group vào account theo flow sau:
//    Bước 1: In ra tên các usernames của user cho người dùng xem
//    Bước 2: Yêu cầu người dùng nhập vào username của account
//    Bước 3: In ra tên các group cho người dùng xem
//    Bước 4: Yêu cầu người dùng nhập vào tên của group
//    Bước 5: Dựa vào username và tên của group người dùng vừa chọn, hãy thêm account vào group đó .
//
    public void input9(Scanner sc, FlowControl flowControl, ArrayList<Account> accounts, ArrayList<Group> groups) {
        System.out.println("Thông tin người dùng : ");
        flowControl.question8ForEach(accounts);

        System.out.println("Chọn user : ");
        System.out.println("Nhập vào username : ");
        String username = sc.nextLine();

        System.out.println("Thông tin về các group : ");
        System.out.println("+-----+---------------+");
        System.out.println("|   ID|      GroupName|");
        for (Group group : groups) {
            System.out.printf("%-5d,%-20s", group.getGroupId(), group.getGroupName());
        }
        Group group = new Group();
        System.out.println("Nhập vào số tương ứng với nhóm bạn muốn tham gia : ");
        System.out.println("1. DevTeam");
        System.out.println("2. TesterTeam");
        System.out.println("3. SaleTeam");
        System.out.println("4. MobileTeam");
        switch (sc.nextInt()) {
            case 1:
                group.setGroupName(GroupName.DevTeam);
                break;
            case 2:
                group.setGroupName(GroupName.TesterTeam);
                break;
            case 3:
                group.setGroupName(GroupName.SaleTeam);
                break;
            case 4:
                group.setGroupName(GroupName.MobileTeam);
        }

        System.out.println("Thực hiện thêm account vào nhóm : ");
        Account accountNew = null;
        for (Account account : accounts) {
            if (account.getUserName().contains(username)) {
                accountNew = account;
            }
        }

        GroupAccount groupAccountNew = new GroupAccount(group, accountNew, new Date());


        System.out.println(
                " Thông tin thêm mới vào group : "
        );
        System.out.println(" UserName : " + groupAccountNew.getGroup().getGroupName() + " thêm vào group : " + groupAccountNew.getAccount().getUserName());
    }

    //
//    Question 10: Tiếp tục Question 8 và Question 9
//    Bổ sung thêm vào bước 2 của Question 8 như sau:
//    Nếu người dùng nhập vào 3 thì sẽ thực hiện chức năng thêm group vào
//            account
//    Bổ sung thêm Bước 3 của Question 8 như sau:
//    Sau khi người dùng thực hiện xong chức năng ở bước 2 thì in ra dòng text để hỏi   người dùng "Bạn có muốn thực hiện chức năng khác không?". Nếu người dùng chọn "Có" thì quay lại bước 1, nếu người dùng chọn "Không" thì kết thúc chương trình (sử dụng lệnh return để kết thúc chương trình).
//
    public void input10(Scanner sc, FlowControl flowControl, ArrayList<Account> accounts, ArrayList<Group> groups
    ) {
        System.out.println("Mời bạn nhập vào chức bạn muốn sử dụng : ");
        System.out.println("1. Tạo account ");
        System.out.println("2. Tạo department ");
        System.out.println("3. Thêm account vào group ");
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    input5(sc);
                    break;
                case 2:
                    input6(sc);
                    break;
                case 3:
                    input9(sc, flowControl,accounts,groups);
                    break;
                default:
                    System.out.println( "Lựa chon ko hợp lệ");
            }
            System.out.println("Bạn có muốn tiếp tục sử dụng : ");
            switch (sc.nextInt()) {
                case 1:
                    break;
                case 2: return;
                default:
                    System.out.println( "Lựa chon ko hợp lệ");
            }
        }
    }
//
//    Question 11: Tiếp tục Question 10
//    Bổ sung thêm vào bước 2 của Question 8 như sau:
//    Nếu người dùng nhập vào 4 thì sẽ thực hiện chức năng thêm account vào 1 nhóm ngẫu nhiên, chức năng sẽ được cài đặt như sau:
//    Bước 1: In ra tên các usernames của user cho người dùng xem
//    Bước 2: Yêu cầu người dùng nhập vào username của account
//    Bước 3: Sau đó chương trình sẽ chọn ngẫu nhiên 1 group
//    Bước 4: Thêm account vào group chương trình vừa chọn ngẫu nhiên
public void input9AddRandom(Scanner sc, FlowControl flowControl, ArrayList<Account> accounts, ArrayList<Group> groups) {
    System.out.println("Thông tin người dùng : ");
    flowControl.question8ForEach(accounts);

    System.out.println("Chọn user : ");
    System.out.println("Nhập vào username : ");
    String username = sc.nextLine();

    ArrayList<GroupName> groupNames = new ArrayList<>();
    groupNames.add(GroupName.DevTeam);
    groupNames.add(GroupName.TesterTeam);
    groupNames.add(GroupName.SaleTeam);
    groupNames.add(GroupName.MobileTeam);

    Random random = new Random();

    int  indexGroupName = random.nextInt(0,groupNames.size()-1);


    Group group = new Group();
    group.setGroupName(groupNames.get(indexGroupName));
    System.out.println("Thực hiện thêm account vào nhóm : ");
    Account accountNew = null;
    for (Account account : accounts) {
        if (account.getUserName().contains(username)) {
            accountNew = account;
        }
    }

    GroupAccount groupAccountNew = new GroupAccount(group, accountNew, new Date());


    System.out.println(
            " Thông tin thêm mới vào group : "
    );
    System.out.println(" UserName : " + groupAccountNew.getGroup().getGroupName() + " thêm vào group : " + groupAccountNew.getAccount().getUserName());
}

    public void input11(Scanner sc, FlowControl flowControl, ArrayList<Account> accounts, ArrayList<Group> groups
    ) {
        System.out.println("Mời bạn nhập vào chức bạn muốn sử dụng : ");
        System.out.println("1. Tạo account ");
        System.out.println("2. Tạo department ");
        System.out.println("3. Thêm account vào group ");
        System.out.println("4. Thêm account vào group random ");
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    input5(sc);
                    break;
                case 2:
                    input6(sc);
                    break;
                case 3:
                    input9(sc, flowControl,accounts,groups);
                    break;
                    case 4: input9AddRandom(sc, flowControl, accounts, groups);
                default:
                    System.out.println( "Lựa chon ko hợp lệ");
            }
            System.out.println("Bạn có muốn tiếp tục sử dụng : ");
            switch (sc.nextInt()) {
                case 1:
                    break;
                case 2: return;
                default:
                    System.out.println( "Lựa chon ko hợp lệ");
            }
        }
    }




}
