package com.zju.app.tutormall.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zju.app.tutormall.R;
import com.zju.app.tutormall.StuCourseDetailActivity;
import com.zju.app.tutormall.beans.CourseInfo;
import com.zju.app.tutormall.tools.RandomIcon;

import java.util.ArrayList;

public class BriefCourseListAdapter extends RecyclerView.Adapter<BriefCourseListAdapter.BriefCourseHolder> {
    private Context context;
    private ArrayList<CourseInfo> courseInfos;

    public class BriefCourseHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView tvTeacherName;
        private TextView tvCourseName;
        private TextView tvExtraInfo;
        private ImageView ivAvatar;

        public BriefCourseHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvTeacherName = (TextView) mView.findViewById(R.id.teacher_name);
            tvCourseName = (TextView) mView.findViewById(R.id.course_name);
            tvExtraInfo = (TextView) mView.findViewById(R.id.extra_info);
            ivAvatar = (ImageView) mView.findViewById(R.id.avatar);
        }
    }

    public BriefCourseListAdapter(Context context, ArrayList<CourseInfo> courseInfos) {
        this.context = context;
        this.courseInfos = courseInfos;
    }

    @Override
    public BriefCourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        view.setBackgroundResource(R.color.whitesmoke);
        return new BriefCourseHolder(view);
    }

    @Override
    public void onBindViewHolder(BriefCourseHolder holder, final int position) {
        holder.tvTeacherName.setText(courseInfos.get(position).teacherName);
        holder.tvCourseName.setText(courseInfos.get(position).courseName);
        holder.tvExtraInfo.setText(courseInfos.get(position).createTime);
        RandomIcon.setRandomIcon(context, holder.ivAvatar);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StuCourseDetailActivity.class);
                intent.putExtra("course_name", courseInfos.get(position).courseName);
                intent.putExtra("course_info", courseInfos.get(position).courseInfo);
                intent.putExtra("course_id", courseInfos.get(position).courseID);
                intent.putExtra("teacher_name", courseInfos.get(position).teacherName);
                intent.putExtra("teacher_id", courseInfos.get(position).teacherID);
                intent.putExtra("create_time", courseInfos.get(position).createTime);
                intent.putExtra("credit", courseInfos.get(position).credit);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseInfos.size();
    }


}
