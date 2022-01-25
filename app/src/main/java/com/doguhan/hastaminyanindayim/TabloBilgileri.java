package com.doguhan.hastaminyanindayim;

import android.provider.BaseColumns;

public class TabloBilgileri {

    public static final class NoteEntry implements BaseColumns {
        public static final String TABLO_ADI = "randevular";
        public static final String RANDEVU_ID = "randevu_id";
        public static final String TARIH = "randevu_tarihi";
        public static final String SAAT = "randevu_saati";
        public static final String HASTA_ADI = "hasta_adi";
        public static final String HASTA_SOYADI = "hasta_soyadi";
        public static final String HASTA_NO = "hasta_no";
        public static final String RANDEVU_ACIKLAMA = "randevu_aciklama";
    }
}
