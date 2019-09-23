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
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.AppointmentResultEntity
import com.zkhy.community.model.bean.LoveHeartEntity
import com.zkhy.community.model.bean.OrderTakeFileEntity
import com.zkhy.community.model.bean.WishEntity
import com.zkhy.community.presenter.BizOrderPresenter
import com.zkhy.community.presenter.ZMPresenter
import com.zkhy.community.view.main.bm.adapters.OrderHistoryListAdapter
import com.zkhy.community.view.main.bm.adapters.OrderTakeFileListAdapter
import com.zkhy.community.view.main.zm.adapter.LoveHeartListAdapter
import com.zkhy.community.view.main.zm.adapter.WishListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_biz_index_list.*
import java.util.*

/**
 * 预约记录列表
 */
class OrderHistoryListActivity : StatusViewBaseActivity(), AndroidExt2View {

    private var presenter: BizOrderPresenter? = null
    private var adapter: OrderHistoryListAdapter? = null


    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "我的预约")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_biz_index_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            refreshData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = OrderHistoryListAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<OrderTakeFileEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            val tempEntity = AppointmentResultEntity()
            tempEntity.appoNo = entity.codeName
            tempEntity.appoDate = entity.appoDate
            tempEntity.windowName = entity.windowName
            tempEntity.windowId = entity.windowId

            IntentUtil.openActivity(this@OrderHistoryListActivity, BmOrderResultActivity::class.java)
                .putSerializableExtra("appointmentResult", tempEntity)
                .putBooleanExtra("isHistory", true)
                .start()
        })

        presenter = BizOrderPresenter(this)
        refreshData()
    }

    private fun refreshData() {
        presenter!!.loadOrderTakeFileList(-1)
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

        val result: BaseData<ArrayList<OrderTakeFileEntity>> = resultData as BaseData<ArrayList<OrderTakeFileEntity>>

        if (result.errcode == 0) {

            if (result.data == null || result.data.size == 0) {
                showTip("暂无数据")
            } else {
                adapter?.setData(result.data)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {

    }
}
