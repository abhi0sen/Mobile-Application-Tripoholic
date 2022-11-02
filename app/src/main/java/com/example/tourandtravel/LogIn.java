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
public class LogIn extends AppCompatActivity {
    TextView createAcc, msgTV;
    EditText user, pass;
    Button SignIn;
    private RequestQueue rQueue;
    private SharedPrefrencesHelper sharedPrefrencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        createAcc = findViewById(R.id.createAcc);
//        msgTV= findViewById(R.id.msgTV);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        SignIn = findViewById(R.id.SignIn);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UserAppRegistration.class));
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAction();

            }
        });
    }
    private void loginAction() {
        final String userr = user.getText().toString();
        final String pswd = pass.getText().toString();
        if (userr.isEmpty()) {
            user.setError("Username or Email is required");
            user.requestFocus();
            return;
        }
        if (pswd.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
//        Toast.makeText(LogIn.this, "before STRING REQ! ", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "login.php",
                response -> {
                    Log.d(TAG, "loginAction: Logged In");
                    rQueue.getCache().clear();
//                    Toast.makeText(LogIn.this, "get CACHE! ", Toast.LENGTH_SHORT).show();
                    Log.e("anyText",response);
                    try {
//                        Toast.makeText(LogIn.this, "in TRY! ", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optString("success").equals("1")) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("details");
                            SharedPreferences sp = getSharedPreferences("details", MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("name", user.getText().toString());
                            ed.apply();
                            sharedPrefrencesHelper.setAddress(jsonObject1.getString("address"));
                            sharedPrefrencesHelper.setDob(jsonObject1.getString("dob"));
                            sharedPrefrencesHelper.setUsername(jsonObject1.getString("username"));
                            sharedPrefrencesHelper.setMail(jsonObject1.getString("mail"));
                            sharedPrefrencesHelper.setPhone(jsonObject1.getString("phone"));
                            sharedPrefrencesHelper.setGender(jsonObject1.getString("gender"));
                            sharedPrefrencesHelper.setRoleId(jsonObject1.getString("roleId"));

                            Toast.makeText(LogIn.this, "Login Successfully! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LogIn.this, jsonObject.getString("Incorrect Username or Password"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogIn.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", userr);
                params.put("password", pswd);
                return params;
            }

        };
        //String sR= stringRequest.toString();
//        String sR= userr;
//
//        msgTV.setText(sR);
        rQueue = Volley.newRequestQueue(LogIn.this);
        rQueue.add(stringRequest);
    }

}

