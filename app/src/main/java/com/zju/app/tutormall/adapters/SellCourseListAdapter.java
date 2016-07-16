package com.zju.app.tutormall.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zju.app.tutormall.CourseDetailActivity;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.beans.CourseInfo;

import java.util.ArrayList;

public class SellCourseListAdapter extends RecyclerView.Adapter<SellCourseListAdapter.SellCourseHolder> {
    private Context context;
    private ArrayList<CourseInfo> courseInfos;

    public class SellCourseHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView tvCourseName;
        private final TextView tvCourseInfo;
        private final TextView tvTeacherRegTime;
        private final TextView tvGoCheckOrder;
        private final ImageView ivGoCheckOrder;

        public SellCourseHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvCourseName = (TextView) itemView.findViewById(R.id.course_name);
            tvCourseInfo = (TextView) itemView.findViewById(R.id.course_info);
            tvTeacherRegTime = (TextView) itemView.findViewById(R.id.teacher_reg_time);
            tvGoCheckOrder = (TextView) itemView.findViewById(R.id.go_check_order_txt);
            ivGoCheckOrder = (ImageView) itemView.findViewById(R.id.go_check_order);
        }
    }

    public SellCourseListAdapter(Context context, ArrayList<CourseInfo> courseInfos) {
        this.context = context;
        this.courseInfos = courseInfos;
        Log.d("VKAZAS-SellAdapter", courseInfos.toString());
    }

    @Override
    public SellCourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_sell_card_item, parent, false);
        view.setBackgroundResource(R.color.whitesmoke);
        return new SellCourseHolder(view);
    }

    @Override
    public void onBindViewHolder(SellCourseHolder holder, final int position) {
        holder.tvCourseInfo.setText(courseInfos.get(position).courseInfo);
        holder.tvCourseName.setText(courseInfos.get(position).courseName);
        holder.tvTeacherRegTime.setText(courseInfos.get(position).createTime);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.go_check_order || v.getId() == R.id.go_check_order_txt) {
                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra("course_id", courseInfos.get(position).courseID);
                    intent.putExtra("course_name", courseInfos.get(position).courseName);
                    intent.putExtra("course_info", courseInfos.get(position).courseInfo);
                    context.startActivity(intent);
                }
            }
        };
        holder.tvGoCheckOrder.setOnClickListener(clickListener);
        holder.ivGoCheckOrder.setOnClickListener(clickListener);
    }


    @Override
    public int getItemCount() {
        return courseInfos.size();
    }
}
