package com.example.duanappdocsach.objec;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.CommentAdapter;
import com.example.duanappdocsach.objec.objec.Comment;

import java.util.ArrayList;

public class BinhLuanActivity extends AppCompatActivity {
    EditText commentInput;
    Button submitButton;
    ListView commentList;
    ArrayList<Comment> comments;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);

        // Lấy tên sách từ Intent
        String tenSach = getIntent().getStringExtra("TEN_SACH");

        // Ánh xạ view
        TextView bookTitleTextView = findViewById(R.id.book_title);
        bookTitleTextView.setText(tenSach);
        commentInput = findViewById(R.id.comment_input);
        submitButton = findViewById(R.id.submit_comment);
        commentList = findViewById(R.id.comment_list);

        // Khởi tạo danh sách bình luận và adapter
        comments = new ArrayList<>();
        adapter = new CommentAdapter(this, comments);
        commentList.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng nhấn nút gửi bình luận
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = commentInput.getText().toString();
                if (!content.isEmpty()) {
                    // Tạo một bình luận mới
                    Comment comment = new Comment(content, "User");
                    // Thêm vào danh sách bình luận và cập nhật ListView
                    comments.add(comment);
                    adapter.notifyDataSetChanged();
                    // Xóa nội dung trong EditText
                    commentInput.setText("");
                }
            }
        });

        // Xử lý sự kiện khi người dùng ghi nhấn giữ một bình luận trong danh sách
        commentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy bình luận được chọn từ danh sách
                final Comment selectedComment = comments.get(position);

                // Tạo một AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(BinhLuanActivity.this);
                builder.setTitle("Chọn hành động");

                // Thêm nút Sửa vào AlertDialog
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showEditCommentDialog(selectedComment);
                    }
                });

                // Thêm nút Xóa vào AlertDialog
                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa bình luận khỏi danh sách
                        comments.remove(selectedComment);
                        // Cập nhật danh sách
                        adapter.notifyDataSetChanged();
                    }
                });

                // Hiển thị AlertDialog
                builder.show();

                return true; // Trả về true để chỉ định rằng sự kiện đã được xử lý
            }
        });
    }

    // Hiển thị Dialog cho phép người dùng sửa bình luận
    private void showEditCommentDialog(final Comment comment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa bình luận");

        // Tạo EditText để người dùng nhập nội dung mới cho bình luận
        final EditText input = new EditText(this);
        input.setText(comment.getContent());
        builder.setView(input);

        // Thiết lập nút lưu và xử lý sự kiện khi người dùng nhấn
        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy nội dung mới từ EditText
                String newContent = input.getText().toString();
                // Cập nhật nội dung mới cho bình luận và cập nhật ListView
                comment.setContent(newContent);
                adapter.notifyDataSetChanged();
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
}