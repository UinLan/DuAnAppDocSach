package com.example.duanappdocsach.objec.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class databaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FavoriteRecord.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "my_favorite1";
    private static final String THEME_ID = "_id3";
    private static final String THEME_TITLE3 = "theme_title";
    private static final String THEME_TIME = "theme_time";
    private static final String THEME_IMAGE = "theme_image";
    private static final String UNIQUE_ID = "unique_id";

    private Context context;
    public databaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query3 = "CREATE TABLE " + TABLE_NAME1 + " (" +
                THEME_ID + " STRING PRIMARY KEY, " +
                THEME_TITLE3 + " TEXT, " +
                THEME_TIME + " TEXT, " +
                THEME_IMAGE + " TEXT, " +
                UNIQUE_ID + " TEXT);";
        sqLiteDatabase.execSQL(query3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
    }

    public long addScanRecord3(String unique_id, String title, int image_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(THEME_ID, unique_id); // Sử dụng tham số unique_id được cung cấp, không phải hằng số UNIQUE_ID
        cv.put(THEME_TITLE3, title);
        cv.put(THEME_IMAGE, image_id);
        long result = db.insert(TABLE_NAME1, null, cv);
        if (result == -1){
            Toast.makeText(context,"Thất bại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Thêm thành công", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return result;
    }
    public Cursor readAllData3(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public void deleteItem3(String get_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1+ " WHERE "+THEME_ID+"='"+get_ID+"'");
    }
}