package com.example.cafebookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ImageView imageView_banner;
    private RecyclerView recyclerView;
    private TextView TextViewDate, TextViewTime, TextViewNumber;
    private ImageButton ImageButtonUP, ImageButtonDOWN;
    private Button ButtonReserve;
    private CuisineAdapter cuisineAdapter;
    private String Date, Time, Cuisine, Name;
    private int NumOfPeople, MaxNumOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeUI();
        TextViewNumber.setText(String.valueOf(0));
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Restaurant restaurant = (Restaurant) bundle.getParcelable("restaurant");
            if (restaurant != null) {
                SetBanner(restaurant);
                SetCuisineMenu(restaurant);
                MaxNumOfPeople = restaurant.getMaxDine();
                Name = restaurant.getName();
            }
        }
    }

    private void initializeUI() {
        imageView_banner = findViewById(R.id.imageView_banner);
        recyclerView = findViewById(R.id.recycleView2);
        TextViewDate = findViewById(R.id.TextViewDate);
        TextViewTime = findViewById(R.id.TextViewTime);
        TextViewNumber = findViewById(R.id.textView_display_number);
        ImageButtonUP = findViewById(R.id.imageButton_ArrowUp);
        ImageButtonDOWN = findViewById(R.id.imageButton_ArrowDown);
        ButtonReserve = findViewById(R.id.button_reserve);
        TextViewDate.setOnClickListener(DateOnClickListener);
        TextViewTime.setOnClickListener(TimeOnClickListener);
        ImageButtonUP.setOnClickListener(ArrowUpOnClickListener);
        ImageButtonDOWN.setOnClickListener(ArrowDownOnClickListener);
        ButtonReserve.setOnClickListener(ReserveBtnOnClickListener);
    }

    private void SetBanner(Restaurant restaurant) {
        if (restaurant.getName().equals("Serendipity Cafe")) { imageView_banner.setImageResource(R.drawable.cafe1); }
        if (restaurant.getName().equals("Old Asian Cafe")) { imageView_banner.setImageResource(R.drawable.cafe2); }
        if (restaurant.getName().equals("International Taste Restaurant")) { imageView_banner.setImageResource(R.drawable.cafe3); }
        if (restaurant.getName().equals("Gardenhouse Cafe")) { imageView_banner.setImageResource(R.drawable.cafe4); }
        if (restaurant.getName().equals("Spice Cafe")) { imageView_banner.setImageResource(R.drawable.cafe5); }
    }

    private void SetCuisineMenu(Restaurant restaurant) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cuisineAdapter = new CuisineAdapter(restaurant);
        recyclerView.setAdapter(cuisineAdapter);
    }

    private View.OnClickListener DateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog date_picker_dialog = new DatePickerDialog (
                    MainActivity2.this,
                    MainActivity2.this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            date_picker_dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
            date_picker_dialog.show();
        }
    };

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
        Date = day_of_month + "-" + (month + 1) + "-" + year;
        TextViewDate.setText(Date);
    }

    private View.OnClickListener TimeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity2.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    String amPm;
                    if (hourOfDay >= 12) {
                        if (hourOfDay != 12) { hourOfDay -= 12; }
                        amPm = "PM";
                    }
                    else {
                        amPm = "AM";
                    }
                    Time = String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                    TextViewTime.setText(Time);
                }
            }, currentHour, currentMinute, false);
            timePickerDialog.show();
        }
    };

    private View.OnClickListener ArrowUpOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (NumOfPeople < MaxNumOfPeople) { NumOfPeople++; }
            TextViewNumber.setText(String.valueOf(NumOfPeople));
        }
    };

    private View.OnClickListener ArrowDownOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (NumOfPeople > 0) { NumOfPeople--; }
            TextViewNumber.setText(String.valueOf(NumOfPeople));
        }
    };

    private View.OnClickListener ReserveBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cuisine="";
            if (!cuisineAdapter.getSelectedCuisine().isEmpty()){
                ArrayList<String> cuisine = cuisineAdapter.getSelectedCuisine();
                for (int i = 0; i < cuisine.size(); i++) {
                    Cuisine += cuisine.get(i);
                    if (i != cuisine.size() - 1) { Cuisine += ", "; }
                }
            }

            if (!Cuisine.equals("") && Date != null && Time != null && NumOfPeople != 0 && Name != null) {
                Bundle bundle = new Bundle();
                Reservation reservation = new Reservation(Cuisine, Date, Time, Name, NumOfPeople);
                bundle.putParcelable("reservation", reservation);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
            else {
                Context context = getApplicationContext();
                CharSequence text = "The input is not complete!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    };
}