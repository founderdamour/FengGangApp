package com.zkhy.fenggang.community.view.main.bm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.OrderTakeFileEntity;

import java.util.ArrayList;

/**
 */
public class OrderTakeFileListAdapter extends RecyclerView.Adapter<OrderTakeFileListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<OrderTakeFileEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public OrderTakeFileListAdapter(ArrayList<OrderTakeFileEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_order_take_file_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final OrderTakeFileEntity entity = data.get(position);

        viewHolder.flowNameTv.setText(entity.getCodeName());
        viewHolder.applyTimeTv.setText(entity.getAppoDate());
        viewHolder.unitNameTv.setText(entity.getWindowName());

        if (position == data.size() - 1) {
            viewHolder.lastLine.setVisibility(View.VISIBLE);
        } else {
            viewHolder.lastLine.setVisibility(View.GONE);
        }

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

    public void setData(ArrayList<OrderTakeFileEntity> data) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemRootView;

        public TextView lastLine, unitNameTv, flowNameTv, applyTimeTv;

        public ViewHolder(View view) {
            super(view);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            flowNameTv = (TextView) view.findViewById(R.id.flowNameTv);

            unitNameTv = (TextView) view.findViewById(R.id.unitNameTv);
            applyTimeTv = (TextView) view.findViewById(R.id.applyTimeTv);
        }
    }
}





















