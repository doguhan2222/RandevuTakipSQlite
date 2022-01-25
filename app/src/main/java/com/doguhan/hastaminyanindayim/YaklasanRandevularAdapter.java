package com.doguhan.hastaminyanindayim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class YaklasanRandevularAdapter extends BaseAdapter {

    private ArrayList<YaklasanRandevularModel> yaklasanList;
    private Context context;

    public YaklasanRandevularAdapter(ArrayList<YaklasanRandevularModel> list, Context cont) {
        this.yaklasanList = list;
        this.context = cont;

    }

    @Override
    public int getCount() {
        return this.yaklasanList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.yaklasanList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder2 = null;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.yaklasan_randevular_listview_item, null);

            holder2 = new YaklasanRandevularAdapter.ViewHolder();
            holder2.tarihYaklasan = (TextView) convertView.findViewById(R.id.yaklasanRandevuTarihItem);
            holder2.saatYaklasan = (TextView) convertView.findViewById(R.id.yaklasanRandevuSaatItem);
            holder2.adYaklasan = (TextView) convertView.findViewById(R.id.yaklasanRandevuAdItem);
            holder2.noYaklasan = (TextView) convertView.findViewById(R.id.yaklasanRandevuNoItem);


            convertView.setTag(holder2);
        } else {
            holder2 = (YaklasanRandevularAdapter.ViewHolder) convertView.getTag();
        }

        YaklasanRandevularModel stu = yaklasanList.get(position);
        holder2.tarihYaklasan.setText(stu.getRandevuTarih());
        holder2.saatYaklasan.setText(stu.getRandevuSaat());
        holder2.adYaklasan.setText(stu.getHastaAdi());
        holder2.noYaklasan.setText(stu.getHastaNo());
        return convertView;
    }


    private static class ViewHolder {
        public TextView tarihYaklasan;
        public TextView saatYaklasan;
        public TextView adYaklasan;
        public TextView noYaklasan;
    }
}

