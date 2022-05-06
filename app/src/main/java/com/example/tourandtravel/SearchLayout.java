package com.example.tourandtravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchLayout extends ArrayAdapter<String> {
private String[] arr;
private Context context;

        public SearchLayout(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource, arr);
        this.context = context;
        this.arr = arr;
        }

@Nullable
@Override
public String getItem(int position) {
        return arr[position];
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_search_layout, parent, false);
        TextView t =  convertView.findViewById(R.id.textView);
        t.setText(getItem(position));
        convertView.setOnClickListener(view -> Toast.makeText(context, "You Clicked on "+position, Toast.LENGTH_SHORT).show());
        return convertView;
        }
}
