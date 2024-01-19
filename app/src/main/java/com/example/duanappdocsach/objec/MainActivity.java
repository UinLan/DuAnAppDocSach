package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.SachAdapter;
import com.example.duanappdocsach.objec.api.ApiLaySach;
import com.example.duanappdocsach.objec.interfaces.LaySachVe;
import com.example.duanappdocsach.objec.objec.Sach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LaySachVe {
GridView gdvDSSach;
SachAdapter adapter;
ArrayList<Sach> docSachArrayList;
EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLaySach(this).execute();
    }
    //@SuppressLint("SuspiciousIndentation")
    private void  init(){
    docSachArrayList = new ArrayList<>();

        adapter = new SachAdapter(this,0,docSachArrayList);
    }
    private void  anhXa(){

    gdvDSSach = findViewById(R.id.gdvDSSach);
    edtTimKiem = findViewById(R.id.edtTimKiem);

    }
    private void  setUp(){
    gdvDSSach.setAdapter(adapter);
    }
    private void  setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            String s = edtTimKiem.getText().toString();
            adapter.sortSach(s);
            }
        });
        gdvDSSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sach docsach = docSachArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("sach",docsach);
                Intent intent = new Intent(MainActivity.this,ChuongActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang Lay Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
    try {
        docSachArrayList.clear(); // Xóa dữ liệu cũ
        JSONArray arr = new JSONArray(data);
        for(int i = 0;i<arr.length();i++)
        {
            JSONObject o = arr.getJSONObject(i);
            docSachArrayList.add(new Sach(o));
        }
        //adapter.notifyDataSetChanged(); // Thông báo cho adapter biết dữ liệu đã thay đổi
        adapter = new SachAdapter(this,0,docSachArrayList);
        gdvDSSach.setAdapter(adapter);

    }catch (JSONException e){}
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi Ket Noi",Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        new ApiLaySach(this).execute();
    }
}