package com.zju.app.tutormall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.adapters.BuyOrderListAdapter;
import com.zju.app.tutormall.application.TutorApplication;
import com.zju.app.tutormall.beans.OrderInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BuyCourseFragment extends Fragment {

    private Context context;
    private TutorApplication app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        app = (TutorApplication) getActivity().getApplication();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_buy_course, container, false);
        setupRecyclerView(rv);
        return rv;
    }


    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new BuyOrderListAdapter(getActivity(), app.stuOrderList));
//        rv.addItemDecoration(new RecyclerViewDivider(getActivity(),
//                LinearLayoutManager.HORIZONTAL,
//                10,
//                getResources().getColor(R.color.mistyrose)));
    }

}
