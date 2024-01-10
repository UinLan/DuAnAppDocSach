package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.DocSach;

public class InformationActivity extends AppCompatActivity {
TextView txvTenSachs;
ImageView imgAnhSachs;
DocSach docSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        docSach =(DocSach) b.getSerializable("sach");
    }
    private void anhXa(){
        imgAnhSachs = findViewById(R.id.imgAnhSachs);
        txvTenSachs= findViewById(R.id.txvTenSachs);

    }
    private void setUp(){
        txvTenSachs.setText(docSach.getTenSach());
        Glide.with(this).load(docSach.getLinkAnh()).into(imgAnhSachs);
    }
    private void setClick(){}

}