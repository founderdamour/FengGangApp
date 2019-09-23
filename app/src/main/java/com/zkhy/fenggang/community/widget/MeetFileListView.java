package com.zkhy.fenggang.community.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.AttachmentEntity;
import com.zkhy.library.utils.UnitUtil;

import java.util.List;

/**
 * <pre>
 *  @author 梁玉涛 (https://github.com/sinothk)
 *  @Create 2018/2/10 10:01
 *  @Project: UIPluginLib
 *  @Description: 商城中，产品详情介绍
 *  @Update:
 * <pre>
 */
public class MeetFileListView extends LinearLayout {

    public MeetFileListView(Context context) {
        super(context);
    }

    public MeetFileListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MeetFileListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MeetFileListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(final List<AttachmentEntity> subList, final OnItemClickListener listener) {

        this.removeAllViews();

        if (subList == null || subList.size() == 0) {
            return;
        }
        for (int i = 0; i < subList.size(); i++) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.meet_detail_file_list, null);

            TextView fileNameTv = view.findViewById(R.id.fileNameTv);
            fileNameTv.setText(StringUtil.getNotNullValue(subList.get(i).getSubmittedFileName(), "未知文件"));

            RelativeLayout itemView = view.findViewById(R.id.itemView);

            final int finalI = i;

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(subList.get(finalI));
                }
            });

            try {
                TextView fileSizeTv = view.findViewById(R.id.fileSizeTv);
                fileSizeTv.setText(UnitUtil.getFileSize(subList.get(i).getSize()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.addView(view, i);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(AttachmentEntity entity);
    }
}
