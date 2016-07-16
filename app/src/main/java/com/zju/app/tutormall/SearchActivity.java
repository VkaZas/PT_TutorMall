package com.zju.app.tutormall;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.adapters.BriefCourseListAdapter;
import com.zju.app.tutormall.application.TutorApplication;
import com.zju.app.tutormall.beans.CourseInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    private TutorApplication app;
    private RequestQueue requestQueue;
    private RecyclerView rv;
    String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("搜索结果");
        actionBar.setBackgroundDrawable(getDrawable(R.color.mainblue));

        app = (TutorApplication) getApplication();
        requestQueue = Volley.newRequestQueue(this);
        rv = (RecyclerView) findViewById(R.id.search_course_list);
        search();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        search();
    }

    private void search() {
        searchKey = getIntent().getStringExtra(SearchManager.QUERY);
        String url = app.getServerAddress() + "course_list/";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("VKAZAS-search", s);
                        JSONObject jObj = null;
                        try {
                            jObj = new JSONObject(s);
                            JSONArray jArr = jObj.getJSONArray("course_list");
                            int len = jObj.getInt("len");
                            app.srchCourseInfoList.clear();
                            for (int i=0; i<len; i++) {
                                JSONObject courseObj = jArr.getJSONObject(i);
                                app.srchCourseInfoList.add(new CourseInfo(courseObj));
                            }
                            setupRecyclerView(rv);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("VKAERR", volleyError.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("uuid", app.getAccessToken());
                map.put("key", searchKey);
                map.put("max_size", "10");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

   private void setupRecyclerView(RecyclerView rv) {
       rv.setLayoutManager(new LinearLayoutManager(this));
       rv.setAdapter(new BriefCourseListAdapter(this, app.srchCourseInfoList));
   }
}
