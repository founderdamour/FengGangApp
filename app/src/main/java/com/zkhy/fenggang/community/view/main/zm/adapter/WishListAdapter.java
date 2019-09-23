package com.zkhy.fenggang.community.view.main.zm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.WishEntity;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;

/**
 */
public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private Context mContext = null;

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<WishEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public WishListAdapter(Context context, ArrayList<WishEntity> data) {
        mContext = context;
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_wish_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final WishEntity entity = data.get(position);

        viewHolder.itemTitle.setText(StringUtil.getNotNullValue(entity.getName(), "未填写"));
        viewHolder.itemContentTv.setText(StringUtil.getNotNullValue(entity.getWishContent()));

        // 状态： 0、待审核 1、待认领 2、进行中 3、已完成 4、已退回
        if (entity.getWishStatus() == 0) {
            viewHolder.itemStatusTv.setText("待审核");
        } else if (entity.getWishStatus() == 1) {
            viewHolder.itemStatusTv.setText("待认领");
        } else if (entity.getWishStatus() == 2) {
            viewHolder.itemStatusTv.setText("进行中");
        } else if (entity.getWishStatus() == 3) {
            viewHolder.itemStatusTv.setText("已完成");
        } else if (entity.getWishStatus() == 4) {
            viewHolder.itemStatusTv.setText("已退回");
        }

        ImageLoader.show(mContext, entity.getPhoto(), R.drawable.mr_tx, viewHolder.itemIconIv);

        // 发布时间
        viewHolder.itemTimeTv.setText(entity.getCreateTime());

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

    public void setData(ArrayList<WishEntity> data) {
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

    public void addData(ArrayList<WishEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout itemRootView;
        public TextView itemTitle, itemContentTv, itemStatusTv, itemTimeTv;
        public ImageView itemIconIv;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (RelativeLayout) view.findViewById(R.id.itemRootView);

            itemIconIv = (ImageView) view.findViewById(R.id.itemIconIv);

            itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            itemContentTv = (TextView) view.findViewById(R.id.itemContentTv);
            itemStatusTv = (TextView) view.findViewById(R.id.itemStatusTv);
            itemTimeTv = (TextView) view.findViewById(R.id.itemTimeTv);
        }
    }
}





















