package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.DatabaseHelper;

public class GhiChuActivity extends AppCompatActivity {

    EditText editTextContent;
    Button buttonAdd,buttonViewNotes;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghi_chu);
        String tenChuong = getIntent().getStringExtra("TEN_CHUONG");
        TextView readTitleTextView = findViewById(R.id.textViewNote);
        readTitleTextView.setText(tenChuong);
        // Ánh xạ các view
        editTextContent = findViewById(R.id.editTextNote);
        buttonAdd = findViewById(R.id.buttonSave);
        buttonViewNotes = findViewById(R.id.buttonViewNotes);
        // Khởi tạo đối tượng DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Xử lý sự kiện khi click vào nút thêm ghi chú
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy nội dung và tác giả từ EditText
                String content = editTextContent.getText().toString();

                // Kiểm tra nội dung và tác giả có rỗng không
                if (!content.isEmpty()) {
                    // Thêm ghi chú vào cơ sở dữ liệu
                    long id = databaseHelper.addComment(content);

                    // Kiểm tra nếu thêm thành công
                    if (id != -1) {
                        Toast.makeText(GhiChuActivity.this, "Thêm ghi chú thành công", Toast.LENGTH_SHORT).show();
                        // Xóa nội dung và tác giả từ EditText sau khi thêm thành công
                        editTextContent.setText("");
                    } else {
                        Toast.makeText(GhiChuActivity.this, "Thêm ghi chú thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GhiChuActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonViewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GhiChuActivity.this, DanhSachGhiChuActivity.class);
                startActivity(intent);
            }
        });
    }
}