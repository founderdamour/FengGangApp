package com.zkhy.fenggang.community.view.main.dw.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.comm.base.RecycleViewBaseFragment
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.DWOpenListEntity
import com.zkhy.fenggang.community.presenter.DWOpenPresenter
import com.zkhy.fenggang.community.view.main.dw.DWOpenDetailActivity
import com.zkhy.fenggang.community.view.main.dw.adapter.DWOpenAdapter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.fragment_loading_recycle_view.*


/**
 * 党务公开  fragment
 */
class DWOpenTabFragment : RecycleViewBaseFragment(), AndroidExt2View {

    private var type: String? = ""
    private var isNeedRefresh: Boolean = false
    private var pageNo: Int = 1
    private var adapter: DWOpenAdapter? = null
    private var presenter: DWOpenPresenter? = null

    companion object {
        fun newInstance(type: String): DWOpenTabFragment {
            val dWOpenTabFragment = DWOpenTabFragment()
            val bundle = Bundle()
            bundle.putString("type", type)
            dWOpenTabFragment.arguments = bundle
            return dWOpenTabFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getString("type")
    }

    var currView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currView == null) {
            currView = inflater.inflate(R.layout.fragment_loading_recycle_view, container, false)
        }
        return currView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleLinearView(loadingRecyclerView)

        adapter = DWOpenAdapter(this.context!!)
        loadingRecyclerView.adapter = adapter
        adapter!!.setItemClickCallBack(ItemClickCallBack { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }
            isNeedRefresh = true
            IntentUtil.openActivity(activity, DWOpenDetailActivity::class.java)
                .putSerializableExtra("entity", entity)
                .start()
        })

        presenter = DWOpenPresenter(this)
        refreshData()
    }

    override fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    private fun loadData(pageNo: Int) {

        if (!NetUtil.isAvailable(context)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val comm = CommReq()
        comm.openPartyType = type
        comm.basicSystemType = "OPEN_PARTY_AFFAIRS"

        pageParam.data = comm
        presenter!!.loadDWOpenAllList(pageParam)
    }

    override fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun load2Complete(resultData: Any?) {
        if (loadingRecyclerView == null || emptyView == null) {
            return
        }

        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<DWOpenListEntity>> = resultData as BaseData<PageData<DWOpenListEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                if (loadType == LoadType.REFRESH) {
                    loadingRecyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                    emptyView.text = "暂无数据"
                } else {
                    showTip("暂无数据")
                }
            } else {
                loadingRecyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE

                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<DWOpenListEntity>

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

    override fun loadComplete(resultData: Any?) {
    }

    override fun loadingShow() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
            adapter?.setData(ArrayList())
            emptyView.visibility = View.GONE
        }
        ToastUtil.show(msg)
    }
}