package com.zju.app.tutormall.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zju.app.tutormall.CourseDetailActivity;
import com.zju.app.tutormall.R;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder> {

    private static String[] courseInfo = {"微积分", "大学物理", "食用医学", "形势与政策2", "权力的游戏", "如何食用人类", "宇宙起源", "Riptide"};
    private static String[] teacherInfo = {"陈铭业 浙江大学物理学教授", "陈铭业 浙江大学车队队长", "陈铭业 浙江大学后勤队长", "陈铭业 浙江大学保卫处长", "陈铭业 浙江大学校长", "陈铭业 浙江大学拉拉队队长", "陈铭业 浙江大学主厨", "陈铭业 浙江大学发言人"};
    private static String[] scheduleInfo = {"周二上课" , "周四上午上课 紫金港", "周四上午上课 之江", "周四上午上课 玉泉", "周四上午上课 钓鱼台", "周四上午上课 维斯特洛", "周四上午上课 洛宁", "周四上午上课 游泳池"};
    int itemCnt = 8;
    private final int viewID;

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final View mView;

        private final TextView tvTeacherInfo;
        private final TextView tvCourseInfo;
        private final TextView tvScheduleInfo;
        private final ImageView ivAvatar;



        public CourseViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvTeacherInfo = (TextView) itemView.findViewById(R.id.teacher_info);
            tvCourseInfo = (TextView) itemView.findViewById(R.id.course_info);
            tvScheduleInfo = (TextView) itemView.findViewById(R.id.schedule_info);
            ivAvatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }

    public CourseRecyclerViewAdapter(Context context, int viewType) {
        viewID = viewType;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewType = viewID;
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        view.setBackgroundResource(R.color.white);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.tvTeacherInfo.setText(teacherInfo[position]);
        holder.tvCourseInfo.setText(courseInfo[position]);
        holder.tvScheduleInfo.setText(scheduleInfo[position]);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("Greetings", "您午饭吃的什么呀？");
                context.startActivity(intent);
            }
        });
        Glide.with(holder.ivAvatar.getContext())
                .load(R.drawable.sample_avatar)
                .fitCenter()
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return itemCnt;
    }
}