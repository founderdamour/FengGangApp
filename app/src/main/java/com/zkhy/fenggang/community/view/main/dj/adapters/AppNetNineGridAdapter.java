package com.zkhy.fenggang.community.view.main.dj.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.sinothk.image.show.NineGridAdapter;
import com.zkhy.fenggang.community.R;

import java.util.ArrayList;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/6 on 20:42
 *  项目:  ImageSelectorLib
 *  描述:
 *  更新:
 * <pre>
 */
public class AppNetNineGridAdapter extends NineGridAdapter<String> {

    private ArrayList<String> imageList;
    private Context context;
    private com.sinothk.image.show.AppNineGridAdapter.OnItemClickListener itemClickListener;

    public AppNetNineGridAdapter(Context context, ArrayList<String> imageList) {
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
        ImageView imageView = view.findViewById(R.id.image_view);

        Glide.with(context).load(getItem(position)).placeholder(R.color.app_bg).into(imageView);

        imageView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.OnItemClick(position, imageList);
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

    public void setOnItemClickListener(com.sinothk.image.show.AppNineGridAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(int position, ArrayList<String> urlOrFilePathList);
    }
}
