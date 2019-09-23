//package com.zkhy.community.comm.base;
//
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.View;
//import com.sinothk.comm.utils.StatusBarUtil;
//import com.sinothk.view.status.base.StatusViewBaseActivity;
//import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView;
//import com.sinothk.widget.loadingRecyclerView.ProgressStyle;
//import com.zkhy.community.R;
//import com.zkhy.library.utils.ActivityUtil;
//
///**
// * <pre>
// *  创建:  梁玉涛 2019/1/19 on 15:19
// *  项目:  WuMinAndroid
// *  描述:
// *  更新:
// * <pre>
// */
//public abstract class StatusViewRecycleViewBaseActivity extends StatusViewBaseActivity {
//    @Override
//    protected View.OnClickListener getContentRetryListener() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                refreshData();
//            }
//        };
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        StatusBarUtil.transparencyBar(this);
//        ActivityUtil.addActivity(this);
//    }
//
//    /**
//     * =========================================
//     * 刷新部分
//     */
//
//    public enum LoadType {
//        REFRESH, LOAD_MORE
//    }
//
//    protected LoadType loadType = LoadType.REFRESH;
//
//    protected void initRecycleLinearView(LoadingRecyclerView recyclerView) {
//        // ListView：设置方向
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(recyclerView.getListViewLine(this, R.drawable.list_divider));
//
//        // 设置刷新样式
//        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag);
//        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
//        // 设置刷新样式:图标
//        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
//        // 设置需要时间
//        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
//        // 设置加载更多相关信息
//        recyclerView.getDefaultFootView().setLoadingHint("正在加载...");
//        recyclerView.getDefaultFootView().setNoMoreHint("已全部加载");
//        recyclerView.setLimitNumberToCallLoadMore(2);
//
//        recyclerView.setLoadingListener(new LoadingRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                refreshData();
//            }
//
//            @Override
//            public void onLoadMore() {
//                loadMoreData();
//            }
//        });
//    }
//
//    protected void stopLoading(LoadingRecyclerView recyclerView, LoadType loadType) {
//        if (loadType == LoadType.REFRESH) {
//            recyclerView.refreshComplete();
//        } else {
//            recyclerView.loadMoreComplete();
//        }
//    }
//
//    protected abstract void refreshData();
//
//    protected abstract void loadMoreData();
//}
