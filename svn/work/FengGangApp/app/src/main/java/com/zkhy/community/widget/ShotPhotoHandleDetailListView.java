package com.zkhy.community.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.DateUtil;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.image.selector.PhotoPreviewActivity;
import com.sinothk.image.show.AppNineGridAdapter;
import com.sinothk.image.show.NineGridView;
import com.zkhy.community.R;
import com.zkhy.community.model.bean.AttachmentEntity;
import com.zkhy.community.model.bean.BizType;
import com.zkhy.community.model.bean.BmShotTaskDeal;
import com.zkhy.community.model.bean.BmShotTaskInfoDTO;
import com.zkhy.community.view.main.dj.AppPhotoPreviewActivity;
import com.zkhy.community.view.photos.adapter.WuMinAppNineGridAdapter;
import com.zkhy.library.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 *  @author 梁玉涛 (https://github.com/sinothk)
 *  @Create 2018/2/10 10:01
 *  @Project: UIPluginLib
 *  @Description: 商城中，产品详情介绍
 *  @Update:
 * <pre>
 */
public class ShotPhotoHandleDetailListView extends LinearLayout {

    public ShotPhotoHandleDetailListView(Context context) {
        super(context);
    }

    public ShotPhotoHandleDetailListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ShotPhotoHandleDetailListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShotPhotoHandleDetailListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(final Activity mActivity, final List<BmShotTaskDeal> subList, final OnItemClickListener listener) {
        this.removeAllViews();

        if (subList == null || subList.size() == 0) {
            return;
        }

        for (int i = 0; i < subList.size(); i++) {

            BmShotTaskDeal info = subList.get(i);

            View view = LayoutInflater.from(getContext()).inflate(R.layout.shot_photo_detail_wc_list, null);
            // 完成部分
            LinearLayout doneItem = view.findViewById(R.id.doingItem);
            TextView donePoint = view.findViewById(R.id.donePoint);
            TextView doneTimeTv = view.findViewById(R.id.doneTimeTv);
            LinearLayout doneLine = view.findViewById(R.id.doneLine);
            TextView doneContentTv = view.findViewById(R.id.doneContentTv);
            LinearLayout doneImgItem = view.findViewById(R.id.doneImgItem);
            NineGridView done9GridView = view.findViewById(R.id.done9GridView);

            LinearLayout checkView = view.findViewById(R.id.checkView);
            TextView checkPoint = view.findViewById(R.id.checkPoint);
            TextView checkTimeTv = view.findViewById(R.id.checkTimeTv);
            TextView checkResultTv = view.findViewById(R.id.checkResultTv);
            LinearLayout checkLine = view.findViewById(R.id.checkLine);
            TextView checkContentTv = view.findViewById(R.id.checkContentTv);

            // 处理完成
            doneTimeTv.setText(DateUtil.getDateStrByDate(info.getDoneTime(), "yyyy-MM-dd HH:mm"));
            doneContentTv.setText(StringUtil.getNotNullValue(info.getDoneDesc()));

            if (info.getAttachments() == null || info.getAttachments().isEmpty()
                    || info.getAttachments().get(BizType.WU_BM_SHOT_WORKSCENEDEAL) == null
                    || Objects.requireNonNull(info.getAttachments().get(BizType.WU_BM_SHOT_WORKSCENEDEAL)).size() == 0) {

                doneImgItem.setVisibility(GONE);

            } else {
                List<AttachmentEntity> contentImgList = info.getAttachments().get(BizType.WU_BM_SHOT_WORKSCENEDEAL);

                if (contentImgList != null && !contentImgList.isEmpty()) {

                    ArrayList<String> imgList = new ArrayList<>();

                    for (AttachmentEntity imgEntity : contentImgList) {
                        imgList.add(imgEntity.getUrl());
                    }

                    WuMinAppNineGridAdapter done9GridAdapter = new WuMinAppNineGridAdapter(mActivity, imgList);
                    done9GridView.setNineGridAdapter(done9GridAdapter);

                    done9GridAdapter.setOnItemClickListener(new AppNineGridAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(int position, ArrayList<String> urlOrFilePathList) {
                            PhotoPreviewActivity.start(mActivity, position, urlOrFilePathList);
                        }
                    });

                    doneImgItem.setVisibility(VISIBLE);
                } else {
                    doneImgItem.setVisibility(GONE);
                }
            }

            // ======================================================================== 处理审核部分
            if (info.getApprovalStatus() == 0) { //审核状态(0未审核,1通过,2不通过)

                donePoint.setBackgroundResource(R.drawable.shape_point_flow_doing);
                doneLine.setBackgroundResource(R.color.white);
                // 审核部分
                checkView.setVisibility(GONE);

            } else {// 1通过,2不通过
                donePoint.setBackgroundResource(R.drawable.shape_point_flow_done);
                doneLine.setBackgroundResource(R.color.tc_list_sub);

                if (i == subList.size() - 1) {
                    checkPoint.setBackgroundResource(R.drawable.shape_point_flow_doing);
                    checkLine.setBackgroundResource(R.color.white);
                } else {
                    checkPoint.setBackgroundResource(R.drawable.shape_point_flow_done);
                    checkLine.setBackgroundResource(R.color.tc_list_sub);
                }

                // 审核部分
                checkView.setVisibility(VISIBLE);

                checkTimeTv.setText(DateUtil.getDateStrByDate(info.getApprovalTime(), "yyyy-MM-dd HH:mm"));
                checkContentTv.setText(StringUtil.getNotNullValue(info.getApprovalDesc()));

                if (info.getApprovalStatus() == 1) {
                    checkResultTv.setVisibility(VISIBLE);
                    checkResultTv.setText("通过");
                    checkResultTv.setBackgroundResource(R.color.status_success);
                } else if (info.getApprovalStatus() == 2) {
                    checkResultTv.setVisibility(VISIBLE);
                    checkResultTv.setText("不通过");
                    checkResultTv.setBackgroundResource(R.color.level_1);
                } else {
                    checkResultTv.setVisibility(GONE);
                }
            }
            this.addView(view, i);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(AttachmentEntity entity);
    }
}
