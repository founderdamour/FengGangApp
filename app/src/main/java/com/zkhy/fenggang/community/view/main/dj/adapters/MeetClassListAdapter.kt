package com.zkhy.fenggang.community.view.main.dj.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.ThreeMeetEntity
import com.zkhy.fenggang.community.model.cache.DataCache
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
class MeetClassListAdapter(
    private val context: Context,
    private var data: ArrayList<ThreeMeetEntity>,
    private val needTimeTip: Boolean
) :

    RecyclerView.Adapter<MeetClassListAdapter.RecyclerViewHolder>() {

    private var itemClickCallBack: ItemClickCallBack<ThreeMeetEntity>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_meet_class_4_all_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemTitleTv.text = data[position].meetName

        // 会议状态
        when (data[position].meetStatus) {
            // 会议状态（0：未启动，1：未开始，2：进行中，3：已结束，4：已取消，5：已归档）
            0 -> { // 0：未开始
                holder.meetStatusTv.text = "未启动"
            }
            1 -> { // 1：未开始
                holder.meetStatusTv.text = "未开始"
            }
            2 -> { // 2：进行中
                holder.meetStatusTv.text = "进行中"
            }
            3 -> { // 3：已结束
                holder.meetStatusTv.text = "已结束"
            }
            4 -> { // 4：已取消
                holder.meetStatusTv.text = "已取消"
            }
            5 -> { // 5：已归档
                holder.meetStatusTv.text = "已归档"
            }
            else -> { // 其他状态
                holder.meetStatusTv.text = "未知状态"
            }
        }

        // 参加状态
        when (data[position].joinStatus) {
            // 登录人参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假， 3：未参加，结束了未签到未请假，4：已参加，已签到
            0 -> { // 0：未读
                holder.joinStatusTv.text = "未  读"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_red_l_square_round)
            }
            1 -> { // 1：待参加
                holder.joinStatusTv.text = "待参加"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_yellow_square_round)
            }
            2 -> { // 2：已请假
                holder.joinStatusTv.text = "已请假"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_blue_square_round)
            }
            3 -> { // 3：未参加
                holder.joinStatusTv.text = "未参加"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_gray_square_round)
            }
            4 -> { // 4：已参加
                holder.joinStatusTv.text = "已参加"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_green_square_round)
            }
            5 -> { // 5：已取消
                holder.joinStatusTv.text = "已取消"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_gray_square_round)
            }
            else -> { // 其他状态
                holder.joinStatusTv.text = "未知状态"
                holder.joinStatusTv.setBackgroundResource(R.drawable.color_gray_square_round)
            }
        }


        // 分割线
        if (position != itemCount - 1) {
            holder.lineView.visibility = View.GONE
        } else {
            holder.lineView.visibility = View.VISIBLE
        }

        // 时间标题
        if (data[position].planStartDate != null) {
            val planStartDate: Date = data[position].planStartDate

            holder.itemYMTv.text = DateUtil.getDateStrByDate(planStartDate, "yyyy.MM")
            holder.itemDayTv.text = DateUtil.getDateStrByDate(planStartDate, "dd")
            holder.weekTv.text = UnitUtil.getWeek(planStartDate)
        }


//        val currTime: String = data[position].planStartDate // 2019-01-18 09:59:03
//        var currYM = currTime.substring(0, 7)
//        val currMD = currTime.substring(5, 10)
//        val currD = currTime.substring(8, 10)
//        holder.itemDayTv.text = currD
//        holder.itemYMTv.text = currYM.replace("-", "·")
//
//        // 显示提示
//        val isSame: Boolean = try {
//            if (position - 1 >= 0) {
//                val lastTime: String = data[position - 1].planStartDate
//                currTime == lastTime
//            } else {
//                false
//            }
//        } catch (e: Exception) {
//            false
//        }
//        if (needTimeTip) {
//            if (isSame) {
//                holder.timeTv.visibility = View.GONE
//            } else {
//                holder.timeTv.visibility = View.VISIBLE
//                holder.timeTv.text = currMD
//            }
//
//            holder.lineView0.visibility = View.GONE
//        } else {
//            holder.lineView0.visibility = View.VISIBLE
//            holder.timeTv.visibility = View.GONE
//        }

        //
        holder.partDeptNameTv.text = data[position].partDeptName
        if (StringUtil.isNotEmpty(data[position].duty)) {
            holder.dutyTv.text = data[position].duty
            holder.dutyLine.visibility = View.VISIBLE
        } else {
            holder.dutyTv.text = ""
            holder.dutyLine.visibility = View.INVISIBLE
        }

        holder.meetTypeTv.text = StringUtil.getNotNullValue(DataCache.findDictionary(data[position].meetType))

        holder.rootView.setOnClickListener {
            if (itemClickCallBack != null) {
                itemClickCallBack!!.onItemClick(position, data[position])
            }
        }
    }

    fun setData(dataList: ArrayList<ThreeMeetEntity>) {
        if (this.data.size > 0) {
            this.data.clear()
        }

        this.data.addAll(dataList)

        notifyDataSetChanged()
    }

    fun addData(dataList: ArrayList<ThreeMeetEntity>) {
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setClickCallBack(itemClickCallBack: ItemClickCallBack<ThreeMeetEntity>) {
        this.itemClickCallBack = itemClickCallBack
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rootView: LinearLayout = view.findViewById(R.id.rootView)

        val itemTitleTv: TextView = view.findViewById(R.id.itemTitleTv)
        val joinStatusTv: TextView = view.findViewById(R.id.joinStatusTv)
        val meetStatusTv: TextView = view.findViewById(R.id.meetStatusTv)

        val lineView: TextView = view.findViewById(R.id.lineView)
        val lineView0: TextView = view.findViewById(R.id.lineView0)

        //        val timeTv: TextView = view.findViewById(R.id.timeTv)
        val weekTv: TextView = view.findViewById(R.id.weekTv)
        val itemDayTv: TextView = view.findViewById(R.id.itemDayTv)
        val itemYMTv: TextView = view.findViewById(R.id.itemYMTv)

        val partDeptNameTv: TextView = view.findViewById(R.id.partDeptNameTv)
        val dutyTv: TextView = view.findViewById(R.id.dutyTv)
        val dutyLine: TextView = view.findViewById(R.id.dutyLine)

        val meetTypeTv: TextView = view.findViewById(R.id.meetTypeTv)

    }


}