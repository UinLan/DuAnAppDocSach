package com.example.duanappdocsach.objec.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.DocSachAdapter;
import com.example.duanappdocsach.objec.api.ApiLaySach;
import com.example.duanappdocsach.objec.interfaces.LaySachVe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity implements LaySachVe {
GridView gdvDSSach;
DocSachAdapter adapter;
ArrayList<DocSach> docSachArrayList;
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
    @SuppressLint("SuspiciousIndentation")
    private void  init(){
    docSachArrayList = new ArrayList<>();

        adapter = new DocSachAdapter(this,0,docSachArrayList);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            String s = edtTimKiem.getText().toString();
            adapter.sortSach(s);

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
        JSONArray arr = new JSONArray(data);
        for(int i = 0;i<arr.length();i++)
        {
            JSONObject o = arr.getJSONObject(i);
            docSachArrayList.add(new DocSach(o));
        }
        adapter = new DocSachAdapter(this,0,docSachArrayList);
        gdvDSSach.setAdapter(adapter);

    }catch (JSONException e){}
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi Ket Noi",Toast.LENGTH_SHORT).show();
    }
}