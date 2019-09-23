package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
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
import kotlinx.android.synthetic.main.activity_bm_handle_history_list.*

class BmHandleHistoryListActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: BizHandlePresenter? = null
    private var adapter: BmHandleProgressListAdapter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_history_list

    override fun loadData(pageNo: Int) {
        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 10
        pageParam.data = CommReq(DataCache.getUserId(), 0)

        presenter!!.loadAllHandleProgressList(pageParam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("办事记录")

        initRecycleLinearView(loadingRecyclerView)

        loadingRecyclerView.emptyView = emptyView
        emptyView.setOnClickListener {
            refreshData()
        }

        adapter = BmHandleProgressListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack<HandleProgressEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            if (entity == null) {
                return@ItemClickCallBack
            }
            IntentUtil.openActivity(this@BmHandleHistoryListActivity, BmHandleDetailsActivity::class.java)
                .putIntExtra("flowId", entity.flowId)
                .putStringExtra("id", entity.id)
                .start()
        })

        presenter = BizHandlePresenter(this)
    }

    override fun onResume() {
        super.onResume()

        refreshData()
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            LoadingDialog.show(this, "正在获取数据")
        }
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
            adapter?.setData(ArrayList())
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
                if (loadType == LoadType.REFRESH) {
                    emptyView.text = "暂无数据"
                } else {
                    showTip("暂无数据")
                }
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
}
