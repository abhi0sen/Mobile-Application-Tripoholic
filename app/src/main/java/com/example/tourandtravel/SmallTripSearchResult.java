package com.example.tourandtravel;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallTripSearchResult extends AppCompatActivity {
    private SharedPrefrencesHelper sharedPrefrencesHelper;
    public static final String EXTRA_MESSAGE = "message";
    String messageText;
    TextView Location, Tripname, TripDesc;
    List<tripList> tripLists;
    String[] arr = {"This is", "Item1", "item3"};
    private RequestQueue rQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_trip_search_result);
        tripLists = new ArrayList<>();
        Intent intent = getIntent();
        messageText = intent.getStringExtra(EXTRA_MESSAGE);
        Log.d(TAG, "onCreate: " + EXTRA_MESSAGE);
//        Toast.makeText(SmallTripSearchResult.this, EXTRA_MESSAGE, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onCreate: " + messageText);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
        Location = findViewById(R.id.Location);
        Tripname = findViewById(R.id.Tripname);
        TripDesc = findViewById(R.id.TripDesc);
        loginAction(messageText);

    }


    private void loginAction(String messageText) {
//        Toast.makeText(SmallTripSearchResult.this, "hiiiiiii it is out", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "category.php",
                response -> {
                    rQueue.getCache().clear();
                    Log.e("anyText", response);
                    String tnam = "", des = "", Loc = "";

                    try {
//                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonRootObject = new JSONObject(response);
                        JSONArray jsonArray = jsonRootObject.optJSONArray("details");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            int id = 0;
                            String name = "", Location = "", Description = "";

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject.optString("Location").equals(messageText)) {
//                                id = Integer.parseInt(jsonObject.optString("tripId").toString());
                                name = jsonObject.optString("tripName");
                                Location = jsonObject.optString("Price");
                                Description = jsonObject.optString("tripDiscription");

                                tnam += "Name = " + name+"\nDescription - " + Description+"\nPrice - " + Location+"\n\n\n";
//                                des += "Description - " + Description;
//                                Loc += "Location - " + Location;
                            }
                        }
                        Location.setText(tnam);
//                        TripDesc.setText(des);
//                        Location.setText(Loc);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SmallTripSearchResult.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        rQueue = Volley.newRequestQueue(SmallTripSearchResult.this);
        rQueue.add(stringRequest);
    }
}
