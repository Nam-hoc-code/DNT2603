package Bai6.Backend.service.Implement;

import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Backend.reponsitory.Implement.QLTVResponsitory;
import Bai6.Backend.service.IQLTVService;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;

import java.util.List;

/**
 * Service: tầng trung gian, chỉ truyền dữ liệu giữa Controller và Repository.
 * Không chứa logic hiển thị, không nhập liệu.
 */
public class QLTVService implements IQLTVService {

    IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();

    @Override
    public List<Account> hienThi() {
        return qlTVResponsitory.hienThi();
    }

    @Override
    public List<Account> timKiem(String keyword) {
        return qlTVResponsitory.timKiem(keyword);
    }

    @Override
    public boolean them(Account account) {
        return qlTVResponsitory.them(account);
    }

    @Override
    public boolean sua(int idAccount, String newName) {
        return qlTVResponsitory.sua(idAccount, newName);
    }

    @Override
    public boolean xoa(int idAccount) {
        return qlTVResponsitory.xoa(idAccount);
    }

    @Override
    public List<Position> getPositions() {
        return qlTVResponsitory.getPositions();
    }

    @Override
    public List<Department> getDepartments() {
        return qlTVResponsitory.getDepartments();
    }
}