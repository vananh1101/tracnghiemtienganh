package btlon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeLuyenTap {
    private Date ngayLamBai;

    public ThongKeLuyenTap() {
        this.ngayLamBai = new java.sql.Date(System.currentTimeMillis());
    }

    public Date getNgayLamBai() {
        return ngayLamBai;
    }

    public void setNgayLamBai(Date ngayLamBai) {
        this.ngayLamBai = ngayLamBai;
    }

    public void themDiemLuyenTap(HocVien hv) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3", "root", "Van Anh 1101");
        String sql = "insert into thongke(User,Password,TenHocVien,Diem,NgayLamBai) values (?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, hv.getUser());
        stm.setString(2,hv.getPass());
        stm.setString(3,hv.getTenHocVien());
        stm.setDouble(4, hv.getDiemLuyenTap());
        stm.setDate(5, this.ngayLamBai);
        stm.executeUpdate();
        stm.close();
        con.close();

    }



    public void thongTinLuyenTap(HocVien hv) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3", "root", "Van Anh 1101");
        String sql = "{call thongkeluyentap(?,?)}";
        CallableStatement stm = con.prepareCall(sql);
        stm.setString(1, hv.getUser());
        stm.setString(2, hv.getPass());
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            System.out.println(String.format("TÊN HỌC VIÊN: %s\nUSER:%s\nTHÁNG:%d\nĐIỂM TRUNG BÌNH THEO THÁNG:%.2f\n" +
                            "SỐ LẦN LUYỆN TẬP TRONG THÁNG:%d\n",
                    rs.getString("TenHocVien"),rs.getString("User"), rs.getInt("month(NgayLamBai)"),
                    rs.getDouble("avg(Diem)"), rs.getInt("COUNT(*)")));
        }
        stm.close();
        rs.close();
        con.close();

    }
    public void thongTinDiemLuyenTap(HocVien hv) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3", "root", "Van Anh 1101");
        String sql = "{call diemmoilan(?,?)}";
        CallableStatement stm = con.prepareCall(sql);
        stm.setString(1, hv.getUser());
        stm.setString(2, hv.getPass());
        ResultSet rs = stm.executeQuery();
        int i=0;
        while (rs.next()) {

            System.out.println(String.format("LẦN %d\nUSER: %s\nTÊN HỌC VIÊN:%s\nĐIỂM:%.2f\n",++i,
                    rs.getString("User"),
                    rs.getString("TenHocVien"),rs.getDouble("Diem")));
        }
        stm.close();
        rs.close();
        con.close();

    }
}
