package com.zkhy.fenggang.community.view.main.bm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.cache.MapCache
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_bm_handle_progress_result.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/29 on 9:46
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleProgressResultActivity : TitleBarBaseActivity() {

    private var applyStatus = 0
    private var apprLink = 0

    private var flowId = 0
    private var id = ""
    override fun getLayout(): Int = R.layout.activity_bm_handle_progress_result

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyStatus = intent.getIntExtra("applyStatus", 0)
        apprLink = intent.getIntExtra("apprLink", 0)
        flowId = intent.getIntExtra("flowId", 0)
        id = intent.getStringExtra("id")

        val flowName = MapCache.getFlowName(flowId, "此次办事")

        when (applyStatus) {
            1 -> {
                setTitleBar("已通过")

                progressCheckingIv.visibility = View.GONE
                progressCheckOkIv.visibility = View.VISIBLE
                progressCheckErrorIv.visibility = View.GONE
                scrollView.setBackgroundResource(R.color.white)

                showApplyItem.visibility = View.GONE

                resultTypeTv.text = "您好，《$flowName》申请已通过"
                resultTipTv.text = "您可以在“办理进度”中查看办理进程及结果。"
            }
            2 -> {
                if (apprLink != 1 && apprLink != 2 && apprLink != 3) {
                    setTitleBar("未通过")
                    scrollView.setBackgroundResource(R.color.app_bg)

                    progressCheckingIv.visibility = View.GONE
                    progressCheckOkIv.visibility = View.GONE
                    progressCheckErrorIv.visibility = View.VISIBLE

                    showApplyItem.visibility = View.VISIBLE
                    showApplyItem.setOnClickListener {
                        reSubmit()
                    }

                    resultTypeTv.text = "您好，您申请的《$flowName》未通过。"
                    resultTipTv.text = "您的信息有误、请完善后再申请 ..."
                } else {
                    setTitleBar("未审核")

                    progressCheckingIv.visibility = View.VISIBLE
                    progressCheckOkIv.visibility = View.GONE
                    progressCheckErrorIv.visibility = View.GONE

                    scrollView.setBackgroundResource(R.color.white)
                    showApplyItem.visibility = View.GONE

                    resultTypeTv.text = "您的申请已受理，正在进行信息核验，请耐心等待！"
                    resultTipTv.text = "您可以在“办理进度”中查看办理进程及结果。"
                }
            }
            else -> {
                setTitleBar("未审核")

                progressCheckingIv.visibility = View.VISIBLE
                progressCheckOkIv.visibility = View.GONE
                progressCheckErrorIv.visibility = View.GONE

                scrollView.setBackgroundResource(R.color.white)
                showApplyItem.visibility = View.GONE

                resultTypeTv.text = "您的申请已受理，正在进行信息核验，请耐心等待！"
                resultTipTv.text = "您可以在“办理进度”中查看办理进程及结果。"
            }
        }


    }

    private fun reSubmit() {
        // 处理办理事项：1.高龄补贴 2.一孩生育登记 3.二孩生育登记 4.汇川区残疾人证发放和管理 5.居住证明 6.关系证明 7.政审证明 8.文化户口登记 9.小孩入学登记
        when (flowId) {
            1 -> {// 1.高龄补贴
//                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleAllowanceActivity::class.java)
//                    .putStringExtra("id", id)
//                    .start()
                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleAllowanceSubmitActivity::class.java)
                    .putStringExtra("id", id)
                    .finish(true)
                    .start()
            }
            2 -> {// 2.一孩生育登记
                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleBody1Activity::class.java)
                    .start()
            }
            3 -> {// 3.二孩生育登记
                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleBody2Activity::class.java)
                    .start()
            }
            4 -> {// 4.汇川区残疾人证发放和管理
//                IntentUtil.openActivity(
//                    this@BmHandleProgressResultActivity, BmHandleDisabilityCertificateHomeActivity::class.java
//                )
//                    .putStringExtra("id", id)
//                    .start()
                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleDisabilityCertificateSubmitActivity::class.java)
                    .putStringExtra("id", id)
                    .finish(true)
                    .start()
            }
            5 -> {// 5.居住证明
                IntentUtil.openActivity(this@BmHandleProgressResultActivity, BmHandleLiveSubmitActivity::class.java)
                    .putStringExtra("id", id)
                    .start()
            }
            6 -> {// 6.关系证明
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleRelationshipSubmitActivity::class.java
                ).putStringExtra("id", id)
                    .start()
            }
            7 -> {// 7.政审证明
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandlePoliticalSubmitActivity::class.java
                )
                    .putStringExtra("id", id)
                    .start()
            }
            8 -> {// 8.文化户口登记
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleHouseholdRegisterSubmitActivity::class.java
                ).putStringExtra("id", id).start()
            }
            9 -> {// 9.小孩入学登记
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleChildrenAgeSubmitActivity::class.java
                )
                    .putStringExtra("id", id).start()
            }
            10 -> { // 零就业家庭认定
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleZeroEmploymentSubmitActivity::class.java
                )
                    .putStringExtra("id", id)
                    .start()
            }
            11 -> { // 就业失业证办理
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleEmploymentPermitSubmitActivity::class.java
                )
                    .putStringExtra("id", id)
                    .start()
            }
            12 -> { // 就业困难对象认定
                IntentUtil.openActivity(
                    this@BmHandleProgressResultActivity,
                    BmHandleEmploymentDifficultySubmitActivity::class.java
                )
                    .putStringExtra("id", id)
                    .start()
            }
        }

        finish()
    }
}