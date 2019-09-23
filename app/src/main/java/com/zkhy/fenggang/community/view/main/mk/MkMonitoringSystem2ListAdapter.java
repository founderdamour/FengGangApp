package com.zkhy.fenggang.community.view.main.mk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.MkMonitoringSystem2Entity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 煤矿检测系统适配器
 */
public class MkMonitoringSystem2ListAdapter extends RecyclerView.Adapter<MkMonitoringSystem2ListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<MkMonitoringSystem2Entity> data;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public MkMonitoringSystem2ListAdapter(Context context, ArrayList<MkMonitoringSystem2Entity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mk_monitoring_system2_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final MkMonitoringSystem2Entity entity = data.get(position);
        viewHolder.nameTv.setText(entity.getKname());
        viewHolder.numTv.setText(entity.getKbh());
        viewHolder.stateTv.setText(entity.getZdflag());
        viewHolder.dateTv.setText(entity.getTimer());

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

    public void setData(ArrayList<MkMonitoringSystem2Entity> data) {
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

    public void addData(ArrayList<MkMonitoringSystem2Entity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTv, numTv, dateTv, stateTv;
        public LinearLayout itemRootView;

        public ViewHolder(View view) {
            super(view);
            nameTv = view.findViewById(R.id.nameTv);
            numTv = view.findViewById(R.id.numTv);
            dateTv = view.findViewById(R.id.dateTv);
            stateTv = view.findViewById(R.id.stateTv);
            itemRootView = view.findViewById(R.id.itemRootView);
        }
    }
}





















