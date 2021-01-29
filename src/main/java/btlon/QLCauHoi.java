package btlon;




import java.sql.*;
import java.util.*;

public class QLCauHoi {
    private List<CauHoi> ds = napDSCauHoi();

    public QLCauHoi() throws SQLException, ClassNotFoundException {
    }

    public List<CauHoi> getDs() {
        return ds;
    }

    public List<CauHoi> napDSCauHoi() throws ClassNotFoundException, SQLException {
        List<CauHoi> dsCH = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3", "root", "Van Anh 1101");
        String sql = "select * from multiple m \n" +
                "inner join multiple_dsphuongan p on m.idMultiple=p.MaCauHoi;";
        String sql1 = "select * from incomplete_conversation i\n" +
                "inner join dscauhoi d on i.id=d.MaDoanVan\n" +
                "inner join dsphuongan p on d.iddscauhoi=p.MaCauHoi;";
        Statement stm = con.createStatement();
        Statement stm1 = con.createStatement();
        ResultSet rs1 = stm1.executeQuery(sql1);
        ResultSet rs = stm.executeQuery(sql);
        int id = 0,kt = 0, kiemTraDoanVan = 0;
        List<CauHoi> dsCauHoiDoanVan1 = new ArrayList<>();
        List<PhuongAn> pa = new ArrayList<>();
        while (rs.next()) {
            if (rs.getInt("idMultiple") == rs.getInt("MaCauHoi")) {
                pa.add(new PhuongAn(rs.getString("NoiDungPhuongAn")));
                if (pa.size() == CauHoi.soLuong) {
                    pa = new ArrayList<>();
                }
            }
            if (kt != rs.getInt("idMultiple")) {
                CauHoi cauHoi = new MultipleQuestion(rs.getString("CauHoi"), DoKho.valueOf(rs.getString("DoKho")),
                        new DanhMuc(rs.getString("DanhMuc")), new PhuongAn(rs.getString("DapAn")),
                        pa);
                dsCH.add(cauHoi);
                kt = rs.getInt("idMultiple");
            }
        }
        while (rs1.next()) {
            if (rs1.getString("Incomplete").equals("null")) {
                if (kiemTraDoanVan != rs1.getInt("MaDoanVan")) {
                    dsCauHoiDoanVan1 = new ArrayList<>();
                    kiemTraDoanVan = rs1.getInt("MaDoanVan");
                }
                if (rs1.getInt("id") == rs1.getInt("MaDoanVan") &&
                        rs1.getInt("iddscauhoi") == rs1.getInt("MaCauHoi")) {
                    pa.add(new PhuongAn(rs1.getString("NoiDungPhuongAn")));
                    if (pa.size() == CauHoi.soLuong) {
                        pa = new ArrayList<>();
                    }
                    if (id != rs1.getInt("iddscauhoi")) {
                        CauHoi cauHoi = new MultipleQuestion(rs1.getString("NoiDungCauHoi"),
                                new PhuongAn(rs1.getString("DapAn")), pa);
                        dsCauHoiDoanVan1.add(cauHoi);
                        id = rs1.getInt("iddscauhoi");
                    }
                    if (kt != rs1.getInt("id")) {
                        CauHoi incomplete = new Conversation(new DoanVan(rs1.getString("Conversation")),
                                DoKho.valueOf(rs1.getString("DoKho")), dsCauHoiDoanVan1);
                        dsCH.add(incomplete);
                        kt = rs1.getInt("id");
                    }
                }
            }
            else {
                if (kiemTraDoanVan != rs1.getInt("MaDoanVan")) {
                    dsCauHoiDoanVan1 = new ArrayList<>();
                    kiemTraDoanVan = rs1.getInt("MaDoanVan");
                }
                if (rs1.getInt("id") == rs1.getInt("MaDoanVan") &&
                        rs1.getInt("iddscauhoi") == rs1.getInt("MaCauHoi")) {
                    pa.add(new PhuongAn(rs1.getString("NoiDungPhuongAn")));
                    if (pa.size() == CauHoi.soLuong) {
                        pa = new ArrayList<>();
                    }
                    if (id != rs1.getInt("iddscauhoi")) {
                        CauHoi cauHoi = new MultipleQuestion(rs1.getString("NoiDungCauHoi"),
                                new PhuongAn(rs1.getString("DapAn")), pa);
                        dsCauHoiDoanVan1.add(cauHoi);
                        id = rs1.getInt("iddscauhoi");
                    }
                    if (kt != rs1.getInt("id")) {
                        CauHoi incomplete = new Incomplete(new DoanVan(rs1.getString("Incomplete")),
                                DoKho.valueOf(rs1.getString("DoKho")), dsCauHoiDoanVan1);
                        dsCH.add(incomplete);
                        kt = rs1.getInt("id");
                    }
                }
            }
        }
        stm.close();
        stm1.close();
        con.close();
        return dsCH;
    }

