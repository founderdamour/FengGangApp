package com.zkhy.fenggang.community.view.main.dj.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.PartyPayInfoEntity;

import java.util.ArrayList;

/**
 */
public class PartyCostUnpaidListAdapter extends RecyclerView.Adapter<PartyCostUnpaidListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<PartyPayInfoEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public PartyCostUnpaidListAdapter(ArrayList<PartyPayInfoEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_party_cost_upaid_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final PartyPayInfoEntity entity = data.get(position);

        if (StringUtil.isNotEmpty(entity.getYearMonth())) {
            String yyyy = entity.getYearMonth().substring(0, 4);
            String MM = entity.getYearMonth().substring(4, 6);

            viewHolder.payDateTv.setText(yyyy + "月" + MM + "月份");
        }

        viewHolder.payAmountTv.setText(StringUtil.getNotNullValue(entity.getPayableAmout(), "0.00") + " 元");

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

    public void setData(ArrayList<PartyPayInfoEntity> data) {
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
        public TextView payDateTv, payAmountTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            lastLine = (TextView) view.findViewById(R.id.lastLine);

            payDateTv = (TextView) view.findViewById(R.id.payDateTv);
            payAmountTv = (TextView) view.findViewById(R.id.payAmountTv);
        }
    }
}





















