package com.zkhy.community.view.main.ts.fragments;

import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.ComplaintEntity
import com.zkhy.community.model.bean.ImpeachEntity
import com.zkhy.community.view.main.ts.fragments.ComplaintListBaseFragment

/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class ComplaintHistoryList4DoingFragment : ComplaintListBaseFragment() {

    override fun loadData(pageNo: Int) {
        if (!NetUtil.isAvailable(context)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val pageParam = PageReq<ComplaintEntity>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val data = ComplaintEntity()
        data.status = "untreated"
        data.from = "app"

        pageParam.data = data

        presenter!!.loadComplaintList(pageParam)
    }
}
