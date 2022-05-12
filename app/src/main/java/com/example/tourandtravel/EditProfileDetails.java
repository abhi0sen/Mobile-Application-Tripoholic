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

public class EditProfileDetails extends AppCompatActivity {
    private SharedPrefrencesHelper sharedPrefrencesHelper;
    Spinner gen;
    EditText uname, birth, Addres, phon, eMail, paswd, cpaswd;
    Button confirm;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_details);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
        uname = findViewById(R.id.uname);
        birth = findViewById(R.id.birth);
        gen = findViewById(R.id.gen);
        Addres = findViewById(R.id.Addres);
        phon = findViewById(R.id.phon);
        eMail = findViewById(R.id.eMail);
//        paswd = findViewById(R.id.paswd);
//        cpaswd = findViewById(R.id.cpaswd);
        confirm = findViewById(R.id.confirm);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Gender");
        arrayList.add("Male");
        arrayList.add("Female");
        arrayList.add("Prefer not to say");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gen.setAdapter(arrayAdapter);

        uname.setText(sharedPrefrencesHelper.getUsername());
        birth.setText(sharedPrefrencesHelper.getDob());
//        gen.setSelected(sharedPrefrencesHelper.getGender());
        Addres.setText(sharedPrefrencesHelper.getAddress());
        phon.setText(sharedPrefrencesHelper.getPhone());
        eMail.setText(sharedPrefrencesHelper.getMail());
//        .setText(sharedPrefrencesHelper.getEmail());


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAction();
            }
        });
    }
        private void registerAction(){
            final String uname1 = uname.getText().toString();
            final String dob1 = birth.getText().toString();
            final String location = Addres.getText().toString();
            final String contact = phon.getText().toString();
            final String mail = eMail.getText().toString();
//            final String pswd = password.getText().toString();
            final String genSpin = gen.getSelectedItem().toString();
//            final String Cpswd = Cpassword.getText().toString();
            if (uname1.isEmpty()) {
                uname.setError("First name is required");
                uname.requestFocus();
                return;
            }
            if (dob1.isEmpty()) {
                birth.setError("Last name is required");
                birth.requestFocus();
                return;
            }
            if (mail.isEmpty()) {
                eMail.setError("Email is required");
                eMail.requestFocus();
                return;
            }

            if (contact.isEmpty()) {
                phon.setError("Contact no is required");
                phon.requestFocus();
                return;
            }


            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "editDetail.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    rQueue.getCache().clear();

                    Log.e("Register Before TRY",response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optString("success").equals("1")) {
                            Toast.makeText(EditProfileDetails.this, "Registered Successfully! Now Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), Account.class));
                            finish();
                        } else {
                            Toast.makeText(EditProfileDetails.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EditProfileDetails.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", uname1);
                    params.put("dob", dob1);
                    params.put("gender", genSpin);
                    params.put("address", location);
                    params.put("email", mail);
                    params.put("contact", contact);
                    return params;
                }
            };
            rQueue = Volley.newRequestQueue(EditProfileDetails.this);
            rQueue.add(stringRequest);
        }

    }



