package Bai5.Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QLAccount implements IQLAccount {

    @Override
    public void hienThiTatCa() {
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department";
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
                System.out.println("Không có dữ liệu account!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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