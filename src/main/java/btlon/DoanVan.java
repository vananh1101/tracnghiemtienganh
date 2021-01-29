package btlon;

import java.util.Scanner;

public class DoanVan {
    private String noiDung;

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public DoanVan(){

    }
    public DoanVan(String noiDung){
        this.noiDung=noiDung;
    }

    @Override
    public String toString() {
        return  noiDung ;
    }
}
