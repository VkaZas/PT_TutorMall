package com.zju.app.tutormall.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCourseFragment extends DialogFragment {

    private TutorApplication app;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        app = (TutorApplication) getActivity().getApplication();
        requestQueue = Volley.newRequestQueue(getActivity());

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        final EditText etCourseName = (EditText) view.findViewById(R.id.add_course_name);
        final EditText etPrice = (EditText) view.findViewById(R.id.add_course_price);
        final EditText etCourseInfo = (EditText) view.findViewById(R.id.add_course_info);

        ImageView ivConfirm = (ImageView) view.findViewById(R.id.btn_confirm_add_course);
        ivConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String courseName = etCourseName.getText().toString();
                final String courseInfo = etCourseInfo.getText().toString();
                final String coursePrice = etPrice.getText().toString();
                final String jwbCredit = "认证通过";

                String url = app.getServerAddress() + "create_course/";
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Log.d("VKAZAS-addCourse", s);
                                AddCourseListener listener = (AddCourseListener) getActivity();
                                listener.onAddCourseComplete();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("VKAERR-addCourse", volleyError.toString());
                            }
                        }

                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("uuid", app.getAccessToken());
                        map.put("course_name", courseName);
                        map.put("course_info", courseInfo);
                        map.put("jwb_credit", jwbCredit);
                        map.put("amount", coursePrice);
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        return view;
    }

    public interface AddCourseListener {
        void onAddCourseComplete();
    }
}
