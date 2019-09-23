package com.zkhy.community.view.main.bm.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.SmartParkEntity;
import com.zkhy.community.model.bean.StadiumEntity;

import java.util.ArrayList;

/**
 */
public class SmartParkListAdapter extends RecyclerView.Adapter<SmartParkListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<SmartParkEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public SmartParkListAdapter(ArrayList<SmartParkEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_smart_park_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final SmartParkEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getParkName()));
        viewHolder.distanceTv.setText(entity.getParkDistance() + "米");
        viewHolder.addressTv.setText(StringUtil.getNotNullValue(entity.getArea()) + "" + StringUtil.getNotNullValue(entity.getAddress()));

        viewHolder.gotoMapBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack != null) {
                            clickCallBack.onItemClick(position, entity);
                        }
                    }
                }
        );

        if (position == data.size() - 1) {
            viewHolder.lastLine.setVisibility(View.VISIBLE);
        } else {
            viewHolder.lastLine.setVisibility(View.GONE);
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<SmartParkEntity> data) {
        if (this.data != null && this.data.size() > 0) {
            this.data.clear();
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        assert this.data != null;
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout gotoMapBtn;
        public TextView nameTv, distanceTv, addressTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            gotoMapBtn = (LinearLayout) view.findViewById(R.id.gotoMapBtn);

            nameTv = (TextView) view.findViewById(R.id.nameTv);
            distanceTv = (TextView) view.findViewById(R.id.distanceTv);
            addressTv = (TextView) view.findViewById(R.id.addressTv);

            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















