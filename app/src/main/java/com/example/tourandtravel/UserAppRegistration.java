package com.example.tourandtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UserAppRegistration extends AppCompatActivity {
    Button finish;
    TextView login;
    EditText username, age, gender, address1, contact, email, password, Cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_app_registration);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address1 = findViewById(R.id.address1);
        contact = findViewById(R.id.contact);
        Cpassword = findViewById(R.id.cPassword);
//        username = findViewById(R.id.gender);
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
            final String age1 = age.getText().toString();
            final String mail = email.getText().toString();
            final String pswd = password.getText().toString();
            final String Cpswd = Cpassword.getText().toString();
            if (uname.isEmpty()) {
                username.setError("First name is required");
                username.requestFocus();
                return;
            }
            if (age1.isEmpty()) {
                age.setError("Last name is required");
                age.requestFocus();
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
            if (!pswd.equals(Cpswd)) {
                Cpassword.setError("Password mismatch");
                Cpassword.requestFocus();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "register.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            RequestQueue rQueue = null;
                            
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
                    params.put("age", age1);
                    params.put("email", mail);
//                    params.put("email", mail);
                    params.put("password", pswd);
                    params.put("Cpassword", Cpswd);
                    return params;
                }
            };
            RequestQueue rQueue = Volley.newRequestQueue(UserAppRegistration.this);
            rQueue.add(stringRequest);
        }

    }
