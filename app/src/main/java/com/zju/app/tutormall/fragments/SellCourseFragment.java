package com.zju.app.tutormall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zju.app.tutormall.R;
import com.zju.app.tutormall.adapters.SellCourseListAdapter;
import com.zju.app.tutormall.application.TutorApplication;



public class SellCourseFragment extends Fragment {

    private TutorApplication app;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (TutorApplication) getActivity().getApplication();
        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_sell_course, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new SellCourseListAdapter(getActivity(), app.teachCourseInfoList));
    }
}
