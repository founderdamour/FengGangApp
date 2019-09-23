package com.zkhy.fenggang.community.view.main.bm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.AppointmentResultEntity
import com.zkhy.fenggang.community.model.bean.AppointmentVo
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.BizOrderPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_bm_order_submit.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  提交预约
 *  更新:
 * <pre>
 */
class BmOrderSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    private var applyTypeCode = ""
    private var oderDate = ""
    private var oderTime = ""
    private var oderAdd = ""
    private var oderTimeType = true

    var presenter: BizOrderPresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_order_submit

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("提交预约")

        applyTypeCode = intent.getStringExtra("applyTypeCode")
        oderDate = intent.getStringExtra("oderDate")
        oderTime = intent.getStringExtra("oderTime")
        oderAdd = intent.getStringExtra("oderAdd")
        oderTimeType = intent.getBooleanExtra("oderTimeType", true)

        setListener()
        presenter = BizOrderPresenter(this)

        initView()
    }

    private fun initView() {
        oderTimeTv.text = "$oderDate $oderTime"
        oderAddTv.text = oderAdd

        val currUser = DataCache.getUserInfo()
        userNameValueTv.text = StringUtil.getNotNullValue(currUser.name, "当前用户")
        userIDValueTv.text = StringUtil.getNotNullValue(currUser.idcard, "暂无")
        userPhoneValueTv.text = StringUtil.getNotNullValue(currUser.phone, "暂无")
    }

    private fun setListener() {
        submitBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            submitBtn -> {
                presenter!!.submitBmOrder(AppointmentVo(applyTypeCode, oderDate, oderTimeType))
            }
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        LoadingDialog.hidden()
    }

    override fun loadComplete(resultData: Any) {
        val result = resultData as BaseData<AppointmentResultEntity>
        if (result.errcode == 0) {

            if (result.data != null) {
                IntentUtil.openActivity(this@BmOrderSubmitActivity, BmOrderResultActivity::class.java)
                    .putSerializableExtra("appointmentResult", result.data).finish(true)
                    .start()
            } else {
                showTip(result.errmsg)
            }
        } else {
            showTip(result.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {
    }
}
