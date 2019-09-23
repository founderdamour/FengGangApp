package com.zkhy.fenggang.community.view.main.lm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.NearbyActiveEntity;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;

/**
 */
public class LMNearbyActiveListAdapter extends RecyclerView.Adapter<LMNearbyActiveListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<NearbyActiveEntity> data = null;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public LMNearbyActiveListAdapter(Context mContext, ArrayList<NearbyActiveEntity> data) {
        this.data = data;
        this.mContext = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lm_nearby_activie_list_item2, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final NearbyActiveEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getName()));

        if (StringUtil.isNotEmpty(entity.getStartTime()) && StringUtil.isNotEmpty(entity.getEndTime()) && entity.getStartTime().length() > 10 && entity.getEndTime().length() > 10) {
//            String startTime = entity.getStartTime().substring(0, 10);
//            String endTime = entity.getEndTime().substring(0, 10);

            viewHolder.timeTv.setText("时间：" + entity.getStartTime());
        } else {
            viewHolder.timeTv.setText("待定");
        }

        viewHolder.addressTv.setText("地址：" + StringUtil.getNotNullValue(entity.getAddr()));

        ImageLoader.show(mContext, entity.getAroundImg(), viewHolder.imgIv);

        viewHolder.itemRootView.setOnClickListener(
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

    public void setData(ArrayList<NearbyActiveEntity> data) {
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

    public void addData(ArrayList<NearbyActiveEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView nameTv, addressTv, timeTv, lastLine;
        ImageView imgIv;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            nameTv = (TextView) view.findViewById(R.id.nameTv);
            addressTv = (TextView) view.findViewById(R.id.addressTv);

            imgIv = (ImageView) view.findViewById(R.id.imgIv);

            timeTv = (TextView) view.findViewById(R.id.timeTv);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















