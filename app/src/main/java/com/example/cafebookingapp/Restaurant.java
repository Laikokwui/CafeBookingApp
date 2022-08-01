package com.example.cafebookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant implements Parcelable {
    private String Name;
    private String Address;
    private String OpenHrs;
    private String Cuisine;
    private int likes;
    private int MaxDine;

    public Restaurant(String name, String address, String openHrs, String cuisine, int likes, int maxDine) {
        this.Name = name;
        this.Address = address;
        this.OpenHrs = openHrs;
        this.Cuisine = cuisine;
        this.likes = likes;
        this.MaxDine = maxDine;
    }

    public Restaurant(Parcel in) {
        this.Name = in.readString();
        this.Address = in.readString();
        this.OpenHrs = in.readString();
        this.Cuisine = in.readString();
        this.likes = in.readInt();
        this.MaxDine = in.readInt();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getOpenHrs() {
        return OpenHrs;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public int getLikes() {
        return likes;
    }

    public int getMaxDine() {
        return MaxDine;
    }

    public static ArrayList<Restaurant> createRestaurantList() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Serendipity Cafe", "3415 Lobortis. Avenue", "Opening hours: 10:00 am - 10:00 pm", "Western,Italian", 75, 10));
        restaurantList.add(new Restaurant("Old Asian Cafe", "430-985 Eleifend St", "Opening hours: 11:00 am - 9:30 pm", "Chinese,Japanese,Malay", 120, 40));
        restaurantList.add(new Restaurant("International Taste Restaurant", "481-8762 Nulla Street", "Opening hours: 9:00 am - 11:00 pm", "Western,Italian,Japanese,Chinese,Malay", 30, 100));
        restaurantList.add(new Restaurant("Gardenhouse Cafe", "2136 Adipiscing Av.", "Opening hours: 10:30 am - 10:00 pm", "Western,Italian,Japanese", 85, 50));
        restaurantList.add(new Restaurant("Spice Cafe", "343-6527 Purus. Avenue", "Opening hours: 5:00 pm - 10:00 pm", "Indian,Malay", 65, 45));
        return restaurantList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Name);
        parcel.writeString(this.Address);
        parcel.writeString(this.OpenHrs);
        parcel.writeString(this.Cuisine);
        parcel.writeInt(this.likes);
        parcel.writeInt(this.MaxDine);
    }
}
