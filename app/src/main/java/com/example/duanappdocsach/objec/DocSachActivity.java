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
import com.example.duanappdocsach.objec.objec.Sach;

import java.util.ArrayList;

public class DocSachActivity extends AppCompatActivity {
TextView tvSachDetail,txvSoTrang;
ArrayList<String> arrSachDetail = new ArrayList<>();
int soTrang,soTrangDangDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sach);
    init();
    anhXa();
    setUp();
    setClick();
}
    //@SuppressLint("SuspiciousIndentation")
    private void  init(){
        arrSachDetail = new ArrayList<>();
        arrSachDetail.add("Chúng tôi đi xuống phố, tay trong tay, chẳng có gì phải vội. Totoca đang dạy tôi về cuộc sống. ");
        arrSachDetail.add("Và điều đó khiến tôi thực sự hạnh phúc, được anh trai nắm tay và dạy nhiều điều.");
        arrSachDetail.add("Nhưng là dạy về những điều thuộc thế giới bên ngoài. Bởi vì nhà, tôi học hỏi bằng cách tự mày mò khám phá và tự làm,");
        arrSachDetail.add("tôi mắc lỗi nhiều và vì mắc lỗi, tôi thường bị ăn đòn.");

    soTrangDangDoc = 1;
    soTrang = arrSachDetail.size();
    }
    private void  anhXa(){
        tvSachDetail = findViewById(R.id.tvSachDetail);
        txvSoTrang = findViewById(R.id.txvSoTrang);
    }
    private void  setUp(){
    docTheoTrang(0);
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
}