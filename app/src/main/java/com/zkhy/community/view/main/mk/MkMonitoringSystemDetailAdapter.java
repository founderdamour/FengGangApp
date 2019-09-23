package com.zkhy.community.view.main.mk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.MkMonitoringSystemDetailEntity;
import com.zkhy.community.model.bean.MkMonitoringSystemEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 煤矿检测系统矿点适配器
 */
public class MkMonitoringSystemDetailAdapter extends RecyclerView.Adapter<MkMonitoringSystemDetailAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<MkMonitoringSystemDetailEntity> data;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public MkMonitoringSystemDetailAdapter(Context context, ArrayList<MkMonitoringSystemDetailEntity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mk_monitoring_system_detail_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final MkMonitoringSystemDetailEntity entity = data.get(position);
        viewHolder.pointTv.setText(entity.getPoint());
        viewHolder.wzTv.setText(entity.getWz());
        viewHolder.sssjTv.setText(entity.getSssj());
        viewHolder.alarmTv.setText(entity.getAlarm());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<MkMonitoringSystemDetailEntity> data) {
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

    public void addData(ArrayList<MkMonitoringSystemDetailEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pointTv, wzTv, sssjTv, alarmTv;
        public LinearLayout itemRootView;

        public ViewHolder(View view) {
            super(view);
            pointTv = view.findViewById(R.id.pointTv);
            wzTv = view.findViewById(R.id.wzTv);
            sssjTv = view.findViewById(R.id.sssjTv);
            alarmTv = view.findViewById(R.id.alarmTv);
            itemRootView = view.findViewById(R.id.itemRootView);
        }
    }
}





















