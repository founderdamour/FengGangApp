package com.zkhy.fenggang.community.view.main.px.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinothk.comm.utils.DateUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.TrainEmploymentEntity;

import java.util.ArrayList;

/**
 * 培训列表适配器
 */
public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<TrainEmploymentEntity> data;
    private ItemClickCallBack clickCallBack;

    public TrainListAdapter(ArrayList<TrainEmploymentEntity> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_train_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(data.get(i).getTitle());
        viewHolder.itemTimeTv.setText(DateUtil.getDateStrByDate(data.get(i).getPublishTime(), "yyyy-MM-dd"));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCallBack != null) {
                    clickCallBack.onItemClick(i, data.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<TrainEmploymentEntity> data) {
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

    public void addData(ArrayList<TrainEmploymentEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }
        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, itemTimeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemTimeTv = itemView.findViewById(R.id.itemTimeTv);
        }
    }
}
