package com.zkhy.community.view.main.bm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.jiangyy.easydialog.SingleChoiceDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.PersonWorkEntity
import com.zkhy.community.model.bean.PersonWorkVo
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.BizHandlePresenter
import com.zkhy.community.view.comm.AddressStreetListActivity
import com.zkhy.comm.plugin.activity.KeyValueListActivity
import com.zkhy.comm.plugin.entity.KValueEntity
import com.zkhy.community.model.cache.StaticCache
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.base.commInfoEdit.InfoEditActivity
import com.zkhy.library.base.commInfoEdit.KeyValueEntity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_bm_handle_employment_permit_submit.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述: 户口本登记
 *  更新:
 * <pre>
 */
class BmHandleEmploymentPermitSubmitActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    var areaCode = ""
    private var nationCode = ""
    private var educationCode = ""

    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_bm_handle_employment_permit_submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("就业失业证办理")
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
        userBirthdayItem.setOnClickListener(this)
        userSexItem.setOnClickListener(this)
        userNationItem.setOnClickListener(this)

        marriageItem.setOnClickListener(this)
        politicalTypeItem.setOnClickListener(this)
        householdClassItem.setOnClickListener(this)
        universityItem.setOnClickListener(this)
        graduationDateItem.setOnClickListener(this)

        specialtyItem.setOnClickListener(this)
        userEducationItem.setOnClickListener(this)

        //
        userIDImgItem.setOnClickListener(this)
        userRegisterImgItem.setOnClickListener(this)

        noticeDetailsItem.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)

        submitBtn.setOnClickListener(this)

        userAreaItem.setOnClickListener(this)
    }

    private val userIDImgCode: Int = 100
    private val userRegisterCode: Int = 101
    private val REQUEST_CODE_ADDRESS: Int = 201

    private val liveAddressItemCode: Int = 301
    private val householdAddressCode: Int = 302
    private val userBirthdayCode: Int = 303
    private val userSexItemCode: Int = 304
    private val userNationItemCode: Int = 305
    private val marriageItemCode: Int = 306

    private val politicalTypeCode: Int = 307
    private val householdClassCode: Int = 308
    private val universityCode: Int = 309
    private val graduationDateCode: Int = 310
    private val specialtyClassCode: Int = 311
    private val userEducationItemCode: Int = 312

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
                InfoEditActivity.start(this@BmHandleEmploymentPermitSubmitActivity, keyValue)
            }

            householdAddressItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "户籍地址"
                keyValue.content = householdAddressValueTv.text.toString()
                keyValue.inputHint = "请填写户籍地址"
                keyValue.maxSize = 64
                keyValue.minSize = 2
                keyValue.requestCode = householdAddressCode
                InfoEditActivity.start(this@BmHandleEmploymentPermitSubmitActivity, keyValue)
            }

            userBirthdayItem -> {
                selectDate(0)
            }

            userSexItem -> {
                SingleChoiceDialog.Builder(this).setTitle("选择性别")
                    .addList(arrayOf("男", "女"))
                    .setOnItemClickListener { value: String, _: Int ->
                        userSexValueEt.text = StringUtil.getNotNullValue(value)
                    }.show()
            }

            userNationItem -> {
                IntentUtil.openActivity(this@BmHandleEmploymentPermitSubmitActivity, KeyValueListActivity::class.java)
                    .putStringExtra(KeyValueListActivity.TITLE, "民族选择")
                    .putSerializableExtra(KeyValueListActivity.DATA, StaticCache.getNationList())
                    .requestCode(userNationItemCode)
                    .start()
            }

            marriageItem -> {
                SingleChoiceDialog.Builder(this).setTitle("选择婚姻信息")
                    .addList(arrayOf("未婚", "已婚", "离异", "丧偶"))
                    .setOnItemClickListener { value: String, _: Int ->
                        marriageValueTv.text = StringUtil.getNotNullValue(value)
                    }.show()
            }

            politicalTypeItem -> {
                SingleChoiceDialog.Builder(this).setTitle("选择政治面貌")
                    .addList(arrayOf("群众", "党员"))
                    .setOnItemClickListener { value: String, _: Int ->
                        politicalTypeValueTv.text = StringUtil.getNotNullValue(value)
                    }.show()
            }

            householdClassItem -> {
                SingleChoiceDialog.Builder(this).setTitle("选择户口性质")
                    .addList(arrayOf("农业户口", "非农业户口"))
                    .setOnItemClickListener { value: String, _: Int ->
                        householdClassValueTv.text = StringUtil.getNotNullValue(value)
                    }.show()
            }

            universityItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "毕业院校"
                keyValue.content = universityValueTv.text.toString()
                keyValue.inputHint = "请填写毕业院校"
                keyValue.maxSize = 32
                keyValue.minSize = 0
                keyValue.requestCode = universityCode
                InfoEditActivity.start(this@BmHandleEmploymentPermitSubmitActivity, keyValue)
            }

            graduationDateItem -> {
                selectDate(1)
            }

            specialtyItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "毕业专业"
                keyValue.content = specialtyValueTv.text.toString()
                keyValue.inputHint = "请填写毕业专业"
                keyValue.maxSize = 32
                keyValue.minSize = 0
                keyValue.requestCode = specialtyClassCode
                InfoEditActivity.start(this@BmHandleEmploymentPermitSubmitActivity, keyValue)
            }

            userEducationItem -> {
                IntentUtil.openActivity(this@BmHandleEmploymentPermitSubmitActivity, KeyValueListActivity::class.java)
                    .putStringExtra(KeyValueListActivity.TITLE, "学历选择")
                    .putSerializableExtra(KeyValueListActivity.DATA, StaticCache.getEducationList())
                    .requestCode(userEducationItemCode)
                    .start()
            }

            // ===============================================
            userIDImgItem -> {
                IntentUtil.openActivity(
                    this@BmHandleEmploymentPermitSubmitActivity, BmIDCardUploadActivity::class.java
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
                    this@BmHandleEmploymentPermitSubmitActivity, BmIDCardUploadActivity::class.java
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
                    this@BmHandleEmploymentPermitSubmitActivity, BmHandleAllowanceNoticeActivity::class.java
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

    private fun selectDate(type: Int) {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        //startDate.set(2013,1,1);
        val endDate = Calendar.getInstance()
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(1950, 0, 1)
        endDate.set(2050, 11, 31)

        val booleanArray: BooleanArray = booleanArrayOf(true, true, true, false, false, false)

        //时间选择器
        val timePickerView: TimePickerView = TimePickerBuilder(this@BmHandleEmploymentPermitSubmitActivity,
            OnTimeSelectListener { date, _ ->
                val sdf = SimpleDateFormat("yyyy-MM-dd")

                when (type) {
                    0 -> {
                        userBirthdayValueTv.text = sdf.format(date)
                    }
                    1 -> {
                        graduationDateValueTv.text = sdf.format(date)
                    }
                }
            })
            .setType(booleanArray)// 默认全部显示
            .setCancelText("取消")//取消按钮文字
            .setSubmitText("确定")//确认按钮文字
            .setContentTextSize(18)//滚轮文字大小
            .setTitleSize(20)//标题文字大小
            .setTitleText(
                if (type == 0) {
                    "选择出生日期"
                } else {
                    "选择毕业日期"
                }
            )//标题文字
            .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(true)//是否循环滚动
            .setTitleColor(Color.BLACK)//标题文字颜色
            .setSubmitColor(Color.BLACK)//确定按钮文字颜色
            .setCancelColor(Color.GRAY)//取消按钮文字颜色
//                    .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                    .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
            .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
            .setRangDate(startDate, endDate)//起始终止年月日设定
            .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .isDialog(false) //是否显示为对话框样式
            .build()

        timePickerView.show()
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
            userNationItemCode -> {
                nationCode = keyValue.key
                userNationValue.text = StringUtil.getNotNullValue(keyValue.value)
            }

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

            userSexItemCode -> {
                userSexValueEt.text = StringUtil.getNotNullValue(value)
            }

            userBirthdayCode -> {
                userBirthdayValueTv.text = StringUtil.getNotNullValue(value)
            }

            marriageItemCode -> {
                marriageValueTv.text = StringUtil.getNotNullValue(value)
            }

            politicalTypeCode -> {
                politicalTypeValueTv.text = StringUtil.getNotNullValue(value)
            }

            householdClassCode -> {
                householdClassValueTv.text = StringUtil.getNotNullValue(value)
            }

            universityCode -> {
                universityValueTv.text = StringUtil.getNotNullValue(value)
            }

            graduationDateCode -> {
                graduationDateValueTv.text = StringUtil.getNotNullValue(value)
            }

            specialtyClassCode -> {
                specialtyValueTv.text = StringUtil.getNotNullValue(value)
            }
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
        // =========================================
        val householdAddressStr: String = householdAddressValueTv.text.toString()
        val userBirthdayStr: String = userBirthdayValueTv.text.toString()
        val userSexStr: String = userSexValueEt.text.toString()

        val marriageStr: String = marriageValueTv.text.toString()
        val politicalTypeStr: String = politicalTypeValueTv.text.toString()
        val householdClassStr: String = householdClassValueTv.text.toString()
        val universityClassStr: String = universityValueTv.text.toString()
        val graduationDateStr: String = graduationDateValueTv.text.toString()

        val specialtyStr: String = specialtyValueTv.text.toString()

        if (StringUtil.isEmpty(householdAddressStr)) {
            ToastUtil.show("请填写户籍地址")
            return
        } else {
            vo.houseRegisterDetailAddr = householdAddressStr
        }

        if (StringUtil.isEmpty(userBirthdayStr)) {
            ToastUtil.show("请填写出生日期")
            return
        } else {
            vo.birthDay = userBirthdayStr
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

        if (StringUtil.isEmpty(nationCode)) {
            ToastUtil.show("请填写民族信息")
            return
        } else {
            vo.nation = nationCode
        }

        if (StringUtil.isEmpty(marriageStr)) {
            ToastUtil.show("请填写婚姻状态")
            return
        } else {
            when (marriageStr) {// 就业办事-婚姻状态(0-未婚 1-已婚 2-离异 3-丧偶)
                "未婚" -> vo.employMarryStatus = 0
                "已婚" -> vo.employMarryStatus = 1
                "离异" -> vo.employMarryStatus = 2
                "丧偶" -> vo.employMarryStatus = 3
                else -> vo.employMarryStatus = 0
            }
        }

        if (StringUtil.isEmpty(politicalTypeStr)) {
            ToastUtil.show("请填写政治面貌")
            return
        } else {
            when (politicalTypeStr) {// 政治面貌(0-群众 1-团员 2-党员)
                "群众" -> vo.politicalStatus = 0
                "团员" -> vo.politicalStatus = 1
                "党员" -> vo.politicalStatus = 2
                else -> vo.politicalStatus = 0
            }
        }

        if (StringUtil.isEmpty(householdClassStr)) {
            ToastUtil.show("请填写户口性质")
            return
        } else {
            when (householdClassStr) {// 户口性质（0-农业户口 1-非农业户口）
                "农业户口" -> vo.accountCharacter = 0
                "非农业户口" -> vo.accountCharacter = 1
                else -> vo.accountCharacter = 0
            }
        }

        vo.graduateSchool = StringUtil.getNotNullValue(universityClassStr)
        if (StringUtil.isNotEmpty(graduationDateStr)) {
            vo.graduateDate = "$graduationDateStr 00:00:00"
        }
        vo.graduateMajor = StringUtil.getNotNullValue(specialtyStr)

        if (StringUtil.isEmpty(educationCode)) {
            ToastUtil.show("请填写学历信息")
            return
        } else {
            vo.education = educationCode // 学历
        }

        // 非填写部分
        vo.id = id
        vo.flowId = 11

        val userInfo: WmUser = DataCache.getUserInfo()
        vo.applyUserId = userInfo.userId
        vo.apprUserId = userInfo.userId

        vo.cityId = 851
        vo.county = 5222
        vo.provinceId = 54
        vo.town = 123

        // ===================================== 验证 ================================
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