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
import kotlinx.android.synthetic.main.activity_bm_handle_children_age_submit.*
import java.util.ArrayList

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleChildrenAgeSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_children_age_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("适龄/超龄儿童入学")
//        id = intent.getStringExtra("id")
        setListener()

        presenter = BizHandlePresenter(this)

        initView()
    }

    private fun initView() {
        val currUser = DataCache.getUserInfo()
        areaCode = currUser.areaCode
        userNameValueEt.setText(StringUtil.getNotNullValue(currUser.name, currUser.account))
        userPhoneValueEt.setText(StringUtil.getNotNullValue(currUser.phone, "暂无"))
        userIDValueEt.setText(StringUtil.getNotNullValue(currUser.idcard, "暂无"))

        areaValueTv.text = StringUtil.getNotNullValue(currUser.areaFullName, "暂无")
        userAddressValueEt.setText(StringUtil.getNotNullValue(currUser.addrDetail, "暂无"))
    }
    private fun setListener() {
        userRegisterItem.setOnClickListener(this)
        birthdayItem.setOnClickListener(this)
        vaccinationItem.setOnClickListener(this)
        propertyCertificateItem.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)
        noticeDetailsItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
    }

    private val userRegisterCode: Int = 100
    private val birthdayCode: Int = 101
    private val vaccinationCode: Int = 102
    private val propertyCertificateCode: Int = 103
    private val REQUEST_CODE_ADDRESS: Int = 201

    override fun onClick(v: View?) {
        when (v) {
            userRegisterItem -> {
                IntentUtil.openActivity(
                    this@BmHandleChildrenAgeSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "户口簿资料")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(userRegisterCode)
                    .start()
            }

            birthdayItem -> {
                // 生育证
                IntentUtil.openActivity(
                    this@BmHandleChildrenAgeSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "孩子出生证")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_BIRTH_CERTIFICATE)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(birthdayCode)
                    .start()
            }

            vaccinationItem -> {
                //  疫苗接种证
                IntentUtil.openActivity(
                    this@BmHandleChildrenAgeSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "孩子疫苗接种证")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_VACCINATION_CERTIFICATE)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(vaccinationCode)
                    .start()
            }

            propertyCertificateItem -> {
                // 房产证或购房合同
//                IntentUtil.openActivity(
//                    this@BmHandleChildrenAgeSubmitActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "房产证或购房合同")
//                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_PURCHASE_CONTRACT)
//                    .putIntExtra("contentType", BmIDCardUploadActivity.BankCard)
//                    .requestCode(propertyCertificateCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "房产证或购房合同"
                fileEntity.tip = "请上传房产证或购房合同主要信息页的清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_PURCHASE_CONTRACT
                fileEntity.fileList = if (houseBookFileList == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    houseBookFileList
                }

                IntentUtil.openActivity(
                    this@BmHandleChildrenAgeSubmitActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(propertyCertificateCode)
                    .start()
            }

            noticeDetailsItem -> {
                IntentUtil.openActivity(
                    this@BmHandleChildrenAgeSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
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

    var userRegister = false
    var birthdayUp = false
    var vaccinationUp = false
    var propertyCertificate = false

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
            userRegisterCode -> {
                fatherRegisterValueTv.text = "已上传"
                userRegister = true
            }

            birthdayCode -> {
                birthdayValueTv.text = "已上传"
                birthdayUp = true
            }

            vaccinationCode -> {
                vaccinationValueTv.text = "已上传"
                vaccinationUp = true
            }

            propertyCertificateCode -> {

                // 附件处理
                houseBookFileList = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?

                if (houseBookFileList != null && houseBookFileList!!.size > 0) {

                    id = houseBookFileList!![0].bizId

                    propertyCertificateValueTv.text = "已上传"
                    propertyCertificate = true
                } else {
                    propertyCertificateValueTv.text = "未上传"
                    propertyCertificate = false
                }
            }

        }
    }

    var houseBookFileList: ArrayList<ImgSelectEntity>? = null

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

        vo.flowId = 9
        vo.handleType = 1

        val userInfo: WmUser = DataCache.getUserInfo()
        vo.applyUserId = userInfo.userId
        vo.apprUserId = userInfo.userId

        vo.cityId = 851
        vo.county = 5222
        vo.provinceId = 54
        vo.town = 123

        // ========================================================
        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show("网络不可用")
            return
        }
        if (TextUtils.isEmpty(id)) {
            ToastUtil.show("请先上传资料")
            return
        }

        if (!userRegister) {
            ToastUtil.show("请先上传户口本资料")
            return
        }
        if (!birthdayUp) {
            ToastUtil.show("请先上传出生证明资料")
            return
        }
        if (!vaccinationUp) {
            ToastUtil.show("请先上传疫苗接种证资料")
            return
        }
        if (!propertyCertificate) {
            ToastUtil.show("请先上传房产证或购房合同资料")
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