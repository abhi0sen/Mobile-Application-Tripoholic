package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SmallTrips extends AppCompatActivity {
    TextView LocationText;
    EditText UserLocation;
    Button LocationSearch;
    TextView SearchMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_trips);
    }

    public void SearchText(View view) {
//        LocationText = findViewById(R.id.LocationText);
        UserLocation = findViewById(R.id.UserLocation);

        String messageText = UserLocation.getText().toString();
        Intent intent = new Intent(this, SmallTripSearchResult.class);
        intent.putExtra(SmallTripSearchResult.EXTRA_MESSAGE, messageText);
        startActivity(intent);
    }
}