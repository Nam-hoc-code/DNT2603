package Bai6.Backend.controller;

import Bai6.Backend.service.IQLTVService;
import Bai6.Backend.service.Implement.QLTVService;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;

import java.util.List;

/**
 * Controller: tầng trung gian, chỉ truyền dữ liệu giữa Frontend và Service.
 */
public class QLTVController {

    IQLTVService qlTVService = new QLTVService();

    // Hiển thị toàn bộ account -> trả danh sách để Frontend in
    public List<Account> getAccounts() {
        return qlTVService.hienThi();
    }

    // Thêm account mới -> true/false để Frontend thông báo
    public boolean themAccount(Account account) {
        return qlTVService.them(account);
    }

    // Sửa tên account -> true/false
    public boolean suaAccount(int idAccount, String newName) {
        return qlTVService.sua(idAccount, newName);
    }

    // Xóa account -> true/false
    public boolean xoaAccount(int idAccount) {
        return qlTVService.xoa(idAccount);
    }

    // Tìm kiếm account theo tên -> trả danh sách để Frontend in
    public List<Account> timKiemAccount(String keyword) {
        return qlTVService.timKiem(keyword);
    }

    // Lấy danh sách vị trí (để Frontend hiển thị menu chọn)
    public List<Position> getPositions() {
        return qlTVService.getPositions();
    }

    // Lấy danh sách phòng ban (để Frontend hiển thị menu chọn)
    public List<Department> getDepartments() {
        return qlTVService.getDepartments();
    }
}