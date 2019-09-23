package com.zkhy.fenggang.community.view.main.ts

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.ComplaintEntity
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.base.commInfoEdit.InfoEditActivity
import com.zkhy.library.base.commInfoEdit.KeyValueEntity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.CommUtil
import kotlinx.android.synthetic.main.activity_am_impeach_submit.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/5/9 on 11:09
 *  项目:  WuMinAndroid
 *  描述:  网上信访
 *  更新:
 * <pre>
 */
class ComplaintCreateActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    var presenter: AMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_am_complaint_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("咨询投诉", "我的咨询") {
            IntentUtil.openActivity(this, ComplaintHistoryListActivity::class.java)
                .start()
        }

        presenter = AMPresenter(this)

        initView()
    }

    private fun initView() {
        val currUser:WmUser = DataCache.getUserInfo()
        userNameValueEt.setText(StringUtil.getNotNullValue(currUser.name, currUser.account))
        userIDValueEt.setText(StringUtil.getNotNullValue(currUser.idcard, "暂无"))
        userPhoneValueEt.setText(StringUtil.getNotNullValue(currUser.phone, "暂无"))

        userAddressItem.setOnClickListener(this)
        titleItem.setOnClickListener(this)
        contentItem.setOnClickListener(this)

        submitBtn.setOnClickListener(this)
    }

    private val userAddressItemCode: Int = 301
    private val titleItemCode: Int = 302
    private val contentItemCode: Int = 303

    override fun onClick(v: View?) {
        when (v) {
            userAddressItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "详细地址"
                keyValue.content = userAddressValueTv.text.toString()
                keyValue.inputHint = "请填写详细地址"
                keyValue.maxSize = 64
                keyValue.minSize = 2
                keyValue.requestCode = userAddressItemCode
                InfoEditActivity.start(this@ComplaintCreateActivity, keyValue)
            }
            titleItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "咨询标题"
                keyValue.content = titleValueTv.text.toString()
                keyValue.inputHint = "请填写咨询标题"
                keyValue.maxSize = 512
                keyValue.minSize = 2
                keyValue.requestCode = titleItemCode
                InfoEditActivity.start(this@ComplaintCreateActivity, keyValue)
            }
            contentItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "咨询内容"
                keyValue.content = contentValueTv.text.toString()
                keyValue.inputHint = "请填写咨询内容"
                keyValue.maxSize = 2048
                keyValue.minSize = 2
                keyValue.requestCode = contentItemCode
                InfoEditActivity.start(this@ComplaintCreateActivity, keyValue)
            }

            submitBtn -> {
                submitImpeach()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != 200 || data == null || data.getStringExtra("value") == null) {
            return
        }

        val value: String? = data.getStringExtra("value")

        when (requestCode) {
            userAddressItemCode -> {
                userAddressValueTv.text = StringUtil.getNotNullValue(value)
            }

            titleItemCode -> {
                titleValueTv.text = StringUtil.getNotNullValue(value)
            }

            contentItemCode -> {
                contentValueTv.text = StringUtil.getNotNullValue(value)
            }
        }
    }

    private fun submitImpeach() {

        val userNameValue: String = userNameValueEt.text.toString()
        val userIDValue: String = userIDValueEt.text.toString()
        val userPhoneValue: String = userPhoneValueEt.text.toString()
        val userAddressValue: String = userAddressValueTv.text.toString()
        val titleValue: String = titleValueTv.text.toString()
        val contentValue: String = contentValueTv.text.toString()

        if (StringUtil.isEmpty(userNameValue)) {
               ToastUtil.show("请正确输入姓名")
            return
        }

        if (StringUtil.isEmpty(userIDValue)) {
            ToastUtil.show("请正确输入身份证")
            return
        }

        if (StringUtil.isEmpty(userPhoneValue)) {
            ToastUtil.show("请正确输入电话号码")
            return
        }

        if (!CommUtil.checkPhoneNum(userPhoneValue)) {
            ToastUtil.show("联系号码格式不正确")
            return
        }

        if (StringUtil.isEmpty(userAddressValue)) {
            ToastUtil.show("请正确输入详细地址")
            return
        }

        if (StringUtil.isEmpty(titleValue)) {
            ToastUtil.show("请正确输入咨询标题")
            return
        }

        if (StringUtil.isEmpty(contentValue)) {
            ToastUtil.show("请正确输入咨询内容")
            return
        }

        val entity = ComplaintEntity()
        entity.name = userNameValue
        entity.idcard = userIDValue
        entity.phone = userPhoneValue

        entity.addr = userAddressValue
        entity.title = titleValue
        entity.content = contentValue

        entity.from = "app"

        presenter?.createComplaint(entity)
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadComplete(resultData: Any?) {
        // 成功提交关闭界面
        finish()
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}