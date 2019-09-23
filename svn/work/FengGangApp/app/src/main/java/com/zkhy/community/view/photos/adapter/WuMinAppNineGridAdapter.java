package com.zkhy.community.view.photos.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.sinothk.image.show.AppNineGridAdapter;
import com.sinothk.image.show.NineGridAdapter;
import com.zkhy.community.R;

import java.util.ArrayList;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/29 on 15:57
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class WuMinAppNineGridAdapter extends NineGridAdapter<String> {

    private ArrayList<String> imageList;
    private Context context;
    private AppNineGridAdapter.OnItemClickListener itemClickListener;

    public WuMinAppNineGridAdapter(Context context, ArrayList<String> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public int getItemLayoutRes(@LayoutRes int position) {
        if (imageList.size() == 1) {
            return R.layout.item_nine_view_big_image;
        } else {
            return R.layout.item_nine_view_normal;
        }
    }

    @Override
    public void onBindItemView(final int position, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);

        Glide.with(context).load(getItem(position)).placeholder(R.drawable.img_loading_default).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClick(position, imageList);
                }
            }
        });
    }

    @Override
    public String getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public void setData(ArrayList<String> path) {
        imageList.clear();
        imageList.addAll(path);

    }

    public void setOnItemClickListener(AppNineGridAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(int position, ArrayList<String> urlOrFilePathList);
    }
}
