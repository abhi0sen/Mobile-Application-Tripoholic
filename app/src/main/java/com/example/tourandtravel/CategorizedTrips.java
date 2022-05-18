package com.example.tourandtravel;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class CategorizedTrips extends AppCompatActivity {
    ListView listView;
    TextView createAcc, TRIP, Place, PRICE;
    public static final String EXTRA_MESSAGE = "message";
    String Category;
    private RequestQueue rQueue;
    String[] arr;
    int j = 0;
    private SharedPrefrencesHelper sharedPrefrencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_trips);
        Place = findViewById(R.id.place);
        Intent intent = getIntent();
        Category = intent.getStringExtra(EXTRA_MESSAGE);

        Place.setText(Category);


        Log.d(TAG, "onCreate: 0" + Category);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);

                CategoryAction(Category);
                listView = findViewById(R.id.listView);
    }

    private void CategoryAction(String Categry) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "category.php",
                response -> {
                    rQueue.getCache().clear();
                    Toast.makeText(CategorizedTrips.this, "Searching... ", Toast.LENGTH_SHORT).show();
                    Log.e("anyText", response);
                    String data = "";
                    String img = "";
                    String Locat = "";
                    String pric = "";
                    TRIP = findViewById(R.id.Tri);
//                    PRICE = findViewById(R.id.PRICE);
//                    DESC = findViewById(R.id.DESC);
                    try {
//                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonRootObject = new JSONObject(response);
                        JSONArray jsonArray = jsonRootObject.optJSONArray("details");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            int id = 0;
                            String name = "", Location = "";

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject.optString("placeType").equals(Categry)){
//                                id = Integer.parseInt(jsonObject.optString("tripId").toString());
                                name = jsonObject.optString("tripName");
                                Location = jsonObject.optString("Location");
                                pric = jsonObject.optString("Price");
//                                ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
//                                arrayList.add(data);
//                                arr = arrayList.toArray(arr);
//                                    arr[j] = name;
//                                    j++;
                                data += "Name = " + name +"\n Estimated Cost - "+pric+"\n Location - "+Location+"\n\n\n";
//                                pric += "Estimated Cost - " + pric;
//                                Locat += "Location - " + Location;
                            }

//                            int id = Integer.parseInt(jsonObject.optString("tripId").toString());
//                            String name = jsonObject.optString("TripName");
//                            String Location = jsonObject.optString("Location");
//                            String Image = jsonObject.optString("image");

//                            Locat += "Location - "+Location;
                        }
                        TRIP.setText(data);
//                        DESC.setText(Locat);
//                        PRICE.setText(pric);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CategorizedTrips.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        rQueue = Volley.newRequestQueue(CategorizedTrips.this);
        rQueue.add(stringRequest);

//        SearchLayout ad = new SearchLayout(this, R.layout.activity_search_layout, arr);
//        Category.setAdapter(ad);
//        SearchLayout ad = new SearchLayout(this, R.layout.activity_search_layout, arr);
//        listView.setAdapter(ad);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(CategorizedTrips.this, "You Clicked on position "+i, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
