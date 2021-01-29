package btlon;

public class PhuongAn {
    private String nd;
    private String ghiChu;

    public PhuongAn(){
    }
    public PhuongAn(String nd,String ghiChu){
        this.nd=nd;
        this.ghiChu=ghiChu;
    }
    public PhuongAn(String nd ){
        this.nd=nd;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
