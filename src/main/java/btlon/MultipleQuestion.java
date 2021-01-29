package btlon;

import java.util.List;
import java.util.Scanner;

public class MultipleQuestion extends CauHoi {
    public MultipleQuestion(){

    }

    public MultipleQuestion(String nd, DoKho mucDo, DanhMuc loai,PhuongAn dapAn, List<PhuongAn> pa) {
        super(nd,mucDo,loai,dapAn,pa);
    }

    public MultipleQuestion(String nd,PhuongAn dapAn,List<PhuongAn> pa){
        super(nd,dapAn,pa);
    }

    @Override
    public double luyenTapCH(CauHoi c, Scanner sc) {
        return 0;
    }


}




