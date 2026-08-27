package Bai1.Utils.Lesson2;

import Bai1.Enums.DepartmentName;
import Bai1.Models.Department;
import Bai1.Models.Question;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectMethod {
//    Question 1:
//    In ra thông tin của phòng ban thứ 1 (sử dụng toString())
//
    public void question1ObjectMethod() {
        Department department = new Department();
        System.out.println(department.toString());
}
//
//    Question 2:
//    In ra thông tin của tất cả phòng ban (sử dụng toString())
//
    public void question2ObjectMethod(ArrayList<Department> departments) {
       for (Department department : departments) {
           System.out.println(department.toString());
       }
    }
//
//    Question 3:
//    In ra địa chỉ của phòng ban thứ 1
//
    public void question3ObjectMethod(ArrayList<Department> departments) {
        Department department = departments.get(1);
        System.out.println(department.getLocation());
    }
//
//    Question 4: Kiểm tra xem phòng ban thứ 1 có tên là "Phòng A" không?
//
    public Boolean question4ObjectMethod(ArrayList<Department> departments) {
        Department department = departments.get(1);
        return department.getDepartmentName().toString().equals("Phòng A");
    }
//
//    Question 5:
//    So sánh 2 phòng ban thứ 1 và phòng ban thứ 2 xem có bằng nhau không (bằng nhau khi tên của 2 phòng ban đó bằng nhau)
//
    public Boolean question5ObjectMethod(ArrayList<Department> departments) {
        Department department1 = departments.get(1);
        Department department2 = departments.get(2);
        return department2.getDepartmentName().toString().equals(department1.getDepartmentName().toString()) ;
    }
//
//    Question 6:
//    Khởi tạo 1 array phòng ban gồm 5 phòng ban, sau đó in ra danh sách phòng ban theo thứ tự tăng dần theo tên (sắp xếp theo vần ABCD)
//    VD:
//    Accounting
//    Boss of director
//            Marketing
//    Sale
//    Waiting room
//
public void question6ObjectMethod() {

    Department[] departments = new Department[5];

    departments[0] = new Department(DepartmentName.Waiting);
    departments[1] = new Department(DepartmentName.Sale);
    departments[2] = new Department(DepartmentName.Marketing);
    departments[3] = new Department(DepartmentName.Accounting);
    departments[4] = new Department(DepartmentName.BossOfDirector);

    Arrays.sort(departments, (department1, department2) ->
            department1.getDepartmentName()
                    .toString()
                    .compareTo(department2.getDepartmentName().toString())
    );

    for (Department department : departments) {
        System.out.println(department.getDepartmentName());
    }

}
//
//    Question 7:
//    Khởi tạo 1 array học sinh gồm 5 Phòng ban, sau đó in ra dan sách phòng ban được sắp xếp theo tên
//    VD:
//    Accounting
//    Boss of director
//    Marketing
//    waiting room
//    Sale
public void question7ObjectMethod(ArrayList<Department> departments) {

    departments.add(new Department(DepartmentName.Waiting));
    departments.add(new Department(DepartmentName.Sale));
    departments.add(new Department(DepartmentName.Marketing));
    departments.add(new Department(DepartmentName.Accounting));
    departments.add(new Department(DepartmentName.BossOfDirector));

    departments.sort((department1, department2) ->
            department1.getDepartmentName().toString()
                    .compareTo(
                            department2.getDepartmentName().toString()
                    )
    );

    for (Department department : departments) {
        System.out.println(department.getDepartmentName());
    }
}
}
