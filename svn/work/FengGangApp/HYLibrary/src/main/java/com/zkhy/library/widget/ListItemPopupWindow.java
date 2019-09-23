package com.zkhy.library.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.zkhy.library.R;

public class ListItemPopupWindow extends PopupWindow {

//    //将像素转换为px
//    public static int dip2px(Context context, float dpValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
//
//    //将px转换为dp
//    public static int px2dp(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
//    }

    public ListItemPopupWindow(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//Color.parseColor("#88000000")
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list_item, null, false);
        setContentView(contentView);

        LinearLayout popView = contentView.findViewById(R.id.popView);
        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListItemPopupWindow.this.dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);// 以屏幕 左上角 为参考系的
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;  //屏幕高度减去 anchor 的 bottom
            setHeight(h);// 重新设置PopupWindow高度
        }
        super.showAsDropDown(anchor);
    }
}