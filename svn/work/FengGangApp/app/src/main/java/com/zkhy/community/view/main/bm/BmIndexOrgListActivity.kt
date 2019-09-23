package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.DepartmentEntity
import com.zkhy.community.presenter.BizHandlePresenter
import com.zkhy.community.view.main.bm.adapters.BmIndexListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_biz_index_list.*

class BmIndexOrgListActivity : StatusViewBaseActivity(), AndroidExt2View {

    private var presenter: BizHandlePresenter? = null
    private var adapter: BmIndexListAdapter? = null


    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "办事指南")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_biz_index_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            presenter!!.loadDepartmentList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = BmIndexListAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<DepartmentEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@BmIndexOrgListActivity, BmIndexDeptFunListActivity::class.java)
                .putStringExtra("unitId", entity.id)
                .putStringExtra("unitName", entity.unitName)
                .start()
        })

        presenter = BizHandlePresenter(this)
        presenter!!.loadDepartmentList()
    }

    override fun loadingShow() {
        StatusView.showLoading("正在获取数据...")
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        StatusView.showError(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<ArrayList<DepartmentEntity>>

        if (result.errcode != 0) {
            showTip(result.errmsg)
        } else {
            if (result.data != null && result.data.isNotEmpty()) {
                adapter?.setData(result.data)
                StatusView.showContent()
            } else {
                StatusView.showEmptyData("暂无数据")
            }
        }
    }

    override fun load2Complete(resultData: Any?) {

    }
}
