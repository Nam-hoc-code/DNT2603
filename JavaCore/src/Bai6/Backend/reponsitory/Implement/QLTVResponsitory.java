package Bai6.Backend.reponsitory.Implement;

import Bai5.Utils.CheckInput;
import Bai6.Backend.reponsitory.IQLTVResponsitory;
import Bai6.Utils.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

public class QLTVResponsitory implements IQLTVResponsitory {

    // convert date to String or return "Trống"
    public String rvToString (Object value) {
        return value != null ?  String.valueOf(value) : "Trống" ;
    }

    @Override
    public void hienThi() {
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection(); // -> try ngoài để kiểm tra kết nối
//            System.out.println("Kết nối thành công!"); bỏ comment để test kêt nối
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)
            ) {
                System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");
                System.out.println("|  ID  |        Tên         |      Location      |   Account Name   | Password  |    Position     |    Department      |");
                System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");

                while (resultSet.next()) {
                    String idAccount = rvToString(resultSet.getObject("id_account"));
                    String name = rvToString(resultSet.getObject("name"));
                    String location = rvToString(resultSet.getObject("location"));
                    String accountName = rvToString(resultSet.getObject("account_name"));
                    String password = rvToString(resultSet.getObject("password"));
                    String positionName = rvToString(resultSet.getObject("position_name"));
                    String departmentName = rvToString(resultSet.getObject("department_name"));

                    System.out.printf("| %-4s | %-18s | %-18s | %-16s | %-9s | %-15s | %-20s |%n",
                            idAccount, name, location, accountName, password, positionName, departmentName);

                }
                System.out.println("+------+--------------------+--------------------+------------------+-----------+-----------------+--------------------+");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }


    }


    @Override
    public void them() {
        Scanner scanner = new Scanner(System.in);
        String name = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên: ");
        String location = CheckInput.nhapChuoiKhongRong(scanner, "Nhập nơi ở: ");
        String accountName = CheckInput.nhapChuoiKhongRong(scanner, "Nhập tên tài khoản: ");
        String password = CheckInput.nhapChuoiKhongRong(scanner, "Nhập mật khẩu: ");
        int idPosition = CheckInput.nhapSoNguyen(scanner, "Nhập id_position: ");
        int idDepartment = CheckInput.nhapSoNguyen(scanner, "Nhập id_department: ");
        String sql = " {call themTaiKhoan(?,?,?,?,?,?) }";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try ( CallableStatement callableStatement = connection.prepareCall(sql) ) {
                callableStatement.setString(1, name);
                callableStatement.setString(2, location);
                callableStatement.setString(3, accountName);
                callableStatement.setString(4, password);
                callableStatement.setInt(5, idPosition);
                callableStatement.setInt(6, idDepartment);

                callableStatement.execute();
                System.out.println("Đã thêm account mới!");
            }


            }
            catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Ko thể thêm ! Vị trí hoặc phòng ban không tồn tại");

            }
            catch (SQLException e) {
            e.printStackTrace();
            }
            finally {
            JDBCUtils.closeConnection(connection);
        }
    }


    public void sua() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào id Account bạn muốn sửa tên : ");
        String idAccount = scanner.nextLine();
        System.out.println("Nhập tên bạn muốn sửa : ");
        String nameAccount = scanner.nextLine();
        String sql = "{call capNhatTenTaiKhoan(?,?)}";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall(sql) ) {
                callableStatement.setString(1, idAccount);
                callableStatement.setString(2, nameAccount);
                callableStatement.execute();
                int rows = callableStatement.getUpdateCount();
                if ( rows > 0) {
                    System.out.println("Đã cập nhật tên tài khoản có id " + idAccount + "!");
                }
                else {
                    System.out.println("Việc cập nhật ko thành công !");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void xoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào id account muốn xóa : ");

        int idAccount = scanner.nextInt();
        Connection connection = null;

        try { // kiểm tra kết nối với db
            connection =  JDBCUtils.getConnection();
            try (
                    CallableStatement callableStatement = connection.prepareCall("{call xoaTaiKhoan(?)}")
            ) {
                    callableStatement.setInt(1, idAccount);
                    callableStatement.execute();

                    int rows = callableStatement.getUpdateCount(); // kiểm tra nếu
                if (rows > 0) {
                    System.out.println("Đã xóa " +rows + " account " );
                }
                else {
                    System.out.println("Không tìm thấy account có id trên");
                }
            }
            catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Không thể xóa ! Account này đang là quản lý của một phòng ban. ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    public void timKiem() {
        Scanner scanner = new Scanner(System.in);
        String sql = "select acc.*, pos.position_name, dep.department_name " +
                "from account acc " +
                "left join position pos on acc.id_position = pos.id_position " +
                "left join department dep on acc.id_department = dep.id_department " +
                "where acc.name like ?";
        System.out.println("Nhập tên của account bạn muốn tìm : ");
        String nameAccount = scanner.nextLine();
        Connection connection = null;
        try {

            connection = JDBCUtils.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String keyWord = "%" + nameAccount + "%";
                preparedStatement.setString(1, keyWord);

                ResultSet resultSet =  preparedStatement.executeQuery();
                boolean found = false;
                while (resultSet.next()) {
                    found = true;
                    String idAccount  = rvToString(resultSet.getObject("id_account"));
                    String name       = rvToString(resultSet.getObject("name"));
                    String location   = rvToString(resultSet.getObject("location"));
                    String accountName = rvToString(resultSet.getObject("account_name"));
                    String password   = rvToString(resultSet.getObject("password"));
                    String positionName   = rvToString(resultSet.getObject("position_name"));
                    String departmentName = rvToString(resultSet.getObject("department_name"));

                    System.out.printf("| %-4s | %-18s | %-18s | %-16s | %-9s | %-15s | %-20s |%n",
                            idAccount, name, location, accountName, password, positionName, departmentName);

                }
                if ( !found ) {
                    System.out.println("Không tìm thấy account. ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection);

        }
    }

}