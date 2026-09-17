package Bai6.Backend.reponsitory.Implement;

import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Entity.Account;
import Bai6.Entity.Department;
import Bai6.Entity.Enums.PositionName;
import Bai6.Entity.Position;
import Bai6.Utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository: tầng DUY NHẤT làm việc với Database.
 * Không nhập liệu, không in kết quả -> chỉ truy vấn và TRẢ DỮ LIỆU lên tầng trên.
 */
public class QLTVResponsitory implements IQLTVResponsitory {

/**
     * Map 1 dòng kết quả (có join position + department) sang entity Account.
     * Giúp hienThi() và timKiem() dùng chung, tránh viết lặp.
     */
    private Account mapAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setIdAccount(resultSet.getInt("id_account"));
        account.setName(resultSet.getString("name"));
        account.setLocation(resultSet.getString("location"));
        account.setAccountName(resultSet.getString("account_name"));
        account.setPassword(resultSet.getString("password"));
        // Khóa ngoại dạng đối tượng: đóng gói thành Position / Department nếu cột không NULL
        Integer idPosition = (Integer) resultSet.getObject("id_position");
        if (idPosition != null) {
            // position_name là ENUM('DEV','MANAGER','LEAD','TESTER') -> map sang enum PositionName
            account.setPosition(new Position(idPosition,
                    PositionName.valueOf(resultSet.getString("position_name"))));
        }
        Integer idDepartment = (Integer) resultSet.getObject("id_department");
        if (idDepartment != null) {
            account.setDepartment(new Department(idDepartment, null,
                    resultSet.getString("department_name"), null));
        }
        return account;
    }

    @Override
    public List<Account> hienThi() {
        // JOIN để lấy kèm tên vị trí (position_name) và phòng ban (department_name)
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department";
        List<Account> accounts = new ArrayList<>(); // danh sách kết quả trả về
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection(); // mở kết nối DB
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                // Duyệt từng dòng kết quả và revert vào entity Account
                while (resultSet.next()) {
                    accounts.add(mapAccount(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection); // luôn đóng kết nối
        }
        return accounts;
    }

    @Override
    public List<Account> timKiem(String keyword) {
        // Dùng PreparedStatement + placeholder ? để tránh SQL injection
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department " +
                "where acc.name like ?";
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + keyword + "%"); // pattern tìm tên chứa từ khóa
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        accounts.add(mapAccount(resultSet));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return accounts;
    }

    @Override
    public boolean them(Account account) {
        // Gọi stored procedure themTaiKhoan với đúng 6 tham số lấy từ entity Account
        String sql = "{call themTaiKhoan(?,?,?,?,?,?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {
                callableStatement.setString(1, account.getName());
                callableStatement.setString(2, account.getLocation());
                callableStatement.setString(3, account.getAccountName());
                callableStatement.setString(4, account.getPassword());
                callableStatement.setInt(5, account.getPosition().getIdPosition());       // lấy id từ đối tượng Position
                callableStatement.setInt(6, account.getDepartment().getIdDepartment());   // lấy id từ đối tượng Department
                callableStatement.execute();
                return true; // thêm thành công
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // FK: id_position / id_department không tồn tại trong DB -> thêm thất bại
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean sua(int idAccount, String newName) {
        // Gọi stored procedure capNhatTenTaiKhoan(id, tên mới)
        String sql = "{call capNhatTenTaiKhoan(?,?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {
                callableStatement.setInt(1, idAccount);
                callableStatement.setString(2, newName);
                callableStatement.execute();
                // getUpdateCount() = số dòng bị ảnh hưởng; > 0 nghĩa là tìm thấy id cần sửa
                return callableStatement.getUpdateCount() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean xoa(int idAccount) {
        // Gọi stored procedure xoaTaiKhoan(id)
        String sql = "{call xoaTaiKhoan(?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {
                callableStatement.setInt(1, idAccount);
                callableStatement.execute();
                return callableStatement.getUpdateCount() > 0;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Account đang là quản lý phòng ban -> vi phạm khóa ngoại, không xóa được
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public List<Position> getPositions() {
        // Lấy toàn bộ vị trí từ DB để Frontend hiển thị menu chọn (không hardcode)
        String sql = "select id_position, position_name from `position` order by id_position";
        List<Position> positions = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    // position_name là ENUM('DEV','MANAGER','LEAD','TESTER') -> map sang enum PositionName
                    positions.add(new Position(
                            resultSet.getInt("id_position"),
                            PositionName.valueOf(resultSet.getString("position_name"))
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return positions;
    }

    @Override
    public List<Department> getDepartments() {
        // Lấy toàn bộ phòng ban từ DB để Frontend hiển thị menu chọn (không hardcode)
        String sql = "select id_department, department_name from department order by id_department";
        List<Department> departments = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    // id_manager, number_people không cần hiển thị trong menu chọn -> để null
                    departments.add(new Department(
                            resultSet.getInt("id_department"),
                            null,
                            resultSet.getString("department_name"),
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return departments;
    }
}