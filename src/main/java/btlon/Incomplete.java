package btlon;

import java.util.List;
import java.util.Scanner;

public class Incomplete extends CauHoi {
    private List<CauHoi> dsCauHoi;

    public Incomplete() {
    }

    public Incomplete(DoanVan doanVan, DoKho mucDo,List<CauHoi> ds) {
        super(doanVan, mucDo);
        this.dsCauHoi=ds;
    }

    public List<CauHoi> getDs() {
        return dsCauHoi;
    }

    public void setDs(List<CauHoi> ds) {
        this.dsCauHoi = ds;
    }

    @Override
    public String toString() {
       String b=String.format("%s\n",this.getDoanVan());
       for(CauHoi ch:dsCauHoi)
           b+=ch;
       return b;
    }



    @Override
    public boolean isChinhXac(String tl) {
        tl=tl.toUpperCase();

        for(int i=0;i<dsCauHoi.size();i++){
            if(dsCauHoi.get(i).getPhuongAn().get(i).equals(dsCauHoi.get(i).getDapAn())&&NHAN[i].equals(tl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double luyenTapCH(CauHoi ch, Scanner sc){
        System.out.print("\nĐÁP ÁN CỦA BẠN CHO CÁC CÂU HỎI: ");
        String[] dsTl = new String[dsCauHoi.size()];
        int soCauDung=0;
        for (int i = 0; i < dsTl.length; i++) {
            System.out.print("\n" + dsCauHoi.get(i).getNoiDung() + ": ");
            dsTl[i] = sc.nextLine();
            if(this.dsCauHoi.get(i).isChinhXac(dsTl[i])){
                System.out.println(dsCauHoi.get(i)+dsTl[i]+"\nCHÍNH XÁC");
                soCauDung++;
            }
            else
                System.out.println(dsCauHoi.get(i)+dsTl[i]+"\nKHÔNG CHÍNH XÁC");
        }
        return soCauDung*tinhDiemMoiCau();
    }

    public double tinhDiemMoiCau(){
        return 10/(dsCauHoi.size());
    }

}
