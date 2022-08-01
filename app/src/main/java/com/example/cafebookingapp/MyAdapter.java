package com.example.cafebookingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Restaurant> restaurantList;
    private Context context;
    private int REQUEST_CODE;

    public MyAdapter(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.textView_res_title.setText(restaurant.getName());
        holder.textView_address.setText(restaurant.getAddress());
        holder.textView_opentime.setText(restaurant.getOpenHrs());
        holder.textView_like_count.setText(String.valueOf(restaurant.getLikes()));

        if (restaurant.getName().equals("Serendipity Cafe")) { holder.imageView_res_icon.setImageResource(R.drawable.cafe1_icon); }
        if (restaurant.getName().equals("Old Asian Cafe")) { holder.imageView_res_icon.setImageResource(R.drawable.cafe2_icon); }
        if (restaurant.getName().equals("International Taste Restaurant")) { holder.imageView_res_icon.setImageResource(R.drawable.cafe3_icon); }
        if (restaurant.getName().equals("Gardenhouse Cafe")) { holder.imageView_res_icon.setImageResource(R.drawable.cafe4_icon); }
        if (restaurant.getName().equals("Spice Cafe")) { holder.imageView_res_icon.setImageResource(R.drawable.cafe5_icon); }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_res_title, textView_address, textView_opentime, textView_like_count;
        public ImageView imageView_res_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_res_title = itemView.findViewById(R.id.textView_res_title);
            textView_address = itemView.findViewById(R.id.textView_address);
            textView_opentime = itemView.findViewById(R.id.textView_opentime);
            textView_like_count = itemView.findViewById(R.id.textView_like_count);
            imageView_res_icon = itemView.findViewById(R.id.imageView_res_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putParcelable("restaurant", restaurantList.get(pos));
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(context, MainActivity2.class);
            ((Activity)context).startActivityForResult(intent,0);
        }
    }
}
