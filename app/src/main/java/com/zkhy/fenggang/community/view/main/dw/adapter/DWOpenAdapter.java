package com.zkhy.fenggang.community.view.main.dw.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sinothk.comm.utils.DateUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.DWOpenListEntity;

import java.util.ArrayList;
import java.util.List;

public class DWOpenAdapter extends RecyclerView.Adapter<DWOpenAdapter.ViewHolder> {

    private Context mContent;
    private ItemClickCallBack<DWOpenListEntity> itemClickCallBack;
    private List<DWOpenListEntity> data;

    public DWOpenAdapter(Context context) {
        mContent = context;
        data = new ArrayList<>();
    }

    public void setItemClickCallBack(ItemClickCallBack<DWOpenListEntity> itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public void setData(List<DWOpenListEntity> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<DWOpenListEntity> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContent).inflate(R.layout.zw_open_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(mContent).load(data.get(i).getUrl()).error(R.mipmap.default_error).placeholder(R.mipmap.default_error).into(viewHolder.itemIconIv);
        viewHolder.itemContentTv.setText(data.get(i).getTitle());
        viewHolder.itemTimeTv.setText(DateUtil.getDateStrByDate(data.get(i).getStartTime(), "yyyy-MM-dd"));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickCallBack != null) {
                    itemClickCallBack.onItemClick(i, data.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemIconIv;
        TextView itemContentTv, itemTimeTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIconIv = itemView.findViewById(R.id.itemIconIv);
            itemContentTv = itemView.findViewById(R.id.itemContentTv);
            itemTimeTv = itemView.findViewById(R.id.itemTimeTv);
        }
    }

}
