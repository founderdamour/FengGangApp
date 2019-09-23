package com.zkhy.fenggang.community.view.main.bm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.*
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.BizType
import com.zkhy.fenggang.community.model.bean.PersonWorkEntity
import com.zkhy.fenggang.community.model.bean.PersonWorkVo
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.comm.AddressStreetListActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_bm_handle_disability_certificate_submit.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleDisabilityCertificateSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_disability_certificate_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("办理残疾证")

//        id = DateUtil.getCurTimeDate().time.toString()

        initView()

        setListener()

        presenter = BizHandlePresenter(this)
    }

    private fun initView() {
        val currUser = DataCache.getUserInfo()
        areaCode = currUser.areaCode
        userNameValueEt.setText(StringUtil.getNotNullValue(currUser.name, currUser.account))
        userIDValueEt.setText(StringUtil.getNotNullValue(currUser.idcard, "暂无"))
        userPhoneValueEt.setText(StringUtil.getNotNullValue(currUser.phone, "暂无"))

        areaValueTv.text = StringUtil.getNotNullValue(currUser.areaFullName, "暂无")
        userAddressValueEt.setText(StringUtil.getNotNullValue(currUser.addrDetail, "暂无"))
    }

    private fun setListener() {
        userIDImgItem.setOnClickListener(this)
        userRegisterImgItem.setOnClickListener(this)
        userPhotoItem.setOnClickListener(this)
        materialItem.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
        noticeDetailsItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
    }

    private val userIDImgCode: Int = 100
    private val userRegisterCode: Int = 101
    private val userPhotoCode: Int = 102
    private val materialCode: Int = 103
    private val REQUEST_CODE_ADDRESS: Int = 201

    override fun onClick(v: View?) {
        when (v) {
            userIDImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleDisabilityCertificateSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "本人身份证")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                    .putIntExtra("contentType", BmIDCardUploadActivity.IDCard)
                    .requestCode(userIDImgCode)
                    .start()
            }

            userRegisterImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleDisabilityCertificateSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "本人户口簿")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(userRegisterCode)
                    .start()
            }

            userPhotoItem -> {
                IntentUtil.openActivity(
                    this@BmHandleDisabilityCertificateSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "近期本人照片")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_PHOTO)
                    .putIntExtra("contentType", BmIDCardUploadActivity.SelfPhoto)
                    .requestCode(userPhotoCode)
                    .start()
            }

            materialItem -> {
                IntentUtil.openActivity(
                    this@BmHandleDisabilityCertificateSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "申请表和评定表")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_APPLICATION_FORM)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(materialCode)
                    .start()
            }

            noticeDetailsItem -> {
                IntentUtil.openActivity(
                    this@BmHandleDisabilityCertificateSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
                ).start()
            }

            userAreaItem -> {
                IntentUtil.openActivity(this, AddressStreetListActivity::class.java)
                    .putStringExtra("id", "525629318659833921")
                    .requestCode(REQUEST_CODE_ADDRESS)
                    .start()
            }

            submitBtn -> {
                submit()
            }
        }
    }

    var userIDImg = false
    var userRegister = false
    var userPhoto = false
    var material = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && resultCode == 200 && REQUEST_CODE_ADDRESS == requestCode) {
            val streetName = data.getStringExtra("streetName")
            val areaName = data.getStringExtra("areaName")
            areaValueTv.text = "$streetName$areaName"
            areaCode = data.getStringExtra("areaCode")
            return
        }

        if (resultCode != 200 || data == null || TextUtils.isEmpty(data.getStringExtra("id"))) {
            return
        }
        id = data.getStringExtra("id")

        when (requestCode) {
            userIDImgCode -> {
                userIDImgValueEt.text = "已上传"
                userIDImg = true
            }

            userRegisterCode -> {
                userRegisterValueTv.text = "已上传"
                userRegister = true
            }

            userPhotoCode -> {
                userPhotoValueTv.text = "已上传"
                userPhoto = true
            }

            materialCode -> {
                materialValueTv.text = "已上传"
                material = true
            }
        }
    }

    private fun submit() {
        val vo = PersonWorkVo()
        val userName = userNameValueEt.text.toString()
        val userID = userIDValueEt.text.toString()
        val userPhone = userPhoneValueEt.text.toString()
        val userAddress = userAddressValueEt.text.toString()

        if (StringUtil.isEmpty(userName)) {
            ToastUtil.show("请填写申请人姓名")
            return
        } else {
            vo.name = userName
        }


        if (StringUtil.isEmpty(userID)) {
            ToastUtil.show("请填写申请人身份证号")
            return
        } else {
            if (!StringUtilExt.isIDNumber(userID)) {
                ToastUtil.show("身份证号格式不正确")
                return
            }
            vo.idcard = userID
        }

        if (StringUtil.isEmpty(userPhone)) {
            ToastUtil.show("请填写申请人电话")
            return
        } else {
            if (!StringUtilExt.isMobile(userPhone) && !StringUtilExt.isPhone(userPhone)) {
                ToastUtil.show("电话格式不正确")
                return
            }
            vo.phone = userPhone
        }

        if (StringUtil.isEmpty(areaCode)) {
            ToastUtil.show("请填写社区信息")
            return
        } else {
            vo.areaCode = areaCode
        }

        if (StringUtil.isEmpty(userAddress)) {
            ToastUtil.show("请填写详细地址")
            return
        } else {
            vo.addrDetail = userAddress
        }

        // 非填写部分
        vo.id = id

        vo.flowId = 4
        vo.handleType = 1

        val userInfo: WmUser = DataCache.getUserInfo()
        vo.applyUserId = userInfo.userId
        vo.apprUserId = userInfo.userId

        vo.cityId = 851
        vo.county = 5222
        vo.provinceId = 54
        vo.town = 123

        // ==================================================
        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show("网络不可用")
            return
        }
        if (TextUtils.isEmpty(id)) {
            ToastUtil.show("请先上传资料")
            return
        }

        if (!userIDImg) {
            ToastUtil.show("请先上传身份证资料")
            return
        }

        if (!userRegister) {
            ToastUtil.show("请先上传户口本资料")
            return
        }

        if (!userPhoto) {
            ToastUtil.show("请先上传本人照片")
            return
        }

        if (!material) {
            ToastUtil.show("请先上传申请材料")
            return
        }

        presenter?.submitAllowance(vo)
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<PersonWorkEntity>
        if (result.errcode == 0) {
            showTip("申请已提交")
            finish()
        } else {
            showTip(result.errmsg)
        }
    }
}