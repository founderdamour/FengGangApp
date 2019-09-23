package com.zkhy.fenggang.community.view.main.mk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.MkMonitoringSystemEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 煤矿检测系统矿点适配器
 */
public class MkMonitoringSystemListAdapter extends RecyclerView.Adapter<MkMonitoringSystemListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<MkMonitoringSystemEntity> data;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public MkMonitoringSystemListAdapter(Context context, ArrayList<MkMonitoringSystemEntity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mk_monitoring_system_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final MkMonitoringSystemEntity entity = data.get(position);
        viewHolder.nameTv.setText(entity.getKname());

        viewHolder.itemRootView.setOnClickListener(
                v -> {
                    if (clickCallBack != null) {
                        clickCallBack.onItemClick(position, entity);
                    }
                }
        );
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<MkMonitoringSystemEntity> data) {
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

    public void addData(ArrayList<MkMonitoringSystemEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTv;
        public RelativeLayout itemRootView;

        public ViewHolder(View view) {
            super(view);
            nameTv = view.findViewById(R.id.nameTv);
            itemRootView = view.findViewById(R.id.itemRootView);
        }
    }
}





















