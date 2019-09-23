package com.zkhy.fenggang.community.view.main.bm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jiangyy.easydialog.SingleChoiceDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
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
import kotlinx.android.synthetic.main.activity_bm_handle_body1.*


/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 9:23
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
abstract class BmHandleBodyActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    var id: String = ""
    var areaCode = ""

    private val fatherIDCode = 100
    private val motherIDCode = 101
    private val certificateCode = 102
    private val fatherRegisterCode = 103
    private val motherRegisterCode = 104

    private val marriageCertificateCode = 105
    private val divorceAgreementCode = 106
    private val childrenProofCode = 107

    private var flowId: Int = 2 // 申请流程类型id(1:高龄补贴,2:一孩生育登记,3:二孩生育登记,4:汇川区残疾人证发放和管理)
    private var marryStatus: Int = 1 // 婚姻状态(1：初婚，2：再婚，3：未婚)

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_body1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setListener()

        presenter = BizHandlePresenter(this)

        showMarryUploadFileView(1)

        initView()
    }

    private fun initView() {
        val currUser = DataCache.getUserInfo()
        areaCode = currUser.areaCode

        userNameValueEt.setText(StringUtil.getNotNullValue(currUser.name, "当前用户"))
        userPhoneValueEt.setText(StringUtil.getNotNullValue(currUser.phone, "暂无"))

        areaValueTv.text = StringUtil.getNotNullValue(currUser.areaFullName, "暂无")

        userIDValueEt.setText(StringUtil.getNotNullValue(currUser.idcard, "暂无"))
        userAddressValueEt.setText(StringUtil.getNotNullValue(currUser.addrDetail, "暂无"))
    }

    private fun setListener() {
        marryStatusItem.setOnClickListener(this)
        // 身份证
        fatherIdItem.setOnClickListener(this)
        motherIdItem.setOnClickListener(this)
        // 鉴定
        certificateItem.setOnClickListener(this)

        // 户口簿
        fatherRegisterItem.setOnClickListener(this)
        motherRegisterItem.setOnClickListener(this)

        // 结婚证
        marriageCertificateItem.setOnClickListener(this)
        // 离婚协议
        divorceAgreementItem.setOnClickListener(this)
        // 再婚前子女证明
        childrenProofItem.setOnClickListener(this)

//        noticeDetailsItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)
    }

    protected fun setFlowId(flowId: Int) {
        this.flowId = flowId
    }

    private fun showMarryUploadFileView(whereOne: Int) {
        // 婚姻状态
        marryStatus = whereOne

        fatherIdItem.visibility = View.GONE
        motherIdItem.visibility = View.GONE

        certificateItem.visibility = View.GONE

        fatherRegisterItem.visibility = View.GONE
        motherRegisterItem.visibility = View.GONE

        marriageCertificateItem.visibility = View.GONE

        divorceAgreementItem.visibility = View.GONE
        childrenProofItem.visibility = View.GONE

        when (whereOne) {
            1 -> { // 初婚
                fatherIdItem.visibility = View.VISIBLE
                motherIdItem.visibility = View.VISIBLE
                certificateItem.visibility = View.VISIBLE
                fatherRegisterItem.visibility = View.VISIBLE
                motherRegisterItem.visibility = View.VISIBLE
                marriageCertificateItem.visibility = View.VISIBLE
            }
            2 -> {  // 再婚
                fatherIdItem.visibility = View.VISIBLE
                motherIdItem.visibility = View.VISIBLE

                certificateItem.visibility = View.VISIBLE

                fatherRegisterItem.visibility = View.VISIBLE
                motherRegisterItem.visibility = View.VISIBLE

                marriageCertificateItem.visibility = View.VISIBLE

                divorceAgreementItem.visibility = View.VISIBLE
                childrenProofItem.visibility = View.VISIBLE
            }
            3 -> {  // 未婚
                fatherIdItem.visibility = View.VISIBLE
                motherIdItem.visibility = View.VISIBLE
                certificateItem.visibility = View.VISIBLE
                fatherRegisterItem.visibility = View.VISIBLE
                motherRegisterItem.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            userAreaItem -> {
                IntentUtil.openActivity(this, AddressStreetListActivity::class.java)
                    .putStringExtra("id", "525629318659833921")
                    .requestCode(REQUEST_CODE_ADDRESS)
                    .start()
            }

            marryStatusItem -> {
                SingleChoiceDialog.Builder(this).setTitle("提示")
                    .addList(arrayOf("初婚", "再婚", "未婚"))
                    .setOnItemClickListener { title, position ->
                        userMaritalValueEt.text = title
                        showMarryUploadFileView((position + 1))
                    }
                    .show()
            }

            fatherIdItem -> { // 男方身份证图片
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "男方身份证")
//                    .putStringExtra("bizType", "admin_convenient_people_spouse_man_identity_card")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.IDCard)
//                    .requestCode(fatherIDCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "男方身份证"
                fileEntity.tip = "请上传男方身份证正反面清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_IDENTITY_CARD
                fileEntity.fileList = if (file01List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file01List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(fatherIDCode)
                    .start()
            }

            motherIdItem -> { // 女方身份证图片
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "女方身份证")
//                    .putStringExtra("bizType", "admin_convenient_people_spouse_women_identity_card")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.IDCard)
//                    .requestCode(motherIDCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "女方身份证"
                fileEntity.tip = "请上传女方身份证正反面清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_IDENTITY_CARD
                fileEntity.fileList = if (file02List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file02List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(motherIDCode)
                    .start()
            }

            certificateItem -> { // 子女签定图片
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "子女签定")
//                    .putStringExtra("bizType", "admin_convenient_people_signature_of_children")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.Certificate)
//                    .requestCode(certificateCode)
//                    .start()
                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "子女签定"
                fileEntity.tip = "请上传子女签定情况清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SIGNATURE_OF_CHILDREN
                fileEntity.fileList = if (file03List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file03List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(certificateCode)
                    .start()
            }


            fatherRegisterItem -> { // 男方户口簿
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "男方户口簿")
//                    .putStringExtra("bizType", "admin_convenient_people_spouse_man_household_register")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
//                    .requestCode(fatherRegisterCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "男方户口簿"
                fileEntity.tip = "请上传男方户口簿户主页和本人信息页清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_HOUSEHOLD_REGISTER
                fileEntity.fileList = if (file04List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file04List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(fatherRegisterCode)
                    .start()
            }

            motherRegisterItem -> { // 女方户口簿
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "女方户口簿")
//                    .putStringExtra("bizType", "admin_convenient_people_spouse_women_household_register")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
//                    .requestCode(motherRegisterCode)
//                    .start()
                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "女方户口簿"
                fileEntity.tip = "请上传女方户口簿户主页和本人信息页清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_HOUSEHOLD_REGISTER
                fileEntity.fileList = if (file05List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file05List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(motherRegisterCode)
                    .start()
            }

            marriageCertificateItem -> { // 结婚证
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "结婚证")
//                    .putStringExtra("bizType", "admin_convenient_people_marriage_certificate")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.MarryBook)
//                    .requestCode(marriageCertificateCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "结婚证"
                fileEntity.tip = "请上传结婚证信息页清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE
                fileEntity.fileList = if (file06List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file06List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(marriageCertificateCode)
                    .start()
            }

            divorceAgreementItem -> { // 离婚协议
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "离婚协议")
//                    .putStringExtra("bizType", "admin_convenient_people_divorce_agreement")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.DivorceAgreement)
//                    .requestCode(divorceAgreementCode)
//                    .start()

                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "离婚协议"
                fileEntity.tip = "请上传离婚协议清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_DIVORCE_AGREEMENT
                fileEntity.fileList = if (file07List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file07List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(divorceAgreementCode)
                    .start()
            }

            childrenProofItem -> { // 再婚前子女情况证明
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmIDCardUploadActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .putStringExtra("tip", "再婚前子女情况")
//                    .putStringExtra("bizType", "admin_convenient_people_situation_of_remarried_children")
//                    .putIntExtra("contentType", BmIDCardUploadActivity.ChildrenProof)
//                    .requestCode(childrenProofCode)
//                    .start()
                val fileEntity = BmFileEntity()
                fileEntity.bizId = id
                fileEntity.titleStr = "再婚前子女情况"
                fileEntity.tip = "请上传再婚前子女情况清晰图片"
                fileEntity.bizType = BizType.ADMIN_CONVENIENT_PEOPLE_SITUATION_OF_REMARRIED_CHILDREN
                fileEntity.fileList = if (file08List == null) {// 本次已选附件或重新提交已存附件回显
                    ArrayList()
                } else {
                    file08List
                }

                IntentUtil.openActivity(
                    this@BmHandleBodyActivity, FileUploadCommActivity::class.java
                )
                    .putSerializableExtra("fileEntity", fileEntity)
                    .requestCode(childrenProofCode)
                    .start()
            }

//            noticeDetailsItem -> {
//                IntentUtil.openActivity(
//                    this@BmHandleBodyActivity, BmHandleBodyNoticeActivity::class.java
//                ).start()
//            }

            submitBtn -> {
                submit()
            }
        }
    }

    private var file01List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file02List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file03List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file04List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file05List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file06List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file07List: java.util.ArrayList<ImgSelectEntity>? = null
    private var file08List: java.util.ArrayList<ImgSelectEntity>? = null

    private val REQUEST_CODE_ADDRESS: Int = 201

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
            fatherIDCode -> {
                // 附件处理
                file01List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file01List != null && file01List!!.size > 0) {
                    id = file01List!![0].bizId
                    fatherIdValueEt.text = "已上传"
                } else {
                    fatherIdValueEt.text = "未上传"
                }
            }
            motherIDCode -> {
                // 附件处理
                file02List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file02List != null && file02List!!.size > 0) {
                    id = file02List!![0].bizId
                    motherIdValueEt.text = "已上传"
                } else {
                    motherIdValueEt.text = "未上传"
                }
            }
            certificateCode -> {
                // 附件处理
                file03List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file03List != null && file03List!!.size > 0) {
                    id = file03List!![0].bizId
                    certificateValueEt.text = "已上传"
                } else {
                    certificateValueEt.text = "未上传"
                }
            }
            fatherRegisterCode -> {
                // 附件处理
                file04List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file04List != null && file04List!!.size > 0) {
                    id = file04List!![0].bizId
                    fatherRegisterValueEt.text = "已上传"
                } else {
                    fatherRegisterValueEt.text = "未上传"
                }
            }
            motherRegisterCode -> {
                // 附件处理
                file05List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file05List != null && file05List!!.size > 0) {
                    id = file05List!![0].bizId
                    motherRegisterValueEt.text = "已上传"
                } else {
                    motherRegisterValueEt.text = "未上传"
                }
            }
            marriageCertificateCode -> {
                // 附件处理
                file06List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file06List != null && file06List!!.size > 0) {
                    id = file06List!![0].bizId
                    marriageCertificateValueEt.text = "已上传"
                } else {
                    marriageCertificateValueEt.text = "未上传"
                }
            }
            divorceAgreementCode -> {
                // 附件处理
                file07List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file06List != null && file07List!!.size > 0) {
                    id = file07List!![0].bizId
                    divorceAgreementValueEt.text = "已上传"
                } else {
                    divorceAgreementValueEt.text = "未上传"
                }
            }
            childrenProofCode -> {
                // 附件处理
                file08List = data.getSerializableExtra("fileList") as ArrayList<ImgSelectEntity>?
                if (file08List != null && file08List!!.size > 0) {
                    id = file08List!![0].bizId
                    childrenProofValueEt.text = "已上传"
                } else {
                    childrenProofValueEt.text = "未上传"
                }
            }
        }
    }

    private fun submit() {
        val vo = PersonWorkVo()
        val userName = userNameValueEt.text.toString()
        val userID = userIDValueEt.text.toString()
        val userPhone = userPhoneValueEt.text.toString()
        val userAddress = userAddressValueEt.text.toString()

        vo.marryStatus = marryStatus

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

        vo.flowId = this.flowId
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

        if (file01List == null || file01List!!.size == 0) {
            ToastUtil.show("请先上传男方身份证正反面资料")
            return
        }

        if (file02List == null || file02List!!.size == 0) {
            ToastUtil.show("请先上传女方身份证正反面资料")
            return
        }

        if (file03List == null || file03List!!.size == 0) {
            ToastUtil.show("请先上传子女签定情况资料")
            return
        }

        if (file04List == null || file04List!!.size == 0) {
            ToastUtil.show("请先上传男方户口簿资料")
            return
        }

        if (file05List == null || file05List!!.size == 0) {
            ToastUtil.show("请先上传女方户口簿资料")
            return
        }

        // 婚姻状态(1：初婚，2：再婚，3：未婚)
        if (marryStatus == 1) {
            // 1：初婚
            if (file06List == null || file06List!!.size == 0) {
                ToastUtil.show("已选择初婚：请先上传结婚证资料")
                return
            }
        } else if (marryStatus == 2) {
            // 1：再婚
            if (file06List == null || file06List!!.size == 0) {
                ToastUtil.show("已选择再婚：请先上传结婚证资料")
                return
            }
            if (file07List == null || file07List!!.size == 0) {
                ToastUtil.show("已选择再婚：请先上传离婚协议资料")
                return
            }
            if (file08List == null || file08List!!.size == 0) {
                ToastUtil.show("已选择再婚：请先上传再婚前子女情况证明资料")
                return
            }
        } else if (marryStatus == 3) {
            // 未婚：没有更多
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

    override fun load2Complete(resultData: Any?) {
    }
}
