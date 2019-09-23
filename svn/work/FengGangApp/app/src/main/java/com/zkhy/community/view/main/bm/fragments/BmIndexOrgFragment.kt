package com.zkhy.community.view.main.bm.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinothk.comm.utils.StringUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.FlowInfoEntity
import com.zkhy.community.model.cache.DataCache
import kotlinx.android.synthetic.main.fragment_biz_index_org.*

class BmIndexOrgFragment : Fragment() {

    var data: FlowInfoEntity? = null

    companion object {
        @JvmStatic
        fun newInstance(data: FlowInfoEntity) = BmIndexOrgFragment().apply {
            arguments = Bundle().apply {
                putSerializable("data", data)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        data = arguments!!.getSerializable("data") as FlowInfoEntity?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_biz_index_org, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (data == null) return

        deptNameTv.text = StringUtil.getNotNullValue(data!!.deptName, "暂无")

        cardTypeTv.text = DataCache.findDictionary(data!!.certifyType)
        handleObjTv.text = DataCache.findDictionary(data!!.handleObj)

        handleTimeTv.text = StringUtil.getNotNullValue(data!!.handleDate, "暂无")
    }
}
