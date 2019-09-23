package com.zkhy.fenggang.community.view.main.bm.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.AppointmentStateEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderDateGridViewAdapter extends BaseAdapter {

    private List<AppointmentStateEntity> apps;
    private Context mContext;

    private OnListItemClickListener secondItemListener;

    public OrderDateGridViewAdapter(Context context, OnListItemClickListener listener) {
        mContext = context;
        secondItemListener = listener;
        apps = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_bm_order_date_list_item, null);
            viewHolder = new ItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) convertView.getTag();
        }

        final AppointmentStateEntity app = apps.get(position);

        if (app.isSelected()) {
            viewHolder.itemLayout.setBackgroundResource(R.drawable.bm_order_date_list_item_bg_selected);

            viewHolder.orderWeekTv.setTextColor(Color.WHITE);
            viewHolder.orderDateTv.setTextColor(Color.WHITE);
        } else {
            viewHolder.itemLayout.setBackgroundResource(R.drawable.bm_order_date_list_item_bg_unselected);

            viewHolder.orderWeekTv.setTextColor(mContext.getResources().getColor(R.color.tc_list_title));
            viewHolder.orderDateTv.setTextColor(mContext.getResources().getColor(R.color.tc_list_title));
        }

        // 正常数据
        viewHolder.orderDateTv.setText(app.getCurrTime());

        if (app.getState() == null || app.getState().length() == 0) {
            viewHolder.orderStatusTv.setText("节假日");

            if (app.isSelected()) {
                viewHolder.orderStatusTv.setTextColor(Color.WHITE);
            } else {
                viewHolder.orderStatusTv.setTextColor(Color.parseColor("#999999"));
            }
        } else if (app.getState().contains("有")) {
            viewHolder.orderStatusTv.setText(app.getState());

            if (app.isSelected()) {
                viewHolder.orderStatusTv.setTextColor(Color.WHITE);
            } else {
                viewHolder.orderStatusTv.setTextColor(Color.parseColor("#18B4ED"));
            }
        } else {
            viewHolder.orderStatusTv.setText(app.getState());

            if (app.isSelected()) {
                viewHolder.orderStatusTv.setTextColor(Color.WHITE);
            } else {
                viewHolder.orderStatusTv.setTextColor(Color.parseColor("#fffa3636"));
            }
        }

        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondItemListener != null) {
                    secondItemListener.onItemClickListener(position, app);
                }

                for (int i = 0; i < apps.size(); i++) {
                    if (i == position) {
                        apps.get(i).setSelected(true);
                    } else {
                        apps.get(i).setSelected(false);
                    }
                }

                notifyDataSetChanged();
            }
        });

        String weekCHN = "";
        if ("1".equals(app.getWeekDay())) {
            weekCHN = "一";
        } else if ("2".equals(app.getWeekDay())) {
            weekCHN = "二";
        } else if ("3".equals(app.getWeekDay())) {
            weekCHN = "三";
        } else if ("4".equals(app.getWeekDay())) {
            weekCHN = "四";
        } else if ("5".equals(app.getWeekDay())) {
            weekCHN = "五";
        } else if ("6".equals(app.getWeekDay())) {
            weekCHN = "六";
        } else if ("7".equals(app.getWeekDay())) {
            weekCHN = "天";
        }
        viewHolder.orderWeekTv.setText("星期" + weekCHN);
        return convertView;
    }

    private class ItemViewHolder {
        TextView orderWeekTv, orderDateTv, orderStatusTv;
        LinearLayout itemLayout;

        ItemViewHolder(View itemView) {
            itemLayout = itemView.findViewById(R.id.itemLayout);

            orderWeekTv = itemView.findViewById(R.id.orderWeekTv);
            orderDateTv = itemView.findViewById(R.id.orderDateTv);
            orderStatusTv = itemView.findViewById(R.id.orderStatusTv);
        }
    }

    public void setData(List<AppointmentStateEntity> apps) {
        for (int i = 0; i < apps.size(); i++) {
            if (i == 0) {
                apps.get(i).setSelected(true);
            } else {
                apps.get(i).setSelected(false);
            }
        }
        this.apps = apps;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {
        void onItemClickListener(int position, AppointmentStateEntity itemData);
    }
}
