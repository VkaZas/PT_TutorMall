package com.zju.app.tutormall;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zju.app.tutormall.adapters.FragmentTabAdapter;
import com.zju.app.tutormall.application.TutorApplication;
import com.zju.app.tutormall.beans.CourseInfo;
import com.zju.app.tutormall.beans.OrderInfo;
import com.zju.app.tutormall.fragments.AddCourseFragment;
import com.zju.app.tutormall.fragments.BuyCourseFragment;
import com.zju.app.tutormall.fragments.MyInfoFragment;
import com.zju.app.tutormall.fragments.PropagateFragment;
import com.zju.app.tutormall.fragments.SellCourseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        AddCourseFragment.AddCourseListener{

    private TutorApplication app;
    private Fragment[] fragments;
    private Toolbar toolbar;
    int currentTabIndex;
    private int postCnt, maxPost;
    private RequestQueue requestQueue;
    private AddCourseFragment addDialog;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (TutorApplication) getApplication();
        requestQueue = Volley.newRequestQueue(this);
        fetchData(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("VKAZAS-mainResume", "Token=" + app.getAccessToken() + " Server=" + app.getServerAddress());
        boolean update;
        if (app.update) {
            update = true;
            app.update = false;
        } else {
            update = false;
        }
        fetchData(update);
    }

    private void fetchData(final boolean refreshUI) {

        String url;
        postCnt = 0;
        maxPost = 3;

        //PropagateCourseInfo
        url = app.getServerAddress() + "course_list/";
        StringRequest pCourseRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("VKAZAS-propCourse", s);
                        try {
                            JSONObject jObj = new JSONObject(s);
                            JSONArray jArr = jObj.getJSONArray("course_list");
                            int len = jObj.getInt("len");
                            app.propCourseInfoList.clear();
                            for (int i=0; i<len; i++) {
                                JSONObject courseObj = jArr.getJSONObject(i);
                                app.propCourseInfoList.add(new CourseInfo(courseObj));
                            }
                            if (refreshUI) postSignal();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("VKAZASERR", volleyError.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("uuid", app.getAccessToken());
                map.put("max_size", "10");
                return map;
            }
        };
        requestQueue.add(pCourseRequest);

        //SellCourseInfo
        url = app.getServerAddress() + "sell_course/";
        StringRequest courseRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("VKAZAS-sellCourse", s);
                        try {
                            JSONObject jObj = new JSONObject(s);
                            int len = jObj.getInt("len");
                            JSONArray jArr = jObj.getJSONArray("course_list");
                            app.teachCourseInfoList.clear();
                            for (int i=0; i<len; i++) {
                                JSONObject courseObj = jArr.getJSONObject(i);
                                app.teachCourseInfoList.add(new CourseInfo(courseObj));
                            }

                            if (refreshUI) postSignal();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("VKAZASERR", volleyError.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("uuid", app.getAccessToken());
                return map;
            }
        };
        requestQueue.add(courseRequest);

        // StuOrderInfo
        url = app.getServerAddress() + "buy_course/";
        StringRequest orderRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("VKAZAS-buyCourse", s);
                        try {
                            JSONObject jObj = new JSONObject(s);
                            int len = jObj.getInt("len");
                            JSONArray jArr = jObj.getJSONArray("order_list");
                            app.stuOrderList.clear();
                            for (int i=0; i<len; i++) {
                                JSONObject orderObj = jArr.getJSONObject(i);
                                app.stuOrderList.add(new OrderInfo(orderObj));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (refreshUI) postSignal();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("VKAZASERR", volleyError.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("VKAZAS-token", app.getAccessToken());
                Map<String, String> map = new HashMap<>();
                map.put("uuid", app.getAccessToken());
                return map;
            }
        };
        requestQueue.add(orderRequest);
    }

    private void postSignal() {
        postCnt++;
        if (postCnt == maxPost) {
            initView();
        }
    }

    private void initView() {
        /**
         * Setting up Toolbar
         * */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("互课");
        toolbar.setBackgroundColor(getResources().getColor(R.color.mainblue));
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_course:
                        addDialog = new AddCourseFragment();
                        addDialog.show(getFragmentManager(), "addDialog");
                        break;
                }
                return false;
            }
        });

        /**
         * Setting up ViewPager and TabLayout
         * */
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentTabIndex = position;
                    toolbar.getMenu().findItem(R.id.add_course).setVisible(false);
                    switch (position) {
                        case 1:
                            lightUp(1);
                            toolbar.setTitle("已发布课程(" + String.valueOf(app.teachCourseInfoList.size()) + ")");
                            toolbar.getMenu().findItem(R.id.add_course).setVisible(true);
                            break;
                        case 2:
                            lightUp(2);
                            toolbar.setTitle("已购买课程(" + String.valueOf(app.stuOrderList.size()) + ")");
                            break;
                        case 3:
                            lightUp(3);
                            toolbar.setTitle("个人资料");
                        default:
                            lightUp(0);
                            toolbar.setTitle("互课");
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        lightUp(0);
    }

    private void lightUp(int index) {
        tabLayout.getTabAt(0).setIcon(R.drawable.normal_02);
        tabLayout.getTabAt(1).setIcon(R.drawable.normal_03);
        tabLayout.getTabAt(2).setIcon(R.drawable.normal_04);
        tabLayout.getTabAt(3).setIcon(R.drawable.normal_05);

        switch (index) {
            case 0:
                tabLayout.getTabAt(0).setIcon(R.drawable.pressed_1);
                break;
            case 1:
                tabLayout.getTabAt(1).setIcon(R.drawable.pressed_2);
                break;
            case 2:
                tabLayout.getTabAt(2).setIcon(R.drawable.pressed_3);
                break;
            case 3:
                tabLayout.getTabAt(3).setIcon(R.drawable.pressed_4);
                break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        fragments = new Fragment[] {
                new PropagateFragment(),
                new SellCourseFragment(),
                new BuyCourseFragment(),
                new MyInfoFragment()
        };
        FragmentTabAdapter fragmentTabAdapter = new FragmentTabAdapter(getSupportFragmentManager());
        fragmentTabAdapter.addFragment(fragments[0], "咨询");
        fragmentTabAdapter.addFragment(fragments[1], "授课");
        fragmentTabAdapter.addFragment(fragments[2], "听课");
        fragmentTabAdapter.addFragment(fragments[3], "我的");
        viewPager.setAdapter(fragmentTabAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_course).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

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

    @Override
    public void onAddCourseComplete() {
        fetchData(true);
        addDialog.dismiss();
        View rootLayout = findViewById(R.id.main_content);
        Snackbar.make(rootLayout, "已新建课程", Snackbar.LENGTH_LONG).show();
    }

}
