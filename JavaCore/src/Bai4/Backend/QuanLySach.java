package Bai4.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class QuanLySach {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "123456Aa@";
        return DriverManager.getConnection(url, username, password);
    }

    public void ketNoiCsdl() { // test connecttion
        try (Connection connection = getConnection()) {
            System.out.println("Kết nối DB thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    a) Thêm mới tài liêu: Sách, tạp chí, báo.
    public boolean themMoiTaiLieu(Scanner sc, int luaChon) {
        String maTaiLieu = null;
        String tenNhaXuatBan = null;
        String soBanPhatHanh = null;
        String loaiTaiLieu = null;
        String tenTacGia = null;
        Integer soTrang = null;
        String soPhatHanh = null;
        Integer thangPhatHanh = null;
        LocalDate ngayPhatHanh = null;

        switch (luaChon) {
            case 1: // Sách
                loaiTaiLieu = "SACH";
                System.out.println("Nhập mã sách : ");
                maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập tên tác giả : ");
                tenTacGia = sc.nextLine();
                System.out.println("Nhập số trang của sách : ");
                soTrang = Integer.parseInt(sc.nextLine());
                break;

            case 2: // Tạp c    hí
                loaiTaiLieu = "TAP_CHI";
                System.out.println("Nhập mã tạp chí : ");
                maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập số phát hành : ");
                soPhatHanh = sc.nextLine();
                System.out.println("Nhập tháng phát hành (1 - 12) : ");
                thangPhatHanh = Integer.parseInt(sc.nextLine());
                break;

            case 3: // Báo
                loaiTaiLieu = "BAO";
                System.out.println("Nhập mã báo : ");
                maTaiLieu = sc.nextLine();
                System.out.println("Nhập tên nhà xuất bản : ");
                tenNhaXuatBan = sc.nextLine();
                System.out.println("Nhập số bản phát hành : ");
                soBanPhatHanh = sc.nextLine();
                System.out.println("Nhập ngày phát hành (dd/MM/yyyy) : ");
                ngayPhatHanh = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;

            default:
                return false;
        }

        if (maTaiLieuDaTonTai(maTaiLieu)) {
            System.out.println("Mã tài liệu đã tồn tại: " + maTaiLieu);
            return false;
        }

        String sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, ten_tac_gia, so_trang, so_phat_hanh, thang_phat_hanh, ngay_phat_hanh) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, maTaiLieu);
            preparedStatement.setString(2, tenNhaXuatBan);
            preparedStatement.setString(3, soBanPhatHanh);
            preparedStatement.setString(4, loaiTaiLieu);
            preparedStatement.setString(5, tenTacGia);
            preparedStatement.setObject(6, soTrang);
            preparedStatement.setString(7, soPhatHanh);
            preparedStatement.setObject(8, thangPhatHanh);
            preparedStatement.setObject(9, ngayPhatHanh);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        System.out.println("Thêm tài liệu thành công! Id vừa thêm = " + keys.getInt(1));
                        hienThiMotTaiLieu(keys.getInt(1));
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean maTaiLieuDaTonTai(String maTaiLieu) {
        String sql = "SELECT COUNT(*) FROM tai_lieu WHERE ma_tai_lieu = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, maTaiLieu);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    //    b) Xoá tài liệu theo mã tài liệu.
    public boolean xoaTaiLieu(Scanner sc) {
        System.out.println("Nhập vào mã tài liệu bạn muốn xóa : ");
        String maTaiLieu = sc.nextLine();

        String sql = "DELETE FROM tai_lieu WHERE ma_tai_lieu = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, maTaiLieu);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //    c) Hiển thị thông tin về tài liệu.
    public void hienThiThongTinTaiLieu() {
        String sql = "SELECT * FROM tai_lieu";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            printHeader();
            while (resultSet.next()) {
                printDongTaiLieu(resultSet);
            }
            printFooter();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hiển thị một tài liệu theo id (lấy id mới nhất sau khi INSERT).
    public void hienThiMotTaiLieu(int id) {
        String sql = "SELECT * FROM tai_lieu WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    printHeader();
                    printDongTaiLieu(resultSet);
                    printFooter();
                } else {
                    System.out.println("Không tìm thấy tài liệu có id = " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    d) Tìm kiếm tài liệu theo loại: Sách, Tạp chí, Báo.
    public void timKiemTheoLoaiTaiLieu(Scanner sc) {
        System.out.println("Nhập vào thể loại muốn tìm (sach / tap_chi / bao) : ");
        String theLoai = sc.nextLine().trim().toLowerCase();

        String loaiTaiLieu;
        switch (theLoai) {
            case "sach":
                loaiTaiLieu = "SACH";
                break;
            case "tap_chi":
            case "tapchi":
                loaiTaiLieu = "TAP_CHI";
                break;
            case "bao":
                loaiTaiLieu = "BAO";
                break;
            default:
                System.out.println("Thể loại không hợp lệ!");
                return;
        }

        String sql = "SELECT * FROM tai_lieu WHERE loai_tai_lieu = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, loaiTaiLieu);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printHeader();
                boolean found = false;
                while (resultSet.next()) {
                    printDongTaiLieu(resultSet);
                    found = true;
                }
                printFooter();

                if (!found) {
                    System.out.println("Không tìm thấy tài liệu loại " + loaiTaiLieu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean suaTaiLieu(Scanner sc) {
        String sql = "UPDATE tai_lieu SET ten_nha_xuat_ban = ? WHERE ma_tai_lieu = ?";

        System.out.println("Nhập vào mã tài liệu muốn đổi tên nhà xuất bản: ");
        String maTaiLieu = sc.nextLine();

        System.out.println("Nhập vào tên nhà xuất bản muốn đổi: ");
        String nhaXuatBan = sc.nextLine();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, nhaXuatBan);
            preparedStatement.setObject(2, maTaiLieu);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private void printHeader() {
         System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");
        System.out.println("|   Mã tài liệu | Tên nhà xuất bản   |    Số bản phát hành|       Số Phát Hành | Thời gian phát hành |        Tên Tác Giả | Số Trang |");
        System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");
    }

    private void printFooter() {
        System.out.println("+---------------+--------------------+--------------------+--------------------+---------------------+--------------------+----------+");
    }

    private void printDongTaiLieu(ResultSet resultSet) throws SQLException {
        String maTaiLieu = rsToString(resultSet.getObject("ma_tai_lieu"));
        String tenNhaXuatBan = rsToString(resultSet.getObject("ten_nxb"));
        String soBanPhatHanh = rsToString(resultSet.getObject("so_ban_phat_hanh"));
        String loai = resultSet.getString("loai_tai_lieu");

        String soPhatHanh = "Trống";
        String thoiGianPhatHanh = "Trống";
        String tenTacGia = "Trống";
        String soTrang = "Trống";

        if ("SACH".equals(loai)) {
            tenTacGia = rsToString(resultSet.getObject("ten_tac_gia"));
            soTrang = rsToString(resultSet.getObject("so_trang"));
        } else if ("TAP_CHI".equals(loai)) {
            soPhatHanh = rsToString(resultSet.getObject("so_phat_hanh"));
            Object thang = resultSet.getObject("thang_phat_hanh");
            thoiGianPhatHanh = thang != null ? "Tháng " + thang : "Trống";
        } else if ("BAO".equals(loai)) {
            Timestamp ngay = resultSet.getTimestamp("ngay_phat_hanh");
            if (ngay != null) {
                thoiGianPhatHanh = ngay.toLocalDateTime().toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        }

        System.out.printf(
                "| %-13s | %-18s | %-18s | %-18s | %-19s | %-18s | %-8s |%n",
                maTaiLieu,
                tenNhaXuatBan,
                soBanPhatHanh,
                soPhatHanh,
                thoiGianPhatHanh,
                tenTacGia,
                soTrang
        );
    }

    private String rsToString(Object value) {
        return value != null ? String.valueOf(value) : "Trống";
    }

    public static void main(String[] args) {
        QuanLySach q = new QuanLySach();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== QUẢN LÝ THƯ VIỆN ==========");
            System.out.println("1. Thêm mới tài liệu.");
            System.out.println("2. Xóa tài liệu theo mã.");
            System.out.println("3. Hiển thị tất cả tài liệu.");
            System.out.println("4. Tìm kiếm theo loại.");
            System.out.println("5. Thoát.");
            System.out.print("Mời bạn chọn: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Chọn loại (1. Sách / 2. Tạp chí / 3. Báo): ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    System.out.println(q.themMoiTaiLieu(sc, type)
                            ? "Thêm thành công!"
                            : "Thêm thất bại!");
                    break;

                case 2:
                    System.out.println(q.xoaTaiLieu(sc)
                            ? "Xóa thành công!"
                            : "Không tìm thấy mã tài liệu!");
                    break;

                case 3:
                    q.hienThiThongTinTaiLieu();
                    break;

                case 4:
                    q.timKiemTheoLoaiTaiLieu(sc);
                    break;

                case 5:
                    q.suaTaiLieu(sc);
                    break;

                case 6:
                    System.out.println("Tạm biệt!");
                    return;

                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}