package com.zkhy.fenggang.community.view.main.zm

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
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.LoveHeartEntity
import com.zkhy.fenggang.community.presenter.ZMPresenter
import com.zkhy.fenggang.community.view.main.zm.adapter.LoveHeartListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_zm_love_list.*
import java.util.*

/**
 * 爱心筹集列表
 */
class LoveAllListActivity : StatusViewRecycleViewBaseActivity(), AndroidExt2View {

    private var presenter: ZMPresenter? = null
    private var adapter: LoveHeartListAdapter? = null
    private var pageNo: Int = 0

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "爱心筹集")

    override fun getContentLayoutId(): Int = R.layout.activity_zm_love_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)

        adapter = LoveHeartListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<LoveHeartEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@LoveAllListActivity, LoveHeartDetailsActivity::class.java)
                .putStringExtra("id", entity.id)
                .start()
        })


        presenter = ZMPresenter(this)

        // ================================
        refreshData()
    }

    private fun loadData(pageNo: Int) {
        val pageParam = PageReq<String>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        pageParam.data = "-1"

        presenter!!.loadLoveHeartList(pageParam)
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

        val result: BaseData<PageData<LoveHeartEntity>> = resultData as BaseData<PageData<LoveHeartEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<LoveHeartEntity>

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

    override fun load2Complete(resultData: Any?) {
    }


//    private var presenter: ZMPresenter? = null
//    private var adapter: LoveHeartListAdapter? = null
//
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "爱心筹集")
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.activity_biz_index_list
//    }
//
//    override fun getContentRetryListener(): View.OnClickListener {
//        return View.OnClickListener {
//            refreshData()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        StatusBarUtil.transparencyBar(this)
//
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))
//
//        adapter = LoveHeartListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        adapter!!.setClickCallBack(ItemClickCallBack<LoveHeartEntity> { _, entity ->
//            if (entity == null) {
//                return@ItemClickCallBack
//            }
//
//            IntentUtil.openActivity(this@LoveAllListActivity, LoveHeartDetailsActivity::class.java)
//                .putStringExtra("id", entity.id)
//                .start()
//        })
//
//        presenter = ZMPresenter(this)
//        refreshData()
//    }
//
//    private fun refreshData() {
//        val pageParam = PageReq<String>()
//        pageParam.pageNo = 1
//        pageParam.pageSize = 100
//
//        pageParam.data = "-1"
//
//        presenter!!.loadLoveHeartList(pageParam)
//    }
//
//    override fun loadingShow() {
//        StatusView.showLoading("正在获取数据...")
//    }
//
//    override fun loadingDismiss() {
//    }
//
//    override fun showTip(msg: String?) {
//        StatusView.showError(msg)
//    }
//
//    override fun loadComplete(resultData: Any?) {
//
//        val result: BaseData<PageData<LoveHeartEntity>> = resultData as BaseData<PageData<LoveHeartEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                val pageData = result.data
//                val listData = pageData.list as ArrayList<LoveHeartEntity>
//                adapter?.setData(listData)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
//
//    override fun load2Complete(resultData: Any?) {
//
//    }
}
