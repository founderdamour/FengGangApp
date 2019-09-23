package com.zkhy.fenggang.community.view.main.bm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.ConvenienceQueryEntity;

import java.util.ArrayList;

/**
 */
public class ConvenienceQueryListAdapter extends RecyclerView.Adapter<ConvenienceQueryListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<ConvenienceQueryEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public ConvenienceQueryListAdapter(ArrayList<ConvenienceQueryEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_convenience_query_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ConvenienceQueryEntity entity = data.get(position);

        viewHolder.serviceSubTypeTv.setText(StringUtil.getNotNullValue(entity.getInformDesc()));
        viewHolder.personInfoTv.setText(StringUtil.getNotNullValue(entity.getPubUserName() + "  " + entity.getPubUserPhone()));

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

    public void setData(ArrayList<ConvenienceQueryEntity> data) {
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

    public void addData(ArrayList<ConvenienceQueryEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout itemRootView;
        public TextView serviceSubTypeTv, personInfoTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (RelativeLayout) view.findViewById(R.id.itemRootView);

            serviceSubTypeTv = (TextView) view.findViewById(R.id.serviceSubTypeTv);
            personInfoTv = (TextView) view.findViewById(R.id.personInfoTv);

            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















