package com.zkhy.fenggang.community.view.main

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.MainActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.model.cache.KeyWord
import com.zkhy.fenggang.community.view.comm.LoginActivity
import com.zkhy.fenggang.community.view.main.am.AmLawHomeActivity
import com.zkhy.fenggang.community.view.main.am.AmPoliceStationMapActivity
import com.zkhy.fenggang.community.view.main.am.ImpeachCreateActivity
import com.zkhy.fenggang.community.view.main.am.SafeWorkHomeActivity
import com.zkhy.fenggang.community.view.main.bm.*
import com.zkhy.fenggang.community.view.main.lm.LmNearbyActiveListActivity
import com.zkhy.fenggang.community.view.main.lm.LmStadiumListActivity
import com.zkhy.fenggang.community.view.main.bm.LifePayHomeActivity
import com.zkhy.fenggang.community.view.main.dj.MeetClassHomeActivity
import com.zkhy.fenggang.community.view.main.dj.PartyCostHomeActivity
import com.zkhy.fenggang.community.view.main.dj.PropagandaSysListActivity
import com.zkhy.fenggang.community.view.main.dw.DWOpenActivity
import com.zkhy.fenggang.community.view.main.km.HouseDocHomeActivity
import com.zkhy.fenggang.community.view.main.mk.MkMonitoringSystemActivity
import com.zkhy.fenggang.community.view.main.zm.LoveAllListActivity
import com.zkhy.fenggang.community.view.main.zm.WishHomeActivity
import com.zkhy.fenggang.community.view.mine.UserInfoSimpleEditActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.utils.AppUtil
import kotlinx.android.synthetic.main.activity_app_class.*

class AppClassActivity : TitleBarBaseActivity(), View.OnClickListener {

    companion object {
        const val DJ: String = "DJ"
        const val BM: String = "BM"
        const val AM: String = "AM"
        const val LM: String = "LM"
        const val KM: String = "KM"
        const val ZM: String = "ZM"
    }

