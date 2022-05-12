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

public class AddAdmin extends AppCompatActivity {
    Button finish;
    TextView login;
    Spinner gender;
    EditText AdminName, DateOB, addr, pho, emailAdd, pswd, cpswd;
    private RequestQueue rQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        gender = findViewById(R.id.Genderspinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Gender");
        arrayList.add("Male");
        arrayList.add("Female");
        arrayList.add("Prefer not to say");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(arrayAdapter);
//        gender.setText(getAdapter(arrayAdapter));
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//              @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                  gender.setText("Gender");
//                  String tutorialsName = parent.getItemAtPosition(position).toString();
//                  Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
//          }
//            @Override
//            public void onNothingSelected(AdapterView <?> parent) {
//            }
//        });


        AdminName = findViewById(R.id.AdminName);
        DateOB = findViewById(R.id.DateOB);
        addr = findViewById(R.id.addr);
        pho = findViewById(R.id.pho);
        emailAdd = findViewById(R.id.emailAdd);
        pswd = findViewById(R.id.pswd);
        cpswd = findViewById(R.id.cpswd);
        finish = findViewById(R.id.Add);


//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), LogIn.class));
//            }
//        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAction();
            }
        });
    }
    private void registerAction(){
        final String Adminname = AdminName.getText().toString();
        final String DateOB1 = DateOB.getText().toString();
        final String phone = pho.getText().toString();
        final String location = addr.getText().toString();
        final String email = emailAdd.getText().toString();
        final String pswrd = pswd.getText().toString();
        final String genSpin = gender.getSelectedItem().toString();
        final String Cpswrd = cpswd.getText().toString();
        if (Adminname.isEmpty()) {
            AdminName.setError("First name is required");
            AdminName.requestFocus();
            return;
        }
        if (DateOB1.isEmpty()) {
            DateOB.setError("Last name is required");
            DateOB.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailAdd.setError("Email is required");
            emailAdd.requestFocus();
            return;
        }
        if (pswrd.isEmpty()) {
            pswd.setError("Password is required");
            pswd.requestFocus();
            return;
        }
        if (Cpswrd.isEmpty()) {
            cpswd.setError("Confirm Your Password");
            cpswd.requestFocus();
            return;
        }
//        if (genSpin.isEmpty()) {
//            gender.setError("Password is required");
//            password.requestFocus();
//            return;
//        }
        if (phone.isEmpty()) {
            pho.setError("Contact no is required");
            pho.requestFocus();
            return;
        }
//        if (!pswd.equals(Cpswrd)) {
//            Cpswrd.setError("Password mismatch");
//            Cpswrd.requestFocus();
//            return;
//        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "adminRegister.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                rQueue.getCache().clear();

                Log.e("Register Before TRY",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("success").equals("1")) {
                        Toast.makeText(AddAdmin.this, "Registered Successfully! Now Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), LogIn.class));
                        finish();
                    } else {
                        Toast.makeText(AddAdmin.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddAdmin.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Adminname", Adminname);
                params.put("dob", DateOB1);
                params.put("gender", genSpin);
                params.put("address", location);
                params.put("email", email);
                params.put("contact", phone);
                params.put("password", pswrd);
                params.put("Cpassword", Cpswrd);
                return params;
            }
        };
        rQueue = Volley.newRequestQueue(AddAdmin.this);
        rQueue.add(stringRequest);
    }

    public void reLogIn(View view){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}