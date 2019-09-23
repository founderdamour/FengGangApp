package com.zkhy.community.view.main.dj.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.DateUtil;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.PartyPayInfoEntity;

import java.util.ArrayList;
import java.util.Date;

/**
 */
public class PartyCostPayHistoryAdapter extends RecyclerView.Adapter<PartyCostPayHistoryAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<PartyPayInfoEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public PartyCostPayHistoryAdapter(ArrayList<PartyPayInfoEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_party_cost_pay_history_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final PartyPayInfoEntity entity = data.get(position);

        viewHolder.payNumberTv.setText(StringUtil.getNotNullValue(entity.getOrgId()));

        viewHolder.payBaseTv.setText(StringUtil.getNotNullValue(entity.getBaseSalary()));
        viewHolder.payableAmoutTv.setText(StringUtil.getNotNullValue(entity.getPayableAmout(), "0") + "元");

        // 缴费方式
        String payModel = "线下";//缴费方式(1、支付宝支付，2：微信支付，3、线下)
        if (entity.getPayModel() == 1) {
            viewHolder.payNumberItem.setVisibility(View.VISIBLE);
            payModel = "支付宝支付";
        } else if (entity.getPayModel() == 2) {
            payModel = "微信支付";
            viewHolder.payNumberItem.setVisibility(View.VISIBLE);
        } else {
            payModel = "线下";
            viewHolder.payNumberItem.setVisibility(View.GONE);
        }
        viewHolder.payModelTv.setText(payModel);

        // 缴费方式
        String payType = "按照比例";//缴费类型(1:按照比例,2固定金额)
        if (entity.getPayType() == 2) {
            payType = "固定金额";
        } else {
            payType = "按照比例";
        }
        viewHolder.payTypeTv.setText(payType);

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

        String time = entity.getUpdateTime().substring(0, 10);
        try {
            Date day = DateUtil.getDateByDateStr(time, "yyyy-MM-dd");
            String dateStr = DateUtil.getDateStrByDate(day, "yyyy年MM月dd日");
            viewHolder.payTimeTv.setText(dateStr);
        } catch (Exception e) {
            viewHolder.payTimeTv.setText(time);
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

        public LinearLayout itemRootView, payNumberItem;

        public TextView payNumberTv, payTimeTv, payBaseTv, payTypeTv, payableAmoutTv, payModelTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            lastLine = (TextView) view.findViewById(R.id.lastLine);

            payNumberItem = (LinearLayout) view.findViewById(R.id.payNumberItem);
            payNumberTv = (TextView) view.findViewById(R.id.payNumberTv);
            payTimeTv = (TextView) view.findViewById(R.id.payTimeTv);
            payBaseTv = (TextView) view.findViewById(R.id.payBaseTv);
            payTypeTv = (TextView) view.findViewById(R.id.payTypeTv);
            payableAmoutTv = (TextView) view.findViewById(R.id.payableAmoutTv);
            payModelTv = (TextView) view.findViewById(R.id.payModelTv);
        }
    }
}





















