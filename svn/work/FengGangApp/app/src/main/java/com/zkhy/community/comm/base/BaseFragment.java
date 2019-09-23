package com.zkhy.community.comm.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.zkhy.community.R;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/9 on 14:46
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class BaseFragment extends Fragment {

    /**
     * 设置标题
     */
    protected void setTitleBar(View currView, String title) {
        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = currView.findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }
    }

    /**
     * 设置标题
     */
    protected void setTitleBarLeftViewShow(View currView, boolean isDisable) {

        RelativeLayout titleBarLeft = currView.findViewById(R.id.titleBarLeft);
        if (isDisable) {
            titleBarLeft.setVisibility(View.INVISIBLE);
        }else{
            titleBarLeft.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题:左返回，标题，右图标事件
     */
    protected void setTitleBar(View currView, String title, int gonnaImageId, View.OnClickListener gonnaListener) {

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = currView.findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }


        RelativeLayout titleBarLeft = currView.findViewById(R.id.titleBarLeft);
        titleBarLeft.setVisibility(View.INVISIBLE);

        ImageView gonnaBtn = currView.findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = currView.findViewById(R.id.gonnaTxt);
        if (gonnaImageId > 0) {
            gonnaTxt.setVisibility(View.GONE);

            gonnaBtn.setVisibility(View.VISIBLE);
            gonnaBtn.setImageResource(gonnaImageId);

            if (gonnaListener != null) {
                gonnaBtn.setOnClickListener(gonnaListener);
            }
        }
    }

}
