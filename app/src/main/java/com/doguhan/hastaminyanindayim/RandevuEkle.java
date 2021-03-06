package com.doguhan.hastaminyanindayim;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class RandevuEkle extends AppCompatActivity {
    String hastaAdi,hastaSoyadi,hastaNo,hastaAciklama,randevuTarih,randevuSaat;
    EditText hastaAdiAl,hastaSoyadiAl,hastaNoAl,hastaAciklamaAl;
    TextView tarihSecTV,saatSecTV,tarihGosterTV,saatGosterTV;
    Button randevuEkle;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_ekle);
        tanimla();
        tarihVeSaatSec();
        randevuyuKaydet();
    }
    public void tanimla(){
        hastaAdiAl = findViewById(R.id.hastaAdiKayitEt);
        hastaSoyadiAl = findViewById(R.id.hastaSoyadiKayitEt);
        hastaNoAl = findViewById(R.id.hastaNoKayitEt);
        hastaAciklamaAl = findViewById(R.id.hastaAciklamaKayitEt);
        tarihSecTV = findViewById(R.id.tarihSecTv);
        saatSecTV = findViewById(R.id.saatSecTv);
        tarihGosterTV = findViewById(R.id.tarihGosterHastaEkraniTv);
        saatGosterTV = findViewById(R.id.saatGosterHastaEkraniTv);
        randevuEkle = findViewById(R.id.randevu_ekle_buton);
    }
    public void tarihVeSaatSec(){
        tarihSecTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(RandevuEkle.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tarihGosterTV.setText(year + "-" + (month+1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        saatSecTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ??imdiki zaman bilgilerini al??yoruz. g??ncel saat, g??ncel dakika.
                final Calendar takvim = Calendar.getInstance();
                int saat = takvim.get(Calendar.HOUR_OF_DAY);
                int dakika = takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(RandevuEkle.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // hourOfDay ve minute de??erleri se??ilen saat de??erleridir.
                        // Edittextte bu de??erleri g??steriyoruz.
                        saatGosterTV.setText(hourOfDay + ":" + minute);
                    }
                }, saat, dakika, true);
// timepicker a????ld??????nda set edilecek de??erleri buraya yaz??yoruz.
// ??imdiki zaman?? g??stermesi i??in yukar??da tan??mlad??????m??z de??i??kenleri kullan??yoruz.
// true de??eri 24 saatlik format i??in.

// dialog penceresinin button bilgilerini ayarl??yoruz ve ekranda g??steriyoruz.
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Se??", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "??ptal", tpd);
                tpd.show();
            }
        });
    }
    public void randevuyuKaydet(){
        randevuEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randevuTarih = tarihGosterTV.getText().toString();
                randevuSaat = saatGosterTV.getText().toString();
                hastaAdi = hastaAdiAl.getText().toString();
                hastaSoyadi = hastaSoyadiAl.getText().toString();
                hastaNo = hastaNoAl.getText().toString();
                hastaAciklama = hastaAciklamaAl.getText().toString();


                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.randevuEkle(randevuTarih,randevuSaat,hastaAdi,hastaSoyadi,hastaNo,hastaAciklama);

                db.close();


            }
        });
    }


}