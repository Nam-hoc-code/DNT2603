package Bai5.Backend;

import Bai5.Utils.CheckInput;
import Bai5.Utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {

    private final Scanner scanner;

    public QLAccount() {
        this.scanner = new Scanner(System.in);
    }

    public QLAccount(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void hienThiTatCa() {
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department";

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                printHeader();
                boolean found = false;
                while (resultSet.next()) {
                    printDongTaiLieu(resultSet);
                    found = true;
                }
                printFooter();

                if (!found) {
                    System.out.println("Không có dữ liệu account!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public void xoaTaiKhoan() {
        int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_account cần xóa: ");

        String sql = "{call xoaTaiKhoan(?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {

                callableStatement.setInt(1, id);
                callableStatement.execute();

                int rows = callableStatement.getUpdateCount();
                if (rows > 0) {
                    System.out.println("Đã xóa " + rows + " account!");
                } else {
                    System.out.println("Không tìm thấy account có id = " + id + "!");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Không thể xóa! Account này đang là quản lý của một phòng ban.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public void suaTaiKhoan() {
        int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_account cần sửa: ");
        String tenMoi = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên mới: ");

        String sql = "{call capNhatTenTaiKhoan(?,?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {

                callableStatement.setInt(1, id);
                callableStatement.setString(2, tenMoi);
                callableStatement.execute();

                int rows = callableStatement.getUpdateCount();
                if (rows > 0) {
                    System.out.println("Đã cập nhật tên account id = " + id + "!");
                } else {
                    System.out.println("Không tìm thấy account có id = " + id + "!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public void themTaiKhoan() {
        String name = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên: ");
        String location = CheckInput.nhapChuoiKhongRong(scanner, "Nhập nơi ở: ");
        String accountName = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên tài khoản: ");
        String password = CheckInput.nhapChuoiKhongRong(scanner, "Nhập mật khẩu: ");

        int idPosition = chonViTri();
        int idDepartment = chonPhong();

        String sql = "{call themTaiKhoan(?,?,?,?,?,?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {

                callableStatement.setString(1, name);
                callableStatement.setString(2, location);
                callableStatement.setString(3, accountName);
                callableStatement.setString(4, password);
                callableStatement.setInt(5, idPosition);
                callableStatement.setInt(6, idDepartment);
                callableStatement.execute();

                System.out.println("Đã thêm account mới!");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Không thể thêm! Vị trí hoặc phòng ban không tồn tại.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    private int chonViTri() {
        System.out.println("\n--- Chọn vị trí (position) ---");
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_position, position_name FROM position";

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_position");
                    ids.add(id);
                    System.out.println("  " + id + ". " + resultSet.getString("position_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        while (true) {
            int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_position: ");
            if (ids.contains(id)) {
                return id;
            }
            System.out.println("Không có vị trí id = " + id + ", mời chọn lại!");
        }
    }

    private int chonPhong() {
        System.out.println("\n--- Chọn phòng ban (department) ---");
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_department, department_name FROM department";

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_department");
                    ids.add(id);
                    System.out.println("  " + id + ". " + resultSet.getString("department_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        while (true) {
            int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_department: ");
            if (ids.contains(id)) {
                return id;
            }
            System.out.println("Không có phòng ban id = " + id + ", mời chọn lại!");
        }
    }

    private void printDongTaiLieu(ResultSet resultSet) throws SQLException {
        String idAccount = rsToString(resultSet.getObject("id_account"));
        String name = rsToString(resultSet.getObject("name"));
        String location = rsToString(resultSet.getObject("location"));
        String accountName = rsToString(resultSet.getObject("account_name"));
        String password = rsToString(resultSet.getObject("password"));
        String positionName = rsToString(resultSet.getObject("position_name"));
        String departmentName = rsToString(resultSet.getObject("department_name"));

        System.out.printf("| %-4s | %-18s | %-18s | %-16s | %-9s | %-15s | %-20s |%n",
                idAccount, name, location, accountName, password, positionName, departmentName);
    }

    private void printHeader() {
        System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");
        System.out.println("|  ID  |        Tên         |      Location      |   Account Name   | Password  |    Position     |    Department      |");
        System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");
    }

    private void printFooter() {
        System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");
    }

    private String rsToString(Object value) {
        return value != null ? String.valueOf(value) : "Trống";
    }
}