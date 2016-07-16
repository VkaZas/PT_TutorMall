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

import com.bumptech.glide.Glide;
import com.zju.app.tutormall.CourseDetailActivity;
import com.zju.app.tutormall.R;
import com.zju.app.tutormall.StuCourseDetailActivity;
import com.zju.app.tutormall.StuOrderDetailActivity;
import com.zju.app.tutormall.beans.OrderInfo;
import com.zju.app.tutormall.tools.RandomIcon;

import java.util.ArrayList;

public class BuyOrderListAdapter extends RecyclerView.Adapter<BuyOrderListAdapter.CourseViewHolder> {

    private ArrayList<OrderInfo> orderInfos;
    private Context context;

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

    public BuyOrderListAdapter(Context context, ArrayList<OrderInfo> orderInfos) {
        Log.d("VKAZAS-BuyAdapter", orderInfos.toString());
        this.context = context;
        this.orderInfos = orderInfos;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_card_item, parent, false);
        view.setBackgroundResource(R.color.white);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, final int position) {
        holder.tvTeacherInfo.setText(orderInfos.get(position).teacherName);
        holder.tvCourseInfo.setText(orderInfos.get(position).courseName);
        holder.tvScheduleInfo.setText(orderInfos.get(position).courseInfo);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StuOrderDetailActivity.class);
                intent.putExtra("course_name", orderInfos.get(position).courseName);
                intent.putExtra("course_info", orderInfos.get(position).courseInfo);
                intent.putExtra("course_id", orderInfos.get(position).courseID);
                intent.putExtra("order_state", orderInfos.get(position).orderState);
                intent.putExtra("teacher_name", orderInfos.get(position).teacherName);
                intent.putExtra("create_time", orderInfos.get(position).createTime);
                context.startActivity(intent);
            }
        });
        RandomIcon.setRandomIcon(context, holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return orderInfos.size();
    }
}