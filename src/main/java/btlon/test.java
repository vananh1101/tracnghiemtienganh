package btlon;

import java.sql.*;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        int chon;
        do {
             chon=0;
            do{
                menuChinh();
                System.out.println("BẠN CHỌN: ");
                chon=Integer.parseInt(sc.nextLine());
                if(chon<0||chon>4)
                    System.out.println("NHẬP SAI MỜI NHẬP LẠI");
            }while(chon<0||chon>4);
            switch (chon) {
            case 1:
                try {
                    QLHocVien dsHocVien=new QLHocVien();
                    int chonMenuQLHV;
                    do{
                        do{
                            menuQlHocVien();
                            System.out.println("BẠN CHỌN: ");
                            chonMenuQLHV=Integer.parseInt(sc.nextLine());
                            if(chonMenuQLHV<=0||chonMenuQLHV>6)
                                System.out.println("NHẬP SAI MỜI NHẬP LẠI");
                        }while(chonMenuQLHV<=0||chonMenuQLHV>6);
                        switch (chonMenuQLHV){
                            case 1:
                                System.out.println("=======DANH SÁCH HỌC VIÊN=======");
                                dsHocVien.hienThiDS();
                                break;
                            case 2:
                                System.out.println("NHẬP TỪ KHOÁ TRA CỨU DANH SÁCH HỌC VIÊN: ");
                                dsHocVien.timKiemHocVien(sc.nextLine());
                                break;
                            case 3:
                                HocVien hv=new HocVien();
                                System.out.println("NHẬP THÔNG TIN HỌC VIÊN CẦN THÊM");
                                hv.nhapHocVien(sc);
                                dsHocVien.themDSHocVien(hv);
                                break;
                            case 4:
                                System.out.println("NHẬP USER CẦN XOÁ TRONG DANH SÁCH: ");
                                dsHocVien.xoaHocVien(sc.nextLine());
                                break;
                            case 5:
                                System.out.println("NHAP USER: ");
                                String user=sc.nextLine();
                                System.out.println("NHẬP TÊN CẦN CẬP NHẬT: ");
                                String ten=sc.nextLine();
                                dsHocVien.suaDSHocVien(ten,user);
                                break;
                            case 6:
                                System.out.println("SEE YOU LATER! ");
                                break;
                        }
                    }while (chonMenuQLHV!=6);
                } catch (SQLException | ClassNotFoundException throwables) {
                    System.err.println("LỖI SQL");
                }
                break;
            case 2:
                try {
                    QLCauHoi dsCauHoi=new QLCauHoi();
                    int chonMenuQLCH;
                    do{
                        do{
                            menuQlCauHoi();
                            System.out.println("BẠN CHỌN: ");
                            chonMenuQLCH=Integer.parseInt(sc.nextLine());
                            if(chonMenuQLCH<=0||chonMenuQLCH>3)
                                System.out.println("NHẬP SAI MỜI NHẬP LẠI");
                        }while(chonMenuQLCH<=0||chonMenuQLCH>3);
                        switch (chonMenuQLCH){
                            case 1:
                                System.out.println("=======DANH SÁCH CÂU HỎI=======");
                                dsCauHoi.hienThiDS();
                                break;
                            case 2:
                                System.out.println("NHẬP NỘI DUNG/DANH MỤC/ĐỘ KHÓ TRA CỨU DANH SÁCH CÂU HỎI: ");
                                System.out.println(dsCauHoi.timKiemCauHoi(sc.nextLine()));
                                break;

                            case 3:
                                System.out.println("SEE YOU LATER! ");
                                break;
                        }
                    }while (chonMenuQLCH!=3);
                } catch (SQLException | ClassNotFoundException throwables) {
                    System.err.println("LỖI SQL");
                }
                break;
            case 3:
                try {
                    QLHocVien dsHocVien=new QLHocVien();
                    QLCauHoi dsCauHoi =new QLCauHoi();
                    ThongKeLuyenTap thongKeLuyenTap=new ThongKeLuyenTap();
                    int chonMenuDangNhap;
                    do{
                        do{
                            menuDangNhap();
                            System.out.println("BẠN CHỌN: ");
                            chonMenuDangNhap=Integer.parseInt(sc.nextLine());
                            if(chonMenuDangNhap<=0||chonMenuDangNhap>3)
                                System.out.println("NHẬP SAI MỜI NHẬP LẠI");
                        }while(chonMenuDangNhap<=0||chonMenuDangNhap>3);
                        switch (chonMenuDangNhap){
                            case 1:
                                System.out.println("USER: ");
                                String user=sc.nextLine();
                                System.out.println("PASSWORD: ");
                                String pass=sc.nextLine();
                                if(dsHocVien.isTaiKhoan(user,pass)){
                                    HocVien hv=dsHocVien.isCoTrongDanhSach(user,pass);
                                    int chonMenuHocVien;
                                    do {
                                        do {
                                            menuHocVien();
                                            System.out.println("BẠN CHỌN: ");
                                            chonMenuHocVien = Integer.parseInt(sc.nextLine());
                                            if (chonMenuHocVien <= 0 || chonMenuHocVien > 3)
                                                System.out.println("NHẬP SAI MỜI NHẬP LẠI");
                                        } while (chonMenuHocVien <= 0 || chonMenuHocVien > 4);
                                        switch (chonMenuHocVien){
                                            case 1:
                                                hv.setDiemLuyenTap(dsCauHoi.luyenTap(sc));
                                                thongKeLuyenTap.themDiemLuyenTap(hv);
                                                break;
                                            case 2:
                                                thongKeLuyenTap.thongTinDiemLuyenTap(hv);
                                                break;
                                            case 3:
                                                thongKeLuyenTap.thongTinLuyenTap(hv);
                                                break;
                                        }
                                    }while (chonMenuHocVien!=4);
                                }
                                else
                                    System.out.println("TÀI KHOẢN KHÔNG HỢP LỆ!!!");
                                break;
                            case 2:
                                HocVien hv=new HocVien();
                                System.out.println("========TẠO TÀI KHOẢN========");
                                hv.nhapHocVien(sc);
                                dsHocVien.themDSHocVien(hv);
                                hv.setDiemLuyenTap(dsCauHoi.luyenTap(sc));
                                thongKeLuyenTap.themDiemLuyenTap(hv);
                                break;
                            case 3:
                                System.out.println("SEE YOU LATER! ");
                            break;
                        }
                    }while (chonMenuDangNhap!=3);
                } catch (SQLException | ClassNotFoundException throwables) {
                    System.err.println("LỖI SQL");
                }
                break;
            case 4:
                System.out.println("SEE YOU LATER! ");
                break;
            }
        }while(chon!=4);

    }

    public static void menuDangNhap(){
        System.out.println("1.ĐĂNG NHẬP\n2.ĐĂNG KÝ\n3.THOÁT");
    }

    public static void menuHocVien(){
        System.out.println("1.LUYỆN TẬP\n2.THÔNG TIN ĐIỂM MỖI LẦN LUYỆN TẬP\n" +
                "3.THÔNG TIN ĐIỂM TRUNG BÌNH VÀ SỐ LẦN LUYÊN TẬP THEO THÁNG\n4.THOÁT ");
    }

    public static void menuChinh(){
        System.out.println("1.QUẢN LÍ HỌC VIÊN\n2.QUẢN LÍ CÂU HỎI\n3.LUYỆN TẬP\n4.THOÁT ");
    }

    public static void menuQlHocVien(){
        System.out.println("1. HIỂN THỊ DANH SÁCH HỌC VIÊN\n2. TRA CỨU THEO TÊN/QUÊ QUÁN" +
                "\n3. THÊM THÔNG TIN HỌC VIÊN\n4. XOÁ THÔNG TIN HỌC VIÊN\n5. CẬP NHẬT THÔNG TIN HỌC VIÊN\n6. THOÁT ");
    }

    public static void menuQlCauHoi(){
        System.out.println("1. HIỂN THỊ DANH SÁCH CÂU HỎI\n2. TRA CỨU CÂU HỎI THEO NỘI DUNG/DANH MỤC/MỨC ĐỘ\n3. THOÁT ");
    }

    public static void menuLuyenTap(){
        System.out.println("1. MULTIPLE QUESTION\n2. INCOMPLETE\n3. CONVERSATION\n4. THOÁT");
    }
}
