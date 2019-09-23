package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.community.view.main.bm.m3.*
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_biz_handle_list.*

class BmHomeListActivity : TitleBarBaseActivity(), View.OnClickListener {

    override fun getLayout(): Int = R.layout.activity_biz_handle_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("我要办事", "办事记录") {
            if (!DataCache.isAutoLogin()) {

                CommonDialog.Builder(this)
                    .setTitle("登录提示")
                    .setCanceledOnTouchOutside(false)
                    .setMessage(resources.getString(R.string.login_tip))
                    .setPositiveButton("登录") {
                        // 前往登录
                        IntentUtil.openActivity(this, LoginActivity::class.java).start()
                    }.setNegativeButton("取消") {
                    }.show()

                return@setTitleBar
            }

            IntentUtil.openActivity(this, BmHandleHistoryListActivity::class.java)
                .start()
        }

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)
        item2Layout.setOnClickListener(this)
        //
        item3Layout.setOnClickListener(this)
        item4Layout.setOnClickListener(this)
        item5Layout.setOnClickListener(this)
        item6Layout.setOnClickListener(this)
        item7Layout.setOnClickListener(this)
        item8Layout.setOnClickListener(this)
        //
        item9Layout.setOnClickListener(this)
        item10Layout.setOnClickListener(this)
        item11Layout.setOnClickListener(this)

        // 新增六项
        item13Layout.setOnClickListener(this)
        item14Layout.setOnClickListener(this)
        item15Layout.setOnClickListener(this)
        item16Layout.setOnClickListener(this)
        item17Layout.setOnClickListener(this)
        item18Layout.setOnClickListener(this)

        // 计划生育
        item19052401Layout.setOnClickListener(this)
        item19052402Layout.setOnClickListener(this)

        // 其他
        item19052403Layout.setOnClickListener(this)
        item19052404Layout.setOnClickListener(this)
        item19052405Layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (!DataCache.isAutoLogin()) {// 是否登录
            CommonDialog.Builder(this)
                .setTitle("登录提示")
                .setCanceledOnTouchOutside(false)
                .setMessage(resources.getString(R.string.login_tip))
                .setPositiveButton("登录") {
                    // 前往登录
                    IntentUtil.openActivity(this, LoginActivity::class.java).start()
                }.setNegativeButton("取消") {
                }.show()
            return
        }

        when (v) {
            item0Layout -> {
                // 一孩
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleBody1Activity::class.java)
                    .putStringExtra("id", "")
                    .start()
            }
            item1Layout -> {
                // 二孩
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleBody2Activity::class.java)
                    .putStringExtra("id", "")
                    .start()
            }
            item2Layout -> {
                // 高龄补贴
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleAllowanceSubmitActivity::class.java)
                    .putStringExtra("id", "")
                    .start()
            }
            item3Layout -> {
                // 居住证明办理
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleLiveSubmitActivity::class.java)
                    .putStringExtra("id", "").start()
            }

            item4Layout -> {
                // 政审证明办理
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandlePoliticalSubmitActivity::class.java)
                    .putStringExtra("id", "").start()
            }

            item5Layout -> {
                // 户口本登记
                IntentUtil.openActivity(
                    this@BmHomeListActivity,
                    BmHandleHouseholdRegisterSubmitActivity::class.java
                )
                    .putStringExtra("id", "").start()
            }

            item6Layout -> {
                // 关系证明办理
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleRelationshipSubmitActivity::class.java)
                    .putStringExtra("id", "").start()
            }

            item7Layout -> {
                // 适龄、超龄儿童入学证明办理
                IntentUtil.openActivity(this@BmHomeListActivity, BmHandleChildrenAgeSubmitActivity::class.java)
                    .putStringExtra("id", "")
                    .start()
            }

            item8Layout -> {
                // 汇川区残疾人证发放和管理
                IntentUtil.openActivity(
                    this@BmHomeListActivity,
                    BmHandleDisabilityCertificateSubmitActivity::class.java
                )
                    .putStringExtra("id", "")
                    .start()
            }

            item9Layout -> {
                // 就业失业证办理
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleEmploymentPermitSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }
            item10Layout -> {
                // 零就业家庭认定
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleZeroEmploymentSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }
            item11Layout -> {
                // 就业困难对象认定
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleEmploymentDifficultySubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item13Layout -> {  // 再生育(三孩)子女审批
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleThreeChildSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item14Layout -> {  // 特扶对象扶助
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleSpecialObjHelpSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item15Layout -> {  // 医疗救助
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleMedicalAssistanceSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item16Layout -> {  // 临时救助
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleTempAssistanceSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item17Layout -> {  // 特困人员供养
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleDifficultySupportSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item18Layout -> {  // 城乡低保办理
                IntentUtil.openActivity(
                    this@BmHomeListActivity, BmHandleCitySafeguardSubmitActivity::class.java
                ).putStringExtra("id", "").start()
            }

            item19052401Layout -> { //  流动人口婚育证明
                IntentUtil.openActivity(
                    this@BmHomeListActivity, ChildLdrkhySubmit::class.java
                ).putStringExtra("id", "").start()
            }
            item19052402Layout -> {//  诚信计生证明
                IntentUtil.openActivity(
                    this@BmHomeListActivity, ChildCxjsSubmit::class.java
                ).putStringExtra("id", "").start()

            }
            item19052403Layout -> {//  户籍接收证明
                IntentUtil.openActivity(
                    this@BmHomeListActivity, OtherHouseholdRegisterSubmit::class.java
                ).putStringExtra("id", "").start()
            }
            item19052404Layout -> {//  死亡证明
                IntentUtil.openActivity(
                    this@BmHomeListActivity, OtherDeathSubmit::class.java
                ).putStringExtra("id", "").start()
            }
            item19052405Layout -> {//  家庭生活困难证明
                IntentUtil.openActivity(
                    this@BmHomeListActivity, OtherPoorFamilySubmit::class.java
                ).putStringExtra("id", "").start()
            }
        }
    }
}
