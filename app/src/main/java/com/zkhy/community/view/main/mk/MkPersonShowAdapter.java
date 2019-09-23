package com.zkhy.community.view.main.mk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.MkMonitoringSystemDetailEntity;
import com.zkhy.community.model.bean.MkPersonShowEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 煤矿检测系统矿点适配器
 */
public class MkPersonShowAdapter extends RecyclerView.Adapter<MkPersonShowAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<MkPersonShowEntity> data;
    private ItemClickCallBack clickCallBack;
    private Context mContext;

    public MkPersonShowAdapter(Context context, ArrayList<MkPersonShowEntity> data) {
        this.data = data;
        mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mk_person_show_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final MkPersonShowEntity entity = data.get(position);
        viewHolder.deptTv.setText(entity.getDept());
        if (!TextUtils.isEmpty(entity.getName())) {
            String name = entity.getName().substring(0, entity.getName().length() - 1);
            String[] names = name.split(",");
            viewHolder.numTv.setText(names.length + "");
            viewHolder.nameTv.setText(name);
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<MkPersonShowEntity> data) {
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

    public void addData(ArrayList<MkPersonShowEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView deptTv, numTv, nameTv;

        public ViewHolder(View view) {
            super(view);
            deptTv = view.findViewById(R.id.deptTv);
            numTv = view.findViewById(R.id.numTv);
            nameTv = view.findViewById(R.id.nameTv);
        }
    }
}





















