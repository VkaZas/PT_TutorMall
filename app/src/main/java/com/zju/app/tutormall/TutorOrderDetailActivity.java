package com.zju.app.tutormall;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TutorOrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_order_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("订单详情");
        actionBar.setBackgroundDrawable(getDrawable(R.color.mainblue));

        TextView tvStuName = (TextView) findViewById(R.id.student_name);
        TextView tvStuInfo = (TextView) findViewById(R.id.student_info);
        TextView tvCourseName = (TextView) findViewById(R.id.course_order_name);
        TextView tvOrderInfo = (TextView) findViewById(R.id.course_order_info);
        TextView tvAmount = (TextView) findViewById(R.id.course_order_amount);

        tvStuName.setText(getIntent().getStringExtra("student_name"));
        tvStuInfo.setText(getIntent().getStringExtra("student_info"));
        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvOrderInfo.setText(getIntent().getStringExtra("order_info"));
        tvAmount.setText(String.valueOf(getIntent().getDoubleExtra("amount", 0.0)));

        Button btnReceivePay = (Button) findViewById(R.id.receive_payment);
        btnReceivePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String packName = "payment.zjuse.android.zjuse_payment";
                String className = "payment.zjuse.android.zjuse_payment.MyQRcode";
                ComponentName comp = new ComponentName(packName, className);
                intent.setComponent(comp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("android.intent.action.MAIN");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutor_order_detail, menu);
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
