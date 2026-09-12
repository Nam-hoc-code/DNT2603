package Bai5.Backend;

import Bai5.Utils.CheckInput;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class QLPosition implements IQLPosition {

    private final Scanner scanner;

    public QLPosition() {
        this.scanner = new Scanner(System.in);
    }

    public QLPosition(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void hienThiTatCa() {
        String sql = "SELECT id_position, position_name FROM position";

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
                System.out.println("Không có dữ liệu position!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoaViTri() {
        int id = CheckInput.nhapSoNguyen(scanner, "Nhập id_position cần xóa: ");

        String sql = "{call xoaViTri(?)}";
        try (Connection connection = ConnectDB.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            callableStatement.execute();

            int rows = callableStatement.getUpdateCount();
            if (rows > 0) {
                System.out.println("Đã xóa " + rows + " vị trí!");
            } else {
                System.out.println("Không tìm thấy vị trí có id = " + id + "!");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Không thể xóa! Vị trí này đang gán cho account.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printDongTaiLieu(ResultSet resultSet) throws SQLException {
        String idPosition = rsToString(resultSet.getObject("id_position"));
        String positionName = rsToString(resultSet.getObject("position_name"));

        System.out.printf("| %-6s | %-20s |%n", idPosition, positionName);
    }

    private void printHeader() {
        System.out.println("+--------+----------------------+");
        System.out.println("|   ID   |    Position Name     |");
        System.out.println("+--------+----------------------+");
    }

    private void printFooter() {
        System.out.println("+--------+----------------------+");
    }

    private String rsToString(Object value) {
        return value != null ? String.valueOf(value) : "Trống";
    }
}