package com.zkhy.fenggang.comm.base

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView
import com.sinothk.widget.loadingRecyclerView.ProgressStyle
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/2 on 11:17
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
abstract class LoadingRecyclerViewBaseActivity : TitleBarBaseActivity() {

    /**
     * =========================================
     * 刷新部分
     */

    enum class LoadType {
        REFRESH, LOAD_MORE
    }

    protected var loadType = LoadType.REFRESH
    protected var pageNo: Int = 1

    protected fun initRecycleLinearView(recyclerView: LoadingRecyclerView) {
        // ListView：设置方向
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(recyclerView.getListViewLine(this, R.drawable.list_divider))

        // 设置刷新样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag)
        // 设置刷新样式:图标
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey)
        // 设置需要时间
        recyclerView.defaultRefreshHeaderView.setRefreshTimeVisible(true)
        // 设置加载更多相关信息
        recyclerView.defaultFootView.setLoadingHint("正在加载...")
        recyclerView.defaultFootView.setNoMoreHint("已全部加载")
        recyclerView.setLimitNumberToCallLoadMore(2)

        recyclerView.setLoadingListener(object : LoadingRecyclerView.LoadingListener {
            override fun onRefresh() {
                refreshData()
            }

            override fun onLoadMore() {
                loadMoreData()
            }
        })
    }

    protected fun initRecycleGridView(recyclerView: LoadingRecyclerView) {
        //        // ListView：设置方向
        //        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //        recyclerView.addItemDecoration(recyclerView.getListViewLine(getActivity(), R.drawable.list_divider));

        // 网格
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(recyclerView.getGridViewLine(10))

        // 设置刷新样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag)
        // 设置刷新样式:图标
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey)
        // 设置需要时间
        recyclerView.defaultRefreshHeaderView.setRefreshTimeVisible(true)
        // 设置加载更多相关信息
        recyclerView.defaultFootView.setLoadingHint("正在加载...")
        recyclerView.defaultFootView.setNoMoreHint("已全部加载")
        recyclerView.setLimitNumberToCallLoadMore(2)

        recyclerView.setLoadingListener(object : LoadingRecyclerView.LoadingListener {
            override fun onRefresh() {
                refreshData()
            }

            override fun onLoadMore() {
                loadMoreData()
            }
        })
    }


    protected fun stopLoading(recyclerView: LoadingRecyclerView, loadType: LoadType) {
        if (loadType == LoadType.REFRESH) {
            recyclerView.refreshComplete()
        } else {
            recyclerView.loadMoreComplete()
        }
    }

    fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    abstract fun loadData(pageNo: Int)
}