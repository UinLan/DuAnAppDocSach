package com.example.duanappdocsach.objec.adapterclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.database.databaseHelper;

import com.example.duanappdocsach.objec.model.FavoriteModel;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.viewHolder> {

    private Context context;
    private ArrayList<FavoriteModel> favArrayList;
    private databaseHelper favDB;
    View.OnClickListener  onClickListener;

    View.OnClickListener onClickListenerDelete;

    public FavAdapter(Context context, ArrayList<FavoriteModel> favArrayList, View.OnClickListener onClickListenerDelete) {
        this.context = context;
        this.favArrayList = favArrayList;
        this.onClickListenerDelete = onClickListenerDelete;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listilen,
                parent, false);
        favDB = new databaseHelper(context);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        FavoriteModel themesModel = favArrayList.get(position);
        holder.favImageView.setImageResource(themesModel.getImage());
        holder.title_texView.setText(themesModel.getTitle());
        holder.favImageView.setTag(position);
        holder.favBtn.setImageResource(R.drawable.favorite_1);
        holder.favBtn.setTag(position);
        holder.favImageView.setOnClickListener(onClickListener);
        holder.favBtn.setOnClickListener(onClickListenerDelete);
    }

    @Override
    public int getItemCount() {
        return favArrayList.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        ImageView favBtn;
        ImageView favImageView;
        TextView title_texView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            favBtn = itemView.findViewById(R.id.fav_Btn);
            favImageView = itemView.findViewById(R.id.id_ImageView);
            title_texView = itemView.findViewById(R.id.title_TextView);
        }
    }

    public void setItems(ArrayList<FavoriteModel> favArrayList) {
        this.favArrayList = favArrayList;
    }
}