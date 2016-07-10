package com.zju.app.tutormall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends AppCompatActivity {

    private static final int GO_HOME = 100;
    private static final int GO_LOGIN = 200;
    private boolean userExists = true;
    Context context = this;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                    break;
                case GO_LOGIN:
                    startActivity(new Intent(context, LoginActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        userExists = false;
        if (userExists) {
            updateUserInfo();
            handler.sendEmptyMessageDelayed(GO_HOME, 3000);
        } else {
            handler.sendEmptyMessageDelayed(GO_LOGIN, 3000);
        }
    }

    private void updateUserInfo() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
