package com.zkhy.fenggang.community.view.main.mk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.MkPersonLocationDetailEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 煤矿检测系统适配器
 */
public class MkPersonLocationDetailAdapter extends RecyclerView.Adapter<MkPersonLocationDetailAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<MkPersonLocationDetailEntity> data;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public MkPersonLocationDetailAdapter(Context context, ArrayList<MkPersonLocationDetailEntity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mk_person_location_detail_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final MkPersonLocationDetailEntity entity = data.get(position);
        viewHolder.numTv.setText(entity.getPoint());
        viewHolder.addressTv.setText(entity.getWz());
        viewHolder.typeTv.setText(entity.getDevid());
        viewHolder.curPersonTv.setText(entity.getK1());
        viewHolder.unusualTv.setText(entity.getK2());
        viewHolder.ratedTv.setText(entity.getK3());
        viewHolder.cyTv.setText(entity.getCy());
        viewHolder.stateTv.setText(entity.getState());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<MkPersonLocationDetailEntity> data) {
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

    public void addData(ArrayList<MkPersonLocationDetailEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView numTv, addressTv, typeTv, curPersonTv, unusualTv, ratedTv, cyTv, stateTv;

        public ViewHolder(View view) {
            super(view);
            numTv = view.findViewById(R.id.numTv);
            addressTv = view.findViewById(R.id.addressTv);
            typeTv = view.findViewById(R.id.typeTv);
            curPersonTv = view.findViewById(R.id.curPersonTv);
            unusualTv = view.findViewById(R.id.unusualTv);
            ratedTv = view.findViewById(R.id.ratedTv);
            cyTv = view.findViewById(R.id.cyTv);
            stateTv = view.findViewById(R.id.stateTv);
        }
    }
}





















