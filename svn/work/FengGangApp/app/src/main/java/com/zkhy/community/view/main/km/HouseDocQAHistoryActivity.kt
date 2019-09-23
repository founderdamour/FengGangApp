package com.zkhy.community.view.main.km

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.HouseDocQAEntity
import com.zkhy.community.presenter.KMPresenter
import com.zkhy.community.view.main.km.adpaters.HouseDocMyConsultHistoryListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_km_doc_consult_history.*

/**
 * 我的咨询历史
 */
class HouseDocQAHistoryActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    private var presenter: KMPresenter? = null
    private var adapter: HouseDocMyConsultHistoryListAdapter? = null

    override fun getLayout(): Int = R.layout.activity_km_doc_consult_history

    override fun loadData(pageNo: Int) {
        val pageParam = PageReq<String>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20
        pageParam.data = "wuming"

        presenter!!.loadHouseDocQAHistoryList(pageParam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("我的咨询", "提问") {
            IntentUtil.openActivity(this, HouseDocQAAddActivity::class.java).start()
        }

        initRecycleLinearView(loadingRecyclerView)

        loadingRecyclerView.emptyView = emptyView
        emptyView.setOnClickListener {
            refreshData()
        }

        adapter = HouseDocMyConsultHistoryListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack<HouseDocQAEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@HouseDocQAHistoryActivity, DocConsultDetailsActivity::class.java)
                .putSerializableExtra("item", entity)
                .start()
        })

        presenter = KMPresenter(this)
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

        val result: BaseData<PageData<HouseDocQAEntity>> = resultData as BaseData<PageData<HouseDocQAEntity>>

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
                val listData = pageData.list as ArrayList<HouseDocQAEntity>

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

    override fun load2Complete(resultData: Any?) {
    }

//    private fun loadData(pageNo: Int) {
//
//        if (!NetUtil.isAvailable(context)) {
//            ToastUtil.show(R.string.net_error)
//            return
//        }
//
//        val userId: String = DataCache.getUserId()
//
//        val pageParam = PageReq<CommReq>()
//        pageParam.pageNo = pageNo
//        pageParam.pageSize = 20
//
//        val comm = CommReq()
//        comm.meetStatus = -1
//        comm.orgId = -1
//        comm.userId = userId
//        comm.joinStatus = 3
//
//        pageParam.data = comm
//        presenter!!.loadMeetForAllList(pageParam)
//    }

//    override fun load2Complete(resultData: Any?) {
//    }
//
//    private var presenter: KMPresenter? = null
//    private var adapter: HouserDocMyConsultHistoryListAdapter? = null
//
//
//    override fun getTitleBarView(): View =
//        TitleBarViewCreator.createTitleLCR(this, "我的咨询", "提问") {
//            IntentUtil.openActivity(this, HouseDocQAAddActivity::class.java).start()
//        }
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.activity_km_doc_consult_history
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
//        adapter = HouserDocMyConsultHistoryListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        adapter!!.setClickCallBack(ItemClickCallBack<HouseDocQAEntity> { _, entity ->
//            if (entity == null) {
//                return@ItemClickCallBack
//            }
//            IntentUtil.openActivity(this@HouseDocQAHistoryActivity, DocConsultDetailsActivity::class.java)
//                .putSerializableExtra("item", entity)
//                .start()
//        })
//
//        presenter = KMPresenter(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        refreshData()
//    }
//
//    private fun refreshData() {
////        val pageParam = PageReq<CommReq>()
////        pageParam.pageNo = 1
////        pageParam.pageSize = 100
////        val comm = CommReq()
////        comm.addr = ""
////        comm.orgName = ""
////        comm.orgType = ""
////        pageParam.data = comm
//        presenter!!.loadHouseDocQAHistoryList()
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
//        val result: BaseData<ArrayList<HouseDocQAEntity>> =
//            resultData as BaseData<ArrayList<HouseDocQAEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.size == 0) {
//                showTip("暂无数据")
//            } else {
//                adapter?.setData(result.data)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
}
