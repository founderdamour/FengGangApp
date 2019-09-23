package com.zkhy.fenggang.community.view.main.am.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.PoliceStationEntity;

import java.util.ArrayList;

/**
 */
public class AMPoliceStationListAdapter extends RecyclerView.Adapter<AMPoliceStationListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<PoliceStationEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public AMPoliceStationListAdapter(ArrayList<PoliceStationEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_am_police_station_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final PoliceStationEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getOfficeName()));
        viewHolder.addressTv.setText(StringUtil.getNotNullValue(entity.getAddr()));

        viewHolder.phoneTv.setText(StringUtil.getNotNullValue(entity.getPhone()));
        viewHolder.telephoneTv.setText(StringUtil.getNotNullValue(entity.getTelephone()));

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

    public void setData(ArrayList<PoliceStationEntity> data) {
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

        public LinearLayout itemRootView;
        public TextView nameTv, addressTv, telephoneTv, phoneTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            nameTv = (TextView) view.findViewById(R.id.nameTv);
            addressTv = (TextView) view.findViewById(R.id.addressTv);

            telephoneTv = (TextView) view.findViewById(R.id.telephoneTv);
            phoneTv = (TextView) view.findViewById(R.id.phoneTv);

            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















