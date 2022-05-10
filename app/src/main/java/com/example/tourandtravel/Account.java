package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Account extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        TextView msgTV;
        msgTV = findViewById(R.id.msgTV);
        SharedPreferences sh = getSharedPreferences("details", MODE_PRIVATE);
        String editVal = sh.getString("name", "Sign In");
        msgTV.setText(editVal);
    }
    public void SignIn(View view) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}