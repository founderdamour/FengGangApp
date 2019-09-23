package com.zkhy.fenggang.community.view.main.dj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.sinothk.comm.utils.StringUtil;
import com.zkhy.fenggang.community.R;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/20 on 14:06
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class MeetImagesAdapter extends BaseAdapter {

    private ArrayList<String> provinceBeanList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public MeetImagesAdapter(Context context, ArrayList<String> provinceBeanList, OnItemClickListener onItemClickListener) {
        this.provinceBeanList = provinceBeanList;
        layoutInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_meet_images_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String url = provinceBeanList.get(position);
        if (StringUtil.isNotEmpty(url)) {
            ImageLoader.show(layoutInflater.getContext(), url, R.drawable.comm_default_nor_avatar, holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, provinceBeanList);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

    public interface OnItemClickListener {

        void onItemClick(int position, ArrayList<String> urlList);

    }
}
