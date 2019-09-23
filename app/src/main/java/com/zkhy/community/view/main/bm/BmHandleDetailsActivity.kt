package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.AttachmentEntity
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.HandleDetailsEntity
import com.zkhy.community.model.cache.MapCache
import com.zkhy.community.model.cache.StaticCache
import com.zkhy.community.presenter.BizHandlePresenter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_bm_handle_details.*
import kotlinx.android.synthetic.main.activity_bm_handle_details_file.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 10:59
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleDetailsActivity : StatusViewBaseActivity(), AndroidExt2View {
    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener { refreshView() }
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, getTitleTxt())

    private fun getTitleTxt(): String = MapCache.getFlowName(flowId, "办理详情")

    override fun getContentLayoutId(): Int = R.layout.activity_bm_handle_details

    private var flowId = 0
    private var id = ""

    private var presenter: BizHandlePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        flowId = intent.getIntExtra("flowId", 0)
        id = intent.getStringExtra("id")

        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        presenter = BizHandlePresenter(this)

        refreshView()
    }

    private fun refreshView() {
        if (!TextUtils.isEmpty(id)) {
            StatusView.showLoading()
            presenter?.loadHandleDetails(flowId, id)
        }
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {

    }

    override fun load2Complete(resultData: Any?) {
        if (resultData != null) {
            val result: BaseData<HandleDetailsEntity> = resultData as BaseData<HandleDetailsEntity>
            if (result.errcode == 0) {
                if (result.data != null) {
                    initView(result.data)
                    StatusView.showContent()
                } else {
                    StatusView.showError(result.errmsg)
                }
            } else {
                StatusView.showError(result.errmsg)
            }
        } else {
            StatusView.showEmptyData("服务器异常")
        }
    }

    private fun initView(handleDetail: HandleDetailsEntity?) {
        if (handleDetail == null) {
            ToastUtil.show("获取用户信息失败")
            return
        }

        // 基本信息
        userNameValueEt.setText(StringUtil.getNotNullValue(handleDetail.userName, handleDetail.applyUserId))
        userIDValueTv.setText(StringUtil.getNotNullValue(handleDetail.idcard, ""))
        userPhoneValueEt.setText(StringUtil.getNotNullValue(handleDetail.phone, ""))

        areaValueTv.text = StringUtil.getNotNullValue(handleDetail.areaName, "")
        userAddressValueEt.text = StringUtil.getNotNullValue(handleDetail.addrDetail, "")

        // 审核结果 0：提交，1：通过，2、驳回
        reSubmitItem.visibility = View.GONE
        when (handleDetail.apprStatus) {
            0 -> {
                apprStatusTv.text = "待审核"
                apprSuggestValueTv.text = StringUtil.getNotNullValue(handleDetail.apprSuggest, "审核中 ···")

                apprStatusTv.setTextColor(resources.getColor(R.color.status_doing))
                apprSuggestValueTv.setTextColor(resources.getColor(R.color.status_doing))
            }

            1 -> {
                apprStatusTv.text = "已通过"
                apprSuggestValueTv.text = StringUtil.getNotNullValue(handleDetail.apprSuggest, "通过")

                apprStatusTv.setTextColor(resources.getColor(R.color.status_success))
                apprSuggestValueTv.setTextColor(resources.getColor(R.color.status_success))
            }

            2 -> {
                if (handleDetail.apprLink != 1 && handleDetail.apprLink != 2 && handleDetail.apprLink != 3) {
                    reSubmitItem.visibility = View.VISIBLE

                    apprStatusTv.text = "已拒绝"
                    apprSuggestValueTv.text = StringUtil.getNotNullValue(handleDetail.apprSuggest, "拒绝")

                    apprStatusTv.setTextColor(resources.getColor(R.color.status_error))
                    apprSuggestValueTv.setTextColor(resources.getColor(R.color.status_error))

                } else {
                    reSubmitItem.visibility = View.GONE

                    apprStatusTv.text = "待审核"
                    apprSuggestValueTv.text = StringUtil.getNotNullValue(handleDetail.apprSuggest, "审核中 ···")

                    apprStatusTv.setTextColor(resources.getColor(R.color.status_doing))
                    apprSuggestValueTv.setTextColor(resources.getColor(R.color.status_doing))
                }
            }
        }

        // 处理显示
        showView(handleDetail)
    }

    private fun showView(handleDetail: HandleDetailsEntity) {
        userIDItem.visibility = View.GONE
        propertyCertificateItem.visibility = View.GONE
        userIDOrRegisterItem.visibility = View.GONE
        singleRegisterItem.visibility = View.GONE
        birthdayCardItem.visibility = View.GONE
        vaccinationItem.visibility = View.GONE
        userPhotoItem.visibility = View.GONE
        materialItem.visibility = View.GONE
        userBCImgItem.visibility = View.GONE

        hideAllExtendView() // 隐藏所有：就业失业证办理,零就业家庭认定,就业困难对象认定

        // 申请流程类型id(1.高龄补贴 2.一孩生育登记 3.二孩生育登记 4.汇川区残疾人证发放和管理 5.居住证明
        // 6.关系证明 7.政审证明 8.文化户口登记 9.小孩入学登记 10.零就业家庭认定
        // 11.就业失业证办理 12.就业困难对象认定 )
        when (handleDetail.flowId) {
            1 -> {// 1.高龄补贴
                userIDItem.visibility = View.VISIBLE
                singleRegisterItem.visibility = View.VISIBLE
                userPhotoItem.visibility = View.VISIBLE
                userBCImgItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleAllowanceSubmitActivity::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }

                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "本人户口簿", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }

                userPhotoItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "本人照片", BizType.ADMIN_CONVENIENT_PEOPLE_PHOTO)
                }

                userBCImgItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "贵州银行卡照片", BizType.ADMIN_CONVENIENT_PEOPLE_BANK_CARD)
                }
            }
            BizType.FlowType.FLOW_2 -> {// 2.一孩生育登记
                flow02View.visibility = View.VISIBLE

                flow02File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "男方身份证", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_IDENTITY_CARD)
                }

                flow02File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "女方身份证", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_IDENTITY_CARD)
                }

                flow02File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "子女签定情况", BizType.ADMIN_CONVENIENT_PEOPLE_SIGNATURE_OF_CHILDREN)
                }

                flow02File04Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "男方户口簿", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_HOUSEHOLD_REGISTER)
                }

                flow02File05Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "女方户口簿", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_HOUSEHOLD_REGISTER)
                }

                flow02File06Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "结婚证", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                flow02File07Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "离婚协议", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                flow02File08Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "再婚前子女情况证明", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                marriageItem.visibility = View.VISIBLE

                when {
                    handleDetail.marryStatus == 2 -> { // 2：再婚，
                        flow02File06Item.visibility = View.VISIBLE
                        flow02File07Item.visibility = View.VISIBLE
                        flow02File08Item.visibility = View.VISIBLE

                        marriageValueTv.text = "再婚"
                    }
                    handleDetail.marryStatus == 3 -> { // 3：未婚
                        flow02File06Item.visibility = View.GONE
                        flow02File07Item.visibility = View.GONE
                        flow02File08Item.visibility = View.GONE

                        marriageValueTv.text = "未婚"
                    }
                    else -> {  //1：初婚，
                        flow02File06Item.visibility = View.VISIBLE
                        flow02File07Item.visibility = View.GONE
                        flow02File08Item.visibility = View.GONE

                        marriageValueTv.text = "初婚"
                    }
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleBody1Activity::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            3 -> {// 3.二孩生育登记
                flow02View.visibility = View.VISIBLE

                flow02File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "男方身份证", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_IDENTITY_CARD)
                }

                flow02File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "女方身份证", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_IDENTITY_CARD)
                }

                flow02File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "子女签定情况", BizType.ADMIN_CONVENIENT_PEOPLE_SIGNATURE_OF_CHILDREN)
                }

                flow02File04Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "男方户口簿", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_MAN_HOUSEHOLD_REGISTER)
                }

                flow02File05Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "女方户口簿", BizType.ADMIN_CONVENIENT_PEOPLE_SPOUSE_WOMEN_HOUSEHOLD_REGISTER)
                }

                flow02File06Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "结婚证", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                flow02File07Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "离婚协议", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                flow02File08Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "再婚前子女情况证明", BizType.ADMIN_CONVENIENT_PEOPLE_MARRIAGE_CERTIFICATE)
                }

                marriageItem.visibility = View.VISIBLE

                when {
                    handleDetail.marryStatus == 2 -> { // 2：再婚，
                        flow02File06Item.visibility = View.VISIBLE
                        flow02File07Item.visibility = View.VISIBLE
                        flow02File08Item.visibility = View.VISIBLE

                        marriageValueTv.text = "再婚"
                    }
                    handleDetail.marryStatus == 3 -> { // 3：未婚
                        flow02File06Item.visibility = View.GONE
                        flow02File07Item.visibility = View.GONE
                        flow02File08Item.visibility = View.GONE

                        marriageValueTv.text = "未婚"
                    }
                    else -> {  //1：初婚，
                        flow02File06Item.visibility = View.VISIBLE
                        flow02File07Item.visibility = View.GONE
                        flow02File08Item.visibility = View.GONE

                        marriageValueTv.text = "初婚"
                    }
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleBody2Activity::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            4 -> {// 4.汇川区残疾人证发放和管理
                userIDItem.visibility = View.VISIBLE
                singleRegisterItem.visibility = View.VISIBLE
                userPhotoItem.visibility = View.VISIBLE
                materialItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleDisabilityCertificateSubmitActivity::class.java
                    )
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }

                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }

                userPhotoItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "本人照片", BizType.ADMIN_CONVENIENT_PEOPLE_PHOTO)
                }

                materialItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "申请表和评定表", BizType.ADMIN_CONVENIENT_PEOPLE_APPLICATION_FORM)
                }
            }
            5 -> {// 5.居住证明
                userIDItem.visibility = View.VISIBLE
                propertyCertificateItem.visibility = View.VISIBLE

                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }
                propertyCertificateItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "房产证/购房合同", BizType.ADMIN_CONVENIENT_PEOPLE_PURCHASE_CONTRACT)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleLiveSubmitActivity::class.java)
                        .putStringExtra("id", id)
                        .start()
                }
            }
            6 -> {// 6.关系证明
                singleRegisterItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleRelationshipSubmitActivity::class.java
                    ).putStringExtra("id", id)
                        .start()
                }

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
            }
            7 -> {// 7.政审证明
                userIDOrRegisterItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandlePoliticalSubmitActivity::class.java)
                        .putStringExtra("id", id)
                        .start()
                }

                userIDOrRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证或户口本", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
            }
            8 -> {// 8.文化户口登记
                singleRegisterItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleHouseholdRegisterSubmitActivity::class.java
                    ).putStringExtra("id", id).start()
                }

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
            }
            9 -> {// 9.小孩入学登记
                singleRegisterItem.visibility = View.VISIBLE
                birthdayCardItem.visibility = View.VISIBLE
                vaccinationItem.visibility = View.VISIBLE
                propertyCertificateItem.visibility = View.VISIBLE

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleChildrenAgeSubmitActivity::class.java)
                        .putStringExtra("id", id).start()
                }

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }

                birthdayCardItem.setOnClickListener {
                    // 生育证
                    // 展示照片
                    showFileList(handleDetail, "孩子生育证", BizType.ADMIN_CONVENIENT_PEOPLE_BIRTH_CERTIFICATE)
                }

                vaccinationItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "孩子疫苗接种证", BizType.ADMIN_CONVENIENT_PEOPLE_VACCINATION_CERTIFICATE)
                }

                propertyCertificateItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "房产证/购房合同", BizType.ADMIN_CONVENIENT_PEOPLE_PURCHASE_CONTRACT)
                }
            }
            10 -> { // 零就业家庭认定
                showZeroEmploymentView()

                singleRegisterItem.visibility = View.VISIBLE
                userIDItem.visibility = View.VISIBLE

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }

                // ============================================
                liveAddressValueTv.text = StringUtil.getNotNullValue(handleDetail.oftenLiveDetailAddr, "")
                householdAddressValueTv.text = StringUtil.getNotNullValue(handleDetail.houseRegisterDetailAddr, "")
                val sexStr: String = when (handleDetail.sex) {// 性别 0：女  1：男
                    1 -> "男"
                    else -> "女"
                }
                userSexValueEt.text = sexStr
                userEducationValueEt.text =
                    StringUtil.getNotNullValue(StaticCache.findDictionary(handleDetail.education), "")

                unemploymentCodeValue.text = StringUtil.getNotNullValue(handleDetail.unemploymentNum, "")

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleZeroEmploymentSubmitActivity::class.java
                    )
                        .putStringExtra("id", id)
                        .start()
                }

            }
            11 -> { // 就业失业证办理
                showEmploymentPermitView()

                singleRegisterItem.visibility = View.VISIBLE
                userIDItem.visibility = View.VISIBLE

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }
                //
                liveAddressValueTv.text = StringUtil.getNotNullValue(handleDetail.oftenLiveDetailAddr, "")
                householdAddressValueTv.text = StringUtil.getNotNullValue(handleDetail.houseRegisterDetailAddr, "")
                userBirthdayValueTv.text = StringUtil.getNotNullValue(handleDetail.birthDay, "")

                val sexStr: String = when (handleDetail.sex) {// 性别 0：女  1：男
                    1 -> "男"
                    else -> "女"
                }
                userSexValueEt.text = sexStr

                userNationValue.text = StringUtil.getNotNullValue(StaticCache.findDictionary(handleDetail.nation), "")

                val marryStatusStr: String = when (handleDetail.employMarryStatus) {// 就业办事-婚姻状态(0-未婚 1-已婚 2-离异 3-丧偶)
                    0 -> "未婚"
                    1 -> "已婚"
                    2 -> "离异"
                    3 -> "丧偶"
                    else -> "未婚"
                }
                marriageValueTv.text = marryStatusStr

                val politicalStatusStr: String = when (handleDetail.politicalStatus) {// 政治面貌(0-群众 1-团员  2-党员)
                    1 -> "团员"
                    2 -> "党员"
                    else -> "群众"
                }
                politicalTypeValueTv.text = politicalStatusStr

                val accountCharacterStr: String = when (handleDetail.accountCharacter) {// 户口性质（0-农业户口 1-非农业户口）
                    1 -> "非农业户口"
                    else -> "农业户口"
                }
                householdClassValueTv.text = StringUtil.getNotNullValue(accountCharacterStr, "")

                universityValueTv.text = StringUtil.getNotNullValue(handleDetail.graduateSchool, "")
                graduationDateValueTv.text = StringUtil.getNotNullValue(handleDetail.graduateDate, "")

                specialtyValueTv.text = StringUtil.getNotNullValue(handleDetail.graduateMajor, "")
                userEducationValueEt.text =
                    StringUtil.getNotNullValue(StaticCache.findDictionary(handleDetail.education), "")

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleEmploymentPermitSubmitActivity::class.java
                    )
                        .putStringExtra("id", id)
                        .start()
                }
            }
            12 -> { // 就业困难对象认定
                showEmploymentDifficultyView()

                singleRegisterItem.visibility = View.VISIBLE
                userIDItem.visibility = View.VISIBLE

                singleRegisterItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口本资料", BizType.ADMIN_CONVENIENT_PEOPLE_HOUSEHOLD_REGISTER)
                }
                userIDItem.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.ADMIN_CONVENIENT_PEOPLE_IDENTITY_CARD)
                }
                //
                liveAddressValueTv.text = StringUtil.getNotNullValue(handleDetail.oftenLiveDetailAddr, "")
                userBirthdayValueTv.text = StringUtil.getNotNullValue(handleDetail.birthDay, "")

                val sexStr: String = when (handleDetail.sex) {// 性别 0：女  1：男
                    1 -> "男"
                    else -> "女"
                }
                userSexValueEt.text = sexStr

                userNationValue.text = StringUtil.getNotNullValue(StaticCache.findDictionary(handleDetail.nation), "")

                val marryStatusStr: String = when (handleDetail.marryStatus) {// 1：初婚，2：再婚，3：未婚
                    1 -> "初婚"
                    2 -> "再婚"
                    else -> "未婚"
                }
                marriageValueTv.text = marryStatusStr

                userEducationValueEt.text =
                    StringUtil.getNotNullValue(StaticCache.findDictionary(handleDetail.education), "")

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(
                        this@BmHandleDetailsActivity,
                        BmHandleEmploymentDifficultySubmitActivity::class.java
                    )
                        .putStringExtra("id", id)
                        .start()
                }
            }

            BizType.FlowType.FLOW_19 -> {
                flow19View.visibility = View.VISIBLE

                flow19File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.WU_BM_LD_RK_HY_ZM7)
                }

                flow19File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口簿", BizType.WU_BM_LD_RK_HY_ZM6)
                }

                flow19File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "本人近期照片", BizType.WU_BM_LD_RK_HY_ZM2)
                }

                flow19File04Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "生育材料", BizType.WU_BM_LD_RK_HY_ZM3)
                }

                flow19File05Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "政策外生育材料", BizType.WU_BM_LD_RK_HY_ZM4)
                }

                flow19File06Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "申请表", BizType.WU_BM_LD_RK_HY_ZM5)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, ChildLdrkhySubmit::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            BizType.FlowType.FLOW_20 -> {
                flow20View.visibility = View.VISIBLE

                flow20File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证或户口簿", BizType.WU_BM_CX_JS_ZM2)
                }

                flow20File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "计划生育情况证明", BizType.WU_BM_CX_JS_ZM3)
                }

                flow20File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "结婚证", BizType.WU_BM_CX_JS_ZM4)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, ChildCxjsSubmit::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            BizType.FlowType.FLOW_21 -> {
                flow21View.visibility = View.VISIBLE

                flow21File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.WU_BM_HJ_JS_ZM3)
                }

                flow21File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口簿", BizType.WU_BM_HJ_JS_ZM2)
                }

                flow21File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "房产证或购房合同", BizType.WU_BM_HJ_JS_ZM4)
                }

                flow21File04Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "结婚证", BizType.WU_BM_HJ_JS_ZM5)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, OtherHouseholdRegisterSubmit::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            BizType.FlowType.FLOW_22 -> {

                flow22View.visibility = View.VISIBLE

                flow22File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "申报义务人身份证或户口薄", BizType.WU_BM_SW_ZM2)
                }

                flow22File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "死者身份证或户口薄", BizType.WU_BM_SW_ZM3)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, OtherDeathSubmit::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
            BizType.FlowType.FLOW_23 -> {

                flow23View.visibility = View.VISIBLE

                flow23File01Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "身份证", BizType.WU_BM_JT_SH_KN_ZM3)
                }

                flow23File02Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "户口簿", BizType.WU_BM_JT_SH_KN_ZM2)
                }

                flow23File03Item.setOnClickListener {
                    // 展示照片
                    showFileList(handleDetail, "有关单位的困难证明", BizType.WU_BM_JT_SH_KN_ZM4)
                }

                reSubmitBtn.setOnClickListener {
                    IntentUtil.openActivity(this@BmHandleDetailsActivity, OtherPoorFamilySubmit::class.java)
                        .putStringExtra("id", id)
                        .finish(true)
                        .start()
                }
            }
        }
    }

    private fun showEmploymentDifficultyView() {
        userBirthdayItem.visibility = View.VISIBLE
        userSexItem.visibility = View.VISIBLE
        userNationItem.visibility = View.VISIBLE
        marriageItem.visibility = View.VISIBLE
        userEducationItem.visibility = View.VISIBLE
    }

    private fun showZeroEmploymentView() {
        householdAddressItem.visibility = View.VISIBLE
        userSexItem.visibility = View.VISIBLE
        userEducationItem.visibility = View.VISIBLE

        unemploymentCodeItem.visibility = View.VISIBLE
    }

    private fun showEmploymentPermitView() {
        householdAddressItem.visibility = View.VISIBLE
        userBirthdayItem.visibility = View.VISIBLE
        userSexItem.visibility = View.VISIBLE
        userNationItem.visibility = View.VISIBLE
        marriageItem.visibility = View.VISIBLE
        politicalTypeItem.visibility = View.VISIBLE
        householdClassItem.visibility = View.VISIBLE
        universityItem.visibility = View.VISIBLE
        graduationDateItem.visibility = View.VISIBLE
        specialtyItem.visibility = View.VISIBLE
        userEducationItem.visibility = View.VISIBLE
    }

    private fun hideAllExtendView() {

        liveAddressItem.visibility = View.GONE
        householdAddressItem.visibility = View.GONE
        userBirthdayItem.visibility = View.GONE
        userSexItem.visibility = View.GONE
        userNationItem.visibility = View.GONE
        marriageItem.visibility = View.GONE
        politicalTypeItem.visibility = View.GONE
        householdClassItem.visibility = View.GONE
        universityItem.visibility = View.GONE
        graduationDateItem.visibility = View.GONE
        specialtyItem.visibility = View.GONE
        userEducationItem.visibility = View.GONE

        //
        unemploymentCodeItem.visibility = View.GONE
    }

    private fun showFileList(handleDetail: HandleDetailsEntity, imgType: String, bizType: String) {

        if (handleDetail.attachmentMap == null || handleDetail.attachmentMap.isEmpty()) {
            ToastUtil.show("无可查看照片")
            return
        }

        val imgList: List<AttachmentEntity>? = handleDetail.attachmentMap[bizType]

        if (imgList != null && imgList.isNotEmpty()) {
            // 提取资料照片
            val fileArray: Array<String?> = arrayOfNulls<String>(imgList.size)
            for (index: Int in imgList.indices) {
                fileArray[index] = imgList[index].url
            }

            IntentUtil.openActivity(this@BmHandleDetailsActivity, BmHandleDetailsFilesActivity::class.java)
                .putStringExtra("title", imgType)
                .putSerializableExtra("fileList", fileArray)
                .start()
        } else {
            ToastUtil.show("无可查看照片")
        }
    }
}