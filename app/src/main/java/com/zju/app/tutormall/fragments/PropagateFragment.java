package com.zju.app.tutormall.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.adapters.CourseRecyclerViewAdapter;
import com.zju.app.tutormall.adapters.RecyclerViewDivider;

import java.util.ArrayList;


public class PropagateFragment extends Fragment {

    private int maxSlider = 4;


    public PropagateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propagate, container, false);

        /**
         * Setting up sliders
         * */
        SliderLayout sliderShow = (SliderLayout) view.findViewById(R.id.propagate_slider);
        ArrayList<TextSliderView> sliders = new ArrayList<TextSliderView>();
        int[] images = {R.drawable.bigbang, R.drawable.game_of_thrones, R.drawable.hannibal, R.drawable.house};
        String[] descriptions = {"Bigbang", "Game of Thrones", "Hannibal", "House of Cards"};
        for (int i=0; i<maxSlider; i++) {
            TextSliderView slide = new TextSliderView(getActivity());
            slide.description(descriptions[i]).image(images[i]);
            sliders.add(slide);
            sliderShow.addSlider(slide);
        }
        sliderShow.setPresetTransformer("CubeIn");

        /**
         * Setting up recycler list
         * */
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.propagate_course_list);
        setupRecyclerView(rv);


        return view;
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new CourseRecyclerViewAdapter(getActivity(), R.layout.course_list_item));

        // divider between items
        rv.addItemDecoration(new RecyclerViewDivider(getActivity(),
                LinearLayoutManager.VERTICAL,
                20,
                getResources().getColor(R.color.mistyrose)));
    }

}
