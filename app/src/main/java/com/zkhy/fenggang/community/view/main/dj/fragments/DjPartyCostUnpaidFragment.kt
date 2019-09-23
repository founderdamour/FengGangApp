package com.zkhy.fenggang.community.view.main.dj.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.PartyPayInfoEntity
import com.zkhy.fenggang.community.presenter.DJPresenter
import com.zkhy.fenggang.community.view.main.dj.PartyCostHomeActivity
import com.zkhy.fenggang.community.view.main.dj.adapters.PartyCostUnpaidListAdapter
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.fragment_dj_party_pay_history.*

class DjPartyCostUnpaidFragment : Fragment(), AndroidView {

    private var adapter: PartyCostUnpaidListAdapter? = null
    private var presenter: DJPresenter? = null

    var parentActivity: PartyCostHomeActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parentActivity = context as PartyCostHomeActivity?
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dj_party_unpaid_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(activity, R.color.white))

        adapter = PartyCostUnpaidListAdapter(ArrayList())
        recyclerView.adapter = adapter

//        adapter!!.setClickCallBack(ItemClickCallBack<PartyPayInfoEntity> { _, entity ->
//            if (entity == null) {
//                return@ItemClickCallBack
//            }
//            IntentUtil.openActivity(activity, PartyCostPayInfoActivity::class.java)
//                .putSerializableExtra("payInfo", entity)
//                .start()
//        })

        presenter = DJPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        val pageInfo = PageReq<CommReq>()

        val commReq = CommReq()
        commReq.orgId = -1 // 党组织Id,-1:所有
        commReq.payStatus = 0// 是否缴费(-1:所有,0:未交费，1：已缴费)
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
                parentActivity?.updatePayInfo(0)
            } else {
                val pageData = result.data
                val listData = pageData.list as ArrayList<PartyPayInfoEntity>
                adapter?.setData(listData)

                parentActivity?.updatePayInfo(pageData.list.size)
            }
        } else {
            showTip(result.errmsg)
        }
    }
}
