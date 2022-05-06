package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LiveRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_registration);
    }

    public void RegistrationForm(View view) {
        Intent intent = new Intent(this, PaymentGatewayLiveRegistration2.class);
        startActivity(intent);
    }

    public void CancelRegister(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}