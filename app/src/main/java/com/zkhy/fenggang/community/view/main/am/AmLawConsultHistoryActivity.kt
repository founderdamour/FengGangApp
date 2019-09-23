package com.zkhy.fenggang.community.view.main.am

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.LawQuestionEntity
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.fenggang.community.view.main.am.adapter.LawMyConsultHistoryListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_am_law_consult_history.*

/**
 * 我的咨询历史
 */
class AmLawConsultHistoryActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    private var presenter: AMPresenter? = null
    private var adapter: LawMyConsultHistoryListAdapter? = null

    override fun getLayout(): Int = R.layout.activity_am_law_consult_history

    override fun loadData(pageNo: Int) {
        val pageParam = PageReq<String>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20
        pageParam.data = "wuming"
        presenter!!.loadLawConsultHistoryList(pageParam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("我的咨询", "提问") {
            IntentUtil.openActivity(this, AmLawConsultAddActivity::class.java).start()
        }

        initRecycleLinearView(loadingRecyclerView)

        loadingRecyclerView.emptyView = emptyView
        emptyView.setOnClickListener {
            refreshData()
        }

        adapter = LawMyConsultHistoryListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack<LawQuestionEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@AmLawConsultHistoryActivity, AmLawConsultDetailsActivity::class.java)
                .putSerializableExtra("item", entity)
                .start()
        })

        presenter = AMPresenter(this)
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

        val result: BaseData<PageData<LawQuestionEntity>> = resultData as BaseData<PageData<LawQuestionEntity>>

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
                val listData = pageData.list as ArrayList<LawQuestionEntity>

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
            emptyView.text = result.errmsg
        }
    }

    override fun load2Complete(resultData: Any?) {
    }
}
