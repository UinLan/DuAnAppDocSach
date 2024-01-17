package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.ChuongSach;

import java.util.ArrayList;
import java.util.List;

public class ChuongSachAdapter extends ArrayAdapter<ChuongSach> {
    private Context ct;
    private ArrayList<ChuongSach> arr;
    public ChuongSachAdapter(@NonNull Context context, int resource, @NonNull List<ChuongSach> objects) {
        super(context, resource, objects);
        this.ct =context;
        this.arr = new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chuong_sach,null);

        }
        if(arr.size()>0){
            TextView txvTenChuongs,txvNgayNhap;
            txvTenChuongs =convertView.findViewById(R.id.txvTenChuongs);
            txvNgayNhap =convertView.findViewById(R.id.txvNgayNhap);

            ChuongSach chuongSach = arr.get(position);
            txvTenChuongs.setText(chuongSach.getTenChuong());
            txvNgayNhap.setText(chuongSach.getNgayDang());

        }
        return convertView;
    }
}
