package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duanappdocsach.R;

public class layout_header extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_header_navigation);
        String email = getIntent().getStringExtra("Email");
        TextView EmailTextView = findViewById(R.id.txvEmail);
        EmailTextView.setText(email);
    }
}