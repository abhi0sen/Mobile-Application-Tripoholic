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

public class UserAppRegistration extends AppCompatActivity {
    Button finish;
    TextView login;
    Spinner gender;
    EditText username, dob, address1, contact, email, password, Cpassword;
    private RequestQueue rQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_app_registration);

        gender = findViewById(R.id.gender);
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


        username = findViewById(R.id.username);
        dob = findViewById(R.id.birthDate);
        address1 = findViewById(R.id.address1);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Cpassword = findViewById(R.id.cPassword);
        finish = findViewById(R.id.Finish);


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
            final String uname = username.getText().toString();
            final String dob1 = dob.getText().toString();
            final String phone = contact.getText().toString();
            final String location = address1.getText().toString();
            final String mail = email.getText().toString();
            final String pswd = password.getText().toString();
            final String genSpin = gender.getSelectedItem().toString();
            final String Cpswd = Cpassword.getText().toString();
            if (uname.isEmpty()) {
                username.setError("First name is required");
                username.requestFocus();
                return;
            }
            if (dob1.isEmpty()) {
                dob.setError("Last name is required");
                dob.requestFocus();
                return;
            }
            if (mail.isEmpty()) {
                email.setError("Email is required");
                email.requestFocus();
                return;
            }
            if (pswd.isEmpty()) {
                password.setError("Password is required");
                password.requestFocus();
                return;
            }
            if (Cpswd.isEmpty()) {
                password.setError("Confirm Your Password");
                password.requestFocus();
                return;
            }
            if (genSpin.isEmpty()) {
                password.setError("Password is required");
                password.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                contact.setError("Contact no is required");
                contact.requestFocus();
                return;
            }
            if (!pswd.equals(Cpswd)) {
                Cpassword.setError("Password mismatch");
                Cpassword.requestFocus();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "register.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            rQueue.getCache().clear();

                            Log.e("Register Before TRY",response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optString("success").equals("1")) {
                                    Toast.makeText(UserAppRegistration.this, "Registered Successfully! Now Login", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), LogIn.class));
                                    finish();
                                } else {
                                    Toast.makeText(UserAppRegistration.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UserAppRegistration.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", uname);
                    params.put("dob", dob1);
                    params.put("gender", genSpin);
                    params.put("address", location);
                    params.put("email", mail);
                    params.put("contact", phone);
                    params.put("password", pswd);
                    params.put("Cpassword", Cpswd);
                    return params;
                }
            };
            rQueue = Volley.newRequestQueue(UserAppRegistration.this);
            rQueue.add(stringRequest);
        }

        public void reLogIn(View view){
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
    }
