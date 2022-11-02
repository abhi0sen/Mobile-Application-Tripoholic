package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class LiveRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_registration);
        Spinner spinner = findViewById(R.id.genderSpinner);
        spinner_item(" ", "Male", "Female", "Others", spinner);
    }

    public void spinner_item(String gen, String gen_male, String gen_female, String gen_others, Spinner spinner_1){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(gen);
        arrayList.add(gen_male);
        arrayList.add(gen_female);
        arrayList.add(gen_others);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(arrayAdapter);
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