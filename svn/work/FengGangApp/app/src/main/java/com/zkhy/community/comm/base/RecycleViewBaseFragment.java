package com.zkhy.community.comm.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView;
import com.sinothk.widget.loadingRecyclerView.ProgressStyle;
import com.zkhy.community.R;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/9 on 14:46
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public abstract class RecycleViewBaseFragment extends Fragment {

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(recyclerView.getListViewLine(getActivity(), R.drawable.list_divider));

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

    protected void initRecycleGridView(LoadingRecyclerView recyclerView) {
//        // ListView：设置方向
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(recyclerView.getListViewLine(getActivity(), R.drawable.list_divider));

        // 网格
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(recyclerView.getGridViewLine(10));

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
        if (recyclerView == null) {
            return;
        }

        if (loadType == LoadType.REFRESH) {
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
    }

    protected abstract void refreshData();

    protected abstract void loadMoreData();
}
