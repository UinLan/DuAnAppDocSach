package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.DocSach;

import java.util.ArrayList;
import java.util.List;

public class DocSachAdapter extends ArrayAdapter<DocSach> {
    private  Context ct;
    private ArrayList<DocSach> arr;

    public DocSachAdapter(@NonNull Context context, int resource, @NonNull List<DocSach> objects) {
        super(context, resource, objects);
        this.ct =context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sach, null);
        }
        if (arr.size()>0)
        {
            DocSach docSach = this.arr.get(position);
            TextView tenTenSach = convertView.findViewById(R.id.txvTenSach);
            ImageView imgAnhSach = convertView.findViewById(R.id.imgAnhSach);
            tenTenSach.setText(docSach.getTenSach());
            Glide.with(this.ct).load(docSach.getLinkAnh()).into(imgAnhSach);
        }
        return convertView;
    }
}
