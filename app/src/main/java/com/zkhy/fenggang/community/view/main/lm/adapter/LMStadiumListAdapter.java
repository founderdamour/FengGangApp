package com.zkhy.fenggang.community.view.main.lm.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.StadiumEntity;

import java.util.ArrayList;

/**
 */
public class LMStadiumListAdapter extends RecyclerView.Adapter<LMStadiumListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<StadiumEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public LMStadiumListAdapter(ArrayList<StadiumEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lm_stadium_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final StadiumEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getName()));

        if (entity.getPalceStatus() == 0) {
            viewHolder.placeStatusTv.setText("正常运营");
        } else {
            viewHolder.placeStatusTv.setText("异常停用");
        }

        if (entity.isFreeStatus()) {
            viewHolder.freeStatusTv.setText("收费");
            viewHolder.freeStatusTv.setTextColor(Color.parseColor("#EFD036"));
        } else {
            viewHolder.freeStatusTv.setText("免费");
            viewHolder.freeStatusTv.setTextColor(Color.parseColor("#60F282"));
        }

        viewHolder.stadiumAddTv.setText(StringUtil.getNotNullValue(entity.getAddr()));
        viewHolder.galleryTv.setText("可容纳 " + entity.getGallery() + " 人");

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

    public void setData(ArrayList<StadiumEntity> data) {
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

    public void addData(ArrayList<StadiumEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView nameTv, stadiumAddTv, galleryTv, freeStatusTv, placeStatusTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            nameTv = (TextView) view.findViewById(R.id.nameTv);
            stadiumAddTv = (TextView) view.findViewById(R.id.stadiumAddTv);

            galleryTv = (TextView) view.findViewById(R.id.galleryTv);
            freeStatusTv = (TextView) view.findViewById(R.id.freeStatusTv);
            placeStatusTv = (TextView) view.findViewById(R.id.placeStatusTv);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















