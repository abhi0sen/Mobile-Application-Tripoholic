package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

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
}