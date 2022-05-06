package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_more);
    }
    public void RegistrationForm(View view) {
        Intent intent = new Intent(this, LiveRegistration.class);
        startActivity(intent);
    }
}