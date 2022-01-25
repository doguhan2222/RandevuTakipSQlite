package com.doguhan.hastaminyanindayim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.devsmart.android.ui.HorizontalListView;

import java.util.ArrayList;

import static com.doguhan.hastaminyanindayim.App.CHANNEL_1_ID;

public class GirisEkrani extends AppCompatActivity {
    private static final String CHANNEL_ID = "1";
    Button randevuEkleGecis, tumRandevularGecis;
    HorizontalListView hListView;
    YaklasanRandevularAdapter yaklasanAdapter;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_ekrani);
        tanimla();
        gecisYap();
        yaklasanRandevulariGoster();
        yaklasanRandevuBildirimi();


    }


    public void tanimla() {
        randevuEkleGecis = findViewById(R.id.girisEkraniRandeuEkleB);
        tumRandevularGecis = findViewById(R.id.girisEkraniTumRandevularB);
        hListView = (HorizontalListView) findViewById(R.id.yaklasanListView);
        notificationManager = NotificationManagerCompat.from(this);


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
    public void yaklasanRandevuBildirimi(){
        String title = "deneme baslik";
        String message = " deneme mesaj";

       /* Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)

                .build();

        notificationManager.notify(1, notification);*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.indir)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // prirority here for version below Oreo
                .setContentText(message)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());


    }
}