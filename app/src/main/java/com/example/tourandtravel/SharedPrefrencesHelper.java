package com.example.tourandtravel;

import android.content.Context;
import android.content.SharedPreferences;
public class SharedPrefrencesHelper {
    private SharedPreferences sharedPreferences;
    private Context context;
    private String Username = "username", address = "address",dob = "dob", gender = "gender", phone = "phone", mail = "mail", roleId = "roleId", TripName = "TripName", Location = "Location", price = "Price", tripDiscription = "tripDiscription", cab = "cab", hotels = "hotels";
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
        return sharedPreferences.getString(price, "");
    }
    public String getTripDiscription() {
        return sharedPreferences.getString(tripDiscription, "");
    }
    public String getCab() {
        return sharedPreferences.getString(cab, "");
    }public String getHotels() {
        return sharedPreferences.getString(hotels, "");
    }

    public String getUsername() {
        return sharedPreferences.getString(Username, "");
    }public String getAddress() {
        return sharedPreferences.getString(address, "");
    }public String getDob() {
        return sharedPreferences.getString(dob, "");
    }public String getGender() {
        return sharedPreferences.getString(gender, "");
    }public String getPhone() {
        return sharedPreferences.getString(phone, "");
    }public String getMail() {
        return sharedPreferences.getString(mail, "");
    }public String getRoleId() {
        return sharedPreferences.getString(roleId, "");
    }




    public void setTripName(String tripName) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.TripName, tripName);
        edit.commit();
    }
    public void setLocation(String location) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.Location, location);
        edit.commit();
    }
    public void setPrice(String Price) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.price + "Rs.", Price);
        edit.commit();
    }public void setTripDiscription(String tripDiscription) {
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


    public void setUsername(String username) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.Username, username);
        edit.commit();
    } public void setAddress(String address) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.address, address);
        edit.commit();
    }public void setDob(String dob) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.dob, dob);
        edit.commit();
    }public void setGender(String gender) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.gender, gender);
        edit.commit();
    }public void setPhone(String phone) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.phone, phone);
        edit.commit();
    }public void setMail(String mail) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.mail, mail);
        edit.commit();
    }public void setRoleId(String roleId) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.roleId, roleId);
        edit.commit();
    }
}
