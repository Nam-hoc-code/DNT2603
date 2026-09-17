package Bai6.Backend.service;

import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;

import java.util.List;

public interface IQLTVService {

    List<Account> hienThi();

    List<Account> timKiem(String keyword);

    boolean them(Account account);

    boolean sua(int idAccount, String newName);

    boolean xoa(int idAccount);

    List<Position> getPositions();

    List<Department> getDepartments();
}