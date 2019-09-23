package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.AppointmentResultEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_bm_order_rsult.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约时间选择
 *  更新:
 * <pre>
 */
class BmOrderResultActivity : TitleBarBaseActivity() {
    private var isHistory = false
    private var appointmentResult: AppointmentResultEntity? = null

    override fun getLayout(): Int = R.layout.activity_bm_order_rsult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("预约结果")

        isHistory = intent.getBooleanExtra("isHistory", false)

        if (isHistory) {
            resultLayout.visibility = View.VISIBLE

            registerBtn.setOnClickListener { ToastUtil.show("功能完善中") }
            cancelBtn.setOnClickListener { ToastUtil.show("功能完善中") }

        } else {
            resultLayout.visibility = View.GONE
        }

        appointmentResult = intent.getSerializableExtra("appointmentResult") as AppointmentResultEntity?
        if (appointmentResult != null) {
            showResult(appointmentResult!!)
        }
    }

    private fun showResult(appointmentResult: AppointmentResultEntity) {
        if (StringUtil.isNotEmpty(appointmentResult.appoNo)) {
            resultIconIv.setImageResource(R.drawable.cgbj)
            resultTxtTv.text = "预约成功"

            bizNameTv.text = appointmentResult.appoNo
            bizNameTv.visibility = View.VISIBLE

            orderNumTv.text = "${appointmentResult.appoNum}号"
            orderNumTv.visibility = View.VISIBLE

            orderNoTv.text = appointmentResult.windowName
            orderNoTv.visibility = View.VISIBLE

            var timeStr = ""
            if (StringUtil.isNotEmpty(appointmentResult.appoDate)) {
                timeStr += appointmentResult.appoDate.substring(0, 10)
            }

            timeStr += if (appointmentResult.isAppoDateType) {
                " 上午 "
            } else {
                " 下午"
            }

            orderDateTv.text = timeStr
            orderDateTv.visibility = View.VISIBLE

        } else {
            resultIconIv.setImageResource(R.drawable.cgbj_fail)
            resultTxtTv.text = "预约失败"

            orderNumTv.visibility = View.GONE
            orderDateTv.visibility = View.GONE
            orderNoTv.visibility = View.GONE
            orderDateTv.visibility = View.GONE

//            orderNoTv.text =
        }
    }
}
