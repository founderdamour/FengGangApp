package com.zkhy.fenggang.community.view.comm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.sinothk.comm.utils.StringUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.NoticeMsgEntity
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/2/20 on 17:16
 *  项目:  FenGangAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class NoticeMsgListAdapter(private var dataList: ArrayList<NoticeMsgEntity>?) :
    RecyclerView.Adapter<NoticeMsgListAdapter.ViewHolder>() {

    var callBack: ItemClickCallBack<NoticeMsgEntity>? = null

    fun setClickCallBack(clickCallBack: ItemClickCallBack<NoticeMsgEntity>) {
        this.callBack = clickCallBack
    }

    fun setData(data: ArrayList<NoticeMsgEntity>?) {

        if (this.dataList != null && this.dataList!!.size > 0) {
            this.dataList!!.clear()
        }

        if (data == null) {
            return
        }

        this.dataList!!.addAll(data)

        notifyDataSetChanged()
    }

    fun addData(data: ArrayList<NoticeMsgEntity>?) {

        if (this.dataList == null) {
            this.dataList = ArrayList()
        }

        if (data == null) {
            return
        }

        this.dataList!!.addAll(data)

        notifyDataSetChanged()
    }

    //创建新View，被LayoutManager所调用
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.notice_msg_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    //将数据与界面进行绑定的操作
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val entity: NoticeMsgEntity = dataList!![position]

        viewHolder.typeTv.text = StringUtil.getNotNullValue(entity.title)
        viewHolder.infoTv.text = StringUtil.getNotNullValue(entity.content)

        viewHolder.itemRootView.setOnClickListener {
            callBack?.onItemClick(position, entity)
        }

        if (entity.read) {
            viewHolder.pointIv.setImageResource(R.drawable.shape_point_gray)
        }else{
            viewHolder.pointIv.setImageResource(R.drawable.shape_point_red)
        }

        if (position == dataList!!.size - 1) {
            viewHolder.lastLine.visibility = View.VISIBLE
        } else {
            viewHolder.lastLine.visibility = View.GONE
        }
    }

    //获取数据的数量
    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRootView: LinearLayout = view.findViewById<View>(R.id.itemRootView) as LinearLayout
        var typeTv: TextView = view.findViewById<View>(R.id.typeTv) as TextView
        var infoTv: TextView = view.findViewById<View>(R.id.infoTv) as TextView
        var lastLine: TextView = view.findViewById<View>(R.id.lastLine) as TextView
        var pointIv: ImageView = view.findViewById<View>(R.id.pointIv) as ImageView
    }
}