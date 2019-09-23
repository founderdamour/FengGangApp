package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.BizDeptFunctionEntity
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.presenter.BizHandlePresenter
import com.zkhy.community.view.main.bm.adapters.BmIndexDeptFunListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_biz_index_list.*

class BmIndexDeptFunListActivity : StatusViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var unitId: String = ""
    var unitName: String = ""


    private var presenter: BizHandlePresenter? = null
    private var adapter: BmIndexDeptFunListAdapter? = null

    override fun getTitleBarView(): View =
        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(unitName, "办事事项"))

    override fun getContentLayoutId(): Int {
        return R.layout.activity_biz_index_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            presenter!!.loadDepartmentList()
        }
    }

    private fun getIntentData() {
        unitId = intent.getStringExtra("unitId")
        unitName = intent.getStringExtra("unitName")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getIntentData()
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = BmIndexDeptFunListAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack { _, entity ->
            val data: BizDeptFunctionEntity = entity as BizDeptFunctionEntity
            IntentUtil.openActivity(this@BmIndexDeptFunListActivity, BmIndexDetailsActivity::class.java)
                .putStringExtra("itemId", data.id)
                .putStringExtra("itemTitle", data.flowName)
                .start()
        }

        presenter = BizHandlePresenter(this)

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = 1
        pageParam.pageSize = 100

        val vo = CommReq()
        vo.deptId = unitId
        pageParam.data = vo
        presenter!!.loadBizIndexList(pageParam)
    }

    override fun loadingShow() {
        runOnUiThread { StatusView.showLoading("正在获取数据...") }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        runOnUiThread { StatusView.showError(msg) }
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<PageData<BizDeptFunctionEntity>>
        if (result.errcode == 0) {

            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                StatusView.showEmptyData("暂无数据")
            } else {
                val listData = result.data.list as ArrayList<BizDeptFunctionEntity>
                adapter?.setData(listData)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }
}
