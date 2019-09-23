package com.zkhy.comm.plugin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.comm.plugin.entity.KValueEntity;
import com.zkhy.community.R;

import java.util.ArrayList;

/**
 */
public class KeyValueListAdapter extends RecyclerView.Adapter<KeyValueListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<KValueEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public KeyValueListAdapter(ArrayList<KValueEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_nation_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final KValueEntity entity = data.get(position);

        viewHolder.itemTitle.setText(entity.getValue());

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
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<KValueEntity> data) {
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

        public RelativeLayout itemRootView;
        public TextView itemTitle;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (RelativeLayout) view.findViewById(R.id.itemRootView);
            itemTitle = (TextView) view.findViewById(R.id.itemTitle);
        }
    }
}





















