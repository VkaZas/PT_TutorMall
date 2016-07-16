package com.zju.app.tutormall;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.adapters.CourseOrderListAdapter;
import com.zju.app.tutormall.application.TutorApplication;
import com.zju.app.tutormall.beans.OrderInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseDetailActivity extends AppCompatActivity {

    private TutorApplication app;
    private RequestQueue requestQueue;
    private ArrayList<OrderInfo> orderInfos;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("课程详情");
        actionBar.setBackgroundDrawable(getDrawable(R.color.mainblue));

        app = (TutorApplication) getApplication();

        TextView tvCourseName = (TextView) findViewById(R.id.course_name);
        TextView tvCourseInfo = (TextView) findViewById(R.id.course_info);
        tvCourseInfo.setText(getIntent().getStringExtra("course_info"));
        tvCourseName.setText(getIntent().getStringExtra("course_name"));

        ImageView ivDelCourse = (ImageView) findViewById(R.id.delete_course);
        ImageView ivAuthorize = (ImageView) findViewById(R.id.authorization);
        ImageView ivEditCourse = (ImageView) findViewById(R.id.edit_course);

        rv = (RecyclerView) findViewById(R.id.course_order_list);

        fetchData();
    }

    private void fetchData() {
        requestQueue = Volley.newRequestQueue(this);
        orderInfos = new ArrayList<>();
        String url = app.getServerAddress() + "order_list/";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("VKAZAS-CourseOrder", s);
                        try {
                            JSONObject jObj = new JSONObject(s);
                            int len = jObj.getInt("len");
                            JSONArray jArr = jObj.getJSONArray("order_list");
                            for (int i=0;i <len;i ++) {
                                JSONObject tmp = jArr.getJSONObject(i);
                                orderInfos.add(new OrderInfo(tmp));
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
                        Log.d("VKAZAS-volley", volleyError.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("course_id", String.valueOf(getIntent().getIntExtra("course_id", 0)));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CourseOrderListAdapter(this, orderInfos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
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
