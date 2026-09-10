package Bai5.Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QLPosition implements IQLPosition {

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