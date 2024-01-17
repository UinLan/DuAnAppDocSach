package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.ChuongSachAdapter;
import com.example.duanappdocsach.objec.api.ApiChuongSach;
import com.example.duanappdocsach.objec.interfaces.LayChuongVe;
import com.example.duanappdocsach.objec.objec.ChuongSach;
import com.example.duanappdocsach.objec.objec.Sach;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChuongActivity extends AppCompatActivity implements LayChuongVe {
TextView txvTenSachs;
ImageView imgAnhSachs;
Sach docSach;
ListView lsvDanhSachChuong;
ArrayList<ChuongSach> arrChuong;
ChuongSachAdapter chuongSachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuong);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChuongSach(this,docSach.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        docSach =(Sach) b.getSerializable("sach");
        //tao du lieu ao
        arrChuong = new ArrayList<>();
       /* for (int i=0;i<20;i++){
            arrChuong.add(new ChuongSach("Chuong "+i,"12 - 01 -2024"));
        }
        chuongSachAdapter = new ChuongSachAdapter(this,0,arrChuong);*/
    }
    private void anhXa(){
        imgAnhSachs = findViewById(R.id.imgAnhSachs);
        txvTenSachs= findViewById(R.id.txvTenSachs);
        lsvDanhSachChuong= findViewById(R.id.lsvDanhSachChuong);


    }
    private void setUp(){
        txvTenSachs.setText(docSach.getTenSach());
        Glide.with(this).load(docSach.getLinkAnh()).into(imgAnhSachs);

        //lsvDanhSachChuong.setAdapter(chuongSachAdapter);
    }
    private void setClick(){}

    @Override
    public void batDau() {
        Toast.makeText(this,"Lay Chuong Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                ChuongSach chuongSach = new ChuongSach(array.getJSONObject(i));
                arrChuong.add(chuongSach);
            }
            chuongSachAdapter = new ChuongSachAdapter(this,0,arrChuong);
            lsvDanhSachChuong.setAdapter(chuongSachAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }
}