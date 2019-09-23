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
import com.zkhy.fenggang.comm.plugin.activity.KeyValueListActivity
import com.zkhy.fenggang.comm.plugin.entity.KValueEntity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.BizType
import com.zkhy.fenggang.community.model.bean.PersonWorkEntity
import com.zkhy.fenggang.community.model.bean.PersonWorkVo
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.model.cache.StaticCache
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.comm.AddressStreetListActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.base.commInfoEdit.InfoEditActivity
import com.zkhy.library.base.commInfoEdit.KeyValueEntity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_bm_handle_zero_employment_submit.*


/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述: 零就业家庭认定
 *  更新:
 * <pre>
 */
class BmHandleZeroEmploymentSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""
    private var educationCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_zero_employment_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("零就业家庭认定")
//        id = intent.getStringExtra("id")
        initView()
        setListener()

        presenter = BizHandlePresenter(this)
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
        liveAddressItem.setOnClickListener(this)
        householdAddressItem.setOnClickListener(this)
        userSexItem.setOnClickListener(this)
        unemploymentCodeItem.setOnClickListener(this)
        userEducationItem.setOnClickListener(this)

        // ====
        userIDImgItem.setOnClickListener(this)
        userRegisterImgItem.setOnClickListener(this)

        noticeDetailsItem.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)

        submitBtn.setOnClickListener(this)
    }

    private val userIDImgCode: Int = 100
    private val userRegisterCode: Int = 101
    private val REQUEST_CODE_ADDRESS: Int = 201

    private val liveAddressItemCode: Int = 301
    private val householdAddressCode: Int = 302
    private val userSexItemCode: Int = 303
    private val unemploymentCodeItemCode: Int = 304
    private val userEducationItemCode: Int = 305
    private val userNationItemCode: Int = 306

    override fun onClick(v: View?) {
        when (v) {
            liveAddressItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "居住地址"
                keyValue.content = liveAddressValueTv.text.toString()
                keyValue.inputHint = "请填写居住地址"
                keyValue.maxSize = 64
                keyValue.minSize = 2
                keyValue.requestCode = liveAddressItemCode
                InfoEditActivity.start(this@BmHandleZeroEmploymentSubmitActivity, keyValue)
            }
            householdAddressItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "户籍地址"
                keyValue.content = householdAddressValueTv.text.toString()
                keyValue.inputHint = "请填写户籍地址"
                keyValue.maxSize = 64
                keyValue.minSize = 2
                keyValue.requestCode = householdAddressCode
                InfoEditActivity.start(this@BmHandleZeroEmploymentSubmitActivity, keyValue)
            }
            userSexItem -> {
                SingleChoiceDialog.Builder(this).setTitle("选择性别")
                    .addList(arrayOf("男", "女"))
                    .setOnItemClickListener { value: String, _: Int ->
                        userSexValueEt.text = StringUtil.getNotNullValue(value)
                    }.show()
            }
            unemploymentCodeItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "失业证号"
                keyValue.content = unemploymentCodeValue.text.toString()
                keyValue.inputHint = "请填写失业证号"
                keyValue.maxSize = 32
                keyValue.minSize = 0
                keyValue.requestCode = unemploymentCodeItemCode
                InfoEditActivity.start(this@BmHandleZeroEmploymentSubmitActivity, keyValue)
            }
            userEducationItem -> {
                IntentUtil.openActivity(this@BmHandleZeroEmploymentSubmitActivity, KeyValueListActivity::class.java)
                    .putStringExtra(KeyValueListActivity.TITLE, "学历选择")
                    .putSerializableExtra(KeyValueListActivity.DATA, StaticCache.getEducationList())
                    .requestCode(userEducationItemCode)
                    .start()
            }

            // ==============================================
            userIDImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleZeroEmploymentSubmitActivity, BmIDCardUploadActivity::class.java
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
                    this@BmHandleZeroEmploymentSubmitActivity, BmIDCardUploadActivity::class.java
                )
                    .putStringExtra("id", id)
                    .putStringExtra("tip", "本人户口簿")
                    .putStringExtra("bizType", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                    .putIntExtra("contentType", BmIDCardUploadActivity.RegisterBook)
                    .requestCode(userRegisterCode)
                    .start()
            }

            noticeDetailsItem -> {
                IntentUtil.openActivity(
                    this@BmHandleZeroEmploymentSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && resultCode == 200 && REQUEST_CODE_ADDRESS == requestCode) {
            val streetName = data.getStringExtra("streetName")
            val areaName = data.getStringExtra("areaName")
            areaValueTv.text = "$streetName$areaName"
            areaCode = data.getStringExtra("areaCode")
            return
        }

        imgPart(requestCode, resultCode, data)

        editPart(requestCode, resultCode, data)

        keyValueResult(requestCode, resultCode, data)
    }

    private fun keyValueResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null || resultCode != 200) {
            return
        }

        val keyValue: KValueEntity =
            data.getSerializableExtra(KeyValueListActivity.DATA) as KValueEntity? ?: return

        when (requestCode) {

            userEducationItemCode -> {
                educationCode = keyValue.key
                userEducationValueEt.text = StringUtil.getNotNullValue(keyValue.value)
            }
        }
    }

    private fun editPart(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != 200 || data == null || data.getStringExtra("value") == null) {
            return
        }

        val value: String? = data.getStringExtra("value")

        when (requestCode) {
            liveAddressItemCode -> {
                liveAddressValueTv.text = StringUtil.getNotNullValue(value)
            }

            householdAddressCode -> {
                householdAddressValueTv.text = StringUtil.getNotNullValue(value)
            }

//            userSexItemCode -> {
//                userSexValueEt.text = StringUtil.getNotNullValue(value)
//            }

            unemploymentCodeItemCode -> {
                unemploymentCodeValue.text = StringUtil.getNotNullValue(value)
            }

//            userEducationItemCode -> {
//                userEducationValueEt.text = StringUtil.getNotNullValue(value)
//            }
        }

    }

    var userIDImg = false
    var userRegister = false
    private fun imgPart(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != 200 || data == null || TextUtils.isEmpty(data.getStringExtra("id"))) {
            return
        }
        id = data.getStringExtra("id")

        when (requestCode) {
            userIDImgCode -> {
                userIDImgValueTv.text = "已上传"
                userIDImg = true
            }

            userRegisterCode -> {
                userRegisterValueTv.text = "已上传"
                userRegister = true
            }
        }
    }

    private fun submit() {
        val householdAddressStr: String = householdAddressValueTv.text.toString()
        val userSexStr: String = userSexValueEt.text.toString()
        val unemploymentCodeStr: String = unemploymentCodeValue.text.toString()

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

        // ==========================================================
        if (StringUtil.isEmpty(householdAddressStr)) {
            ToastUtil.show("请填写户籍地址")
            return
        } else {
            vo.houseRegisterDetailAddr = householdAddressStr
        }

        if (StringUtil.isEmpty(userSexStr)) {
            ToastUtil.show("请选择性别")
            return
        } else {
            when (userSexStr) {// 性别 0：女 1：男
                "女" -> vo.sex = "0"
                "男" -> vo.sex = "1"
                else -> vo.sex = "0"
            }
        }

        if (StringUtil.isEmpty(unemploymentCodeStr)) {
            ToastUtil.show("请填写失业证编号")
            return
        } else {
            vo.unemploymentNum = unemploymentCodeStr
        }

        if (StringUtil.isEmpty(educationCode)) {
            ToastUtil.show("请选择学历")
            return
        } else {
            vo.education = educationCode
        }

        // 非填写部分
        vo.id = id

        vo.flowId = 10
        vo.handleType = 1

        val userInfo: WmUser = DataCache.getUserInfo()
        vo.applyUserId = userInfo.userId
        vo.apprUserId = userInfo.userId

        vo.cityId = 851
        vo.county = 5222
        vo.provinceId = 54
        vo.town = 123

        // ===================================== 验证 =================================

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