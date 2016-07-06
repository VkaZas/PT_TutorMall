package com.zju.app.tutormall;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;

import com.zju.app.tutormall.fragments.BuyCourseFragment;
import com.zju.app.tutormall.fragments.MyInfoFragment;
import com.zju.app.tutormall.fragments.PropagateFragment;
import com.zju.app.tutormall.fragments.SellCourseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("互课");
        toolbar.setBackgroundColor(0xc0c0c0);
        setSupportActionBar(toolbar);

        /**
         * Setting up ViewPager and TabLayout
         * */
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        fragments = new Fragment[] {
                new PropagateFragment(),
                new SellCourseFragment(),
                new BuyCourseFragment(),
                new MyInfoFragment()
        };
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(fragments[0], "咨询");
        adapter.addFragment(fragments[1], "授课");
        adapter.addFragment(fragments[2], "听课");
        adapter.addFragment(fragments[3], "我的");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }
}
