package com.zkhy.community.view.comm

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.NoticeMsgEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.CommPresenter
import com.zkhy.community.view.comm.adapter.NoticeMsgListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_comm_loading_recycle_view_list.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/6/5 on 9:44
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class NoticeMsgListActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    var presenter: CommPresenter? = null
    var adapter: NoticeMsgListAdapter? = null

    override fun getLayout(): Int = R.layout.activity_comm_loading_recycle_view_list

    override fun loadData(pageNo: Int) {
        val commReq = CommReq()
        commReq.userId = DataCache.getUserId()
        commReq.isFromApp = true

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20
        pageParam.data = commReq

        presenter!!.loadNoticeMsgList(pageParam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("通知消息")

        presenter = CommPresenter(this)

        initRecycleLinearView(loadingRecyclerView)

        loadingRecyclerView.emptyView = emptyView
        emptyView.setOnClickListener {
            refreshData()
        }

        adapter = NoticeMsgListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@NoticeMsgListActivity, NoticeMsgDetailActivity::class.java)
                .putSerializableExtra("entity", entity)
                .start()
        })
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            LoadingDialog.show(this, "正在获取数据")
        }
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
//            adapter?.setData(ArrayList())
            emptyView.text = msg
        } else {
            ToastUtil.show(msg)
        }
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<NoticeMsgEntity>> = resultData as BaseData<PageData<NoticeMsgEntity>>

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
                val listData = pageData.list as ArrayList<NoticeMsgEntity>

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