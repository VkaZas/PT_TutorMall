package com.zju.app.tutormall;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.application.TutorApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Context context = this;
    private TutorApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etEmail = (EditText) findViewById(R.id.user_email);
        final EditText etUname = (EditText) findViewById(R.id.user_name);
        final EditText etUpass = (EditText) findViewById(R.id.user_password);
        Button btnReg = (Button) findViewById(R.id.register_btn);
        TextView tvGoLogin = (TextView) findViewById(R.id.go_login);

        requestQueue = Volley.newRequestQueue(this);
        app = (TutorApplication) getApplication();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = app.getServerAddress() + "register/";
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    Log.d("VKAZAS", s);
                                    JSONObject jObj = new JSONObject(s);
                                    Boolean success = jObj.getBoolean("success");
                                    if (success) {
                                        app.setAccessToken(jObj.getString("token"));
                                        if (jObj.has("user_name")) app.setUserName(jObj.getString("user_name"));
                                        if (jObj.has("user_email")) app.setEmail(jObj.getString("user_email"));
                                        startActivity(new Intent(context, MainActivity.class));
                                    } else {
                                        View rootLayout = findViewById(R.id.register_root);
                                        Snackbar.make(rootLayout, jObj.getString("message"), Snackbar.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("VKAZAS", volleyError.toString());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("email", etEmail.getText().toString());
                        map.put("username", etUname.getText().toString());
                        map.put("userpwd", etUpass.getText().toString());
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
