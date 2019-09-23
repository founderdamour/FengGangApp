package com.zkhy.fenggang.community.view.main.am

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.LatLngEntity
import com.zkhy.fenggang.community.model.bean.LawFirmEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.fenggang.community.view.main.am.adapter.AMLawFirmListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_comm_loading_recycle_view_list.*

/**
 * 法律服务机构
 */
class AmLawFirmListActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    private var presenter: AMPresenter? = null
    private var adapter: AMLawFirmListAdapter? = null

    override fun loadData(pageNo: Int) {

        val latLon: LatLngEntity? = DataCache.findLatLng()
        if (latLon == null) {
            showTip("位置获取失败")
            return
        }

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = 1
        pageParam.pageSize = 100
        val comm = CommReq()

        comm.addr = ""
        comm.orgName = ""
        comm.orgType = ""
        comm.isFromApp = true

        comm.lon = latLon.longitude
        comm.lat = latLon.latitude

        pageParam.data = comm
        presenter!!.loadLawFirmList(pageParam)
    }

    override fun getLayout(): Int = R.layout.activity_comm_loading_recycle_view_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("法律服务机构")

        initRecycleLinearView(loadingRecyclerView)

        loadingRecyclerView.emptyView = emptyView
        emptyView.setOnClickListener {
            refreshData()
        }

        adapter = AMLawFirmListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack<LawFirmEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            if (TextUtils.isEmpty(entity.phone) && TextUtils.isEmpty(entity.telephone)) {
                ToastUtil.show("暂无电话号码")
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
            } catch (e: Exception) {
                e.printStackTrace()
                ToastUtil.show("拨号功能不可用")
            }
        })

        presenter = AMPresenter(this)
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
            emptyView.text = StringUtil.getNotNullValue(msg)
        } else {
            ToastUtil.show(msg)
        }
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<LawFirmEntity>> = resultData as BaseData<PageData<LawFirmEntity>>

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
                val listData = pageData.list as ArrayList<LawFirmEntity>

                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(listData)
                } else {
                    adapter?.addData(listData)
                }

                // 设置：通用
                if (pageData.isHasNextPage) {
                    loadingRecyclerView.setLoadingMoreEnabled(true)
                    pageNo = pageData.nextPage
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
}
