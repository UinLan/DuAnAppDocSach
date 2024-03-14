package com.example.duanappdocsach.objec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.SachAdapter;
import com.example.duanappdocsach.objec.api.ApiLaySach;
import com.example.duanappdocsach.objec.fragment.canhan;
import com.example.duanappdocsach.objec.fragment.dangxuat;
import com.example.duanappdocsach.objec.fragment.mucyeuthich;
import com.example.duanappdocsach.objec.fragment.nguoidung;
import com.example.duanappdocsach.objec.interfaces.LaySachVe;
import com.example.duanappdocsach.objec.objec.Sach;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LaySachVe, NavigationView.OnNavigationItemSelectedListener {
    GridView gdvDSSach;
    SachAdapter adapter;
    ArrayList<Sach> docSachArrayList;
    EditText edtTimKiem;
    ImageView imgShopping;
    private  static final int FRAGMENT_NGUOIDUNG = 0;
    private  static final int FRAGMENT_MUCYEUTHICH = 1;
    private  static final int FRAGMENT_CANHAN = 2;
    private  static final int FRAGMENT_DANGXUAT = 3;
    private int mCurrentFragment = -1;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        //replaceFragment(new nguoidung());
        //navigationView.getMenu().findItem(R.id.nav_nguoidung).setChecked(true);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLaySach(this).execute();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Trả về MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_nguoidung) {
            if (mCurrentFragment != FRAGMENT_NGUOIDUNG){
                replaceFragment(new nguoidung());
                mCurrentFragment = FRAGMENT_NGUOIDUNG;
            }
        } else if (id == R.id.nav_dangxuat) {
            if (mCurrentFragment != FRAGMENT_DANGXUAT){
                replaceFragment(new dangxuat());
                mCurrentFragment = FRAGMENT_DANGXUAT;
            }
        } else if (id == R.id.nav_mucuathich) {
            if (mCurrentFragment != FRAGMENT_MUCYEUTHICH){
                replaceFragment(new mucyeuthich());
                mCurrentFragment = FRAGMENT_MUCYEUTHICH;
            }
        } else if (id == R.id.nav_taikhoanvabaomat) {
            if (mCurrentFragment != FRAGMENT_CANHAN){
                replaceFragment(new canhan());
                mCurrentFragment = FRAGMENT_CANHAN;
            }

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private  void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
    //@SuppressLint("SuspiciousIndentation")
    private void  init(){
        docSachArrayList = new ArrayList<>();

        adapter = new SachAdapter(this,0,docSachArrayList);
    }
    private void  anhXa(){

        gdvDSSach = findViewById(R.id.gdvDSSach);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        imgShopping = findViewById(R.id.imgShopping);

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
        imgShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BanGoiXemSachActivity.class);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}