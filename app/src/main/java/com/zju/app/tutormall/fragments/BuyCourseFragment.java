package com.zju.app.tutormall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zju.app.tutormall.MainActivity;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.adapters.CourseRecyclerViewAdapter;
import com.zju.app.tutormall.adapters.RecyclerViewDivider;


public class BuyCourseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_buy_course, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new CourseRecyclerViewAdapter(getActivity(), R.layout.course_card_item));
//        rv.addItemDecoration(new RecyclerViewDivider(getActivity(),
//                LinearLayoutManager.HORIZONTAL,
//                10,
//                getResources().getColor(R.color.mistyrose)));
    }

}