    public double tinhDiem(int soCauDung, int soCau) {
        return soCauDung * (10 / soCau);
    }

    public List<CauHoi> layCauHoiTheoLoai(String classPath, Scanner sc) throws ClassNotFoundException, SQLException {
        Class c = Class.forName(classPath);
        List<CauHoi> kq = new ArrayList<>();
        this.timKiemCauHoi(sc.nextLine()).stream().filter((t) -> (c.isInstance(t) == true)).forEachOrdered((t) -> {
            kq.add(t);
        });
        return kq;
    }


    public List<CauHoi> timKiemCauHoi(String a) throws ClassNotFoundException, SQLException {
        a=a.toUpperCase();
        List<CauHoi> dsTimKiem = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/quiz3", "root", "Van Anh 1101");
        String sql = "select * from multiple m " +
                "inner join multiple_dsphuongan p on m.idMultiple=p.MaCauHoi where DoKho=? or DanhMuc like ? " +
                "or  CauHoi like concat('%',?,'%');";
        String sql1 = "select * from incomplete_conversation i\n" +
                "inner join dscauhoi d on i.id=d.MaDoanVan\n" +
                "inner join dsphuongan p on d.iddscauhoi=p.MaCauHoi where DoKho=? or Incomplete like concat('%',?,'%')" +
                "or Conversation like concat('%',?,'%');";
        PreparedStatement statement=con.prepareStatement(sql);
        PreparedStatement statement1=con.prepareStatement(sql1);
        statement.setString(1,a);
        statement.setString(2,a);
        statement.setString(3,a);
        statement1.setString(1,a);
        statement1.setString(2,a);
        statement1.setString(3,a);
        ResultSet rs=statement.executeQuery();
        ResultSet rs1=statement1.executeQuery();
        List<PhuongAn> pa=new ArrayList<>();
        List<CauHoi> dsCauHoiDoanVan1 = new ArrayList<>();
        int id = 0,kt = 0, kiemTraDoanVan = 0;
        while (rs.next()){
            if (rs.getInt("idMultiple") == rs.getInt("MaCauHoi")) {
                pa.add(new PhuongAn(rs.getString("NoiDungPhuongAn")));
                if (pa.size() == CauHoi.soLuong) {
                    pa = new ArrayList<>();
                }
            }
            if (kt != rs.getInt("idMultiple")) {
                CauHoi cauHoi = new MultipleQuestion(rs.getString("CauHoi"), DoKho.valueOf(rs.getString("DoKho")),
                        new DanhMuc(rs.getString("DanhMuc")), new PhuongAn(rs.getString("DapAn")),
                        pa);
                dsTimKiem.add(cauHoi);
                kt = rs.getInt("idMultiple");
            }
        }
        while (rs1.next()) {
            if (rs1.getString("Incomplete").equals("null")) {
                if (kiemTraDoanVan != rs1.getInt("MaDoanVan")) {
                    dsCauHoiDoanVan1 = new ArrayList<>();
                    kiemTraDoanVan = rs1.getInt("MaDoanVan");
                }
                if (rs1.getInt("id") == rs1.getInt("MaDoanVan") &&
                        rs1.getInt("iddscauhoi") == rs1.getInt("MaCauHoi")) {
                    pa.add(new PhuongAn(rs1.getString("NoiDungPhuongAn")));
                    if (pa.size() == CauHoi.soLuong) {
                        pa = new ArrayList<>();
                    }
                    if (id != rs1.getInt("iddscauhoi")) {
                        CauHoi cauHoi = new MultipleQuestion(rs1.getString("NoiDungCauHoi"),
                                new PhuongAn(rs1.getString("DapAn")), pa);
                        dsCauHoiDoanVan1.add(cauHoi);
                        id = rs1.getInt("iddscauhoi");
                    }
                    if (kt != rs1.getInt("id")) {
                        CauHoi incomplete = new Conversation(new DoanVan(rs1.getString("Conversation")),
                                DoKho.valueOf(rs1.getString("DoKho")), dsCauHoiDoanVan1);
                        dsTimKiem.add(incomplete);
                        kt = rs1.getInt("id");
                    }
                }
            }
            else {
                if (kiemTraDoanVan != rs1.getInt("MaDoanVan")) {
                    dsCauHoiDoanVan1 = new ArrayList<>();
                    kiemTraDoanVan = rs1.getInt("MaDoanVan");
                }
                if (rs1.getInt("id") == rs1.getInt("MaDoanVan") &&
                        rs1.getInt("iddscauhoi") == rs1.getInt("MaCauHoi")) {
                    pa.add(new PhuongAn(rs1.getString("NoiDungPhuongAn")));
                    if (pa.size() == CauHoi.soLuong) {
                        pa = new ArrayList<>();
                    }
                    if (id != rs1.getInt("iddscauhoi")) {
                        CauHoi cauHoi = new MultipleQuestion(rs1.getString("NoiDungCauHoi"),
                                new PhuongAn(rs1.getString("DapAn")), pa);
                        dsCauHoiDoanVan1.add(cauHoi);
                        id = rs1.getInt("iddscauhoi");
                    }
                    if (kt != rs1.getInt("id")) {
                        CauHoi incomplete = new Incomplete(new DoanVan(rs1.getString("Incomplete")),
                                DoKho.valueOf(rs1.getString("DoKho")), dsCauHoiDoanVan1);
                        dsTimKiem.add(incomplete);
                        kt = rs1.getInt("id");
                    }
                }
            }
        }
        statement.close();
        statement1.close();
        con.close();
        return dsTimKiem;
    }

