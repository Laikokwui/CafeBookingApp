package com.example.cafebookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.CuisineViewHolder> {
    private ArrayList<String> cuisineList;
    private Context context;
    private ArrayList<String> selectedCuisine = new ArrayList<>();

    public CuisineAdapter(Restaurant restaurant) {
        this.cuisineList = new ArrayList<>(Arrays.asList(restaurant.getCuisine().split(",")));
    }

    @NonNull
    @Override
    public CuisineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontallayout,parent,false);
        context = parent.getContext();
        return new CuisineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineViewHolder holder, int position) {
        String cuisine = cuisineList.get(position);
        if (cuisine.equals("Western")) { holder.imageView_cuisine.setImageResource(R.drawable.western_icon); }
        if (cuisine.equals("Italian")) { holder.imageView_cuisine.setImageResource(R.drawable.italian_icon); }
        if (cuisine.equals("Japanese")) { holder.imageView_cuisine.setImageResource(R.drawable.japanese_icon); }
        if (cuisine.equals("Chinese")) { holder.imageView_cuisine.setImageResource(R.drawable.chinese_icon); }
        if (cuisine.equals("Malay")) { holder.imageView_cuisine.setImageResource(R.drawable.malay_icon); }
    }

    @Override
    public int getItemCount() {
        return cuisineList.size();
    }

    public ArrayList<String> getSelectedCuisine() {
        return selectedCuisine;
    }

    public class CuisineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView_cuisine;
        public CheckBox Checkbox_cuisine;

        public CuisineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_cuisine = itemView.findViewById(R.id.imageView_cuisine);
            Checkbox_cuisine = itemView.findViewById(R.id.checkBox_cuisine);
            Checkbox_cuisine.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (Checkbox_cuisine.isChecked()) { selectedCuisine.add(cuisineList.get(pos)); }
            else { selectedCuisine.remove(cuisineList.get(pos)); }
        }
    }
}
