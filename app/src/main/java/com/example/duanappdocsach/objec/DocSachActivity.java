package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.fragment.api.ApiLayDetailSach;
import com.example.duanappdocsach.objec.objec.interfaces.LayDetailSachVe;
import com.example.duanappdocsach.objec.objec.ChuongSach;
import com.example.duanappdocsach.objec.objec.Sach;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocSachActivity extends AppCompatActivity implements LayDetailSachVe {
TextView tvSachDetail,txvSoTrang,textViewNote;
ArrayList<String> arrSachDetail = new ArrayList<>();
int soTrang,soTrangDangDoc;
String idChuong;
Button btnGhiChu;

    Sach docSach;
    ArrayList<ChuongSach> arrChuong;
    TextView readTitleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sach);
        String tenChuong = getIntent().getStringExtra("TEN_CHUONG");
        TextView readTitleTextView = findViewById(R.id.tenChuongDocSach);
        readTitleTextView.setText(tenChuong);
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
        docSach =(Sach) b.getSerializable("sach");
    }
    private void  anhXa(){
        tvSachDetail = findViewById(R.id.tvSachDetail);
        txvSoTrang = findViewById(R.id.txvSoTrang);
        btnGhiChu = findViewById(R.id.btnGhiChu);
        textViewNote = findViewById(R.id.textViewNote);
        readTitleTextView = findViewById(R.id.tenChuongDocSach);
        arrChuong = new ArrayList<>();
    }
    private void  setUp(){
    //docTheoTrang(0);
    }
    private void  setClick(){
        btnGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenChuong = readTitleTextView.getText().toString();
                Intent intent = new Intent(DocSachActivity.this, GhiChuActivity.class);
                intent.putExtra("TEN_CHUONG", tenChuong);
                startActivity(intent);
            }
        });
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