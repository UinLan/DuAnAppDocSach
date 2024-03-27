package com.example.duanappdocsach.objec.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.ChangeInformationActivity;
import com.example.duanappdocsach.objec.ChangePasswordActivity;
import com.example.duanappdocsach.objec.objec.User;

public class nguoidung extends AppCompatActivity {
    private static final  int MY_REQUEST_CODE = 10;
    private TextView edtHoTen;
    private TextView edtEmail;
    private TextView edtSdt;
    private TextView edtNgaysinh;
    private TextView edtNoisinhsong;
    private Button btnThayDoi;
    Toolbar myToolBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);
        myToolBar = findViewById(R.id.materialToolbarNguoiDung);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edtEmail = findViewById(R.id.edtEmail);
        edtSdt = findViewById(R.id.edtSdt);
        edtNgaysinh = findViewById(R.id.edtNgaysinh);
        edtNoisinhsong = findViewById(R.id.edtNoisinhsong);
        edtHoTen = findViewById(R.id.edtHoten);
        btnThayDoi = findViewById(R.id.btnThayDoi);
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }
    private void nextActivity() {
        String strHoTen = edtHoTen.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();
        String strSdt = edtSdt.getText().toString().trim();
        String strNgaysinh = edtNgaysinh.getText().toString().trim();
        String strNoisinhsong = edtNoisinhsong.getText().toString().trim();

        User user = new User(strHoTen,strEmail,strSdt,strNgaysinh,strNoisinhsong);
        Intent intent = new Intent(nguoidung.this, ChangeInformationActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_User",user);

        intent.putExtras(bundle);
        startActivityForResult(intent,MY_REQUEST_CODE);

        SharedPreferences preferences = getSharedPreferences("myprefrences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        String email = preferences.getString("email","");
        edtEmail.setText(email);
    }
    // @Override
    // protected void onResume() {
    //  super.onResume();
    //  edtHoTen.setText(AppUtil.mHoTen);
    //}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(MY_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK){
            User user = (User) data.getExtras().get("object_User");
            edtHoTen.setText(user.getHoten());
            edtEmail.setText(user.getEmail());
            edtSdt.setText(user.getSoDienThoai());
            edtNgaysinh.setText(user.getNgaySinh());
            edtNoisinhsong.setText(user.getNoiSinhSong());
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}