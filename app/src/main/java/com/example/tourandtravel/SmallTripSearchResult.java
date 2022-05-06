package com.example.tourandtravel;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SmallTripSearchResult extends AppCompatActivity {
    ListView listView;
    String[] arr = {"This is", "Item1", "item3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_trip_search_result);
        listView = findViewById(R.id.listview1);
//        using built in arrray adapter
//        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
//        listView.setAdapter(ad);

        // using custom Adapter
        SearchLayout ada = new SearchLayout(this, R.layout.activity_search_layout, arr);
        listView.setAdapter(ada);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SmallTripSearchResult.this, "You Clicked on position "+i, Toast.LENGTH_SHORT).show();
            }
        });
    }
}