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
import com.zkhy.community.model.bean.DictionaryEntity;
import com.zkhy.community.model.bean.PoliceStationEntity;

import java.util.ArrayList;

/**
 */
public class AMLawQAClassListAdapter extends RecyclerView.Adapter<AMLawQAClassListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<DictionaryEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public AMLawQAClassListAdapter(ArrayList<DictionaryEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_am_law_q_a_class_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final DictionaryEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getName()));
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

    public void setData(ArrayList<DictionaryEntity> data) {
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
        public TextView nameTv, lastLine;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            nameTv = (TextView) view.findViewById(R.id.nameTv);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















