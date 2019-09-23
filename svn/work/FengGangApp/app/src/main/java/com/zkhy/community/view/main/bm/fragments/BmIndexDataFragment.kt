package com.zkhy.community.view.main.bm.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import com.sinothk.comm.utils.StringUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.FlowInfoEntity
import com.zkhy.community.model.bean.FlowItemBean
import kotlinx.android.synthetic.main.fragment_biz_index_data.*
import java.util.ArrayList

class BmIndexDataFragment : Fragment() {

    var data: FlowInfoEntity? = null

    companion object {

        @JvmStatic
        fun newInstance(data: FlowInfoEntity) = BmIndexDataFragment().apply {
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
        return inflater.inflate(R.layout.fragment_biz_index_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (data == null) return

        listView.adapter = FlowListAdapter(context, data!!.fileList)
    }
}

class FlowListAdapter(context: Context?, fileList: ArrayList<FlowItemBean>) : BaseAdapter() {
    private val mContext = context
    private val dataList = fileList

    override fun getItem(position: Int): FlowItemBean = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = dataList.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.fragment_biz_index_data_item, parent, false)
        val titleTv = view.findViewById<TextView>(R.id.titleTv)
        val contentTv = view.findViewById<TextView>(R.id.contentTv)

        val entity = dataList[position]
        titleTv.text = StringUtil.getNotNullValue(entity.flowName, "暂无")
        contentTv.text = StringUtil.getNotNullValue(entity.content, "暂无")
        return view
    }
}
