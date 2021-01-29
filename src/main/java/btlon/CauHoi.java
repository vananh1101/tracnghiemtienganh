package btlon;




import java.util.List;
import java.util.Scanner;

public abstract class CauHoi {
    protected final String[] NHAN={"A","B","C","D"};
    public static final int soLuong=4;
    private static int dem;
    protected int maCauHoi=++dem;
    private String noiDung;
    private DoanVan doanVan;
    private PhuongAn dapAn;
    private List<PhuongAn> phuongAn;
    private DoKho mucDoKho;
    private DanhMuc loaiDanhMuc;
    private String ghiChu;

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaCauHoi() {
        return maCauHoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public DoanVan getDoanVan() {
        return doanVan;
    }

    public void setDoanVan(DoanVan doanVan) {
        this.doanVan = doanVan;
    }

    public PhuongAn getDapAn() {
        return dapAn;
    }

    public void setDapAn(PhuongAn dapAn) {
        this.dapAn = dapAn;
    }

    public List<PhuongAn> getPhuongAn() {
        return phuongAn;
    }

    public void setPhuongAn(List<PhuongAn> phuongAn) {
//        if (phuongAn.size() == soLuong) {
            this.phuongAn = phuongAn;
//            return true;
//        }
//        else/throw new Exception("Số lượng phương án phải là 4");
    }

    public DoKho getMucDoKho() {
        return mucDoKho;
    }

    public void setMucDoKho(DoKho mucDoKho)  {
//        if(mucDoKho.equals("EASY")||mucDoKho.equals("MEDIUM")||mucDoKho.equals("HARD")) {
            this.mucDoKho = mucDoKho;
//            return true;
//        }
//        else {
//            throw new IllegalArgumentException("DO KHO CUA CAU HOI GOM: EASY/ MEDIUM/ HARD\n");
//        }
    }

    public DanhMuc getLoaiDanhMuc() {
        return loaiDanhMuc;
    }

    public void setLoaiDanhMuc(DanhMuc loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
    }

    public String[] getNHAN() {
        return NHAN;
    }

    public CauHoi(){

    }

    public CauHoi(String nd,DoKho mucDo,DanhMuc loai,PhuongAn dapAn,List<PhuongAn> pa)  {
            this.noiDung = nd;
//            if((mucDo.equals(DoKho.EASY)||mucDo.equals(DoKho.HARD)||mucDo.equals(DoKho.MEDIUM))
//                    &&this.phuongAn.size()==soLuong) {
                this.mucDoKho = mucDo;
                this.loaiDanhMuc = loai;
                this.dapAn = dapAn;
                this.phuongAn = pa;
//            }
//            else throw new Exception("MUC DO PHAI NHAP EASY/MEDIUM/HARD\nSỐ LUONG PHƯƠNG ÁN PHẢI LÀ 4");
    }

    public CauHoi(DoanVan  dv,DoKho mucDo){
        this.doanVan=dv;
        this.mucDoKho=mucDo;
    }

    public CauHoi (String nd,PhuongAn dapAn,List<PhuongAn> pa ){
        this.noiDung=nd;
        this.phuongAn=pa;
        this.dapAn=dapAn;
    }

    @Override
    public String toString() {
        String b = String.format("%s\n", this.getNoiDung());
        for (int i = 0; i < this.getPhuongAn().size(); i++) {
            b += String.format("%s) %s\n", NHAN[i], this.getPhuongAn().get(i).getNd());
        }
        return b;
    }

    public boolean isChinhXac(String tl) {
        tl=tl.toUpperCase();
        for (int i = 0; i < this.getPhuongAn().size(); i++) {
            if(this.getPhuongAn().get(i).getNd().equals(this.getDapAn().getNd())&&NHAN[i].equals(tl))
                return true;
        }
        return false;
    }

    public abstract double luyenTapCH(CauHoi c,Scanner sc);
}
