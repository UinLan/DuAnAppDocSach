package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.Comment;
import com.example.duanappdocsach.objec.objec.DatabaseHelper;

import java.util.ArrayList;

public class DanhSachGhiChuActivity extends AppCompatActivity {

    ListView listViewNotes;
    ArrayList<Comment> listNotes;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_ghi_chu);

        // Ánh xạ ListView
        listViewNotes = findViewById(R.id.listViewNotes);

        // Khởi tạo danh sách ghi chú
        listNotes = new ArrayList<>();

        // Khởi tạo đối tượng DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Lấy danh sách ghi chú từ cơ sở dữ liệu
        listNotes = databaseHelper.getAllComments();

        // Tạo adapter để hiển thị danh sách ghi chú
        ArrayAdapter<Comment> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNotes);

        // Gán adapter cho ListView
        listViewNotes.setAdapter(adapter);

        // Thiết lập sự kiện khi nhấn giữ vào một ghi chú trong danh sách
        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ghi chú được chọn từ danh sách
                Comment selectedComment = listNotes.get(position);

                // Hiển thị Dialog để sửa ghi chú
                showEditCommentDialog(selectedComment);

                return true;
            }
        });
    }

    private void showEditCommentDialog(final Comment comment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa hoặc xóa ghi chú");

        // Tạo EditText để người dùng nhập nội dung mới cho ghi chú
        final EditText input = new EditText(this);
        input.setText(comment.getContent());
        builder.setView(input);

        // Thiết lập nút lưu và xử lý sự kiện khi người dùng nhấn
        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy nội dung mới từ EditText
                String newContent = input.getText().toString();

                // Cập nhật nội dung mới cho ghi chú và lưu vào cơ sở dữ liệu
                comment.setContent(newContent);
                databaseHelper.updateComment(comment);

                // Cập nhật lại danh sách ghi chú trên ListView
                refreshList();
            }
        });
        // Thiết lập nút xóa và xử lý sự kiện khi người dùng nhấn
        builder.setNeutralButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xóa ghi chú khỏi cơ sở dữ liệu
                databaseHelper.deleteComment(comment.getId());

                // Cập nhật lại danh sách ghi chú trên ListView
                refreshList();
            }
        });

        // Thiết lập nút hủy
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Hiển thị Dialog
        builder.show();
    }

    // Phương thức cập nhật lại danh sách ghi chú trên ListView
    private void refreshList() {
        listNotes.clear();
        listNotes.addAll(databaseHelper.getAllComments());
        ((ArrayAdapter<Comment>) listViewNotes.getAdapter()).notifyDataSetChanged();
    }
}
