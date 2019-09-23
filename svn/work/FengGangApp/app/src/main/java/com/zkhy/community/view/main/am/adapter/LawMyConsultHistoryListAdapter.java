package com.zkhy.community.view.main.am.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.LawQuestionEntity;

import java.util.ArrayList;

/**
 */
public class LawMyConsultHistoryListAdapter extends RecyclerView.Adapter<LawMyConsultHistoryListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<LawQuestionEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public LawMyConsultHistoryListAdapter(ArrayList<LawQuestionEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_am_law_my_consult_history_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final LawQuestionEntity entity = data.get(position);

        viewHolder.itemTitleTv.setText(StringUtil.getNotNullValue(entity.getQuestion()));
        viewHolder.itemTimeTv.setText(StringUtil.getNotNullValue(entity.getConsTime()));
        viewHolder.itemTotalTv.setText(StringUtil.getNotNullValue(entity.getAnsCount()));

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

    public void setData(ArrayList<LawQuestionEntity> data) {
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

    public void addData(ArrayList<LawQuestionEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView itemTitleTv, itemTimeTv, itemTotalTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            lastLine = (TextView) view.findViewById(R.id.lastLine);

            itemTitleTv = (TextView) view.findViewById(R.id.itemTitleTv);
            itemTimeTv = (TextView) view.findViewById(R.id.itemTimeTv);
            itemTotalTv = (TextView) view.findViewById(R.id.itemTotalTv);
        }
    }
}





















