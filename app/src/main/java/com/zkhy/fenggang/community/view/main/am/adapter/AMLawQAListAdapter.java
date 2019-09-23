package com.zkhy.fenggang.community.view.main.am.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.LawQuestionEntity;

import java.util.ArrayList;

/**
 */
public class AMLawQAListAdapter extends RecyclerView.Adapter<AMLawQAListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<LawQuestionEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public AMLawQAListAdapter(ArrayList<LawQuestionEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_am_law_q_a_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    int itemLines = 0;

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final LawQuestionEntity entity = data.get(position);

        viewHolder.lawAValueTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //这个回调会调用多次，获取完行数记得注销监听
                viewHolder.lawAValueTv.getViewTreeObserver().removeOnPreDrawListener(this);

                itemLines = viewHolder.lawAValueTv.getLineCount();
                if (itemLines < 3) {
                    viewHolder.moreIv.setVisibility(View.GONE);
                } else {
                    viewHolder.moreIv.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        viewHolder.lawAValueTv.setText(StringUtil.getNotNullValue(entity.getAnswer()));

        viewHolder.lawQValueTv.setText(StringUtil.getNotNullValue(entity.getQuestion()));
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

        // 更多
        if (entity.isSelected()) {
            viewHolder.moreIv.setImageResource(R.drawable.xs_more);
            viewHolder.lawAValueTv.setMaxLines(100);
        } else {
            viewHolder.moreIv.setImageResource(R.drawable.xx_more);
            viewHolder.lawAValueTv.setMaxLines(3);
        }

        viewHolder.moreIv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (data.get(position).isSelected()) {
                            data.get(position).setSelected(false);
                        } else {
                            for (int i = 0; i < data.size(); i++) {
                                data.get(i).setSelected(false);
                            }

                            data.get(position).setSelected(true);
                        }

                        notifyDataSetChanged();
                    }
                }
        );

//        viewHolder.lawAValueTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                //这个回调会调用多次，获取完行数记得注销监听
//                viewHolder.lawAValueTv.getViewTreeObserver().removeOnPreDrawListener(this);
//
//                // 更多
//                int lines = viewHolder.lawAValueTv.getLineCount();
//
//                if (lines > 2) {
//                    viewHolder.moreIv.setVisibility(View.VISIBLE);
//
//
//                } else {
//                    viewHolder.moreIv.setVisibility(View.GONE);
//                }
//
//
//                return false;
//            }
//        });

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

    public void setData(ArrayList<LawQuestionEntity> data) {
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

    public void addData(ArrayList<LawQuestionEntity> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
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
        public TextView lawQValueTv, lawAValueTv, lastLine;

        public ImageView moreIv;

        public ViewHolder(View view) {
            super(view);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            lawQValueTv = (TextView) view.findViewById(R.id.lawQValueTv);
            lawAValueTv = (TextView) view.findViewById(R.id.lawAValueTv);

            moreIv = (ImageView) view.findViewById(R.id.moreIv);
        }
    }
}





















