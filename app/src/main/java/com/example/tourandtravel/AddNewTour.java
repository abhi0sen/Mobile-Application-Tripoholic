package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNewTour extends AppCompatActivity {

    Button done, image;
//    TextView login;
//    Spinner gender;
    EditText tourname, duration, seatLimit, tourDesc, tourDepart, price;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tour);
        tourname = findViewById(R.id.tourName);
        duration = findViewById(R.id.duration);
        seatLimit = findViewById(R.id.seatLimit);
        tourDesc = findViewById(R.id.tourDesc);
        tourDepart = findViewById(R.id.tourDepart);
        price = findViewById(R.id.price);
//        feature = findViewById(R.id.feature);
//        image = findViewById(R.id.image);
        done = findViewById(R.id.doneIt);


//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), LogIn.class));
//            }
//        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TourAdded();
            }
        });
    }
    private void TourAdded(){
        final String tname = tourname.getText().toString();
        final String duration1 = duration.getText().toString();
        final String tourDescr = tourDesc.getText().toString();
        final String seatLimit1 = seatLimit.getText().toString();
        final String pricePer = price.getText().toString();
        final String tourDeparture = tourDepart.getText().toString();
//        final String features = feature.getText().toString();
//        final String img = image.getText().toString();
        if (tname.isEmpty()) {
            tourname.setError("First name is required");
            tourname.requestFocus();
            return;
        }
        if (duration1.isEmpty()) {
            duration.setError("Last name is required");
            duration.requestFocus();
            return;
        }
        if (tourDeparture.isEmpty()) {
            tourDepart.setError("Email is required");
            tourDepart.requestFocus();
            return;
        }
        if (pricePer.isEmpty()) {
            price.setError("Password is required");
            price.requestFocus();
            return;
        }

        if (tourDescr.isEmpty()) {
            tourDesc.setError("Contact no is required");
            tourDesc.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "tourAdd.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                rQueue.getCache().clear();

                Log.e("Register Before TRY",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("success").equals("1")) {
                        Toast.makeText(AddNewTour.this, "Registered Successfully! Now Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), Account.class));
                        finish();
                    } else {
                        Toast.makeText(AddNewTour.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNewTour.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tourname", tname);
                params.put("duration", duration1);
                params.put("tourDesc", tourDescr);
                params.put("seatLimit", seatLimit1);
                params.put("tourDepart", tourDeparture);
                params.put("price", pricePer);
//                params.put("image", img);
//                params.put("features", features);
                return params;
            }
        };
        rQueue = Volley.newRequestQueue(AddNewTour.this);
        rQueue.add(stringRequest);
    }

//    public void tourAdded(View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }

}