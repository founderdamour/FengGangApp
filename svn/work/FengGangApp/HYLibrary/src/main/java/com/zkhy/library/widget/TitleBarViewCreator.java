package com.zkhy.library.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.zkhy.library.R;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 16:13
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class TitleBarViewCreator {

    public static View createTitleLC(final Activity mActivity, String title) {
        View titleView = LayoutInflater.from(mActivity).inflate(R.layout.title_bar_normal, null);

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = titleView.findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        // 返回提示
        RelativeLayout titleBarLeft = titleView.findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        return titleView;
    }

    public static View createTitleLCR(final Activity mActivity, String title, String rTxt, View.OnClickListener listener) {
        View titleView = LayoutInflater.from(mActivity).inflate(R.layout.title_bar_normal, null);

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = titleView.findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        // 返回提示
        RelativeLayout titleBarLeft = titleView.findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });

        ImageView gonnaBtn = titleView.findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = titleView.findViewById(R.id.gonnaTxt);

        if (StringUtil.isNotEmpty(rTxt)) {
            gonnaBtn.setVisibility(View.GONE);
            gonnaTxt.setVisibility(View.VISIBLE);

            gonnaTxt.setText(rTxt);

            if (listener != null) {
                gonnaTxt.setOnClickListener(listener);
            }
        }

        return titleView;
    }

    public static View createTitleLCR(final Activity mActivity, String title, int imgResId, View.OnClickListener listener) {
        View titleView = LayoutInflater.from(mActivity).inflate(R.layout.title_bar_normal, null);

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = titleView.findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        // 返回提示
        RelativeLayout titleBarLeft = titleView.findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });

        ImageView gonnaBtn = titleView.findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = titleView.findViewById(R.id.gonnaTxt);

        gonnaBtn.setVisibility(View.VISIBLE);
        gonnaTxt.setVisibility(View.GONE);

        gonnaBtn.setImageResource(imgResId);

        if (listener != null) {
            gonnaBtn.setOnClickListener(listener);
        }

        return titleView;
    }
}
