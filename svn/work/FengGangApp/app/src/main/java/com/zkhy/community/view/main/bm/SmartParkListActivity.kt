package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.community.model.bean.SmartParkEntity
import com.zkhy.community.view.main.bm.adapters.SmartParkListAdapter
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.MapNavigateUtil
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_smart_park_list.*
import com.jiangyy.easydialog.SingleChoiceDialog


class SmartParkListActivity : StatusViewRecycleViewBaseActivity(), AndroidView {

    private var adapter: SmartParkListAdapter? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "智慧停车")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_smart_park_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            refreshData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.setLoadingMoreEnabled(true)

        adapter = SmartParkListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<SmartParkEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }
            SingleChoiceDialog.Builder(this).setTitle("选择导航地图")
                .addList(arrayOf("百度地图", "高德地图", "腾讯地图"))
                .setOnItemClickListener { title, position ->

                    when (position) {
                        0 -> {
                            MapNavigateUtil.goToBaiduMap(
                                this,
                                entity.parkName,
                                entity.lng,
                                entity.lat,
                                MapNavigateUtil.DATA_FROM_GAODE
                            )
                        }

                        1 -> {
                            MapNavigateUtil.goToGaodeMap(
                                this,
                                entity.parkName,
                                entity.lng,
                                entity.lat,
                                MapNavigateUtil.DATA_FROM_GAODE
                            )
                        }

                        2 -> {
                            MapNavigateUtil.goToTencentMap(
                                this,
                                entity.parkName,
                                entity.lng,
                                entity.lat,
                                MapNavigateUtil.DATA_FROM_GAODE
                            )
                        }
                    }
                }
                .show()
        })

        // ================================
        StatusView.showLoading("正在加载 ...")
        refreshData()
    }

//    private fun refreshData() {
////        val pageParam = PageReq<CommReq>()
////        pageParam.pageNo = 1
////        pageParam.pageSize = 100
////        val comm = CommReq()
////        comm.name = ""
////        comm.palceStatus = "-1"
////        comm.useStatus = "-1"
////        pageParam.data = comm
////        presenter!!.loadStadiumList(pageParam)
//
//        loadComplete(null)
//    }

    override fun refreshData() {
        loadComplete(null)
    }

    override fun loadMoreData() {

    }

    override fun loadingShow() {
//        StatusView.showLoading("正在获取数据...")
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
//        StatusView.showError(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val listData = ArrayList<SmartParkEntity>()
        listData.add(SmartParkEntity("乌江恬苑-停车场", 116, "汇川区", "人民路12号附近", 27.7211800000, 106.9229700000))
        listData.add(SmartParkEntity("乌江恬苑二期13号楼-地下停车场", 163, "汇川区", "人民路乌江恬苑西门", 27.7223900000, 106.9237100000))
        listData.add(SmartParkEntity("遵义义都大酒店-停车场", 173, "汇川区", "人民路路东50米", 27.7228600000, 106.9243300000))
        listData.add(SmartParkEntity("天安星园一期-停车场", 181, "汇川区", "厦门路与广州路交叉口东北150米", 27.7194060000, 106.9248810000))
        listData.add(SmartParkEntity("天赐花园（人民路）-停车场", 200, "汇川区", "人民路197号附近", 27.7251400000, 106.9284600000))

        adapter?.setData(listData)
        StatusView.showContent()

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
    }
}
