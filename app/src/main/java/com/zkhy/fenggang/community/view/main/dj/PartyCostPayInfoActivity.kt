package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.bean.PartyPayInfoEntity
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.DJPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_party_cost_pay_info.*

/**
 * 党费缴纳主页
 */
class PartyCostPayInfoActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidView {

    private var presenter: DJPresenter? = null
    var payInfo: PartyPayInfoEntity? = null

    override fun getLayout(): Int = R.layout.activity_party_cost_pay_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("缴党费")

        payInfo = intent.getSerializableExtra("payInfo") as PartyPayInfoEntity?

        if (payInfo != null) {
            initView(payInfo!!)
        }

        presenter = DJPresenter(this)
        submitBtn.setOnClickListener(this)
    }

    private fun initView(payInfo: PartyPayInfoEntity) {
        payAmountTv.text = payInfo.payableAmout

        val userInfo: WmUser? = DataCache.getUserInfo()
        if (userInfo != null) {
            userNameTv.text = StringUtil.getNotNullValue(userInfo.name, userInfo.account)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            submitBtn -> {
                val vo = CommReq()
                vo.id = payInfo!!.id
                vo.yearMonth = payInfo!!.yearMonth
                presenter?.updatePartyPayStatus(vo)
            }
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在缴费")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any) {
        val result: BaseData<String> = resultData as BaseData<String>
        if (result.errcode == 0) {
            ToastUtil.show("缴费成功")
            finish()
        } else {
            ToastUtil.show(result.errmsg)
        }
    }
}
