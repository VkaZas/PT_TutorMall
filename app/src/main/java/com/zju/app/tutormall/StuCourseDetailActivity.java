package com.zju.app.tutormall;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zju.app.tutormall.application.TutorApplication;
import com.zju.app.tutormall.fragments.AddOrderFragment;
import com.zju.app.tutormall.tools.RandomIcon;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StuCourseDetailActivity extends AppCompatActivity implements AddOrderFragment.AddOrderListener{
    private AddOrderFragment addDialog;
    private TutorApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_course_detail);
        app = (TutorApplication) getApplication();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("课程详情");
        actionBar.setBackgroundDrawable(getDrawable(R.color.mainblue));

        TextView tvCourseName = (TextView) findViewById(R.id.course_name);
        TextView tvCourseInfo = (TextView) findViewById(R.id.course_info);
        Button btnEnroll = (Button) findViewById(R.id.enroll_btn);
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("course_id", getIntent().getIntExtra("course_id", 0));
                addDialog = new AddOrderFragment();
                addDialog.setArguments(bundle);
                addDialog.show(getFragmentManager(), "addOrder");
            }
        });

        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvCourseInfo.setText(getIntent().getStringExtra("course_info"));

        TextView tvTeacherInfo = (TextView) findViewById(R.id.teacher_info);
        TextView tvTeacherInfoExt = (TextView) findViewById(R.id.teacher_info_ext);
        CircleImageView avatar = (CircleImageView) findViewById(R.id.teacher_avatar);
        RandomIcon.setRandomIcon(this, avatar);

        tvTeacherInfo.setText(getIntent().getStringExtra("teacher_name"));
        tvTeacherInfoExt.setText("注册时间：" + getIntent().getStringExtra("create_time"));
    }

    @Override
    public void onAddOrderComplete() {
        app.update = true;
        addDialog.dismiss();
        View rootLayout = findViewById(R.id.stu_course_root);
        Snackbar.make(rootLayout, "报名成功", Snackbar.LENGTH_LONG).show();
    }

}
