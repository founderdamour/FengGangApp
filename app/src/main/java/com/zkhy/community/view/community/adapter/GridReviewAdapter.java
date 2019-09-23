package com.zkhy.community.view.community.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zkhy.community.R;

import java.util.ArrayList;
import java.util.List;

public class GridReviewAdapter extends RecyclerView.Adapter<GridReviewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> data;

    public GridReviewAdapter(Context context) {
        mContext = context;
        data = new ArrayList<>();
    }

    public void setData(List<String> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_review_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemNameTv.setText(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTv = itemView.findViewById(R.id.itemNameTv);
        }
    }
}
