package com.zkhy.fenggang.community.view.main.am

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.*
import com.sinothk.map.amap.location.AMapLocationEntity
import com.sinothk.map.amap.location.MapLocationHelper
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.PoliceStationEntity
import com.zkhy.fenggang.community.model.bean.PoliceStationRecordEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.fenggang.community.view.main.am.adapter.AMPoliceStationListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_biz_index_list.*
import java.lang.Exception
import java.util.*

class AmPoliceStationListActivity : StatusViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }


    private var presenter: AMPresenter? = null
    private var adapter: AMPoliceStationListAdapter? = null
    private var mapLocEntity: AMapLocationEntity? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "警务管理")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_am_police_station_list
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

        adapter = AMPoliceStationListAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<PoliceStationEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            if (TextUtils.isEmpty(entity.phone) && TextUtils.isEmpty(entity.telephone)) {
                return@ItemClickCallBack
            }

            val phoneNum = if (StringUtil.isNotEmpty(entity.phone)) {
                entity.phone
            } else {
                entity.telephone
            }

            try {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                Thread {
                    submitCallRecord(phoneNum)
                }.start()
            } catch (e: Exception) {
                e.printStackTrace()
                ToastUtil.show("拨号功能不可用")
            }
        })

        presenter = AMPresenter(this)

        // ================================
        refreshData()

        MapLocationHelper.with(this).location { locEntity ->
            mapLocEntity = locEntity
        }
    }

    private fun refreshData() {
        if (!NetUtil.isConnected(this)) {
            StatusView.showNetWorkError("网络已断开")
            return
        }

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = 1
        pageParam.pageSize = 100
        val comm = CommReq()
        comm.addr = ""
        comm.officeName = ""
        comm.officeType = ""
        pageParam.data = comm
        presenter!!.loadPoliceStationList(pageParam)
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
        val result: BaseData<PageData<PoliceStationEntity>> = resultData as BaseData<PageData<PoliceStationEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                val pageData = result.data
                val listData = pageData.list as ArrayList<PoliceStationEntity>
                adapter?.setData(listData)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun submitCallRecord(phoneNum: String) {
        if (!NetUtil.isConnected(this)) {
            return
        }

        val vo = PoliceStationRecordEntity()
        val userInfo = DataCache.getUserInfo()

        if (userInfo != null) {
            vo.phone = userInfo.phone
            vo.callUserId = userInfo.userId.toString()
            vo.callPhone = phoneNum
            vo.callTime = DateUtil.getDateStrByDate(Date(), "yyyy-MM-dd HH:mm:ss")
            vo.isAnswerStatus = true

            if (mapLocEntity != null) {
                vo.addr = mapLocEntity!!.address
                vo.callLat = mapLocEntity!!.latitude
                vo.callLon = mapLocEntity!!.longitude
            }
            presenter?.submitPoliceCallRecord(vo)
        }
    }
}
