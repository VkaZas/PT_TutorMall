package com.zju.app.tutormall.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.zju.app.tutormall.LoginActivity;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.application.TutorApplication;

import java.util.HashMap;
import java.util.Map;

public class MyInfoFragment extends Fragment {

    private TutorApplication app;
    private Context context;
    private RequestQueue requestQueue;
    EditText etUserInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        app = (TutorApplication) getActivity().getApplication();
        requestQueue = Volley.newRequestQueue(getActivity());
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        TextView tvUserName = (TextView) view.findViewById(R.id.user_name);
        TextView tvUserNameForm = (TextView) view.findViewById(R.id.user_name_form);
        TextView tvEmail = (TextView) view.findViewById(R.id.user_email);

        etUserInfo = (EditText) view.findViewById(R.id.user_payid);
        etUserInfo.addTextChangedListener(textWatcher);

        Button btnLogout = (Button) view.findViewById(R.id.logout_btn);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = app.getServerAddress() + "logout/";
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                                app.setAccessToken("");
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("uuid", app.getAccessToken());
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        tvUserName.setText(app.getUserName());
        tvUserNameForm.setText(app.getUserName());
        tvEmail.setText(app.getEmail());
        return view;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String url = app.getServerAddress() + "modify_user_info/";
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Log.d("VKAZAS-modifyUinfo", s);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("uuid", app.getAccessToken());
                    map.put("user_info", etUserInfo.getText().toString());
                    return map;
                }
            };
            requestQueue.add(stringRequest);
        }
    };
}

