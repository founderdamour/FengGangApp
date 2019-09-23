package com.zkhy.fenggang.community.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.map.amap.location.MapLocationHelper
import com.zkhy.fenggang.community.MainActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.comm.base.MainBaseFragment
import com.zkhy.fenggang.community.model.bean.LatLngEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.model.cache.KeyWord
import com.zkhy.fenggang.community.view.comm.LoginActivity
import com.zkhy.fenggang.community.view.main.AppClassActivity
import com.zkhy.fenggang.community.view.comm.NoticeMsgListActivity
import com.zkhy.fenggang.community.view.main.am.AmLawHomeActivity
import com.zkhy.fenggang.community.view.main.am.AmPoliceStationMapActivity
import com.zkhy.fenggang.community.view.main.bm.*
import com.zkhy.fenggang.community.view.main.dj.PartyCostHomeActivity
import com.zkhy.fenggang.community.view.main.dj.MeetClassHomeActivity
import com.zkhy.fenggang.community.view.main.dj.PropagandaSysListActivity
import com.zkhy.fenggang.community.view.main.km.DoctorOrderListActivity
import com.zkhy.fenggang.community.view.main.km.HouseDocHomeActivity
import com.zkhy.fenggang.community.view.main.lm.LmNearbyActiveListActivity
import com.zkhy.fenggang.community.view.main.lm.LmStadiumListActivity
import com.zkhy.fenggang.community.view.main.ts.ComplaintCreateActivity
import com.zkhy.fenggang.community.view.main.zm.LoveAllListActivity
import com.zkhy.fenggang.community.view.main.zm.WishHomeActivity
import com.zkhy.fenggang.community.view.mine.UserInfoSimpleEditActivity
import com.zkhy.library.utils.AppUtil
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_content.*

class HomeFragment : MainBaseFragment(), View.OnClickListener {

    companion object {
        var instance: HomeFragment? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instance = this

        initView()
        setListener()
    }

    private fun initView() {

        MapLocationHelper.with(context!!).location { locEntity ->
            val district = locEntity.district
            cityTv.text = StringUtil.getNotNullValue(district, "省内")

            DataCache.saveLatLng(LatLngEntity(locEntity.latitude, locEntity.longitude))
        }


//        val noticeList = ArrayList<String>()
//        noticeList.add("动态丨曾征带队调研全区安全生产工作")
//        noticeList.add("动态丨汇川区安全生产工作紧急调度会召开等2则")
//        noticeList.add("时讯丨区委理论学习中心组第三次集中学习（扩大）会议暨宗教工作专题讲座举行")
//
//        noticeList.add("简讯丨张红兵带队检查中心城区小型经营性场所消防安全工作等2则")
//        noticeList.add("关注丨4.5万亩李花缤纷成海…走嘛！到山盆看最美的春天")
//
//        noticeTxtView.startWithList(noticeList)
//        noticeTxtView.setOnItemClickListener { position, _ ->
//
////            var title = ""
////            var url = ""
////
////            when (position) {
////                0 -> {
////                    title = "动态"
////                    url = "https://mp.weixin.qq.com/s/n9vzEhFjhR-4y-P9YIzjLQ"
////                }
////                1 -> {
////                    title = "动态"
////                    url = "https://mp.weixin.qq.com/s/YhF7WHM6HGdTl-kN7oxpbw"
////                }
////                2 -> {
////                    title = "时讯"
////                    url = "https://mp.weixin.qq.com/s/qwIehcZgaEIIkRmu5sxNAA"
////                }
////                3 -> {
////                    title = "简讯"
////                    url = "https://mp.weixin.qq.com/s/v74lJdRMbROGX3Qf0ip_2w"
////                }
////                4 -> {
////                    title = "关注"
////                    url = "https://mp.weixin.qq.com/s/L1Wl-1DLSuBJo_4x61eHUg"
////                }
////            }
////
////            IntentUtil.openActivity(activity, WebPageActivity::class.java)
////                    .putStringExtra("webUrl", url)
////                    .putStringExtra("webTitle", title)
////                    .start()
//
//            if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
//                ToastUtil.show("党员专有功能")
//                return@setOnItemClickListener
//            }
//
//            if (!DataCache.isAutoLogin()) {
//                CommonDialog.Builder(activity)
//                    .setTitle("登录提示")
//                    .setCanceledOnTouchOutside(false)
//                    .setMessage(resources.getString(R.string.login_tip))
//                    .setPositiveButton("登录") {
//                        // 前往登录
//                        IntentUtil.openActivity(activity, LoginActivity::class.java).start()
//                    }.setNegativeButton("取消") {
//                    }.show()
//                return@setOnItemClickListener
//            }
//
////                IntentUtil.openActivity(activity, WebPageActivity::class.java)
////                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/part")
////                    .putStringExtra("webTitle", "宣传平台")
////                    .start()
//            IntentUtil.openActivity(activity, PropagandaSysListActivity::class.java)
//                .start()
//        }

    }

