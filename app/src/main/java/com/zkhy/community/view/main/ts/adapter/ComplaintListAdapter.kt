package com.zkhy.community.view.main.ts.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sinothk.comm.utils.DateUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.model.bean.ComplaintEntity
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/29 on 15:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class ComplaintListAdapter(
    private val context: Context,
    private var data: ArrayList<ComplaintEntity>
) :

    RecyclerView.Adapter<ComplaintListAdapter.RecyclerViewHolder>() {

    private var itemClickCallBack: ItemClickCallBack<ComplaintEntity>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_impeach_list_4_all_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.describeTv.text = data[position].title

        // 分割线
        if (position != itemCount - 1) {
            holder.lineView.visibility = View.GONE
        } else {
            holder.lineView.visibility = View.VISIBLE
        }

        holder.timeTv.text = DateUtil.getDateStrByDate(data[position].createTime, "yyyy-MM-dd")

        when (data[position].status) {
            "untreated" -> {
                holder.statusTv.text = "未处理"
                holder.statusTv.setTextColor(context.resources.getColor(R.color.status_doing))
            }
            "complete" -> {
                holder.statusTv.text = "已处理"
                holder.statusTv.setTextColor(context.resources.getColor(R.color.status_success))
            }
            else -> {
                holder.statusTv.text = "未定义"
                holder.statusTv.setTextColor(context.resources.getColor(R.color.tc_list_tip))
            }
        }

        holder.rootView.setOnClickListener {
            if (itemClickCallBack != null) {
                itemClickCallBack!!.onItemClick(position, data[position])
            }
        }
    }

    fun setData(dataList: ArrayList<ComplaintEntity>) {
        if (this.data.size > 0) {
            this.data.clear()
        }

        this.data.addAll(dataList)

        notifyDataSetChanged()
    }

    fun addData(dataList: ArrayList<ComplaintEntity>) {
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setClickCallBack(itemClickCallBack: ItemClickCallBack<ComplaintEntity>) {
        this.itemClickCallBack = itemClickCallBack
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rootView: LinearLayout = view.findViewById(R.id.rootView)
        val lineView: TextView = view.findViewById(R.id.lineView)
        val lineView0: TextView = view.findViewById(R.id.lineView0)

        val describeTv: TextView = view.findViewById(R.id.describeTv)
        val timeTv: TextView = view.findViewById(R.id.timeTv)
        val statusTv: TextView = view.findViewById(R.id.statusTv)
        val levelTv: TextView = view.findViewById(R.id.levelTv)
    }


}