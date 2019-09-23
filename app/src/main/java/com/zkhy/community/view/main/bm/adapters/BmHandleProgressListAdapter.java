package com.zkhy.community.view.main.bm.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.haozhang.lib.SlantedTextView;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.BizType;
import com.zkhy.community.model.bean.DepartmentEntity;
import com.zkhy.community.model.bean.HandleProgressEntity;

import java.util.ArrayList;

/**
 */
public class BmHandleProgressListAdapter extends RecyclerView.Adapter<BmHandleProgressListAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public ArrayList<HandleProgressEntity> data = null;
    private ItemClickCallBack clickCallBack;

    public BmHandleProgressListAdapter(ArrayList<HandleProgressEntity> data) {
        this.data = data;
    }

    //创建新View，被LayoutManager所调用
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bm_handle_progress_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final HandleProgressEntity entity = data.get(position);

        //申请流程类型id(1:高龄补贴,2:一孩生育登记,3:二孩生育登记,4:汇川区残疾人证发放和管理)

        String flowName = "流程未定义-" + entity.getFlowId();
        if (entity.getFlowId() == 1) {
            flowName = "高龄补贴";
        } else if (entity.getFlowId() == 2) {
            flowName = "一孩生育登记";
        } else if (entity.getFlowId() == 3) {
            flowName = "二孩生育登记";
        } else if (entity.getFlowId() == 4) {
            flowName = "汇川区残疾人证发放和管理";
        } else if (entity.getFlowId() == 5) {
            flowName = "居住证明";
        } else if (entity.getFlowId() == 6) {
            flowName = "关系证明";
        } else if (entity.getFlowId() == 7) {
            flowName = "政审证明";
        } else if (entity.getFlowId() == 8) {
            flowName = "文化户口登记";
        } else if (entity.getFlowId() == 9) {
            flowName = "小孩入学登记";

        } else if (entity.getFlowId() == 10) {
            flowName = "零就业家庭认定";

        } else if (entity.getFlowId() == 11) {
            flowName = "就业失业证办理";

        } else if (entity.getFlowId() == 12) {
            flowName = "就业困难对象认定";


        } else if (entity.getFlowId() == 13) {
            flowName = "再生育(三孩)子女审批";
        } else if (entity.getFlowId() == 14) {
            flowName = "特扶对象扶助";
        } else if (entity.getFlowId() == 15) {
            flowName = "医疗救助";
        } else if (entity.getFlowId() == 16) {
            flowName = "临时救助";
        } else if (entity.getFlowId() == 17) {
            flowName = "特困人员供养";
        } else if (entity.getFlowId() == 18) {
            flowName = "城乡低保办理";

        } else if (entity.getFlowId() == BizType.FlowType.FLOW_19) {
            flowName = "流动人口婚育证明";
        } else if (entity.getFlowId() == BizType.FlowType.FLOW_20) {
            flowName = "诚信计生证明";
        } else if (entity.getFlowId() == BizType.FlowType.FLOW_21) {
            flowName = "户籍接收证明";
        } else if (entity.getFlowId() == BizType.FlowType.FLOW_22) {
            flowName = "死亡证明";
        } else if (entity.getFlowId() == BizType.FlowType.FLOW_23) {
            flowName = "家庭生活困难证明";
        }

        viewHolder.flowNameTv.setText(flowName);

        //未审核、已通过、未通过状态 审批状态（最新，0：提交，1：通过，2、驳回） + 1
        if (entity.getApprStatus() == 1) {
            viewHolder.topFlagTv.setText("已通过");
            viewHolder.topFlagTv.setSlantedBackgroundColor(Color.parseColor("#3FC63A"));

        } else if (entity.getApprStatus() == 2) {
            viewHolder.topFlagTv.setText("已驳回");
            viewHolder.topFlagTv.setSlantedBackgroundColor(Color.parseColor("#F52F07"));

        } else {
            viewHolder.topFlagTv.setText("未审核");
            viewHolder.topFlagTv.setSlantedBackgroundColor(Color.parseColor("#4BA5FB"));
        }

        viewHolder.applyTimeTv.setText(entity.getApplyTime());
        viewHolder.unitNameTv.setText(entity.getHandleDeptName());

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

    public void setData(ArrayList<HandleProgressEntity> data) {
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

    public void addData(ArrayList<HandleProgressEntity> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }

        this.data.addAll(listData);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout itemRootView;

        public TextView lastLine, unitNameTv, flowNameTv, applyTimeTv;
        public SlantedTextView topFlagTv;

        public ViewHolder(View view) {
            super(view);
            lastLine = (TextView) view.findViewById(R.id.lastLine);
            itemRootView = (RelativeLayout) view.findViewById(R.id.itemRootView);

            flowNameTv = (TextView) view.findViewById(R.id.flowNameTv);

            unitNameTv = (TextView) view.findViewById(R.id.unitNameTv);
            applyTimeTv = (TextView) view.findViewById(R.id.applyTimeTv);

            topFlagTv = (SlantedTextView) view.findViewById(R.id.topFlagTv);
        }
    }
}





