    override fun initNoticeTxtView(noticeList: ArrayList<String>) {
//        val noticeList = ArrayList<String>()
//
//        noticeList.add("动态丨曾征带队调研全区安全生产工作")
//        noticeList.add("动态丨汇川区安全生产工作紧急调度会召开等2则")
//        noticeList.add("时讯丨区委理论学习中心组第三次集中学习（扩大）会议暨宗教工作专题讲座举行")
//
//        noticeList.add("简讯丨张红兵带队检查中心城区小型经营性场所消防安全工作等2则")
//        noticeList.add("关注丨4.5万亩李花缤纷成海…走嘛！到山盆看最美的春天")

        noticeTxtView.startWithList(noticeList)
        noticeTxtView.setOnItemClickListener { _, _ ->
            IntentUtil.openActivity(activity, PropagandaSysListActivity::class.java)
                .start()
        }
    }

    private fun setListener() {
        // 顶部
        localView.setOnClickListener(this)
        searchItem.setOnClickListener(this)
        noticeView.setOnClickListener(this)
        // 顶部Item
        banShiZhiNanItem.setOnClickListener(this)
        woYaoBanShiItem.setOnClickListener(this)
        banLiJinDuItem.setOnClickListener(this)
        ziXunTouSuItem.setOnClickListener(this)

        // 通知
//        noticeItem.setOnClickListener(this)

        // 党建
        dj0Item.setOnClickListener(this)
        dj1Item.setOnClickListener(this)
        dj2Item.setOnClickListener(this)
        djMoreItem.setOnClickListener(this)

        // 安民
        am01Item.setOnClickListener(this)
        am02Item.setOnClickListener(this)
        am03Item.setOnClickListener(this)
        amMoreItem.setOnClickListener(this)

        // 便民
        bm01Item.setOnClickListener(this)
        bm02Item.setOnClickListener(this)
        bm03Item.setOnClickListener(this)
        bmMoreItem.setOnClickListener(this)

        // 乐民
        lm01Item.setOnClickListener(this)
        lm02Item.setOnClickListener(this)
        lm03Item.setOnClickListener(this)
        lmMoreItem.setOnClickListener(this)

        // 康民
        km0Item.setOnClickListener(this)
        km1Item.setOnClickListener(this)
        km2Item.setOnClickListener(this)
        kmMoreItem.setOnClickListener(this)
        // 助民
        zm01Item.setOnClickListener(this)
        zm02Item.setOnClickListener(this)
        zm03Item.setOnClickListener(this)
        zmMoreItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            localView -> {
            }
            searchItem -> {
            }
            noticeView -> {
                showNoticeTip(false)
                IntentUtil.openActivity(activity, NoticeMsgListActivity::class.java).start()
            }
            // ==========================================
            banShiZhiNanItem -> {
                IntentUtil.openActivity(activity, BmIndexOrgListActivity::class.java).start()
            }

            woYaoBanShiItem -> {
//                IntentUtil.openActivity(activity, BmHandleHomeListActivity::class.java).start()
//                IntentUtil.openActivity(activity, TownListActivity::class.java)
//                    .putStringExtra("id", "525629318659833921").start()

                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(activity)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(activity, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(activity, BmHomeListActivity::class.java).start()
                }

            }

            banLiJinDuItem -> {
                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }

                IntentUtil.openActivity(activity, BmHandleProgressActivity::class.java).start()
            }

            ziXunTouSuItem -> {
//                IntentUtil.openActivity(activity, BmComplainHomeActivity::class.java).start()
//
//                IntentUtil.openActivity(activity, WebPageActivity::class.java)
//                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/tsjy")
//                    .putStringExtra("webTitle", "咨询投诉")
//                    .start()

                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }

