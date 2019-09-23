package com.zkhy.fenggang.community.view.main.bm.m3

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
import com.zkhy.fenggang.community.view.main.bm.BmHandleAllowanceNoticeActivity
import com.zkhy.fenggang.community.view.main.bm.BmIDCardUploadActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_bm_handle_med_ass_submit.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleMedicalAssistanceSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_med_ass_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("医疗救助")

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
        file1Item.setOnClickListener(this)
        file2Item.setOnClickListener(this)
        file3Item.setOnClickListener(this)
        file4Item.setOnClickListener(this)
        file5Item.setOnClickListener(this)
        file6Item.setOnClickListener(this)
        file7Item.setOnClickListener(this)

        userAreaItem.setOnClickListener(this)
        noticeDetailsItem.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
    }

    private val File_1_Code: Int = 101
    private val File_2_Code: Int = 102
    private val File_3_Code: Int = 103
    private val File_4_Code: Int = 104
    private val File_5_Code: Int = 105
    private val File_6_Code: Int = 106
    private val File_7_Code: Int = 107

    private val REQUEST_CODE_ADDRESS: Int = 201

    override fun onClick(v: View?) {
        when (v) {
            file1Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "身份证")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ1)
                    .putIntExtra("contentType", BmIDCardUploadActivity.IDCard)
                    .requestCode(File_1_Code)
                    .start()
            }

            file2Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "户口簿")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ2)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_2_Code)
                    .start()
            }

            file3Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "低保证/五保证/优抚证等")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ3)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_3_Code)
                    .start()
            }

            file4Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "疾病证明书")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ4)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_4_Code)
                    .start()
            }

            file5Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "出院小结")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ5)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_5_Code)
                    .start()
            }

            file6Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "住院费用清单和发票")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ6)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_6_Code)
                    .start()
            }

            file7Item -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "出院病员结算单")
                    .putStringExtra("bizType", BizType.WU_BM_YL_JZ7)
                    .putIntExtra("contentType",
                        BmIDCardUploadActivity.RegisterBook
                    )
                    .requestCode(File_7_Code)
                    .start()
            }

            noticeDetailsItem -> {
                IntentUtil.openActivity(
                    this@BmHandleMedicalAssistanceSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
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

    var file1Img = false
    var file2Img = false
    var file3Img = false
    var file4Img = false
    var file5Img = false
    var file6Img = false
    var file7Img = false

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
            File_1_Code -> {
                file1ValueTv.text = "已上传"
                file1Img = true
            }
            File_2_Code -> {
                file2ValueTv.text = "已上传"
                file2Img = true
            }

            File_3_Code -> {
                file3ValueTv.text = "已上传"
                file3Img = true
            }

            File_4_Code -> {
                file4ValueTv.text = "已上传"
                file4Img = true
            }
            File_5_Code -> {
                file1ValueTv.text = "已上传"
                file5Img = true
            }
            File_6_Code -> {
                file2ValueTv.text = "已上传"
                file6Img = true
            }

            File_7_Code -> {
                file3ValueTv.text = "已上传"
                file7Img = true
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

        vo.flowId = 15 // 医疗救助
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

        if (!file1Img) {
            ToastUtil.show("请先上传身份证资料")
            return
        }

        if (!file2Img) {
            ToastUtil.show("请先上传户口簿资料")
            return
        }

        if (!file3Img) {
            ToastUtil.show("请先上传低保证/五保证/优抚证等")
            return
        }

        if (!file4Img) {
            ToastUtil.show("请先上传疾病证明书")
            return
        }

        if (!file5Img) {
            ToastUtil.show("请先上传出院小结")
            return
        }

        if (!file6Img) {
            ToastUtil.show("请先上传住院费用清单和发票")
            return
        }

        if (!file7Img) {
            ToastUtil.show("请先上传出院病员结算单")
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