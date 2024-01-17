package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    private  Context ct;
    private ArrayList<Sach> arr;

    public SachAdapter( Context context, int resource, List<Sach> objects) {
        super(context, resource, objects);
        this.ct =context;
        this.arr = new ArrayList<>(objects);
    }
public void sortSach(String s)
{
    s=s.toUpperCase();
    int k =0;
    for(int i=0;i<arr.size();i++)
    {
        Sach d =arr.get(i);
        String ten = d.getTenSach().toUpperCase();
        if(ten.indexOf(s)>=0)
        {
            arr.set(i,arr.get(k));
            arr.set(k,d);
            k++;
        }
    }
    notifyDataSetChanged();
}
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sach, null);
        }
        if (arr.size()>0)
        {
            Sach docSach = this.arr.get(position);
            TextView tenTenSach = convertView.findViewById(R.id.txvTenSach);
            ImageView imgAnhSach = convertView.findViewById(R.id.imgAnhSach);
            tenTenSach.setText(docSach.getTenSach());
            Glide.with(this.ct).load(docSach.getLinkAnh()).into(imgAnhSach);
        }
        return convertView;
    }
}
