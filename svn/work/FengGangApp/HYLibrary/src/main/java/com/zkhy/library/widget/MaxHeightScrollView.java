package com.zkhy.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * <pre>
 *  创建:  LiangYT 2018/9/6/006 on 14:10
 *  项目: gxqAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class MaxHeightScrollView extends ScrollView {

    private Context mContext;

    public MaxHeightScrollView(
            Context context)

    {
        super(context);
        init(context);
    }

    public MaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MaxHeightScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            // 获得屏幕高度
            WindowManager windowManager = (WindowManager) mContext.getSystemService("window");
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int screenHeight = outMetrics.heightPixels;

            //此处是关键，设置控件高度不能超过屏幕高度一半（d.heightPixels / 2）（在此替换成自己需要的高度）
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(screenHeight / 3, MeasureSpec.AT_MOST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