                IntentUtil.openActivity(activity, ComplaintCreateActivity::class.java).start()
            }

            // ===========================================
            noticeItem -> {
//                ToastUtil.show("5")
            }

            // 党建
            dj0Item -> {
                if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
                    ToastUtil.show("党员专有功能")
                    return
                }

                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }

                IntentUtil.openActivity(activity, PartyCostHomeActivity::class.java).start()
            }

            dj1Item -> {// 三会一课
                if (!MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
                    ToastUtil.show("党员专有功能")
                    return
                }

                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }

                IntentUtil.openActivity(activity, MeetClassHomeActivity::class.java).start()
            }

            dj2Item -> {// 宣传平台
                IntentUtil.openActivity(activity, PropagandaSysListActivity::class.java)
                    .start()
            }

            djMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.DJ)
                    .start()
            }

            // ======= 便民
            bm01Item -> {
                IntentUtil.openActivity(activity, BmIndexOrgListActivity::class.java).start()
            }
            bm02Item -> {
//                IntentUtil.openActivity(activity, TownListActivity::class.java)
//                    .putStringExtra("id", "525629318659833921").start()

                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(activity)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(activity, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(activity, BmHomeListActivity::class.java).start()
                }
            }
            bm03Item -> {
                IntentUtil.openActivity(activity, ConvenienceQueryHomeActivity::class.java).start()
//                IntentUtil.openActivity(activity, BmOrderHomeActivity::class.java).start()
            }

            bmMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.BM)
                    .start()
            }
            // 安民
            am01Item -> {
                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(activity)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(activity, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(activity, AmLawHomeActivity::class.java).start()
                }
            }
            am02Item -> {
                IntentUtil.openActivity(activity, AmPoliceStationMapActivity::class.java).start()
            }
            am03Item -> {
//                IntentUtil.openActivity(activity, WebPageActivity::class.java)
//                    .putStringExtra("webUrl", "http://58.16.181.23:7070/site/index.html#/yjyl")
//                    .putStringExtra("webTitle", "应急演练")
//                    .start()

                val info = AppUtil.startOtherApp(activity, "com.howjoy.watchfield", null)
                if (!TextUtils.isEmpty(info)) {
                    ToastUtil.show("智慧门禁应用未安装")
                }
            }
            amMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.AM)
                    .start()
            }

            // ======乐民
            lm01Item -> {
                IntentUtil.openActivity(activity, LmNearbyActiveListActivity::class.java).start()
            }

            lm02Item -> {
                IntentUtil.openActivity(activity, LmStadiumListActivity::class.java).start()
            }

            lm03Item -> {
                IntentUtil.openActivity(activity, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "https://wk3.bookan.com.cn/?id=20730&token=&productId=5!#/book/596"
                    )//http://cbm_zunyihuichuan.3eol.com.cn/Mobile
                    .putStringExtra("webTitle", "全民阅读")
                    .start()
            }

            lmMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.LM)
                    .start()
            }
            // == 康民
            km0Item -> {
//                IntentUtil.openActivity(activity, WebPageActivity::class.java)
////                    .putStringExtra("webUrl", "http://yxy.zy20.com/hosreg.php?orgid=1")
////                    .putStringExtra("webTitle", "预约挂号")
////                    .start()
                IntentUtil.openActivity(activity, DoctorOrderListActivity::class.java).start()
            }

            km1Item -> {
                IntentUtil.openActivity(activity, HouseDocHomeActivity::class.java).start()
            }
            km2Item -> {
                IntentUtil.openActivity(activity, WebPageActivity::class.java)
                    .putStringExtra(
                        "webUrl",
                        "http://mp.weixin.qq.com/mp/homepage?__biz=MzU0ODg4MjA3MQ==&hid=3&sn=17601cc040a52aa85e02ac5fdfd9c327&scene=18#wechat_redirect"
                    )
                    .putStringExtra("webTitle", "医疗保险")
                    .start()
            }

            kmMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.KM)
                    .start()
            }

            // 助民
            zm01Item -> {

                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }

                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
                if (needTip) {
                    CommonDialog.Builder(activity)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(activity, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(activity, WishHomeActivity::class.java).start()
                }
            }

            zm02Item -> { // 爱心筹
                IntentUtil.openActivity(activity, LoveAllListActivity::class.java).start()
            }

            zm03Item -> {
                IntentUtil.openActivity(activity, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://www.juxianw.net/")
                    .putStringExtra("webTitle", "创业就业")
                    .start()
            }

            zmMoreItem -> {
                IntentUtil.openActivity(activity, AppClassActivity::class.java)
                    .putStringExtra("BizType", AppClassActivity.ZM)
                    .start()
            }
            // ============ TEMP
        }
    }

    fun showNoticeTip(isShow: Boolean) {
        if (isShow) {
            itemUnreadTip.visibility = View.VISIBLE
        } else {
            itemUnreadTip.visibility = View.GONE
        }
    }
}
