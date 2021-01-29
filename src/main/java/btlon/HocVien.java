package btlon;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class HocVien {
//    private static int dem = 0;
    protected String user;
    protected String pass;
    private String tenHocVien;
    private String queQuan;
    private String ngaySinh;
    private String gioiTinh;
    private Date ngayGiaNhap;
    private double diemLuyenTap;
    private int stt;


    public HocVien(String tenHocVien, String queQuan, String ngaySinh,
                   String gioiTinh, String user, String password,int stt) {
        this.tenHocVien = tenHocVien;
        this.queQuan = queQuan;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.ngayGiaNhap = new java.sql.Date(System.currentTimeMillis());
        this.user = user;
        this.pass = password;
        this.stt=stt;
    }

    public HocVien(String ten,String que,String ngaySinh,String gioiTinh,String user){
        this.tenHocVien = ten;
        this.queQuan = que;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.user = user;
    }

    public HocVien() {
        this.ngayGiaNhap = new java.sql.Date(System.currentTimeMillis());

    }

    public void setTen(String tenHocVien) throws java.lang.Exception {
        for (int i = 0; i < this.getTenHocVien().length(); i++) {
            if (Character.isDigit(this.tenHocVien.charAt(i))) {
                throw new Exception("Ten Khong Duoc Chua Ky Tu So!!!");
            } else
                this.tenHocVien = tenHocVien;
        }

    }

    public void nhapHocVien(Scanner scanner)  {
        System.out.println("== NHAP THONG TIN HOC VIEN ==");
        System.out.print("Nhap ho ten: ");
        this.tenHocVien = scanner.nextLine();
        System.out.print("Nhap que quan: ");
        this.queQuan = scanner.nextLine();
        System.out.print("Nhap ngay sinh: ");
        this.setNgaySinh(scanner.nextLine());
        System.out.print("Nhap gioi tinh: ");
        this.gioiTinh = scanner.nextLine();
        System.out.print("Nhap User: ");
        this.user = scanner.nextLine();
        System.out.print("Nhap password: ");
        this.pass = scanner.nextLine();
//        System.out.print("Nhap ngay gia nhap: ");
//        String ngn = scanner.nextLine();
//        SimpleDateFormat t = new SimpleDateFormat("dd/MM/yyyy");
//        this.ngayGiaNhap = t.parse(ngn);

    }


    @Override
    public String toString() {
        return String.format("Ten User: %s\nHo ten: %s\n"
                        + "Que quan: %s\nNgay sinh: %s\nGioi tinh: %s\nDiem: %.2f\n",
                this.user, this.tenHocVien, this.queQuan,
                this.ngaySinh, this.gioiTinh,this.diemLuyenTap);
    }

    /**
     * @return the tenHocVien
     */
    public String getTenHocVien() {
        return tenHocVien;
    }

    /**
     * @param tenHocVien the tenHocVien to set
     */
    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }


    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the diemLuyenTap
     */
    public double getDiemLuyenTap() {
        return diemLuyenTap;
    }

    /**
     * @param diemLuyenTap the diemLuyenTap to set
     */
    public void setDiemLuyenTap(double diemLuyenTap) {
        this.diemLuyenTap = diemLuyenTap;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the ngaySinh
     */
    public String getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the ngayGiaNhap
     */
    public Date getNgayGiaNhap() {
        return ngayGiaNhap;
    }

    /**
     * @return the stt
     */
    public int getStt() {
        return stt;
    }



}