package Bai5.Backend;

import Bai5.Utils.CheckInput;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class QLDepartment implements IQLDepartment {

    private final Scanner scanner;

    public QLDepartment() {
        this.scanner = new Scanner(System.in);
    }

    public QLDepartment(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void hienThiTatCa() {
        String sql = "SELECT id_department, id_manager, department_name, number_people FROM department";

        try (Connection connection = ConnectDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            printHeader();
            boolean found = false;
            while (resultSet.next()) {
                printDongTaiLieu(resultSet);
                found = true;
            }
            printFooter();

            if (!found) {
                System.out.println("Không có dữ liệu department!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoaPhong() {
        int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_department cần xóa: ");

        String sql = "{call xoaPhong(?)}";
        try (Connection connection = ConnectDB.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            callableStatement.execute();

            int rows = callableStatement.getUpdateCount();
            if (rows > 0) {
                System.out.println("Đã xóa " + rows + " phòng ban!");
            } else {
                System.out.println("Không tìm thấy phòng ban có id = " + id + "!");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Không thể xóa! Phòng này còn account đang tham chiếu.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suaPhong() {
        int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_department cần sửa: ");
        String tenMoi = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên phòng mới: ");

        String sql = "{call capNhatTenPhong(?,?)}";
        try (Connection connection = ConnectDB.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            callableStatement.setString(2, tenMoi);
            callableStatement.execute();

            int rows = callableStatement.getUpdateCount();
            if (rows > 0) {
                System.out.println("Đã cập nhật tên phòng id = " + id + "!");
            } else {
                System.out.println("Không tìm thấy phòng ban có id = " + id + "!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void themPhong() {
        int idManager = CheckInput.nhapSoTrongKhoang(scanner,
                "Nhập id_manager (0 = chưa có quản lý): ", 0, Integer.MAX_VALUE);
        String tenPhong = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên phòng: ");
        int soNguoi = CheckInput.nhapSoNguyen(scanner, "Nhập số người: ");

        String sql = "{call themPhong(?,?,?)}";
        try (Connection connection = ConnectDB.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setObject(1, idManager == 0 ? null : idManager);
            callableStatement.setString(2, tenPhong);
            callableStatement.setInt(3, soNguoi);
            callableStatement.execute();

            System.out.println("Đã thêm phòng ban mới!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Không thể thêm! id_manager không tồn tại.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printDongTaiLieu(ResultSet resultSet) throws SQLException {
        String idDepartment = rsToString(resultSet.getObject("id_department"));
        String idManager = rsToString(resultSet.getObject("id_manager"));
        String departmentName = rsToString(resultSet.getObject("department_name"));
        String numberPeople = rsToString(resultSet.getObject("number_people"));

        System.out.printf("| %-6s | %-12s | %-18s | %-18s |%n",
                idDepartment, idManager, departmentName, numberPeople);
    }

    private void printHeader() {
        System.out.println("+--------+--------------+--------------------+--------------------+");
        System.out.println("|   ID   | ID Manager   |  Department Name   | Number Of People   |");
        System.out.println("+--------+--------------+--------------------+--------------------+");
    }

    private void printFooter() {
        System.out.println("+--------+--------------+--------------------+--------------------+");
    }

    private String rsToString(Object value) {
        return value != null ? String.valueOf(value) : "Trống";
    }
}