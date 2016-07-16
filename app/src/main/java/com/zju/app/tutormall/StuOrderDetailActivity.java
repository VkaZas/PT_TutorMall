package com.zju.app.tutormall;

import android.content.ComponentName;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zju.app.tutormall.tools.RandomIcon;

import de.hdodenhof.circleimageview.CircleImageView;

public class StuOrderDetailActivity extends AppCompatActivity {
    ImageView ivPaidIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_order_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("订单详情");
        actionBar.setBackgroundDrawable(getDrawable(R.color.mainblue));

        TextView tvCourseName = (TextView) findViewById(R.id.course_name);
        TextView tvCourseInfo = (TextView) findViewById(R.id.course_info);
        ivPaidIcon = (ImageView) findViewById(R.id.icon_paid);

        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvCourseInfo.setText(getIntent().getStringExtra("course_info"));
        int orderState = getIntent().getIntExtra("order_state", -1);
        switch (getIntent().getIntExtra("order_state", 0)) {
            case 0:
                ivPaidIcon.setImageResource(R.drawable.order_detail_cny_nonactive);
                break;
            case 1:
                ivPaidIcon.setImageResource(R.drawable.order_detail_cny_active);
                break;
        }

        final int courseID = getIntent().getIntExtra("course_id", 0);
        Log.d("VKAZAS-stuOrder", "orderstate=" + orderState + " courseID=" + courseID);

        Button btnPay = (Button) findViewById(R.id.pay_btn);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String packName = "payment.zjuse.android.zjuse_payment";
                String className = "com.zxing.activity.CaptureActivity";
                ComponentName comp = new ComponentName(packName, className);
                intent.setComponent(comp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("android.intent.action.MAIN");
                intent.putExtra("comment", String.valueOf(courseID));
                startActivity(intent);
            }
        });

        TextView tvTeacherInfo = (TextView) findViewById(R.id.teacher_info);
        TextView tvTeacherInfoExt = (TextView) findViewById(R.id.teacher_info_ext);
        CircleImageView avatar = (CircleImageView) findViewById(R.id.avatar);
        RandomIcon.setRandomIcon(this, avatar);


        tvTeacherInfo.setText(getIntent().getStringExtra("teacher_name"));
        tvTeacherInfoExt.setText("注册时间：" + getIntent().getStringExtra("create_time"));
    }

    @Override
    protected void onRestart() {
        super.onResume();
        ivPaidIcon.setImageResource(R.drawable.order_detail_cny_active);
        View rootLayout = findViewById(R.id.stu_order_root);
        Snackbar.make(rootLayout, "支付成功", Snackbar.LENGTH_LONG).show();
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
