package com.zkhy.community.view.main.lm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.StadiumEntity
import com.zkhy.community.presenter.LMPresenter
import com.zkhy.community.view.main.lm.adapter.LMStadiumListAdapter
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_lm_stadium_list.*

class LmStadiumListActivity : StatusViewRecycleViewBaseActivity(), AndroidView {

        private var presenter: LMPresenter? = null
    private var adapter: LMStadiumListAdapter? = null
    private var pageNo: Int = 0

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "文体场所")

    override fun getContentLayoutId(): Int = R.layout.activity_lm_stadium_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)

        adapter = LMStadiumListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<StadiumEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }
            IntentUtil.openActivity(this@LmStadiumListActivity, LmStadiumDetailsActivity::class.java)
                .putStringExtra("id", entity.id)
                .putStringExtra("title", entity.name)
                .start()
        })


        presenter = LMPresenter(this)

        // ================================
        refreshData()
    }

    private fun loadData(pageNo: Int) {
                val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20
        val comm = CommReq()
        comm.name = ""
        comm.palceStatus = "-1"
        comm.useStatus = "-1"
        pageParam.data = comm
        presenter!!.loadStadiumList(pageParam)
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

        val result: BaseData<PageData<StadiumEntity>> = resultData as BaseData<PageData<StadiumEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<StadiumEntity>

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







//    private var presenter: LMPresenter? = null
//    private var adapter: LMStadiumListAdapter? = null
//
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "文体场所")
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.activity_lm_stadium_list
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
//        adapter = LMStadiumListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        adapter!!.setClickCallBack(ItemClickCallBack<StadiumEntity> { _, entity ->
//            if (entity == null) {
//                return@ItemClickCallBack
//            }
//            IntentUtil.openActivity(this@LmStadiumListActivity, LmStadiumDetailsActivity::class.java)
//                .putStringExtra("id", entity.id)
//                .putStringExtra("title", entity.name)
//                .start()
//        })
//
//        presenter = LMPresenter(this)
//
//        // ================================
//        refreshData()
//    }
//
//    private fun refreshData() {
//        val pageParam = PageReq<CommReq>()
//        pageParam.pageNo = 1
//        pageParam.pageSize = 100
//        val comm = CommReq()
//        comm.name = ""
//        comm.palceStatus = "-1"
//        comm.useStatus = "-1"
//        pageParam.data = comm
//        presenter!!.loadStadiumList(pageParam)
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
//        val result: BaseData<PageData<StadiumEntity>> = resultData as BaseData<PageData<StadiumEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                val pageData = result.data
//                val listData = pageData.list as ArrayList<StadiumEntity>
//                adapter?.setData(listData)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
}
