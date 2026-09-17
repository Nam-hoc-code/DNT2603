package Bai6.Backend.reponsitory;

import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Position;

import java.util.List;

public interface IQLTVResponsitory {

    // Hiển thị toàn bộ account -> trả List<Account> (việc in ấn do Frontend đảm nhiệm)
    List<Account> hienThi();

    // Tìm kiếm account theo tên (từ khóa gần đúng LIKE) -> trả List<Account>
    List<Account> timKiem(String keyword);

    // Thêm 1 account mới -> true nếu thêm thành công, false nếu vị trí/phòng ban không tồn tại
    boolean them(Account account);

    // Sửa tên account theo id -> true nếu tìm thấy và cập nhật thành công
    boolean sua(int idAccount, String newName);

    // Xóa account theo id -> true nếu xóa được, false nếu không tồn tại hoặc bị ràng buộc khóa ngoại
    boolean xoa(int idAccount);

    // Lấy danh sách vị trí (position) từ DB để Frontend hiển thị menu chọn
    List<Position> getPositions();

    // Lấy danh sách phòng ban (department) từ DB để Frontend hiển thị menu chọn
    List<Department> getDepartments();
}