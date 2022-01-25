package com.doguhan.hastaminyanindayim;

public class RandevularModel {
    private String randevuTarih, randevuSaat, hastaAdi;

     public RandevularModel(String randevuTarih, String randevuSaat, String hastaAdi) {
         this.randevuTarih = randevuTarih;
         this.randevuSaat = randevuSaat;
         this.hastaAdi = hastaAdi;

     }

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




}
