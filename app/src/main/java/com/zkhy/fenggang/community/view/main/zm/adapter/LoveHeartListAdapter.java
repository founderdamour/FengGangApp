package com.zkhy.fenggang.community.view.main.zm.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.LoveHeartEntity;

import java.util.ArrayList;

/**
 */
public class LoveHeartListAdapter extends RecyclerView.Adapter<LoveHeartListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<LoveHeartEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public LoveHeartListAdapter(ArrayList<LoveHeartEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_love_heart_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final LoveHeartEntity entity = data.get(position);

        viewHolder.itemTitle.setText(StringUtil.getNotNullValue(entity.getProName(), "未填写"));

        String content = StringUtil.getNotNullValue(entity.getContent());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            viewHolder.itemContentTv.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
        } else {
            viewHolder.itemContentTv.setText(Html.fromHtml(content));
        }

        // 发布时间
        viewHolder.itemTimeTv.setText(entity.getPubTime());

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

    public void setData(ArrayList<LoveHeartEntity> data) {
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

    public void addData(ArrayList<LoveHeartEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView itemTitle, itemContentTv, itemTimeTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            lastLine = (TextView) view.findViewById(R.id.lastLine);

            itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            itemContentTv = (TextView) view.findViewById(R.id.itemContentTv);
            itemTimeTv = (TextView) view.findViewById(R.id.itemTimeTv);
        }
    }
}





















