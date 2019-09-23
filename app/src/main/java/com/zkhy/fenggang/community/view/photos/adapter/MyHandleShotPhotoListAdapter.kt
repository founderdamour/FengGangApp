package com.zkhy.fenggang.community.view.photos.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sinothk.comm.utils.DateUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.PhotoShotEntity
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/29 on 15:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class MyHandleShotPhotoListAdapter(
    private val context: Context,
    private var data: ArrayList<PhotoShotEntity>
) :

    RecyclerView.Adapter<MyHandleShotPhotoListAdapter.RecyclerViewHolder>() {

    private var itemClickCallBack: ItemClickCallBack<PhotoShotEntity>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_photo_shot_4_my_handle_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.describeTv.text = data[position].questionDesc

        // 分割线
        if (position != itemCount - 1) {
            holder.lineView.visibility = View.GONE
        } else {
            holder.lineView.visibility = View.VISIBLE
        }

        holder.timeTv.text = DateUtil.getDateStrByDate(data[position].createTime, "yyyy-MM-dd")

        when (data[position].dealStatus) {
            1 -> {
                holder.statusTv.text = "已处理"
                holder.statusTv.setTextColor(Color.parseColor("#3FC63A"))
            }
            else -> {
                holder.statusTv.text = "待处理"
                holder.statusTv.setTextColor(Color.parseColor("#3398E9"))
            }
        }

        when (data[position].worryLevel) {// 紧急程度(0普通1紧急2特急)
            0 -> {
                holder.levelTv.text = "一般"
                holder.levelTv.setBackgroundResource(R.color.level_3)
            }
            1 -> {
                holder.levelTv.text = "紧急"
                holder.levelTv.setBackgroundResource(R.color.level_2)
            }
            2 -> {
                holder.levelTv.text = "特急"
                holder.levelTv.setBackgroundResource(R.color.level_1)
            }
            else -> {
                holder.levelTv.text = "一般"
                holder.levelTv.setBackgroundResource(R.color.level_3)
            }
        }

        holder.rootView.setOnClickListener {
            if (itemClickCallBack != null) {
                itemClickCallBack!!.onItemClick(position, data[position])
            }
        }
    }

    fun setData(dataList: ArrayList<PhotoShotEntity>) {
        if (this.data.size > 0) {
            this.data.clear()
        }

        this.data.addAll(dataList)

        notifyDataSetChanged()
    }

    fun addData(dataList: ArrayList<PhotoShotEntity>) {
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setClickCallBack(itemClickCallBack: ItemClickCallBack<PhotoShotEntity>) {
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