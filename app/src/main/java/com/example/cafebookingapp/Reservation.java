package com.example.cafebookingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Reservation implements Parcelable {
    private String Cuisine;
    private String Date;
    private String Time;
    private String Name;
    private int NumberOfPeople;

    public Reservation(String cuisine, String date, String time, String name, int numberOfPeople) {
        Cuisine = cuisine;
        Date = date;
        Time = time;
        Name = name;
        NumberOfPeople = numberOfPeople;
    }

    protected Reservation(Parcel in) {
        Cuisine = in.readString();
        Date = in.readString();
        Time = in.readString();
        Name = in.readString();
        NumberOfPeople = in.readInt();
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public String getCuisine() {
        return Cuisine;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() { return Time; }

    public String getName() {
        return Name;
    }

    public int getNumberOfPeople() {
        return NumberOfPeople;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Cuisine);
        parcel.writeString(this.Date);
        parcel.writeString(this.Time);
        parcel.writeString(this.Name);
        parcel.writeInt(this.NumberOfPeople);
    }
}
