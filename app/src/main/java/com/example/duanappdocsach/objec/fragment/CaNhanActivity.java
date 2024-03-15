package com.example.duanappdocsach.objec.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duanappdocsach.objec.ChangePasswordActivity;
import com.example.duanappdocsach.R;

public class CaNhanActivity extends AppCompatActivity {
    Button btChangpass;
    Toolbar myToolBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canhan);
        btChangpass = findViewById(R.id.button_PassWord);
        myToolBar = findViewById(R.id.materialToolbarCaNhan);
        btChangpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CaNhanActivity.this, ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}