package com.zju.app.tutormall;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import android.support.v7.widget.Toolbar;

import com.zju.app.tutormall.adapters.FragmentTabAdapter;
import com.zju.app.tutormall.fragments.BuyCourseFragment;
import com.zju.app.tutormall.fragments.MyInfoFragment;
import com.zju.app.tutormall.fragments.PropagateFragment;
import com.zju.app.tutormall.fragments.SellCourseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments;
    private Toolbar toolbar;
    int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        /**
         * Setting up Toolbar
         * */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("互课");
        toolbar.setBackgroundColor(0xc0c0c0);
        setSupportActionBar(toolbar);

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
                    if (position == 2) {
                        toolbar.setTitle("已购课程(3)");
                    } else {
                        toolbar.setTitle("互课");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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

}
