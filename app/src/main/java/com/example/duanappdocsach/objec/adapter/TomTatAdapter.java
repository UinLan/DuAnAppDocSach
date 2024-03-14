package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.TomTat;

public class TomTatAdapter extends BaseAdapter {
    private Context ct;
    private TomTat tomTat;

    public TomTatAdapter(Context context, TomTat tomTat) {
        this.ct = context;
        this.tomTat = tomTat;
    }

    @Override
    public int getCount() {
        return 1; // Chỉ có một tóm tắt
    }

    @Override
    public Object getItem(int position) {
        return tomTat;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chuong, null);
        }

        TextView txvTomTat = convertView.findViewById(R.id.txvTomTat); // Đảm bảo rằng id của TextView tóm tắt đã được đặt trong layout activity_chuong.xml
        txvTomTat.setText(tomTat.getTomTat()); // Sử dụng phương thức getTomTat() để lấy tóm tắt của đối tượng TomTat và hiển thị trong TextView

        return convertView;
    }
}