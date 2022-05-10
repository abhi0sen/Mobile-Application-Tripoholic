package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void Register(View view) {
        Intent intent = new Intent(this, LiveRegistration.class);
        startActivity(intent);
    }

    public void SmallTrips(View view) {
        Intent intent = new Intent(this, SmallTrips.class);
        startActivity(intent);
    }

    public void CategorizedTrips(View view) {
        Intent intent = new Intent(this, CategorizedTrips.class);
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