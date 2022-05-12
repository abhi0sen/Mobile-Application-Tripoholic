package com.example.tourandtravel;

import java.util.*;
class tripList {
    String hotels, cab, tripDiscription;
    int id;
    String name,author;
//    int quantity;
//    double rating, price, image;
    String rating, price, image;


//    public tripList(int id, String title, String shortdesc, double rating, double price, String image) {
//        this.id = id;
//        this.name = title;
//        this.author = shortdesc;
//        this.rating = rating;
//        this.price = price;
//    }

    public tripList(int tripId, String tripName, String location, String price, String tripDiscription, String cab, String hotels) {
        this.id = tripId;
        this.name = tripName;
        this.author = location;
        this.price = price;
        this.tripDiscription = tripDiscription;
        this.cab = cab;
        this.hotels = hotels;
    }


    public String getHotels() {
        return hotels;
    }

    public String getCab() {
        return cab;
    }

    public String getTripDiscription() {
        return tripDiscription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}

