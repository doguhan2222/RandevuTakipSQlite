package com.doguhan.hastaminyanindayim;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

// AUTOINCREMENT
public class DatabaseHelper extends SQLiteOpenHelper {
    String ad;
    public static final String VERITABANI_ISMI = "randevular.db";
    private static final int VERITABANI_VERSIYON = 2;

    private static final String TABLO_YARAT =
            "CREATE TABLE " + TabloBilgileri.NoteEntry.TABLO_ADI + " (" +
                    TabloBilgileri.NoteEntry.RANDEVU_ID + " INTEGER PRIMARY KEY, " +
                    TabloBilgileri.NoteEntry.TARIH + " TEXT, " +
                    TabloBilgileri.NoteEntry.SAAT + " TEXT, " +
                    TabloBilgileri.NoteEntry.HASTA_ADI + " TEXT, " +
                    TabloBilgileri.NoteEntry.HASTA_SOYADI + " TEXT, " +
                    TabloBilgileri.NoteEntry.HASTA_NO + " TEXT, " +
                    TabloBilgileri.NoteEntry.RANDEVU_ACIKLAMA + " TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, VERITABANI_ISMI, null, VERITABANI_VERSIYON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLO_YARAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TabloBilgileri.NoteEntry.TABLO_ADI);

        onCreate(db);
    }


    public void randevuEkle(String tarih,String saat,String hastaAdi,String hastaSoyadi, String hastaNo, String aciklama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TabloBilgileri.NoteEntry.TARIH,tarih);
        cv.put(TabloBilgileri.NoteEntry.SAAT,saat);
        cv.put(TabloBilgileri.NoteEntry.HASTA_ADI,hastaAdi);
        cv.put(TabloBilgileri.NoteEntry.HASTA_SOYADI,hastaSoyadi);
        cv.put(TabloBilgileri.NoteEntry.HASTA_NO,hastaNo);
        cv.put(TabloBilgileri.NoteEntry.RANDEVU_ACIKLAMA,aciklama);
        long result = db.insert(TabloBilgileri.NoteEntry.TABLO_ADI, null, cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Not başarıyla kaydedildi");
        else
            Log.i("DatabaseHelper", "Not kaydedilemedi");

        db.close();
    }



    public ArrayList<RandevularModel> getAllData() {
        ArrayList<RandevularModel> randevuList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TabloBilgileri.NoteEntry.TABLO_ADI,null);

        while(res.moveToNext()) {
            String tarih = res.getString(1);   //0 is the number of id column in your database table
            String saat = res.getString(2);
             ad = res.getString(3);

            RandevularModel newDog = new RandevularModel(tarih,saat,ad);
            randevuList.add(newDog);
        }
        return randevuList;
    }
    public ArrayList<YaklasanRandevularModel> yaklasanRandevulariCek(){
        ArrayList<YaklasanRandevularModel> yaklasanListDB = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res2 = db.rawQuery("select * from "+TabloBilgileri.NoteEntry.TABLO_ADI,null);
        Cursor res2 = db.rawQuery("select * from "+TabloBilgileri.NoteEntry.TABLO_ADI +" ORDER BY "+TabloBilgileri.NoteEntry.TARIH + " ASC " +" Limit 10", null);
        while(res2.moveToNext()) {
            String tarih = res2.getString(1);   //0 is the number of id column in your database table
            String saat = res2.getString(2);
            ad = res2.getString(3);
            String no = res2.getString(5);

            YaklasanRandevularModel rm = new YaklasanRandevularModel(tarih,saat,ad,no);
            yaklasanListDB.add(rm);
        }
        return yaklasanListDB;
    }


}
