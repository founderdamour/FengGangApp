package com.zkhy.community.view.main.dj.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.PartyPayInfoEntity
import com.zkhy.community.presenter.DJPresenter
import com.zkhy.community.view.main.dj.adapters.PartyCostPayHistoryAdapter
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.fragment_dj_party_pay_history.*

class DjPartyCostPayHistoryFragment : Fragment(), AndroidView {

    private var adapter: PartyCostPayHistoryAdapter? = null
    private var presenter: DJPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dj_party_pay_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(activity, R.color.white))

        adapter = PartyCostPayHistoryAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<PartyPayInfoEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }
//            IntentUtil.openActivity(this@LmStadiumListActivity, LmStadiumDetailsActivity::class.java)
//                .putStringExtra("id", entity.id)
//                .putStringExtra("title", entity.name)
//                .start()
        })

        presenter = DJPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        val pageInfo = PageReq<CommReq>()

        val commReq = CommReq()
        commReq.orgId = -1 // 党组织Id,-1:所有
        commReq.payStatus = 1// 是否缴费(-1:所有,0:未交费，1：已缴费)
        commReq.selectType = 2 // 查询类型（1:为所有，2:当前登录用户）

        pageInfo.data = commReq
        pageInfo.pageNo = 1
        pageInfo.pageSize = 100
        presenter?.loadDJUnpaidList(pageInfo)
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {

        val result: BaseData<PageData<PartyPayInfoEntity>> = resultData as BaseData<PageData<PartyPayInfoEntity>>

        if (result.errcode == 0) {

            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                val pageData = result.data
                val listData = pageData.list as ArrayList<PartyPayInfoEntity>
                adapter?.setData(listData)
            }
        } else {
            showTip(result.errmsg)
        }
    }
}
