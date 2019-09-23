package com.zkhy.community.view.photos.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.comm.base.RecycleViewBaseFragment
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.PhotoShotEntity
import com.zkhy.community.model.bean.PhotosCreatorVo
import com.zkhy.community.model.bean.ThreeMeetEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.MeetClassPresenter
import com.zkhy.community.presenter.PhotosCreatorPresenter
import com.zkhy.community.view.main.dj.adapters.MeetClassListAdapter
import com.zkhy.community.view.photos.ShotPhotoDetailActivity
import com.zkhy.community.view.photos.ShotPhotoHandleDetailActivity
import com.zkhy.community.view.photos.adapter.MyHandleShotPhotoListAdapter
import com.zkhy.community.view.photos.adapter.MyShotPhotoListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.fragment_loading_recycle_view.*

/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class MyHandleShotPhotoDoingListFragment : RecycleViewBaseFragment(), AndroidExt2View {

    private var pageNo: Int = 1
    private var adapter: MyHandleShotPhotoListAdapter? = null
    var presenter: PhotosCreatorPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading_recycle_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleLinearView(loadingRecyclerView)

        adapter = MyHandleShotPhotoListAdapter(this.context!!, ArrayList())
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack { _, entity ->
            IntentUtil.openActivity(activity, ShotPhotoHandleDetailActivity::class.java)
                .putStringExtra("id", entity.id.toString()).start()
        })

        presenter = PhotosCreatorPresenter(this)

        emptyView.setOnClickListener {
            refreshData()
        }
    }

    override fun onResume() {
        super.onResume()
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

        val pageParam = PageReq<PhotosCreatorVo>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val pCreatorVo = PhotosCreatorVo()
        pCreatorVo.isDealMyOrg = true
        pCreatorVo.dealStatus = 0

        pageParam.data = pCreatorVo
        presenter!!.loadMyAllShotPhotoList(pageParam)
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

        val result: BaseData<PageData<PhotoShotEntity>> = resultData as BaseData<PageData<PhotoShotEntity>>

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
                val listData = pageData.list as ArrayList<PhotoShotEntity>

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

    override fun load2Complete(resultData: Any?) {
    }
}
