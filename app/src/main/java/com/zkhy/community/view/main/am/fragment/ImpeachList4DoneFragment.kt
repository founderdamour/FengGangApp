package com.zkhy.community.view.main.am.fragment;

import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.ImpeachEntity

/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class ImpeachList4DoneFragment : ImpeachListBaseFragment() {

    override fun loadData(pageNo: Int) {
        if (!NetUtil.isAvailable(context)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val pageParam = PageReq<ImpeachEntity>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val data = ImpeachEntity()
        data.status = "complete"
        data.from = "app"

        pageParam.data = data

        presenter!!.loadImpeachList(pageParam)
    }
}
