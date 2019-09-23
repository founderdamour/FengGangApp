package com.zkhy.library.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StatusBarUtil;
import com.sinothk.comm.utils.StringUtil;
import com.zkhy.library.R;
import com.zkhy.library.utils.ActivityUtil;

public abstract class TitleBarBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActivityUtil.addActivity(this);

        setTitleTransparencyBar();
    }

    protected void setTitleTransparencyBar() {
        StatusBarUtil.transparencyBar(this);
    }

    protected abstract int getLayout();

    protected void setTitleBar(String title) {
        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = findViewById(R.id.gonnaTxt);

        gonnaBtn.setVisibility(View.GONE);
        gonnaTxt.setVisibility(View.INVISIBLE);
    }

    protected void setTitleBar(String title, View.OnClickListener listener) {
        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(listener);

        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = findViewById(R.id.gonnaTxt);

        gonnaBtn.setVisibility(View.GONE);
        gonnaTxt.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置标题:左返回，标题，右文本事件
     */
    protected void setTitleBar(String title, String rightBtnTxt, View.OnClickListener listener) {

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = findViewById(R.id.gonnaTxt);

        if (StringUtil.isNotEmpty(rightBtnTxt)) {
            gonnaBtn.setVisibility(View.GONE);
            gonnaTxt.setVisibility(View.VISIBLE);

            gonnaTxt.setText(rightBtnTxt);

            if (listener != null) {
                gonnaTxt.setOnClickListener(listener);
            }
        }
    }

    /**
     * 设置标题:左返回，标题，右文本事件
     */
    protected void setTitleBar(String title, View.OnClickListener leftListener, String rightBtnTxt, View.OnClickListener listener) {

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
        if (titleBarLeft != null && leftListener != null) {
            titleBarLeft.setOnClickListener(leftListener);
        }

        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = findViewById(R.id.gonnaTxt);

        if (StringUtil.isNotEmpty(rightBtnTxt)) {
            gonnaBtn.setVisibility(View.GONE);
            gonnaTxt.setVisibility(View.VISIBLE);

            gonnaTxt.setText(rightBtnTxt);

            if (listener != null) {
                gonnaTxt.setOnClickListener(listener);
            }
        }
    }

    /**
     * 设置标题:左返回，标题，右图标事件
     */
    protected void setTitleBar(String title, int rightBtnIcon, View.OnClickListener listener) {

        if (StringUtil.isNotEmpty(title)) {
            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
            titleBarTxt.setText(title);
        }

        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
        titleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
        TextView gonnaTxt = findViewById(R.id.gonnaTxt);

        gonnaBtn.setVisibility(View.VISIBLE);
        gonnaTxt.setVisibility(View.GONE);
        gonnaBtn.setImageResource(rightBtnIcon);

        if (listener != null) {
            gonnaBtn.setOnClickListener(listener);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = this.getCurrentFocus();
        if (currentFocus == null)
            return;
        IBinder windowToken = currentFocus.getWindowToken();
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftInput();
    }


}
