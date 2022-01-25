package com.doguhan.hastaminyanindayim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private ArrayList<RandevularModel> dogsList;
    private Context context;

    public Adapter(ArrayList<RandevularModel> list, Context cont) {
        this.dogsList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.dogsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dogsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.tum_randevular_list, null);

            holder = new ViewHolder();
            holder.tarih = (TextView) convertView.findViewById(R.id.text_tarih);
            holder.saat = (TextView) convertView.findViewById(R.id.text_saat);
            holder.ad = (TextView) convertView.findViewById(R.id.text_ad);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RandevularModel stu = dogsList.get(position);
        holder.tarih.setText(stu.getRandevuTarih());
        holder.saat.setText(stu.getRandevuSaat());
        holder.ad.setText(stu.getHastaAdi());
        return convertView;
    }

    private static class ViewHolder {
        public TextView tarih;
        public TextView saat;
        public TextView ad;

    }
}

