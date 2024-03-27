package com.example.duanappdocsach.objec.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapterclass.ThemesAdapter;
import com.example.duanappdocsach.objec.model.ThemeModel;

import java.util.ArrayList;

public class Add_Favorite extends AppCompatActivity {
    private final ArrayList<ThemeModel> themeModels = new ArrayList<>();
    RecyclerView recyclerView;
    Button favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite);
        recyclerView = findViewById(R.id.recyclerviewThemes);
        favButton = findViewById(R.id.favoriteShow);
        recyclerView.setHasFixedSize(true);
        addToFavorite();
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Add_Favorite.this, mucyeuthich.class));
                }
            });
        }

        public void addToFavorite() {
            recyclerView.setAdapter(new ThemesAdapter(themeModels, Add_Favorite.this));
            recyclerView.setLayoutManager(new GridLayoutManager(Add_Favorite.this, 1));
            themeModels.add(new ThemeModel(R.drawable.a, "1", "theme1"));
            themeModels.add(new ThemeModel(R.drawable.pic_2, "2", "theme2"));
            themeModels.add(new ThemeModel(R.drawable.pic_3, "3", "theme3"));
            themeModels.add(new ThemeModel(R.drawable.pic_4, "4", "theme4"));
            themeModels.add(new ThemeModel(R.drawable.pic_5, "5", "theme5"));
        }
    }
