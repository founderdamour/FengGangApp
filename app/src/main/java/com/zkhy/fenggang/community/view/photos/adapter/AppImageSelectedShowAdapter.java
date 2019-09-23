package com.zkhy.fenggang.community.view.photos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.ImgSelectEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/29 on 11:24
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppImageSelectedShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_ADD = 0;//添加图片
    private static int TYPE_COMMON = 1;//普通图片展示
    private Context context;
    private LayoutInflater mLayoutInflater;
    //data
    private int mMaxAlbum;//最大选择图片的数量
    private List<ImgSelectEntity> imgSelectList;//图片url集合

    public AppImageSelectedShowAdapter(Context context, int maxAlbum) {
        this.context = context;
        this.mMaxAlbum = maxAlbum;
        this.mLayoutInflater = LayoutInflater.from(context);

        this.imgSelectList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD) {
            return new AppImageSelectedShowAdapter.ItemViewHolderAdd(mLayoutInflater.inflate(R.layout.image_selected_view_add, parent, false));
        } else {
            return new AppImageSelectedShowAdapter.ItemViewHolderCommon(mLayoutInflater.inflate(R.layout.image_selected_view_common, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        View itemView = null;
        if (holder instanceof AppImageSelectedShowAdapter.ItemViewHolderAdd) {

            AppImageSelectedShowAdapter.ItemViewHolderAdd itemViewHolderAdd = (AppImageSelectedShowAdapter.ItemViewHolderAdd) holder;
            if (position >= mMaxAlbum) {
                itemViewHolderAdd.itemView.setVisibility(View.GONE);
            } else {
//                itemViewHolderAdd.tvNum.setText(position + "/" + mMaxAlbum);
                itemViewHolderAdd.itemView.setVisibility(View.VISIBLE);

                itemView = ((AppImageSelectedShowAdapter.ItemViewHolderAdd) holder).itemView;
            }

        } else if (holder instanceof AppImageSelectedShowAdapter.ItemViewHolderCommon) {
            String url = imgSelectList.get(position).getFilePath();

            Glide.with(context).load(new File(url)).placeholder(R.drawable.img_loading_default).into(((AppImageSelectedShowAdapter.ItemViewHolderCommon) holder).ivCommon);
            itemView = ((AppImageSelectedShowAdapter.ItemViewHolderCommon) holder).itemView;

            ((AppImageSelectedShowAdapter.ItemViewHolderCommon) holder).delImgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemDelClick != null) {
                        int position = holder.getLayoutPosition();
                        itemDelClick.onItemDelClick(position);
                    }
                }
            });

        }
        if (mOnItemClickListener != null && null != itemView) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == imgSelectList.size() ? TYPE_ADD : TYPE_COMMON;
    }

    @Override
    public int getItemCount() {
        if (imgSelectList.size() < mMaxAlbum) {
            return imgSelectList.size() + 1;//加一代表最后一个添加图片按钮
        } else {
            return imgSelectList.size();//加一代表最后一个添加图片按钮,已是最大数据则隐藏添加按钮
        }
    }

    public void setData(ArrayList<ImgSelectEntity> path) {
        if (path == null) {
            path = new ArrayList<>();
        }

        if (this.imgSelectList != null) {
            this.imgSelectList.clear();
        }

        this.imgSelectList.addAll(path);
        notifyDataSetChanged();
    }

    public static class ItemViewHolderAdd extends RecyclerView.ViewHolder {
//        private TextView tvNum;

        public ItemViewHolderAdd(View itemView) {
            super(itemView);
//            tvNum = itemView.findViewById(R.id.tv_album_selected_num);
        }
    }

    public static class ItemViewHolderCommon extends RecyclerView.ViewHolder {
        private ImageView ivCommon;
        private ImageView delImgBtn;

        public ItemViewHolderCommon(View itemView) {
            super(itemView);
            ivCommon = itemView.findViewById(R.id.iv_album_selected);
            delImgBtn = itemView.findViewById(R.id.delImgBtn);
        }
    }

    private AppImageSelectedShowAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AppImageSelectedShowAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    private AppImageSelectedShowAdapter.OnItemDelClickListener itemDelClick;

    public void setItemDelClick(AppImageSelectedShowAdapter.OnItemDelClickListener itemDelClick) {
        this.itemDelClick = itemDelClick;
    }

    public interface OnItemDelClickListener {
        void onItemDelClick(int position);
    }
}
