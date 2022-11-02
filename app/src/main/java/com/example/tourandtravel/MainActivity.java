package com.example.tourandtravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView tourImg;
//    Button searc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        searc = findViewById(R.id.searc);

//        searc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), SearchTrip.class));
//            }
//        });

        tourImg = findViewById(R.id.tourImg);
    }

    public void Register(View view) {
        Intent intent = new Intent(this, LiveRegistration.class);
        startActivity(intent);
    }

    public void SmallTrips(View view) {
        Intent intent = new Intent(this, SmallTrips.class);
        startActivity(intent);
    }

    public void CategorizedHillyTrips(View view) {
        Intent intent = new Intent(this, CategorizedTrips.class);
        intent.putExtra(CategorizedTrips.EXTRA_MESSAGE, "Hilly Areas");
        startActivity(intent);
    }public void CategorizedWateryTrips(View view) {
        Intent intent = new Intent(this, CategorizedTrips.class);
        intent.putExtra(CategorizedTrips.EXTRA_MESSAGE, "Water Bodies");
        startActivity(intent);
    }public void CategorizedHolyTrips(View view) {
        Intent intent = new Intent(this, CategorizedTrips.class);
        intent.putExtra(CategorizedTrips.EXTRA_MESSAGE, "Holy Places");
        startActivity(intent);
    }public void CategorizedDesertTrips(View view) {
        Intent intent = new Intent(this, CategorizedTrips.class);
        intent.putExtra(CategorizedTrips.EXTRA_MESSAGE, "Desert Areas");
        startActivity(intent);
    }

    public void TourDetail(View view) {
        Intent intent = new Intent(this, ViewMore.class);
        startActivity(intent);
    }

    public void Account(View view) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void SearchTrip(View view) {
        Intent intent = new Intent(this, SearchTrip.class);
        startActivity(intent);
    }

    public void NewTour(View view) {
        Intent intent = new Intent(this, AddNewTour.class);
        startActivity(intent);
    }

    public void NewTrip(View view) {
        Intent intent = new Intent(this, AddNewTrip.class);
        startActivity(intent);
    }

    public void ModifyTrip(View view) {
        Intent intent = new Intent(this, ModifyTrip.class);
        startActivity(intent);
    }

    public void adminOptions(View view) {
        CardView adminFeatures;
//        Button add;
        ScrollView dashboard;
//        add = findViewById(R.id.add);
        adminFeatures = findViewById(R.id.adminFeatures);
        dashboard = findViewById(R.id.dashboard);
        adminFeatures.setVisibility(View.VISIBLE);
        dashboard.setAlpha((float) 0.3);
    }

    public void backtrack(View view) {
        CardView adminFeatures;
//        Button add;
        ScrollView dashboard;
//        add = findViewById(R.id.add);
        adminFeatures = findViewById(R.id.adminFeatures);
        dashboard = findViewById(R.id.dashboard);
        adminFeatures.setVisibility(View.INVISIBLE);
        dashboard.setAlpha((float) 1);
    }
}