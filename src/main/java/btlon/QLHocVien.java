package btlon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Van Xuyen
 */
public class QLHocVien {
    private List<HocVien> ds = napDSHocVien();

    public QLHocVien() throws SQLException, ClassNotFoundException{

    }

    public void hienThiDS(){
        this.ds.forEach((HocVien h) -> System.out.println(h)) ;
    }


    public boolean isTaiKhoan(String user,String pass){
        if (ds.stream().anyMatch((a) -> (a.getUser().equals(user)&&a.getPass().equals(pass)))) {
            return true;
        }
        return false;

    }
    public HocVien isCoTrongDanhSach(String user,String pass){
        HocVien hv=new HocVien();
        for(int i=0;i<ds.size();i++){
            if(ds.get(i).getUser().equals(user)&&ds.get(i).getPass().equals(pass))
                hv=ds.get(i);
        }
        return hv;

    }
    public void xoaHocVien(String user) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3",
                "root", "Van Anh 1101");

        String sql = "delete from danhsachhocvien where User = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, user);
        stm.executeUpdate();
        ds=napDSHocVien();
        stm.close();
        conn.close();
    }


    public List<HocVien> napDSHocVien() throws ClassNotFoundException, SQLException{
        List<HocVien> dsNap=new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3",
                "root", "Van Anh 1101");
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("select * from danhsachhocvien");
        while(rs.next()){

            String tenHocVien = rs.getString("TenHocVien");
            String queQuan = rs.getString("QueQuan");
            String ngaySinh = rs.getString("NgaySinh");
            String gioiTinh = rs.getString("GioiTinh");
            String user = rs.getString("User");
            String password = rs.getString("password");
            int stt=rs.getInt("Stt");
            HocVien hv = new HocVien(tenHocVien, queQuan, ngaySinh, gioiTinh, user, password,stt);
            dsNap.add(hv);
        }

        stm.close();
        conn.close();
        return dsNap;
    }

    public void themDSHocVien(HocVien hv) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3",
                "root", "Van Anh 1101");
        String sql = "insert into danhsachhocvien(Stt, TenHocVien, QueQuan,"
                + "NgaySinh, GioiTinh, NgayGiaNhap, User, password) values (?,?,?,?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, hv.getStt());
        stm.setString(2, hv.getTenHocVien());
        stm.setString(3, hv.getQueQuan());
        stm.setString(4,  hv.getNgaySinh());
        stm.setString(5,  hv.getGioiTinh());
        stm.setDate(6, (Date) hv.getNgayGiaNhap());
        stm.setString(7, hv.getUser());
        stm.setString(8,  hv.getPass());

        ds.add(hv);
        stm.executeUpdate();
        conn.close();
    }


    public void suaDSHocVien(String tenHocVien, String user) throws ClassNotFoundException, SQLException{

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3","root", "Van Anh 1101");
        String sql = "update danhsachhocvien set TenHocVien=? where User = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, tenHocVien);
        stm.setString(2, user);
        stm.executeUpdate();
        ds=napDSHocVien();
        stm.close();
        conn.close();
    }

    public List<HocVien> timKiemHocVien(String keyWord)  throws ClassNotFoundException,SQLException{
        try{
            List<HocVien> dsTimKiem=new ArrayList<>();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quiz3",
                    "root", "Van Anh 1101");

            String sql = "select * from danhsachhocvien where TenHocVien like concat('%',?,'%') or QueQuan like concat('%',?,'%')";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, keyWord);
            stm.setString(2, keyWord);


            ResultSet rs = stm.executeQuery();
            while(rs.next()){

                String tenHocVien = rs.getString("TenHocVien");
                String queQuan = rs.getString("QueQuan");
                String ngaySinh = rs.getString("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String user = rs.getString("User");
                String password = rs.getString("password");
                int stt=rs.getInt("Stt");
                HocVien hv = new HocVien(tenHocVien, queQuan, ngaySinh, gioiTinh, user, password,stt);
                dsTimKiem.add(hv);
                System.out.println(hv);
            }

            conn.close();
            return dsTimKiem;
        }catch(ClassNotFoundException c){
            throw new ClassNotFoundException("Khong Co Ten Trong Danh Sach");
        }

    }

    public List<HocVien> getDs() {
        return ds;
    }

    public void setDs(List<HocVien> ds) {
        this.ds = ds;
    }



}
