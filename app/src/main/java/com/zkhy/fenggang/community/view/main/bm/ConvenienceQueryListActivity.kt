package com.zkhy.fenggang.community.view.main.bm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.ConvenienceQueryEntity
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.main.bm.adapters.ConvenienceQueryListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_bm_service_list.*
import java.lang.Exception

class ConvenienceQueryListActivity : StatusViewRecycleViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    private var classCode = ""
    private var className = ""
    private var areaCode = ""
    private var presenter: BizHandlePresenter? = null
    private var adapter: ConvenienceQueryListAdapter? = null
    private var pageNo: Int = 0

    override fun getTitleBarView(): View =
        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(className, "便民服务"))

    override fun getContentLayoutId(): Int = R.layout.activity_bm_service_list

    private fun getIntentValue() {
        classCode = intent.getStringExtra("classCode")
        className = intent.getStringExtra("className")
        areaCode = intent.getStringExtra("areaCode")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getIntentValue()
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)

        adapter = ConvenienceQueryListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<ConvenienceQueryEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            if (TextUtils.isEmpty(entity.pubUserPhone)) {
                return@ItemClickCallBack
            }

            try {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${entity.pubUserPhone}"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } catch (e: Exception) {
                ToastUtil.show("拨号功能不可用")
            }
        })


        presenter = BizHandlePresenter(this)

        // ================================
        refreshData()
    }

    private fun loadData(pageNo: Int) {
        val vo = PageReq<CommReq>()
        vo.pageSize = 20
        vo.pageNo = pageNo

        val comm = CommReq()
        comm.areaCode = areaCode
        comm.informName = ""
        comm.informType = classCode
        comm.streetCode = ""

        vo.data = comm

        presenter!!.loadConvenienceQueryList(vo)
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

        val result: BaseData<PageData<ConvenienceQueryEntity>> =
            resultData as BaseData<PageData<ConvenienceQueryEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<ConvenienceQueryEntity>

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


//    private var presenter: BizHandlePresenter? = null
//    private var adapter: ConvenienceQueryListAdapter? = null
//
//    private var classCode = ""
//    private var className = ""
//    private var areaCode = ""
//
//    private fun getIntentValue() {
//        classCode = intent.getStringExtra("classCode")
//        className = intent.getStringExtra("className")
//        areaCode = intent.getStringExtra("areaCode")
//    }
//
//    override fun getTitleBarView(): View =
//        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(className, "便民服务"))
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.recycler_view
//    }
//
//    override fun getContentRetryListener(): View.OnClickListener {
//        return View.OnClickListener {
//            refreshData()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        getIntentValue()
//        super.onCreate(savedInstanceState)
//        StatusBarUtil.transparencyBar(this)
//
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))
//
//        adapter = ConvenienceQueryListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        adapter!!.setClickCallBack(ItemClickCallBack<ConvenienceQueryEntity> { _, entity ->
//            if (entity == null) {
//                return@ItemClickCallBack
//            }
//
//            if (TextUtils.isEmpty(entity.pubUserPhone)) {
//                return@ItemClickCallBack
//            }
//
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${entity.pubUserPhone}"))
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//        })
//
//        presenter = BizHandlePresenter(this)
//        refreshData()
//    }
//
//    private fun refreshData() {
//        val vo = PageReq<CommReq>()
//        vo.pageSize = 100
//        vo.pageNo = 1
//
//        val comm = CommReq()
//        comm.areaCode = areaCode
//        comm.informName = ""
//        comm.informType = classCode
//        comm.streetCode = ""
//
//        vo.data = comm
//
//        presenter!!.loadConvenienceQueryList(vo)
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
//        val result: BaseData<PageData<ConvenienceQueryEntity>> = resultData as BaseData<PageData<ConvenienceQueryEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                val pageData = result.data
//                val listData = pageData.list as ArrayList<ConvenienceQueryEntity>
//                adapter?.setData(listData)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
}
