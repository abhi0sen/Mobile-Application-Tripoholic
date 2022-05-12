package com.example.tourandtravel;

import android.content.Context;
import android.content.SharedPreferences;
public class SharedPrefrencesHelper {
    private SharedPreferences sharedPreferences;
    private Context context;
    private String TripName = "TripName", Location = "Location", Price = "Price", tripDiscription = "tripDiscription", cab = "cab", hotels = "hotels";
    public SharedPrefrencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences("login_session",
                Context.MODE_PRIVATE);
        this.context = context;
    }
    public String getTripName() {
        return sharedPreferences.getString(TripName, "");
    }
    public String getLocation() {
        return sharedPreferences.getString(Location, "");
    }
    public String getPrice() {
        return sharedPreferences.getString(Price, "");
    }
    public String getTripDiscription() {
        return sharedPreferences.getString(tripDiscription, "");
    }
    public String getCab() {
        return sharedPreferences.getString(cab, "");
    }public String getHotels() {
        return sharedPreferences.getString(hotels, "");
    }
    public void setTripName(String tripName) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.TripName, tripName);
        edit.commit();
    }
    public void setLastname(String location) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.Location, location);
        edit.commit();
    }
    public void setPrice(String Price) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.Price, Price);
        edit.commit();
    }public void setTripDiscription(String Price) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.tripDiscription, tripDiscription);
        edit.commit();
    }
    public void setCab(String cab) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.cab, cab);
        edit.commit();
    }
    public void setHotels(String hotels) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.hotels, hotels);
        edit.commit();
    }
}