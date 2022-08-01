package com.example.cafebookingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private TextView textView_Date, textView_Time, textView_MaxDine, textView_Cuisine, textView_Name;
    private ImageView imageView_banner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        myAdapter = new MyAdapter(Restaurant.createRestaurantList());
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                setContentView(R.layout.activity_main_replace);
                if (data != null) {
                    initializeUI();
                    Reservation reservation = data.getParcelableExtra("reservation");
                    if (reservation != null) {
                        SetBanner(reservation);
                        String date = "Date: " + reservation.getDate();
                        String time = "Time: " + reservation.getTime();
                        String NumberOfPeople = "Number of diners: " + reservation.getNumberOfPeople();
                        String Cuisine = "Cuisine: " + reservation.getCuisine();
                        textView_Date.setText(date);
                        textView_Time.setText(time);
                        textView_MaxDine.setText(NumberOfPeople);
                        textView_Cuisine.setText(Cuisine);
                        textView_Name.setText(reservation.getName());
                    }
                }
            }
        }
    }

    private void initializeUI() {
        textView_Date = findViewById(R.id.textView_display_date);
        textView_Time = findViewById(R.id.textView_display_time);
        textView_MaxDine = findViewById(R.id.textView_number_of_dine);
        textView_Cuisine = findViewById(R.id.textView_Cuisine);
        textView_Name = findViewById(R.id.textView_name);
        imageView_banner2 = findViewById(R.id.imageView_banner2);
    }
    private void SetBanner(Reservation reservation) {
        if (reservation.getName().equals("Serendipity Cafe")) { imageView_banner2.setImageResource(R.drawable.cafe1); }
        if (reservation.getName().equals("Old Asian Cafe")) { imageView_banner2.setImageResource(R.drawable.cafe2); }
        if (reservation.getName().equals("International Taste Restaurant")) { imageView_banner2.setImageResource(R.drawable.cafe3); }
        if (reservation.getName().equals("Gardenhouse Cafe")) { imageView_banner2.setImageResource(R.drawable.cafe4); }
        if (reservation.getName().equals("Spice Cafe")) { imageView_banner2.setImageResource(R.drawable.cafe5); }
    }
}