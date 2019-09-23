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
import com.zkhy.fenggang.community.model.bean.LawFirmEntity;
import com.zkhy.fenggang.community.model.cache.DataCache;
import com.zkhy.library.utils.UnitUtil;

import java.util.ArrayList;

/**
 */
public class AMLawFirmListAdapter extends RecyclerView.Adapter<AMLawFirmListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<LawFirmEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public AMLawFirmListAdapter(ArrayList<LawFirmEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_am_law_firm_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final LawFirmEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getOrgName()));
        viewHolder.addressTv.setText(StringUtil.getNotNullValue(entity.getAddr()));

        viewHolder.orgTypeTv.setText(StringUtil.getNotNullValue(DataCache.findDictionary(entity.getOrgType())));

        viewHolder.distanceTv.setText(UnitUtil.distanceFormat(entity.getDis()));

        viewHolder.phoneTv.setText(StringUtil.getNotNullValue(entity.getPhone(), "暂无电话"));

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

    public void setData(ArrayList<LawFirmEntity> data) {
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

    public void addData(ArrayList<LawFirmEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView nameTv, addressTv, orgTypeTv, distanceTv, phoneTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            nameTv = (TextView) view.findViewById(R.id.nameTv);
            addressTv = (TextView) view.findViewById(R.id.addressTv);

            orgTypeTv = (TextView) view.findViewById(R.id.orgTypeTv);
            distanceTv = (TextView) view.findViewById(R.id.distanceTv);
            phoneTv = (TextView) view.findViewById(R.id.phoneTv);

            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















