package com.zkhy.fenggang.community.view.main.dj.fragments;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.comm.base.RecycleViewBaseFragment
import com.zkhy.fenggang.community.model.bean.DjThreeMeetMemDTO
import com.zkhy.fenggang.community.view.main.dj.adapters.MeetReadUserListAdapter
import kotlinx.android.synthetic.main.fragment_loading_recycle_view.*

/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class ReadListFragment : RecycleViewBaseFragment() {

    private var adapter: MeetReadUserListAdapter? = null

    var receivedList: ArrayList<DjThreeMeetMemDTO>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        receivedList = arguments!!.getSerializable("data") as ArrayList<DjThreeMeetMemDTO>?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading_recycle_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.setPullRefreshEnabled(false)
        loadingRecyclerView.setLoadingMoreEnabled(false)

        loadingRecyclerView.emptyView = emptyView

        adapter = MeetReadUserListAdapter(this.context!!, this.receivedList!!)
        loadingRecyclerView.adapter = adapter

        if (this.receivedList!!.size == 0) {
            emptyView.text = "暂无数据"
        }
    }

    override fun onResume() {
        super.onResume()

        refreshData()
    }

    override fun refreshData() {
    }

    override fun loadMoreData() {
    }

    fun loadingShow() {
    }

    fun loadingDismiss() {

    }

    fun showTip(msg: String?) {
    }

    fun loadComplete(upData: Any?) {
    }
}
