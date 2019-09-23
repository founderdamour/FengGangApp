package com.zkhy.fenggang.community.view.main.dj.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.image.rounded.RoundedImageView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.DjThreeMeetMemDTO
import com.zkhy.library.utils.ImageLoader
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/29 on 15:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class MeetReadUserListAdapter(
    private val context: Context,
    private var data: ArrayList<DjThreeMeetMemDTO>
) :

    RecyclerView.Adapter<MeetReadUserListAdapter.RecyclerViewHolder>() {

    private var itemClickCallBack: ItemClickCallBack<DjThreeMeetMemDTO>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_meet_read_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.nameTv.text = StringUtil.getNotNullValue(data[position].name)

        ImageLoader.show(context, data[position].photo, R.drawable.mr_tx, holder.userAvatarIv)

        if (data[position].ackData == null) {
            holder.timeTv.visibility = View.GONE
        } else {
            holder.timeTv.visibility = View.VISIBLE
            val time: Date = data[position].ackData
            holder.timeTv.text = DateUtil.getFriendlyDate(time)
        }
    }

    fun setData(dataList: ArrayList<DjThreeMeetMemDTO>) {
        if (this.data.size > 0) {
            this.data.clear()
        }

        this.data.addAll(dataList)

        notifyDataSetChanged()
    }

    fun addData(dataList: ArrayList<DjThreeMeetMemDTO>) {
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setClickCallBack(itemClickCallBack: ItemClickCallBack<DjThreeMeetMemDTO>) {
        this.itemClickCallBack = itemClickCallBack
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userAvatarIv: RoundedImageView = view.findViewById(R.id.userAvatarIv)
        val nameTv: TextView = view.findViewById(R.id.nameTv)
        val timeTv: TextView = view.findViewById(R.id.timeTv)
    }
}