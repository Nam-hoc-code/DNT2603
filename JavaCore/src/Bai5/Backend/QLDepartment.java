package Bai5.Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QLDepartment implements IQLDepartment {

    @Override
    public void hienThiTatCa() {
        String sql = "SELECT id_department, id_maneger, department_name, number_people FROM department";

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

    private void printDongTaiLieu(ResultSet resultSet) throws SQLException {
        String idDepartment = rsToString(resultSet.getObject("id_department"));
        String idManager = rsToString(resultSet.getObject("id_maneger"));
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