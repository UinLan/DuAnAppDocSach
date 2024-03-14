package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.ChuongSachAdapter;
import com.example.duanappdocsach.objec.adapter.TomTatAdapter;
import com.example.duanappdocsach.objec.api.ApiChuongSach;
import com.example.duanappdocsach.objec.api.ApiTomTatSach;
import com.example.duanappdocsach.objec.interfaces.LayChuongVe;
import com.example.duanappdocsach.objec.interfaces.LayTomTatVe;
import com.example.duanappdocsach.objec.objec.ChuongSach;
import com.example.duanappdocsach.objec.objec.Sach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChuongActivity extends AppCompatActivity implements LayChuongVe,LayTomTatVe {
TextView txvTenSachs;
ImageView imgAnhSachs;
Sach docSach;
ChuongSach chuongSach;
ListView lsvDanhSachChuong;
ArrayList<ChuongSach> arrChuong;
ChuongSachAdapter chuongSachAdapter;
TomTatAdapter tomTatAdapter;
Button btnBinhLuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuong);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChuongSach(this,docSach.getId()).execute();
        new ApiTomTatSach(this,docSach.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        docSach =(Sach) b.getSerializable("sach");
        chuongSach = (ChuongSach) b.getSerializable("chuongsach");
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
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
    }
    private void setUp(){
        txvTenSachs.setText(docSach.getTenSach());
        Glide.with(this).load(docSach.getLinkAnh()).into(imgAnhSachs);

        //lsvDanhSachChuong.setAdapter(chuongSachAdapter);
    }
    private void setClick(){
        lsvDanhSachChuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Lấy đối tượng ChuongSach tương ứng với vị trí được chọn từ danh sách
                ChuongSach selectedChuong = arrChuong.get(i);
                // Lấy tên chương từ đối tượng ChuongSach
                String tenChuong = selectedChuong.getTenChuong();
                Bundle b = new Bundle();
                b.putString("idChuong",arrChuong.get(i).getId());
                Intent intent = new Intent(ChuongActivity.this,DocSachActivity.class);
                intent.putExtra("TEN_SACH", docSach.getTenSach());
                intent.putExtra("TEN_CHUONG", tenChuong);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChuongActivity.this, BinhLuanActivity.class);
                intent.putExtra("TEN_SACH", docSach.getTenSach());
                startActivity(intent);
            }
        });
    }

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
        try {
            JSONArray jsonArray = new JSONArray(data);
            StringBuilder tomTat = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String tomTatItem = jsonObject.getString("tomTat");
                tomTat.append(tomTatItem).append("\n"); // Nối các tóm tắt lại với nhau
            }

            // Hiển thị tóm tắt trên TextView
            TextView txvTomTat = findViewById(R.id.txvTomTat);
            txvTomTat.setText(tomTat.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Đã xảy ra lỗi khi xử lý dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void biLoi() {

    }
}