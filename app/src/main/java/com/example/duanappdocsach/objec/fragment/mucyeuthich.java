package com.example.duanappdocsach.objec.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanappdocsach.objec.adapterclass.FavAdapter;
import com.example.duanappdocsach.objec.database.databaseHelper;


import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.model.FavoriteModel;
import com.example.duanappdocsach.objec.model.ThemeModel;

import java.util.ArrayList;
import java.util.Currency;

public class mucyeuthich extends AppCompatActivity {
    private RecyclerView recyclerView;
    private databaseHelper favDB;
    private FavAdapter favAdapter;
    private ArrayList<FavoriteModel> arrayListFav = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mucyeuthich_nav);
        recyclerView = findViewById(R.id.recyclerviewFav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favDB = new databaseHelper(this);

        getAllFavorite();
    }
    private void getAllFavorite() {
        Cursor cursor = favDB.readAllData3();
        if(cursor.getCount() == 0) {
            Toast.makeText(mucyeuthich.this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {

                FavoriteModel themeModel = new FavoriteModel();
                themeModel.setKey_id(cursor.getString(0));
                themeModel.setTitle(cursor.getString(1));
                themeModel.setImage(cursor.getInt(3));
                arrayListFav.add(themeModel);
            }
            favAdapter = new FavAdapter(mucyeuthich.this,arrayListFav,onclickListnerDelete);
            recyclerView.setAdapter(favAdapter);
            favAdapter.notifyDataSetChanged();
        }
    }
    View.OnClickListener onclickListnerDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            favDB.deleteItem3(arrayListFav.get(pos).getKey_id());
            arrayListFav.remove(arrayListFav.get(pos));
            favAdapter.setItems(arrayListFav);
            favAdapter.notifyDataSetChanged();
        }
    };
}