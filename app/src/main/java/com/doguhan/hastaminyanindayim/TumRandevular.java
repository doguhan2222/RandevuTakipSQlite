package com.doguhan.hastaminyanindayim;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;


public class TumRandevular extends AppCompatActivity {
    EditText ad, soyad, no, aciklama;
    TextView tarih, saat, deneme;
    Button sil, guncelle;
    String tarihA, saatA, adA, soyadA, noA, aciklamaA, value;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ListView myListview;
    Adapter myAdapter;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private int year, month, dayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_randevular);
        fillListview();

    }

    public void fillListview() {
        myListview = findViewById(R.id.listview);
        DatabaseHelper dbhelper = new DatabaseHelper(this);

        ArrayList<RandevularModel> dogList = dbhelper.getAllData();

        myAdapter = new Adapter(dogList, this);
        myListview.setAdapter(myAdapter);



        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.text_ad);

                // value değeri listview tıklanan elamanın ismi.Budeğer silmek ve alert açmak için tıklanan elamanın ismini kullanarak siler ve alerti açarak diğer
                //bilgileri gösterir.
                value = tv.getText().toString();
                Log.d("deneme2", value);
                alertAc(value);


            }
        });

    }


    public void alertAc(String value) {
        // dialog nesnesi oluştur ve layout dosyasına bağlan
        Dialog dialog = new Dialog(TumRandevular.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog);

        // custom dialog elemanlarını tanımla - text, image ve button
        tarih = dialog.findViewById(R.id.alert_randevu_tarih);
        saat = dialog.findViewById(R.id.alert_randevu_saat);
        ad = dialog.findViewById(R.id.alertAd);
        soyad = dialog.findViewById(R.id.alertSoyad);
        no = dialog.findViewById(R.id.alertNo);
        aciklama = dialog.findViewById(R.id.alertAciklama);
        sil = dialog.findViewById(R.id.sil);
        guncelle = dialog.findViewById(R.id.guncelle);
        deneme = dialog.findViewById(R.id.textView8);


        dialog.show();
        final String[] args = {String.valueOf(value)};
        databaseHelper = new DatabaseHelper(TumRandevular.this);
        db = databaseHelper.getWritableDatabase();
        //Cursor cursorc = db.rawQuery("SELECT * FROM " + TabloBilgileri.NoteEntry.TABLO_ADI + " WHERE " + TabloBilgileri.NoteEntry.RANDEVU_ID + "=" + index, null);
        Cursor cursorc = db.rawQuery("SELECT * FROM " + TabloBilgileri.NoteEntry.TABLO_ADI + " WHERE " + TabloBilgileri.NoteEntry.HASTA_ADI + "=?", args);


        while (cursorc.moveToNext()) {
            tarihA = cursorc.getString(1);   //0 is the number of id column in your database table
            saatA = cursorc.getString(2);
            adA = cursorc.getString(3);
            soyadA = cursorc.getString(4);
            noA = cursorc.getString(5);
            aciklamaA = cursorc.getString(6);

            tarih.setText(tarihA);
            saat.setText(saatA);
            ad.setText(adA);
            soyad.setText(soyadA);
            no.setText(noA);
            aciklama.setText(aciklamaA);

        }

        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(TumRandevular.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tarih.setText(year + "-" + (month+1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        saat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Şimdiki zaman bilgilerini alıyoruz. güncel saat, güncel dakika.
                final Calendar takvim = Calendar.getInstance();
                int saaat = takvim.get(Calendar.HOUR_OF_DAY);
                int dakikaa = takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(TumRandevular.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // hourOfDay ve minute değerleri seçilen saat değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        saat.setText(hourOfDay + ":" + minute);
                    }
                }, saaat, dakikaa, true);
// timepicker açıldığında set edilecek değerleri buraya yazıyoruz.
// şimdiki zamanı göstermesi için yukarıda tanımladığımız değişkenleri kullanıyoruz.
// true değeri 24 saatlik format için.

// dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                tpd.show();
            }
        });
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randevuSil(value);
            }
        });
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randevuGuncelle(value);
            }
        });

    }

    public void randevuSil(String value) {
        final String[] args = {String.valueOf(value)};
        db = databaseHelper.getWritableDatabase();
        db.delete(TabloBilgileri.NoteEntry.TABLO_ADI, TabloBilgileri.NoteEntry.HASTA_ADI + "=?", args);
        //myListview.invalidateViews();
        db.close();
        Intent intent = new Intent(TumRandevular.this, TumRandevular.class);
        startActivity(intent);
        myAdapter.notifyDataSetChanged();

        Toast.makeText(TumRandevular.this, "Randevu silindi", Toast.LENGTH_SHORT);

    }

    // alert içindekileri edittext yap veri çek , sonra put ile koy ve güncelle
    public void randevuGuncelle(String value) {
        final String[] args = {String.valueOf(value)};
        db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(TabloBilgileri.NoteEntry.TARIH, tarih.getText().toString());
        values.put(TabloBilgileri.NoteEntry.SAAT, saat.getText().toString());
        values.put(TabloBilgileri.NoteEntry.HASTA_ADI, ad.getText().toString());
        values.put(TabloBilgileri.NoteEntry.HASTA_SOYADI, soyad.getText().toString());
        values.put(TabloBilgileri.NoteEntry.HASTA_NO, no.getText().toString());
        values.put(TabloBilgileri.NoteEntry.RANDEVU_ACIKLAMA, aciklama.getText().toString());

        // notunu güncelleyelim
        db.update(TabloBilgileri.NoteEntry.TABLO_ADI, values, TabloBilgileri.NoteEntry.HASTA_ADI + "=?", args);
        Intent intent = new Intent(TumRandevular.this, TumRandevular.class);
        startActivity(intent);
        myAdapter.notifyDataSetChanged();


    }


}
/*

        tarih.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(TumRandevular.this,
        new DatePickerDialog.OnDateSetListener() {
@Override
public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        tarih.setText(day + "/" + (month + 1) + "/" + year);
        }
        }, year, month, dayOfMonth);
        datePickerDialog.show();
        }
        });
        saat.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// Şimdiki zaman bilgilerini alıyoruz. güncel saat, güncel dakika.
final Calendar takvim = Calendar.getInstance();
        int saaat = takvim.get(Calendar.HOUR_OF_DAY);
        int dakikaa = takvim.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(TumRandevular.this, new TimePickerDialog.OnTimeSetListener() {

@Override
public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // hourOfDay ve minute değerleri seçilen saat değerleridir.
        // Edittextte bu değerleri gösteriyoruz.
        saat.setText(hourOfDay + ":" + minute);
        }
        }, saaat, dakikaa, true);
// timepicker açıldığında set edilecek değerleri buraya yazıyoruz.
// şimdiki zamanı göstermesi için yukarıda tanımladığımız değişkenleri kullanıyoruz.
// true değeri 24 saatlik format için.

// dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
        tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
        tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
        tpd.show();
        }
        });*/