    override fun getLayout(): Int = R.layout.activity_app_class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)
        setTitleBar("更多应用")

        initView()

        setListener()
    }

    private fun initView() {
        val bizType: String = intent.getStringExtra("BizType")
        when (bizType) {
            DJ -> {
                scrollView.post {
                    scrollView.scrollTo(0, djLayout.top)
                }
            }
            BM -> {
                scrollView.post {
                    scrollView.scrollTo(0, bmLayout.top)
                }
            }
            AM -> {
                scrollView.post {
                    scrollView.scrollTo(0, amLayout.top)
                }
            }
            LM -> {
                scrollView.post {
                    scrollView.scrollTo(0, lmLayout.top)
                }
            }
            KM -> {
                scrollView.post {
                    scrollView.scrollTo(0, kmLayout.top)
                }
            }
            ZM -> {
                scrollView.post {
                    scrollView.scrollTo(0, zmLayout.top)
                }
            }
            else -> {
                scrollView.post {
                    scrollView.scrollTo(0, djLayout.top)
                }
            }
        }

        if (MainActivity.getRoles().contains(KeyWord.wm_coal_supervise)) {
            am8Item.visibility = View.VISIBLE
        } else {
            am8Item.visibility = View.INVISIBLE
        }

//        if (MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
//            djLayout.visibility = View.VISIBLE
//            djLine.visibility = View.VISIBLE
//        } else {
//            djLayout.visibility = View.GONE
//            djLine.visibility = View.GONE
//        }
    }

    private fun setListener() {
        // 党建
        dj0Item.setOnClickListener(this)
        dj1Item.setOnClickListener(this)
        dj2Item.setOnClickListener(this)
        dj3Item.setOnClickListener(this)
        dj4Item.setOnClickListener(this)
        dj5Item.setOnClickListener(this)
        dj6Item.setOnClickListener(this)
        // 安民
        am0Item.setOnClickListener(this)
        am1Item.setOnClickListener(this)
        am2Item.setOnClickListener(this)
        am3Item.setOnClickListener(this)
        am4Item.setOnClickListener(this)
        am5Item.setOnClickListener(this)
        am6Item.setOnClickListener(this)
        am7Item.setOnClickListener(this)
        am8Item.setOnClickListener(this)
        // 便民
        bm0Item.setOnClickListener(this)
        bm1Item.setOnClickListener(this)
        bm2Item.setOnClickListener(this)
        bm3Item.setOnClickListener(this)
        bm4Item.setOnClickListener(this)
        bm5Item.setOnClickListener(this)
        bm6Item.setOnClickListener(this)
        bm7Item.setOnClickListener(this)
        bm8Item.setOnClickListener(this)
        bm9Item.setOnClickListener(this)
        bm10Item.setOnClickListener(this)
        bm11Item.setOnClickListener(this)
        bm12Item.setOnClickListener(this)
        // 乐民
        lm0Item.setOnClickListener(this)
        lm1Item.setOnClickListener(this)
        lm2Item.setOnClickListener(this)
        lm3Item.setOnClickListener(this)
        lm4Item.setOnClickListener(this)
        lm5Item.setOnClickListener(this)

        // 康民
        km0Item.setOnClickListener(this)
        km1Item.setOnClickListener(this)
        km2Item.setOnClickListener(this)
        km3Item.setOnClickListener(this)
        km4Item.setOnClickListener(this)
        km5Item.setOnClickListener(this)

        // 助民
        zm0Item.setOnClickListener(this)
        zm1Item.setOnClickListener(this)
        zm2Item.setOnClickListener(this)
        zm3Item.setOnClickListener(this)
        zm4Item.setOnClickListener(this)
        zm5Item.setOnClickListener(this)
        zm6Item.setOnClickListener(this)
        zm7Item.setOnClickListener(this)
        zm8Item.setOnClickListener(this)
        zm9Item.setOnClickListener(this)
        zm10Item.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            // 党建
            dj0Item -> {
                if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
                    ToastUtil.show("党员专有功能")
                    return
                }

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
                    return
                }

                IntentUtil.openActivity(this, PartyCostHomeActivity::class.java).start()
            }

            dj1Item -> {// 三会一课
                if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
                    ToastUtil.show("党员专有功能")
                    return
                }

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
                    return
                }

                IntentUtil.openActivity(this, MeetClassHomeActivity::class.java).start()
            }

            dj2Item -> {
            }
            dj3Item -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/fg-h5-webapp/feedback.html")
                    .putStringExtra("webTitle", "群众反馈")
                    .start()
            }
            dj4Item -> {
                val info = AppUtil.startOtherApp(this, "cn.xuexi.android", null)
                if (!TextUtils.isEmpty(info)) {
                    ToastUtil.show("应用未安装")
                }
            }

            dj5Item -> {
//                if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
//                    ToastUtil.show("党员专有功能")
//                    return
//                }
//
//                if (!DataCache.isAutoLogin()) {
//                    CommonDialog.Builder(this)
//                        .setTitle("登录提示")
//                        .setCanceledOnTouchOutside(false)
//                        .setMessage(resources.getString(R.string.login_tip))
//                        .setPositiveButton("登录") {
//                            // 前往登录
//                            IntentUtil.openActivity(this, LoginActivity::class.java).start()
//                        }.setNegativeButton("取消") {
//                        }.show()
//                    return
//                }

                IntentUtil.openActivity(this, PropagandaSysListActivity::class.java)
                    .start()
            }

            dj6Item -> {
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
                    return
                }

                IntentUtil.openActivity(this@AppClassActivity, DWOpenActivity::class.java)
                    .start()
            }

            // 安民
            am0Item -> { // 法律服务
//                IntentUtil.openActivity(this, AmLawHomeActivity::class.java).start()

                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(this)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(this, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(this, AmLawHomeActivity::class.java).start()
                }
            }
            am1Item -> { // 警务管理
                IntentUtil.openActivity(this, AmPoliceStationMapActivity::class.java).start()
            }
            am2Item -> { //
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/yjyl")
                    .putStringExtra("webTitle", "应急演练")
                    .start()
            }
            am3Item -> { // 智慧门禁
                val info = AppUtil.startOtherApp(this, "com.howjoy.watchfield", null)
                if (!TextUtils.isEmpty(info)) {
                    ToastUtil.show("智慧门禁应用未安装")
                }
            }

            am4Item -> { // 网格调度
//                IntentUtil.openActivity(this, WebPageActivity::class.java)
//                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/cwgl")
//                    .putStringExtra("webTitle", "宠物管理")
//                    .start()

                AppUtil.downLoadByBrowser(this, "https://47.106.221.149/test/?441")
            }

            am5Item -> { //宠物管理
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/cwgl")
                    .putStringExtra("webTitle", "宠物管理")
                    .start()
            }

            am6Item -> { // 网上信访
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
                    return
                }

                IntentUtil.openActivity(this, ImpeachCreateActivity::class.java).start()
            }

            am7Item -> { //安全生产
                IntentUtil.openActivity(this@AppClassActivity, SafeWorkHomeActivity::class.java)
                    .start()
            }

            am8Item -> { //煤矿监控
                IntentUtil.openActivity(this@AppClassActivity, MkMonitoringSystemActivity::class.java)
                    .start()
            }

            // 便民
            bm0Item -> { // 办事指南
                IntentUtil.openActivity(this, BmIndexOrgListActivity::class.java).start()
            }
            bm1Item -> { // 我要办事
                IntentUtil.openActivity(this, BmHomeListActivity::class.java).start()
//                IntentUtil.openActivity(this, TownListActivity::class.java)
//                    .putStringExtra("id", "525629318659833921").start()

                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(this)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(this, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(this, BmHomeListActivity::class.java).start()
                }
            }

            bm2Item -> { // 预约排号
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
                    return
                }
                IntentUtil.openActivity(this, BmOrderHomeActivity::class.java).start()
            }
            bm3Item -> { // 生活缴费
                IntentUtil.openActivity(this, LifePayHomeActivity::class.java).start()
            }
            bm4Item -> { // 便民查询
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
                    return
                }

                IntentUtil.openActivity(this, ConvenienceQueryHomeActivity::class.java).start()
            }

            bm5Item -> { // 社保查询 打开App
                val info = AppUtil.startOtherApp(this, "com.thbt.zunyi", null)
                if (!TextUtils.isEmpty(info)) {
                    ToastUtil.show("社保应用未安装")
                }
            }

            bm6Item -> { // 公积金服务
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "https://wsdt.zygjj.cn/")
                    .putStringExtra("webTitle", "公积金服务")
                    .start()
            }

            bm7Item -> { // 物业管理
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/wygl")
                    .putStringExtra("webTitle", "物业管理")
                    .start()
            }

            bm8Item -> { // 社区志愿者
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/zyzgl")
                    .putStringExtra("webTitle", "社区志愿者")
                    .start()
            }

            bm9Item -> { // 家政服务
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/jzfw")
                    .putStringExtra("webTitle", "家政服务")
                    .start()
            }
            bm10Item -> { // 房屋中介
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "https://zunyi.58.com/huichuanqu/chuzu/?PGTID=0d3090a7-01dc-45d5-b3f8-409aff9b2c94&ClickID=2"
                    )
                    .putStringExtra("webTitle", "房屋中介")
                    .start()
            }
            bm11Item -> { // 智慧停车
//                IntentUtil.openActivity(this, WebPageActivity::class.java)
//                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/zhtc")
//                    .putStringExtra("webTitle", "智慧停车")
//                    .start()

                IntentUtil.openActivity(this, SmartParkListActivity::class.java).start()
            }

            bm12Item -> { // 全民跑跑
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "https://www.pao360.com/paoapi/hcapph5/index.php"
                    )
                    .putStringExtra("webTitle", "全民跑跑")
                    .start()
            }

            // 乐民
            lm0Item -> { // 周边活动
                IntentUtil.openActivity(this, LmNearbyActiveListActivity::class.java).start()
            }
            lm1Item -> { // 文体场所
                IntentUtil.openActivity(this, LmStadiumListActivity::class.java).start()
            }

            lm2Item -> { // 全民阅读
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "https://wk3.bookan.com.cn/?id=20730&token=&productId=5!#/book/596")
                    .putStringExtra("webTitle", "全民阅读")
                    .start()
            }

            lm4Item -> { // 文化中心
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/whzx")
                    .putStringExtra("webTitle", "文化中心")
                    .start()
            }

            // 康民
            km0Item -> { // 预约挂号
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://yxy.zy20.com/hosreg.php?orgid=1")
                    .putStringExtra("webTitle", "预约挂号")
                    .start()
            }

            km1Item -> {
                IntentUtil.openActivity(this, HouseDocHomeActivity::class.java).start()
            }

            km2Item -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/xlzx")
                    .putStringExtra("webTitle", "心理咨询")
                    .start()
            }

            km4Item -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/tyss")
                    .putStringExtra("webTitle", "体育赛事")
                    .start()
            }

            km5Item -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "http://mp.weixin.qq.com/mp/homepage?__biz=MzU0ODg4MjA3MQ==&hid=3&sn=17601cc040a52aa85e02ac5fdfd9c327&scene=18#wechat_redirect"
                    )
                    .putStringExtra("webTitle", "医疗保险")
                    .start()
            }

            //
            zm0Item -> { // 创业就业
                IntentUtil.openActivity(this, WishHomeActivity::class.java).start()
            }
            zm1Item -> {
                IntentUtil.openActivity(this, LoveAllListActivity::class.java).start()
            }
            zm2Item -> { // 创业就业
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://www.juxianw.net/")
                    .putStringExtra("webTitle", "创业就业")
                    .start()
            }

            zm3Item -> { //医疗救助
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/yljz")
                    .putStringExtra("webTitle", "医疗救助")
                    .start()
            }

            zm5Item -> { //教育帮扶
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/jybf")
                    .putStringExtra("webTitle", "教育帮扶")
                    .start()
            }

            zm6Item -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://www.zyhc.gov.cn/zwgk/zdlygk/ggzypz/czzfbzajgc/")
                    .putStringExtra("webTitle", "公租房管理")
                    .start()
            }

            zm7Item -> { //公益活动
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/gyhd")
                    .putStringExtra("webTitle", "公益活动")
                    .start()
            }

            zm8Item -> { //养老机构
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/yljg")
                    .putStringExtra("webTitle", "养老机构")
                    .start()
            }

            zm9Item -> { // 尊品直供
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "https://wap.youcunapp.com/#/shop/custom/091b23d41a1f4805a2fd98f43acce6b5?shopNo=091b23d41a1f4805a2fd98f43acce6b5"
                    )
                    .putStringExtra("webTitle", "遵品直供")
                    .start()
            }

            zm10Item -> { // 青找
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "http://jw.wjuxiang.com/wx/index/index.html"
                    )
                    .putStringExtra("webTitle", "青找")
                    .start()
            }

        }
    }
}

