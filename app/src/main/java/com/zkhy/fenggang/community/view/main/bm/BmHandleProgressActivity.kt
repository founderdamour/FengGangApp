package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.HandleProgressEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.main.bm.adapters.BmHandleProgressListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_bm_handle_progress.*

class BmHandleProgressActivity : StatusViewRecycleViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: BizHandlePresenter? = null
    private var adapter: BmHandleProgressListAdapter? = null
    private var pageNo: Int = 0

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "办事进度")

    override fun getContentLayoutId(): Int = R.layout.activity_bm_handle_progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)

        adapter = BmHandleProgressListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(object : ItemClickCallBack<HandleProgressEntity> {

            override fun onItemClick(position: Int, entity: HandleProgressEntity?) {
                IntentUtil.openActivity(this@BmHandleProgressActivity, BmHandleProgressResultActivity::class.java)
                    .putIntExtra("flowId", entity!!.flowId)
                    .putIntExtra("applyStatus", entity!!.apprStatus)
                    .putIntExtra("apprLink", entity!!.apprLink)
                    .putStringExtra("id", entity.id)
                    .start()

            }
        })


        presenter = BizHandlePresenter(this)

        // ================================
        refreshData()
    }

    private fun loadData(pageNo: Int) {
        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 10

        pageParam.data = CommReq(DataCache.getUserId(), 0)

        presenter!!.loadAllHandleProgressList(pageParam)
    }

    override fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    override fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            StatusView.showLoading("正在获取数据...")
        }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
            StatusView.showError(msg)
        } else {
            ToastUtil.show(msg)
        }
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<HandleProgressEntity>> = resultData as BaseData<PageData<HandleProgressEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<HandleProgressEntity>

                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(listData)
                } else {
                    adapter?.addData(listData)
                }
                StatusView.showContent()

                // 设置：通用
                pageNo = pageData.nextPage
                if (pageData.isHasNextPage) {
                    loadingRecyclerView.setLoadingMoreEnabled(true)
                } else {
                    loadingRecyclerView.setLoadingMoreEnabled(false)
                }
            }
        } else {
            showTip(result.errmsg)
        }
    }


//
//    override fun getContentRetryListener(): View.OnClickListener {
//        return View.OnClickListener {
//            val pageParam = PageReq<CommReq>()
//            pageParam.pageNo = 1
//            pageParam.pageSize = 100
//
//            pageParam.data = CommReq(DataCache.getUserId(), 0)
//
//            presenter!!.loadAllHandleProgressList(pageParam)
//        }
//    }
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "办事进度")
//
//    override fun getContentLayoutId(): Int = R.layout.activity_bm_handle_progress
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        StatusBarUtil.transparencyBar(this)
//
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))
//
//        adapter = BmHandleProgressListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        adapter!!.setClickCallBack(object : ItemClickCallBack<HandleProgressEntity> {
//            override fun onItemClick(position: Int, entity: HandleProgressEntity?) {
//                IntentUtil.openActivity(this@BmHandleProgressActivity, BmHandleProgressResultActivity::class.java)
//                    .putIntExtra("flowId", entity!!.flowId)
//                    .putIntExtra("applyStatus", entity!!.apprStatus)
//                    .putIntExtra("apprLink", entity!!.apprLink)
//                    .putStringExtra("id", entity.id)
//                    .start()
//            }
//        })
//
//        presenter = BizHandlePresenter(this)
//
//        val pageParam = PageReq<CommReq>()
//        pageParam.pageNo = 1
//        pageParam.pageSize = 100
//        pageParam.data = CommReq(DataCache.getUserId(), 0)
//
//        presenter!!.loadAllHandleProgressList(pageParam)
//    }
//
//    override fun loadingShow() {
//        StatusView.showLoading("正在获取数据...")
//    }
//
//    override fun loadingDismiss() {
//
//    }
//
//    override fun showTip(msg: String?) {
//        StatusView.showError(msg)
//    }
//
//    override fun loadComplete(resultData: Any?) {
//        val result: BaseData<PageData<HandleProgressEntity>> = resultData as BaseData<PageData<HandleProgressEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                val pageData = result.data
//                val listData = pageData.list as ArrayList<HandleProgressEntity>
//                adapter?.setData(listData)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
}
