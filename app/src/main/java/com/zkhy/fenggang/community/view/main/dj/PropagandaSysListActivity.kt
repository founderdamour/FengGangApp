package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.WechatEntity
import com.zkhy.fenggang.community.model.bean.WechatParam
import com.zkhy.fenggang.community.presenter.DJPresenter
import com.zkhy.fenggang.community.view.main.dj.adapters.PropagandaListAdapter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

class PropagandaSysListActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var presenter: DJPresenter? = null
    private var adapter: PropagandaListAdapter? = null

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        setTitleBar("宣传平台")

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        adapter = PropagandaListAdapter(this, ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<WechatEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this, WebPageActivity::class.java)
                .putStringExtra("webUrl", entity.url)
                .putStringExtra("webTitle", "信息详情")
                .start()
        })

        presenter = DJPresenter(this)

        refreshData()
    }

    override fun loadData(pageNo: Int) {
        val pageParam = PageReq<WechatParam>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 3

        val vo = WechatParam()
        vo.count = 0
        vo.offset = 0
        vo.type = "news"
        pageParam.data = vo

        presenter?.loadPropagandaListData(pageParam)
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.text = "正在加载..."
        } else {

        }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.text = msg
        } else {
            ToastUtil.show(msg)
        }
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<WechatEntity>> = resultData as BaseData<PageData<WechatEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<WechatEntity>

                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(listData)
                } else {
                    adapter?.addData(listData)
                }

                // 设置：通用
                if (pageData.isHasNextPage) {
                    pageNo += 1
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
