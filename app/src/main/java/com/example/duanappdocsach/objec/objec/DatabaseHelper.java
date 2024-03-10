package com.example.duanappdocsach.objec.objec;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột cho ghi chú
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_COMMENT_ID = "id";
    private static final String COLUMN_COMMENT_CONTENT = "content";
    private static final String COLUMN_COMMENT_AUTHOR = "author";

    // Tên bảng và các cột cho bình luận
    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_NOTE_ID = "id";
    private static final String COLUMN_NOTE_CONTENT = "content";
    private static final String COLUMN_CONTENT = "content";
    public static final String TABLE_NAME = "your_table_name";
    public static final String COLUMN_ID = "id";




    // Câu lệnh tạo bảng cho ghi chú
    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS + "("
            + COLUMN_COMMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_COMMENT_CONTENT + " TEXT,"
            + COLUMN_COMMENT_AUTHOR + " TEXT"
            + ")";

    // Câu lệnh tạo bảng cho bình luận
    private static final String CREATE_TABLE_NOTES = "CREATE TABLE " + TABLE_NOTES + "("
            + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOTE_CONTENT + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng cho ghi chú và bình luận
        db.execSQL(CREATE_TABLE_COMMENTS);
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nếu cần nâng cấp cơ sở dữ liệu, bạn có thể thực hiện thay đổi ở đây
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    // Thêm một ghi chú vào cơ sở dữ liệu
    public long addComment(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMMENT_CONTENT, content);
        long id = db.insert(TABLE_COMMENTS, null, values);
        db.close();
        return id;
    }

    // Lấy danh sách các ghi chú từ cơ sở dữ liệu
    @SuppressLint("Range")
    public ArrayList<Comment> getAllComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COMMENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Lặp qua tất cả các hàng và thêm vào danh sách ghi chú
        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_COMMENT_ID)));
                comment.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT_CONTENT)));
                comment.setAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT_AUTHOR)));
                comments.add(comment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return comments;
    }

    // Thêm một ghi chú vào cơ sở dữ liệu
    public long addNote(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_CONTENT, content);
        long id = db.insert(TABLE_NOTES, null, values);
        db.close();
        return id;
    }

    // Lấy danh sách các ghi chú từ cơ sở dữ liệu
    @SuppressLint("Range")
    public ArrayList<String> getAllNotes() {
        ArrayList<String> notes = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Lặp qua tất cả các hàng và thêm vào danh sách ghi chú
        if (cursor.moveToFirst()) {
            do {
                String content = cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_CONTENT));
                notes.add(content);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notes;
    }
    // Phương thức cập nhật ghi chú
    public void updateComment(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMMENT_CONTENT, comment.getContent()); // Đảm bảo cập nhật nội dung ghi chú

        // Cập nhật ghi chú với ID tương ứng
        db.update(TABLE_COMMENTS, values, COLUMN_COMMENT_ID + " = ?", new String[]{String.valueOf(comment.getId())});
        db.close();
    }
    // Thêm phương thức xóa ghi chú từ cơ sở dữ liệu
    public void deleteComment(int commentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMMENTS, COLUMN_COMMENT_ID + " = ?", new String[]{String.valueOf(commentId)});
        db.close();
    }
}
