package com.zkhy.fenggang.community.view.main.ts.fragments;

import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.ComplaintEntity

/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class ComplaintHistoryList4AllFragment : ComplaintListBaseFragment() {

    override fun loadData(pageNo: Int) {
        if (!NetUtil.isAvailable(context)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val pageParam = PageReq<ComplaintEntity>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val data = ComplaintEntity()
        data.status = "-1"
        data.from = "app"

        pageParam.data = data

        presenter!!.loadComplaintList(pageParam)
    }
}
