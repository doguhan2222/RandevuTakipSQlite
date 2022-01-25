package com.doguhan.hastaminyanindayim;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.devsmart.android.ui.HorizontalListView;
import java.util.ArrayList;


public class GirisEkrani extends AppCompatActivity {
    Button randevuEkleGecis, tumRandevularGecis;
    HorizontalListView hListView;
    YaklasanRandevularAdapter yaklasanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_ekrani);
        tanimla();
        gecisYap();
        yaklasanRandevulariGoster();



    }


    public void tanimla() {
        randevuEkleGecis = findViewById(R.id.girisEkraniRandeuEkleB);
        tumRandevularGecis = findViewById(R.id.girisEkraniTumRandevularB);
        hListView = (HorizontalListView) findViewById(R.id.yaklasanListView);


    }

    public void yaklasanRandevulariGoster() {
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        ArrayList<YaklasanRandevularModel> list = dbhelper.yaklasanRandevulariCek();
        yaklasanAdapter = new YaklasanRandevularAdapter(list, this);
        hListView.setAdapter(yaklasanAdapter);
        yaklasanAdapter.notifyDataSetChanged();
    }


    public void gecisYap() {
        randevuEkleGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent randevuEkleIntent = new Intent(GirisEkrani.this, RandevuEkle.class);
                startActivity(randevuEkleIntent);

            }

        });
        tumRandevularGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tumRandevularIntent = new Intent(GirisEkrani.this, TumRandevular.class);
                startActivity(tumRandevularIntent);
            }
        });
    }

}
