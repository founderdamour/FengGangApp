package com.zkhy.fenggang.community.view.main.dj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.DateUtil;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.WechatEntity;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;

/**
 *
 */
public class PropagandaListAdapter extends RecyclerView.Adapter<PropagandaListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<WechatEntity> data = null;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public PropagandaListAdapter(Context context, ArrayList<WechatEntity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_propaganda_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final WechatEntity entity = data.get(position);

        viewHolder.nameTv.setText(StringUtil.getNotNullValue(entity.getTitle()));
        if (entity.getUpdateTime() == null) {
            viewHolder.timeTv.setText("暂无时间");
        } else {
            viewHolder.timeTv.setText(DateUtil.getDateStrByDate(entity.getUpdateTime(), "yyyy年MM月dd日"));
        }

        ImageLoader.show(mContext, entity.getThumbUrl(), viewHolder.imgIv);

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

    public void setData(ArrayList<WechatEntity> data) {
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

    public void addData(ArrayList<WechatEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemRootView;
        public TextView nameTv, timeTv, lastLine;
        public ImageView imgIv;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);
            nameTv = (TextView) view.findViewById(R.id.nameTv);
            timeTv = (TextView) view.findViewById(R.id.timeTv);

            imgIv = (ImageView) view.findViewById(R.id.imgIv);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
        }
    }
}





