    public double luyenTap(Scanner sc) throws SQLException, ClassNotFoundException,IndexOutOfBoundsException {
        double diem=0;
        int soCauDung = 0;
        List<CauHoi> dsCH;
        int chonMenuLuyenTap;
            do{
                test.menuLuyenTap();
                System.out.println("BẠN CHỌN: ");
                chonMenuLuyenTap=Integer.parseInt(sc.nextLine());
                if(chonMenuLuyenTap<=0||chonMenuLuyenTap>4)
                    System.out.println("NHẬP SAI MỜI NHẬP LẠI");
            }while(chonMenuLuyenTap<=0||chonMenuLuyenTap>4);
            switch (chonMenuLuyenTap) {
                case 1:
                    int soCau;
                    Collections.shuffle(this.ds);
                    System.out.print("\nNHÂP MỨC ĐỘ CÂU HỎI: ");
                    dsCH = this.layCauHoiTheoLoai("btlon.MultipleQuestion", sc);
                    if (dsCH.isEmpty()) {
                        throw new IndexOutOfBoundsException("KHÔNG CÓ CÂU HỎI THUỘC MỨC ĐỘ CẦN LUYỆN TẬP ");
                    }
                    System.out.println("NHÂP SỐ CÂU MUỐN LUYỆN TẬP: ");
                    soCau = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < soCau; i++) {
                        System.out.println(dsCH.get(i));
                        System.out.print("\nĐÁP ÁN CỦA BẠN: ");
                        if (dsCH.get(i).isChinhXac(sc.nextLine())) {
                            System.out.print("\nCHÍNH XÁC!");
                            soCauDung++;
                        } else {
                            System.out.print("\nKHÔNG CHÍNH XÁC!!\n");
                        }
                    }
                    diem = this.tinhDiem(soCauDung, soCau);
                    break;
                case 2:
                    System.out.println("1. EASY\n2. MEDIUM\n3. HARD");
                    System.out.print("NHẬP ĐỘ KHÓ CÂU HỎI BẠN MUỐN LUYỆN TẬP: ");
                    dsCH = this.layCauHoiTheoLoai("btlon.Incomplete", sc);
                    if (dsCH.isEmpty()) {
                        throw new IndexOutOfBoundsException("KHÔNG CÓ CÂU HỎI THUỘC MỨC ĐỘ CẦN LUYỆN TẬP ");
                    }
                    Collections.shuffle(this.ds);
                    for (int i = 0; i < 1; i++) {
                        System.out.println(dsCH.get(i));
                        diem = dsCH.get(i).luyenTapCH(dsCH.get(i), sc);
                    }
                    break;
                case 3:
                    System.out.println("1. EASY\n2. MEDIUM\n3. HARD");
                    System.out.print("\nNHẬP ĐỘ KHÓ CÂU HỎI BẠN MUỐN LUYỆN TẬP: ");
                    dsCH = this.layCauHoiTheoLoai("btlon.Conversation", sc);
                    if (dsCH.isEmpty()) {
                        throw new IndexOutOfBoundsException("KHÔNG CÓ CÂU HỎI THUỘC MỨC ĐỘ CẦN LUYỆN TẬP ");
                    }
                    Collections.shuffle(this.ds);
                    for (int i = 0; i < 1; i++) {
                        System.out.println(dsCH.get(i));
                        diem = dsCH.get(i).luyenTapCH(dsCH.get(i), sc);
                    }
                    break;
            }
        return diem;
    }


    public void hienThiDS() {
        ds.forEach(ch -> {
            System.out.print(ch);
        });

    }


}







