package com.example.tourandtravel;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;
public class CategorizedTrips extends AppCompatActivity {
//    ListView Category;
    TextView createAcc, TRIP, DESC, PRICE;
//    EditText user, pass;
//    String[] arr = {"This is", "Item1", "item3"};
//    Button SignIn;
    private RequestQueue rQueue;
    private SharedPrefrencesHelper sharedPrefrencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_trips);
//        Category = findViewById(R.id.Category);
//        msgTV= findViewById(R.id.msgTV);
//        user = findViewById(R.id.user);
//        pass = findViewById(R.id.pass);
//        SignIn = findViewById(R.id.SignIn);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
//        createAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), CategorizedTrips.class));
//            }
//        });
//        SignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                CategoryAction();
    }
    private void CategoryAction() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "category.php",
                response -> {
                    rQueue.getCache().clear();
                    Toast.makeText(CategorizedTrips.this, "get CACHE! ", Toast.LENGTH_SHORT).show();
                    Log.e("anyText",response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optString("success").equals("1")) {
                        Toast.makeText(CategorizedTrips.this, "in Process! ", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject1 = jsonObject.getJSONObject("details");

                            TRIP = findViewById(R.id.TRIP);
                            PRICE = findViewById(R.id.PRICE);
                            DESC = findViewById(R.id.DESC);

                            sharedPrefrencesHelper.setTripName(jsonObject1.getString("TripName"));
                            sharedPrefrencesHelper.setLocation(jsonObject1.getString("Location"));
                            sharedPrefrencesHelper.setTripDiscription(jsonObject1.getString("tripDiscription"));
                            sharedPrefrencesHelper.setCab(jsonObject1.getString("cab"));
                            sharedPrefrencesHelper.setPrice(jsonObject1.getString("Price"));
//                            Toast.makeText(CategorizedTrips.this, "hiiiiiii", Toast.LENGTH_SHORT).show();
                            sharedPrefrencesHelper.setHotels(jsonObject1.getString("hotels"));

                            TRIP.setText(sharedPrefrencesHelper.getTripName());
                            PRICE.setText(sharedPrefrencesHelper.getLocation());
                            DESC.setText(sharedPrefrencesHelper.getTripDiscription());

                            Toast.makeText(CategorizedTrips.this, "Login Successfully! ", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getBaseContext(), MainActivity.class));
//                            finish();
                        } else {
                            Toast.makeText(CategorizedTrips.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CategorizedTrips.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {


        };
//
//        msgTV.setText(sR);
        rQueue = Volley.newRequestQueue(CategorizedTrips.this);
        rQueue.add(stringRequest);

//        SearchLayout ad = new SearchLayout(this, R.layout.activity_search_layout, arr);
//        Category.setAdapter(ad);
    }
}

//    public void CreateAcco(View view) {
//        Intent intent = new Intent(this, UserAppRegistration.class);
//        startActivity(intent);
//    }