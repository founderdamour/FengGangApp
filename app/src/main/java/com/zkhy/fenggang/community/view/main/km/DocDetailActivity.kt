package com.zkhy.fenggang.community.view.main.km

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.HomeDocInfoEntity
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.base.activity.StatusViewTitleBarActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_am_lawyer_detail.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/7/29 on 16:40
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class DocDetailActivity : StatusViewTitleBarActivity(), AndroidExt2View {

    var id = ""
    private var presenter: AMPresenter? = null

    override fun loadData() {
        presenter!!.loadLawyerDetail(id)
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "职务详细")

    override fun getContentLayoutId(): Int = R.layout.activity_am_lawyer_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id")
        presenter = AMPresenter(this)
        presenter!!.loadDocDetail(id)

        phoneItem.setOnClickListener {
            val phone: String = userPhoneTv.text as String
            if (StringUtil.isEmpty(phone)) {
                ToastUtil.show("电话号码为空不能拨号")
                return@setOnClickListener
            }

            callPhone(this@DocDetailActivity, phone)
        }
    }

    private fun callPhone(activity: Activity, phoneNum: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtil.show("拨号功能不可用")
        }

    }

    override fun loadingDismiss() {
    }

    override fun loadComplete(resultData: Any?) {
        if (resultData == null) {
            StatusView.showError("获取数据失败")
            return
        }

        val lawyerInfo: HomeDocInfoEntity = resultData as HomeDocInfoEntity

        userNameTv.text = StringUtil.getNotNullValue(lawyerInfo.name)
        userPhoneTv.text = StringUtil.getNotNullValue(lawyerInfo.wmUserSaveReqDTO.phone)
        workOrgTv.text = StringUtil.getNotNullValue(lawyerInfo.wmUserSaveReqDTO.orgName)
        workSkillTv.text = StringUtil.getNotNullValue(lawyerInfo.skill)

        ImageLoader.show(this, lawyerInfo.wmUserSaveReqDTO.photo, R.drawable.mr_tx, avatarIv)

        StatusView.showContent()
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        StatusView.showLoading("加载中...")
    }

    override fun showTip(msg: String?) {

    }
}