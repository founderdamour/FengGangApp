package com.zkhy.community.view.main.km.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.sinothk.view.image.rounded.RoundedImageView;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.DocQAReplyBean;
import com.zkhy.community.model.bean.LawQuestionReplyBean;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghejie on 15/11/26.
 */
public class DocQuestionReplyAdapter extends RecyclerView.Adapter<DocQuestionReplyAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<DocQAReplyBean> data = null;
    private ItemClickCallBack clickCallBack;

    private Context mContext;

    public DocQuestionReplyAdapter(Context context, ArrayList<DocQAReplyBean> datas) {
        this.data = datas;
        this.mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doc_consult_details_reply_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.userNameTv.setText(data.get(position).getAnswerUserName());
        viewHolder.timeTv.setText(data.get(position).getConsTime());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            viewHolder.contentTv.setText(Html.fromHtml(data.get(position).getQuestion(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            viewHolder.contentTv.setText(Html.fromHtml(data.get(position).getQuestion()));
        }

        ImageLoader.show(mContext, data.get(position).getAnswerUserPhoto(), R.drawable.mr_tx, viewHolder.userAvatarIv);

//        viewHolder.likeBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (clickCallBack != null) {
//                            clickCallBack.onItemClick(100, data.get(position));
//                        }
//                    }
//                }
//        );
//
//        viewHolder.statusBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (clickCallBack != null) {
//                            clickCallBack.onItemClick(200, data.get(position));
//                        }
//                    }
//                }
//        );
//
//        viewHolder.addBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (clickCallBack != null) {
//                            clickCallBack.onItemClick(300, data.get(position));
//                        }
//                    }
//                }
//        );

        viewHolder.itemRootView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack != null) {
                            clickCallBack.onItemClick(position, data.get(position));
                        }
                    }
                }
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<? extends DocQAReplyBean> dataList) {
        data.clear();

        if (dataList != null && dataList.size() > 0) {
            data.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTv, timeTv, contentTv, likeBtn, statusBtn, addBtn;
        RoundedImageView userAvatarIv;
        public LinearLayout itemRootView;

        public ViewHolder(View view) {
            super(view);
            itemRootView = (LinearLayout) view.findViewById(R.id.itemRootView);

            userAvatarIv = (RoundedImageView) view.findViewById(R.id.userAvatarIv);

            userNameTv = (TextView) view.findViewById(R.id.userNameTv);
            timeTv = (TextView) view.findViewById(R.id.timeTv);
            contentTv = (TextView) view.findViewById(R.id.contentTv);

            likeBtn = (TextView) view.findViewById(R.id.likeBtn);
            statusBtn = (TextView) view.findViewById(R.id.statusBtn);
            addBtn = (TextView) view.findViewById(R.id.addBtn);
        }
    }
}





















