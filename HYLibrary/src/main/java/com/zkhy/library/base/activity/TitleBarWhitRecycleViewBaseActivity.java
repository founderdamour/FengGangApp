package com.zkhy.library.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StatusBarUtil;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView;
import com.sinothk.widget.loadingRecyclerView.ProgressStyle;
import com.zkhy.library.R;
import com.zkhy.library.utils.ActivityUtil;

public abstract class TitleBarWhitRecycleViewBaseActivity extends TitleBarBaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(getLayout());
//        StatusBarUtil.transparencyBar(this);
//        ActivityUtil.addActivity(this);
//    }
//
//    protected abstract int getLayout();
//
//    protected void setTitleBar(String title) {
//        if (StringUtil.isNotEmpty(title)) {
//            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
//            titleBarTxt.setText(title);
//        }
//
//        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
//        titleBarLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    /**
//     * 设置标题:左返回，标题，右文本事件
//     */
//    protected void setTitleBar(String title, String rightBtnTxt, View.OnClickListener listener) {
//
//        if (StringUtil.isNotEmpty(title)) {
//            TextView titleBarTxt = findViewById(R.id.titleBarTxt);
//            titleBarTxt.setText(title);
//        }
//
//        RelativeLayout titleBarLeft = findViewById(R.id.titleBarLeft);
//        titleBarLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        ImageView gonnaBtn = findViewById(R.id.gonnaBtn);
//        TextView gonnaTxt = findViewById(R.id.gonnaTxt);
//
//        if (StringUtil.isNotEmpty(rightBtnTxt)) {
//            gonnaBtn.setVisibility(View.GONE);
//            gonnaTxt.setVisibility(View.VISIBLE);
//
//            gonnaTxt.setText(rightBtnTxt);
//
//            if (listener != null) {
//                gonnaTxt.setOnClickListener(listener);
//            }
//        }
//    }

    /**
     * =========================================
     * 刷新部分
     */

    public enum LoadType {
        REFRESH, LOAD_MORE
    }

    protected LoadType loadType = LoadType.REFRESH;

    protected void initRecycleLinearView(LoadingRecyclerView recyclerView) {
        // ListView：设置方向
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(recyclerView.getListViewLine(this, R.drawable.list_divider));

        // 设置刷新样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
        // 设置刷新样式:图标
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        // 设置需要时间
        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        // 设置加载更多相关信息
        recyclerView.getDefaultFootView().setLoadingHint("正在加载...");
        recyclerView.getDefaultFootView().setNoMoreHint("已全部加载");
        recyclerView.setLimitNumberToCallLoadMore(2);

        recyclerView.setLoadingListener(new LoadingRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    protected void stopLoading(LoadingRecyclerView recyclerView, LoadType loadType) {
        if (loadType == LoadType.REFRESH) {
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
    }

    protected abstract void refreshData();

    protected abstract void loadMoreData();
}
