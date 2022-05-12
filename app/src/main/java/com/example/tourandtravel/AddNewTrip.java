package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddNewTrip extends AppCompatActivity {
    Button upload;
    TextView login;
    Spinner cab, hotel, category;
    EditText tripname, location, tourDesc, price, image, feature;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);


        category = findViewById(R.id.category);
        tripname = findViewById(R.id.modifyName);
        location = findViewById(R.id.ModifyLocation);
        tourDesc = findViewById(R.id.ModifyTour);
        price = findViewById(R.id.modifyPrice);
        cab = findViewById(R.id.cab);
        hotel = findViewById(R.id.hotel);
//        image = findViewById(R.id.image);
        upload = findViewById(R.id.ModifyUpload);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("-Select-");
        arrayList.add("Yes");
        arrayList.add("No");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cab.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hotel.setAdapter(arrayAdapter1);

//        Categories
        ArrayList<String> cat = new ArrayList<>();
        arrayList.add("-Select-");
        arrayList.add("Hilly Areas");
        arrayList.add("Water bodies");
        arrayList.add("Desert Areas");
        arrayList.add("Holy Places");

        ArrayAdapter<String> CateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        CateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(CateAdapter);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), LogIn.class));
//            }
//        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TourAdded();
            }
        });
    }
    private void TourAdded(){
        final String tname = tripname.getText().toString();
        final String location1 = location.getText().toString();
        final String tourDescr = tourDesc.getText().toString();
        final String CAB = cab.getSelectedItem().toString();
        final String CateGory = category.getSelectedItem().toString();
        final String pricePer = price.getText().toString();
        final String HOTEL = hotel.getSelectedItem().toString();
//        final String img = image.getText().toString();
        if (tname.isEmpty()) {
            tripname.setError("First name is required");
            tripname.requestFocus();
            return;
        }
        if (location1.isEmpty()) {
            location.setError("Last name is required");
            location.requestFocus();
            return;
        }
        if (tourDescr.isEmpty()) {
            tourDesc.setError("Email is required");
            tourDesc.requestFocus();
            return;
        }
        if (pricePer.isEmpty()) {
            price.setError("Password is required");
            price.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "tripAdd.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                rQueue.getCache().clear();

                Log.e("Register Before TRY",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("success").equals("1")) {
                        Toast.makeText(AddNewTrip.this, "Registered Successfully! Now Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), LogIn.class));
                        finish();
                    } else {
                        Toast.makeText(AddNewTrip.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNewTrip.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tripname", tname);
                params.put("location", location1);
                params.put("tripDesc", tourDescr);
                params.put("category", CateGory);
                params.put("cab", CAB);
                params.put("hotel", HOTEL);
                params.put("price", pricePer);
//                params.put("image", img);
                return params;
            }
        };
        rQueue = Volley.newRequestQueue(AddNewTrip.this);
        rQueue.add(stringRequest);
    }

    public void tripAdded(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
