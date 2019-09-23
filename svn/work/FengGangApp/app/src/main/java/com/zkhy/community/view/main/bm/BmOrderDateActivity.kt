package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.AppointmentBaseEntity
import com.zkhy.community.model.bean.AppointmentDayEntity
import com.zkhy.community.model.bean.AppointmentStateEntity
import com.zkhy.community.presenter.BizOrderPresenter
import com.zkhy.community.view.main.bm.adapters.OrderDateGridViewAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_bm_order_date.*
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约时间选择
 *  更新:
 * <pre>
 */
class BmOrderDateActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    private var typeCode = ""
    private var presenter: BizOrderPresenter? = null

    private fun setListener() {
        amOrderBtn.setOnClickListener(this)
        pmOrderBtn.setOnClickListener(this)
    }

    override fun getLayout(): Int = R.layout.activity_bm_order_date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("选择办理日期")
        setListener()

        typeCode = intent.getStringExtra("typeCode")

        presenter = BizOrderPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter!!.getBmOrderDate(typeCode)
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<AppointmentBaseEntity>
        if (result.errcode != 0) {
            showTip(result.errmsg)
        } else {
            val bppointmentBase: AppointmentBaseEntity? = result.data ?: return

            bppointmentBaseTemp = bppointmentBase

            orderAddressTv.text = StringUtil.getNotNullValue(bppointmentBase!!.windowName, "无办事窗口信息")

            val dateInfoList = bppointmentBase.appointmentStateList
            val dateGridViewAdapter = OrderDateGridViewAdapter(this,
                OrderDateGridViewAdapter.OnListItemClickListener { _, itemData ->
                    val data: AppointmentStateEntity = itemData
                    // 获得某天的预约情况
                    initOrderDayInfo(bppointmentBase.id, data.currDate)
                })

            dateGridViewAdapter.setData(dateInfoList)
            gridView.adapter = dateGridViewAdapter

            // 初始化今天预约情况
            var currDate = DateUtil.getDateStrByDate(Date(), "yyyy-MM-dd")
            initOrderDayInfo(bppointmentBase.id, currDate)
        }
    }

    private fun initOrderDayInfo(id: String?, currDate: String?) {
        currSelectedDate = currDate

        presenter!!.getAppointmentStateByDate(id, currDate)
        selectedDateTv.text = "$currSelectedDate 可选号源"
    }

    override fun load2Complete(resultData: Any?) {

        val result = resultData as BaseData<AppointmentDayEntity>
        if (result.errcode != 0) {
            showTip(result.errmsg)

        } else {
            if (result.data == null) {
                orderDayInfoView.visibility = View.GONE

            } else {
                val appointmentDay: AppointmentDayEntity? = result.data as AppointmentDayEntity

                orderDayInfoView.visibility = View.VISIBLE

                appointmentDayTemp = appointmentDay

                amValueTv.text = appointmentDay!!.amTime
                amOrderedNumTv.text = appointmentDay.amAppointmentNum.toString()
                amSurplusNumTv.text = appointmentDay.amSurplusNum.toString()

                pmValueTv.text = appointmentDay.pmTime
                pmOrderedNumTv.text = appointmentDay.pmAppointmentNum.toString()
                pmSurplusNumTv.text = appointmentDay.pmSurplusNum.toString()

                if (appointmentDay.isAmState) {
                    amOrderBtn.isClickable = true
                    amOrderBtn.setBackgroundColor(resources.getColor(R.color.colorAccent))
                } else {
                    amOrderBtn.isClickable = false
                    amOrderBtn.setBackgroundColor(resources.getColor(R.color.app_bg))
                }

                if (appointmentDay.isPmState) {
                    pmOrderBtn.isClickable = true
                    pmOrderBtn.setBackgroundColor(resources.getColor(R.color.colorAccent))
                } else {
                    pmOrderBtn.isClickable = false
                    pmOrderBtn.setBackgroundColor(resources.getColor(R.color.app_bg))
                }
            }
        }
    }

    private var appointmentDayTemp: AppointmentDayEntity? = null
    private var bppointmentBaseTemp: AppointmentBaseEntity? = null
    private var currSelectedDate: String? = null

    override fun onClick(v: View?) {

        if (appointmentDayTemp != null && bppointmentBaseTemp != null) {

            when (v) {
                amOrderBtn -> {
                    IntentUtil.openActivity(this@BmOrderDateActivity, BmOrderSubmitActivity::class.java)
                        .putStringExtra("applyTypeCode", typeCode)
                        .putStringExtra("oderDate", currSelectedDate)
                        .putStringExtra("oderTime", appointmentDayTemp!!.amTime)
                        .putStringExtra("oderAdd", bppointmentBaseTemp!!.windowName)
                        .putBooleanExtra("oderTimeType", true)
                        .start()
                }

                pmOrderBtn -> {
                    IntentUtil.openActivity(this@BmOrderDateActivity, BmOrderSubmitActivity::class.java)
                        .putStringExtra("applyTypeCode", typeCode)
                        .putStringExtra("oderDate", currSelectedDate)
                        .putStringExtra("oderTime", appointmentDayTemp!!.pmTime)
                        .putStringExtra("oderAdd", bppointmentBaseTemp!!.windowName)
                        .putBooleanExtra("oderTimeType", false)
                        .start()
                }
            }
        }
    }
}
