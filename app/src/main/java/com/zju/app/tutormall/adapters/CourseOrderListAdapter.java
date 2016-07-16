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

import com.zju.app.tutormall.R;
import com.zju.app.tutormall.TutorOrderDetailActivity;
import com.zju.app.tutormall.beans.OrderInfo;
import com.zju.app.tutormall.tools.RandomIcon;

import java.util.ArrayList;

public class CourseOrderListAdapter extends RecyclerView.Adapter<CourseOrderListAdapter.OrderViewHolder> {
    private Context context;
    private ArrayList<OrderInfo> orderInfos;

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView tvStuName;
        private TextView tvStuInfo;
        private TextView tvCreateTime;
        private TextView tvCheckDetail;
        private ImageView ivDelOrder;
        private ImageView ivCheckDetail;
        private ImageView ivOrderAvatar;

        public OrderViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvStuName = (TextView) itemView.findViewById(R.id.student_name);
            tvStuInfo = (TextView) itemView.findViewById(R.id.student_info);
            tvCreateTime = (TextView) itemView.findViewById(R.id.order_create_time);
            ivDelOrder = (ImageView) itemView.findViewById(R.id.delete_order);
            ivCheckDetail = (ImageView) itemView.findViewById(R.id.go_check_order);
            tvCheckDetail = (TextView) itemView.findViewById(R.id.go_check_order_txt);
            ivOrderAvatar = (ImageView) itemView.findViewById(R.id.order_avatar);
        }
    }

    public CourseOrderListAdapter(Context context, ArrayList<OrderInfo> orderInfos) {
        this.context = context;
        this.orderInfos = orderInfos;
        Log.d("VKAZAS-OrderAdapter", orderInfos.toString());
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_list_item, parent, false);
        view.setBackgroundResource(R.color.whitesmoke);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, final int position) {
        holder.tvStuName.setText(orderInfos.get(position).stuName);
        holder.tvStuInfo.setText(orderInfos.get(position).stuInfo);
        holder.tvCreateTime.setText(orderInfos.get(position).createTime);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == holder.ivCheckDetail.getId() || v.getId() == holder.tvCheckDetail.getId()) {
                    Intent intent = new Intent(context, TutorOrderDetailActivity.class);
                    intent.putExtra("student_name", orderInfos.get(position).stuName);
                    intent.putExtra("student_info", orderInfos.get(position).stuInfo);
                    intent.putExtra("student_id", orderInfos.get(position).stuID);
                    intent.putExtra("course_name", orderInfos.get(position).courseName);
                    intent.putExtra("amount", orderInfos.get(position).amount);
                    intent.putExtra("order_info", orderInfos.get(position).orderInfo);
                    context.startActivity(intent);
                }
            }
        };
        Log.d("VKAZAS-listener", onClickListener.toString());
        holder.tvCheckDetail.setOnClickListener(onClickListener);
        holder.ivCheckDetail.setOnClickListener(onClickListener);
        RandomIcon.setRandomIcon(context, holder.ivOrderAvatar);
        holder.ivDelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/7/12 add delete order function
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderInfos.size();
    }
}
