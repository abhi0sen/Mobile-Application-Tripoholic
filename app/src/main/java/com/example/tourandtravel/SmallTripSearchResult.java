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
//    ListView listView;
    String messageText;
    TextView Location, Tripname, TripDesc;
    List<tripList> tripLists;
    String[] arr = {"This is", "Item1", "item3"};
    private RequestQueue rQueue;

//    JSONObject jsonObject1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_trip_search_result);
//        listView = findViewById(R.id.listView1);
        tripLists = new ArrayList<>();
        Intent intent = getIntent();
        messageText = intent.getStringExtra(EXTRA_MESSAGE);
        Log.d(TAG, "onCreate: "+EXTRA_MESSAGE);
        Toast.makeText(SmallTripSearchResult.this, EXTRA_MESSAGE, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onCreate: " + messageText);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
        Location = findViewById(R.id.Location);
        Tripname = findViewById(R.id.Tripname);
        TripDesc = findViewById(R.id.TripDesc);
        loginAction(messageText);

    }


    private void loginAction(String messageText) {
        Toast.makeText(SmallTripSearchResult.this, "hiiiiiii it is out", Toast.LENGTH_SHORT).show();
        if (messageText.equals(sharedPrefrencesHelper.getLocation())) {
            Toast.makeText(SmallTripSearchResult.this, "hiiiiiii it is in", Toast.LENGTH_SHORT).show();
            Tripname.setText(sharedPrefrencesHelper.getTripName());
            Location.setText(sharedPrefrencesHelper.getLocation());
            TripDesc.setText(sharedPrefrencesHelper.getTripDiscription());
        }

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.url) + "category.php",
//                response ->  {
//                    rQueue.getCache().clear();
//                    Toast.makeText(SmallTripSearchResult.this, "get CACHE! ", Toast.LENGTH_SHORT).show();
//                    Log.e("anyText",response);
//
//                        try {
//                            JSONArray array = new JSONArray(response);
//
//                            for (int i = 0; i < array.length(); i++) {
//
//                                //getting product object from json array
//                                JSONObject product = array.getJSONObject(i);
//
//                                //adding the product to product list
//                                tripLists.add(new tripList(
//                                        product.getInt("tripId"),
//                                        product.getString("TripName"),
//                                        product.getString("Location"),
//                                        product.getString("Price"),
//                                        product.getString("tripDiscription"),
//                                        product.getString("cab"),
//                                        product.getString("hotels")
//                                ));
//                            }
//
//                            SearchLayout ad = new SearchLayout(this, R.layout.activity_search_layout, arr);
//                            listView.setAdapter(ad);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
////                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
        //adding our stringrequest to queue
//        Volley.newRequestQueue(this).add(stringRequest);
//        rQueue = Volley.newRequestQueue(SmallTripSearchResult.this);
//        rQueue.add(stringRequest);
    }
}




