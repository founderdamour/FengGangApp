package com.zkhy.community.view.photos.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.model.bean.PhotoShotEntity
import com.zkhy.community.model.bean.ThreeMeetEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.library.utils.UnitUtil
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/29 on 15:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class MyShotPhotoListAdapter(
    private val context: Context,
    private var data: ArrayList<PhotoShotEntity>
) :

    RecyclerView.Adapter<MyShotPhotoListAdapter.RecyclerViewHolder>() {

    private var itemClickCallBack: ItemClickCallBack<PhotoShotEntity>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_photo_shot_4_my_all_list_item,
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

        when (data[position].status) {
            2 -> {
                holder.statusTv.text = "已处理"
                holder.statusTv.setTextColor(Color.parseColor("#3FC63A"))
            }
            else -> {
                holder.statusTv.text = "处理中"
                holder.statusTv.setTextColor(Color.parseColor("#3398E9"))
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
    }


}