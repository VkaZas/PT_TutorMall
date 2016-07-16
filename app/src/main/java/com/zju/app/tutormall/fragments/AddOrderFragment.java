package com.zju.app.tutormall.fragments;


import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.application.TutorApplication;

import java.util.HashMap;
import java.util.Map;

public class AddOrderFragment extends DialogFragment {
    private TutorApplication app;
    private RequestQueue requestQueue;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);
        context = getActivity();
        app = (TutorApplication) getActivity().getApplication();
        requestQueue = Volley.newRequestQueue(getActivity());
        context = getActivity();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        final EditText etOrderInfo = (EditText) view.findViewById(R.id.add_order_info);
        ImageView ivOrderConfirm = (ImageView) view.findViewById(R.id.btn_confirm_add_order);
        ivOrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Bundle bundle = getArguments();

                String url = app.getServerAddress() + "create_order/";
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Log.d("VKAZAS-addOrder", s);
                                AddOrderListener listener = (AddOrderListener) getActivity();
                                listener.onAddOrderComplete();
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
                        map.put("course_id", String.valueOf(bundle.getInt("course_id")));
                        map.put("order_info", etOrderInfo.getText().toString());
                        map.put("uuid", app.getAccessToken());
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });


        return view;
    }

    public interface AddOrderListener {
        void onAddOrderComplete();
    }

}
