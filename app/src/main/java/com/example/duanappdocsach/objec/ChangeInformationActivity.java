package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.User;

public class ChangeInformationActivity extends AppCompatActivity {
    Toolbar myToolBar;
    private EditText edtHoTen;
    private TextView edtEmail;
    private EditText edtSdt;
    private EditText edtNgaysinh;
    private EditText edtNoisinhsong;
    private Button btUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        myToolBar = findViewById(R.id.materialToolbarNguoiDung);

        edtHoTen = findViewById(R.id.edtHoten);
        edtEmail = findViewById(R.id.edtEmail);
        edtSdt = findViewById(R.id.edtSdt);
        edtNgaysinh = findViewById(R.id.edtNgaysinh);
        edtNoisinhsong = findViewById(R.id.edtNoisinhsong);
        btUpdate = findViewById(R.id.btUpdate);

        if(getIntent().getExtras() != null){
            User user = (User) getIntent().getExtras().get("object_User");
            edtHoTen.setText(user.getHoten());
            edtEmail.setText(user.getEmail());
            edtSdt.setText(user.getSoDienThoai());
            edtNgaysinh.setText(user.getNgaySinh());
            edtNoisinhsong.setText(user.getNoiSinhSong());
        }
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void backActivity() {
        String strHoTenUpdate = edtHoTen.getText().toString().trim();
        String strEmailUpdate = edtEmail.getText().toString().trim();
        String strSdtUpdate = edtSdt.getText().toString().trim();
        String strNgaySinhUpdate = edtNgaysinh.getText().toString().trim();
        String strNoiSinhSongUpdate = edtNoisinhsong.getText().toString().trim();

        User user = new User(strHoTenUpdate,strEmailUpdate,strSdtUpdate,strNgaySinhUpdate,strNoiSinhSongUpdate);

        Intent returnintent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_User", user);
        returnintent.putExtras(bundle);

        returnintent.putExtra("key_HoTen",strHoTenUpdate);
        setResult(Activity.RESULT_OK, returnintent);
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
    }
}