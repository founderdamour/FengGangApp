package com.zkhy.fenggang.community.view.main.bm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.CommListItem0Entity;

import java.util.ArrayList;
import java.util.List;

public class CommGridViewAdapter extends BaseAdapter {

    private List<CommListItem0Entity> apps;
    private Context mContext;

    private OnListItemClickListener secondItemListener;

    public CommGridViewAdapter(Context context, OnListItemClickListener listener) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_zm_pay_life_pay_obj_list_item, null);
            viewHolder = new ItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) convertView.getTag();
        }

        final CommListItem0Entity app = apps.get(position);

        viewHolder.itemIconIv.setImageResource(app.getObjIcon());
        viewHolder.itemNameTv.setText(app.getObjName());

        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondItemListener != null) {
                    secondItemListener.onItemClickListener(position, app);
                }
            }
        });

        return convertView;
    }

    private class ItemViewHolder {
        TextView itemNameTv;
        ImageView itemIconIv;

        LinearLayout itemLayout;

        ItemViewHolder(View itemView) {
            itemLayout = itemView.findViewById(R.id.itemLayout);

            itemNameTv = itemView.findViewById(R.id.itemNameTv);
            itemIconIv = itemView.findViewById(R.id.itemIconIv);
        }
    }

    public void setData(List<CommListItem0Entity> apps) {
        this.apps = apps;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {
        void onItemClickListener(int position, CommListItem0Entity itemData);
    }
}
