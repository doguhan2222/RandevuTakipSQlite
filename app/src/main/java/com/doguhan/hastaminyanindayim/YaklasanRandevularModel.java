package com.doguhan.hastaminyanindayim;

public class YaklasanRandevularModel {
    private String randevuTarih;
    private String randevuSaat;
    private String hastaAdi;
    private String hastaNo;

    public String getRandevuTarih() {
        return randevuTarih;
    }

    public void setRandevuTarih(String randevuTarih) {
        this.randevuTarih = randevuTarih;
    }

    public String getRandevuSaat() {
        return randevuSaat;
    }

    public void setRandevuSaat(String randevuSaat) {
        this.randevuSaat = randevuSaat;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public void setHastaAdi(String hastaAdi) {
        this.hastaAdi = hastaAdi;
    }

    public String getHastaNo() {
        return hastaNo;
    }

    public void setHastaNo(String hastaNo) {
        this.hastaNo = hastaNo;
    }

    public YaklasanRandevularModel(String randevuTarih, String randevuSaat, String hastaAdi, String hastaNo) {
        this.randevuTarih = randevuTarih;
        this.randevuSaat = randevuSaat;
        this.hastaAdi = hastaAdi;
        this.hastaNo = hastaNo;
    }


}
