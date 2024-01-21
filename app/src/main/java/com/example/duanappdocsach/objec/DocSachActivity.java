package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.SachAdapter;
import com.example.duanappdocsach.objec.api.ApiLayDetailSach;
import com.example.duanappdocsach.objec.interfaces.LayDetailSachVe;
import com.example.duanappdocsach.objec.objec.Sach;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocSachActivity extends AppCompatActivity implements LayDetailSachVe {
TextView tvSachDetail,txvSoTrang;
ArrayList<String> arrSachDetail = new ArrayList<>();
int soTrang,soTrangDangDoc;
String idChuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sach);
    init();
    anhXa();
    setUp();
    setClick();
    new ApiLayDetailSach(this,idChuong).execute();
}
    //@SuppressLint("SuspiciousIndentation")
    private void  init(){

        Bundle b = getIntent().getBundleExtra("data");
        idChuong= b.getString("idChuong");
    }
    private void  anhXa(){
        tvSachDetail = findViewById(R.id.tvSachDetail);
        txvSoTrang = findViewById(R.id.txvSoTrang);
    }
    private void  setUp(){
    //docTheoTrang(0);
    }
    private void  setClick(){

    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);
    }
    private void docTheoTrang(int i){
        soTrangDangDoc = soTrangDangDoc+i;
        if(soTrangDangDoc ==0){
            soTrangDangDoc =1;
        }
        if(soTrangDangDoc>soTrang){
            soTrangDangDoc=soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc+"/"+soTrang);
        tvSachDetail.setText(arrSachDetail.get(soTrangDangDoc - 1));
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        arrSachDetail = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(data);
            for(int i=0;i<arr.length();i++){
                arrSachDetail.add(arr.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang = arrSachDetail.size();
            docTheoTrang(0);
        }catch (JSONException e){

        }


    }

    @Override
    public void biLoi() {

    }
}