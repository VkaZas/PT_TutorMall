package com.zju.app.tutormall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import de.hdodenhof.circleimageview.CircleImageView;

public class StuOrderDetailActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_order_detail);

        requestQueue = Volley.newRequestQueue(this);

        TextView tvCourseName = (TextView) findViewById(R.id.course_name);
        TextView tvCourseInfo = (TextView) findViewById(R.id.course_info);

        // TODO: 2016/7/10 dynamicly change icon color
        ImageView ivPaidIcon = (ImageView) findViewById(R.id.icon_paid);
        ImageView ivUnpaidIcon = (ImageView) findViewById(R.id.icon_unpaid);

        // TODO: 2016/7/10 add button function
        Button btnPay = (Button) findViewById(R.id.pay_btn);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView tvTeacherInfo = (TextView) findViewById(R.id.teacher_info);
        TextView tvTeacherInfoExt = (TextView) findViewById(R.id.teacher_info_ext);
        CircleImageView avatar = (CircleImageView) findViewById(R.id.avatar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_detail, menu);
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
