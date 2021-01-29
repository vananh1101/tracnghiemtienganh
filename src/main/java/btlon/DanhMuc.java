package btlon;

public class DanhMuc {
    private String loai;
    public DanhMuc(){};
    public DanhMuc(String loai){
        this.loai=loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public String toString() {
        return loai;
    }
}
