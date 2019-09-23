package com.zkhy.fenggang.community.view.main.bm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.*
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.*
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.comm.AddressStreetListActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_bm_handle_allowance_submit.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleAllowanceSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_allowance_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("高龄补贴")

//        id = intent.getStringExtra("id")

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
        userPhotoImgItem.setOnClickListener(this)
        userBCImgItem.setOnClickListener(this)

        userAreaItem.setOnClickListener(this)
        noticeDetailsItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
    }

    private val userIDImgCode: Int = 100
    private val userRegisterCode: Int = 101
    private val userPhotoImgCode: Int = 102
    private val userBCImgCode: Int = 103
    private val REQUEST_CODE_ADDRESS: Int = 201

    override fun onClick(v: View?) {
        when (v) {
            userIDImgItem -> {
//                IntentUtil.openActivity(
//                    this@BmHandleAllowanceSubmitActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "本人身份证")
//                    .putStringExtra("bizType", "admin_convenient_people_identity_card")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.IDCard)
//                    .requestCode(userIDImgCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "本人身份证"
                fileEntity.tip = "请上传本人身份证正反面"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD
                fileEntity.contentType = BmIDCardUploadActivity.IDCard
                fileEntity.fileMap = if (idCardFileList == null) {// 本次已选附件或重新提交已存附件回显
                    HashMap()
                } else {
                    idCardFileList
                }

                IntentUtil.openActivity(
                    this@BmHandleAllowanceSubmitActivity, IDCardUploadActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(userIDImgCode)
                    .start()

            }

            userRegisterImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleAllowanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "本人户口簿")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(userRegisterCode)
                    .start()
            }

            userPhotoImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleAllowanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "近期本人照片")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_PHOTO)
                    .putIntExtra("contentType", BmIDCardUploadActivity.SelfPhoto)
                    .requestCode(userPhotoImgCode)
                    .start()
            }

            userBCImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleAllowanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "贵州银行卡照片")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_BANK_CARD)
                    .putIntExtra("contentType", BmIDCardUploadActivity.BankCard)
                    .requestCode(userBCImgCode)
                    .start()
            }

            noticeDetailsItem -> {
                IntentUtil.openActivity(
                    this@BmHandleAllowanceSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
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

    private var idCardFileList: HashMap<Int, ImgSelectEntity>? = null

    //    var userIDImg = false
    var userRegister = false
    var userPhoto = false
    var userBCImgUp = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && resultCode == 200 && REQUEST_CODE_ADDRESS == requestCode) {
            val streetName = data.getStringExtra("streetName")
            val areaName = data.getStringExtra("areaName")
            areaValueTv.text = "$streetName$areaName"
            areaCode = data.getStringExtra("areaCode")
            return
        }

        if (resultCode != 200 || data == null) {
            return
        }

        when (requestCode) {

            userIDImgCode -> {
                // 附件处理
                idCardFileList = data.getSerializableExtra("fileList") as HashMap<Int, ImgSelectEntity>

                if (idCardFileList != null && idCardFileList!!.size > 0) {

                    val imgSelectEntity: ImgSelectEntity = if (idCardFileList!![0] != null) {
                        idCardFileList!![0]!!
                    } else {
                        idCardFileList!![1]!!
                    }

                    id = imgSelectEntity.bizId

                    userIDImgValueTv.text = "已上传"
                } else {
                    userIDImgValueTv.text = "未上传"
                }
            }
        }

        if (resultCode != 200 || data == null || TextUtils.isEmpty(data.getStringExtra("id"))) {
            return
        }
        id = data.getStringExtra("id")

        when (requestCode) {
//            userIDImgCode -> {
//                userIDImgValueTv.text = "已上传"
//                userIDImg = true
//            }
//
//            userIDImgCode -> {
//                // 附件处理
//                idCardFileList = data.getSerializableExtra("fileList") as HashMap<Int, ImgSelectEntity>
//
//                if (idCardFileList != null && idCardFileList!!.size > 0) {
//
//                    id = idCardFileList!![0]!!.bizId
//
//                    userIDImgValueEt.text = "已上传"
//                    userIDImg = true
//                } else {
//                    userIDImgValueEt.text = "未上传"
//                    userIDImg = false
//                }
//            }

            userRegisterCode -> {
                userRegisterValueTv.text = "已上传"
                userRegister = true
            }

            userPhotoImgCode -> {
                userPhotoValueTv.text = "已上传"
                userPhoto = true
            }

            userBCImgCode -> {
                userBCValueTv.text = "已上传"
                userBCImgUp = true
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

        vo.flowId = 1
        vo.handleType = 1

        val userInfo: WmUser = DataCache.getUserInfo()
        vo.applyUserId = userInfo.userId
        vo.apprUserId = userInfo.userId

        vo.cityId = 851
        vo.county = 5222
        vo.provinceId = 54
        vo.town = 123

        // ==========================================
        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show("网络不可用")
            return
        }

        if (TextUtils.isEmpty(id)) {
            ToastUtil.show("请先上传资料")
            return
        }

        if (idCardFileList == null || idCardFileList!!.size < 2) {
            ToastUtil.show("请先上传身份证正反面资料")
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

        if (!userBCImgUp) {
            ToastUtil.show("请先上传本人贵州银行卡照片")
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