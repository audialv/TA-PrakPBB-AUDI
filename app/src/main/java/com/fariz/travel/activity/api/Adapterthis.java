package com.fariz.travel.activity.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.fariz.travel.R;

import java.util.ArrayList;

public class Adapterthis extends RecyclerView.Adapter<Adapterthis.ViewHolder> {

    ArrayList<Response> mainModels;
    Context context;



    public Adapterthis(Context context, ArrayList<Response> mainModels){
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_distributor_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_name.setText(mainModels.get(position).getId());
        holder.tv_location.setText(mainModels.get(position).getName());


        Glide.with(holder.imageView.getContext()).load(mainModels.get(position).getImg()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_name, tv_location,tv_email;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.row_dl_tv_name);
            tv_location = itemView.findViewById(R.id.row_dl_tv_location);

            imageView = itemView.findViewById(R.id.imageList);
        }
    }
}

