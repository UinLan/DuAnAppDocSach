package com.example.duanappdocsach.objec.adapterclass;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.database.databaseHelper;
import com.example.duanappdocsach.objec.model.ThemeModel;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ViewHolder> {
    private final ArrayList<ThemeModel> themeModels;
    private final Context context;
    private databaseHelper favDB;
    public ThemesAdapter(ArrayList<ThemeModel> themeModels, Context context) {
        this.themeModels = themeModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ThemesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listilen,parent,false);
        favDB = new databaseHelper(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesAdapter.ViewHolder holder, int position) {
        final ThemeModel themeModelfav =themeModels.get(position);
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                ArrayList<String> ids = new ArrayList<>();
                ids.add(cursor.getString(0));
                if (ids.contains((themeModelfav.getKey_id()))){
                    holder.favBtn.setImageResource(R.drawable.favorite_2);
                }
            }
        }
        String title = themeModelfav.getTitle();
        int image = themeModelfav.getImage();
        String id = themeModelfav.getKey_id();
        holder.imageView.setImageResource(themeModelfav.getImage());
        holder.titleTextView.setText(themeModelfav.getTitle());
        holder.imageView.setTag(position);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFavorite(title,image,holder.favBtn,id);
            }
        });
    }
    @Override
    public int getItemCount() {
        return themeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleTextView;
        ImageView favBtn;
        CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.id_ImageView);
            titleTextView = itemView.findViewById(R.id.title_TextView);
            favBtn = itemView.findViewById(R.id.fav_Btn);
            cardView = itemView.findViewById(R.id.cardViewThemes);
        }
    }
    private void  checkFavorite(String title, int image,ImageView favorite, String unique_id){
        Cursor cursor = favDB.readAllData3();

        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                if (cursor.getString(0).equalsIgnoreCase(unique_id)){
                    favDB.deleteItem3(unique_id);
                    favorite.setImageResource(R.drawable.favorite_2);
                    return;
                }
            }
        }
        favDB.addScanRecord3(unique_id,title,image);
        favorite.setImageResource(R.drawable.favorite_1);
    }
}