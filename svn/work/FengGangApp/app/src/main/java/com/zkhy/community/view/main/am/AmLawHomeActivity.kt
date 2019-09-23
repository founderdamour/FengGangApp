package com.zkhy.community.view.main.am

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.widget.bannerView.style1.ext.BannerBean
import com.sinothk.widget.bannerView.style1.ext.BannerUtil
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.BizUserEntity
import com.zkhy.community.model.bean.LawActivityBean
import com.zkhy.community.model.bean.LawCountEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.AMPresenter
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.community.widget.LawyerListItemView
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_am_law_home.*
import java.lang.Exception
import java.util.*

/**
 * 法律服务
 */
open class AmLawHomeActivity : TitleBarBaseActivity(), AndroidExt2View, View.OnClickListener {

    private var presenter: AMPresenter? = null
    private var lawActivityList: List<LawActivityBean>? = null

    override fun getLayout(): Int = R.layout.activity_am_law_home

    /**
     * 显示BannerView
     */
    private fun showBanner(bannerData: MutableList<BannerBean>?) {

        bannerView.setOnBannerListener { position ->

            if (lawActivityList != null && lawActivityList!!.isNotEmpty() && StringUtil.isNotEmpty(lawActivityList!![position].id)) {

                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawPopularizingDetailsActivity::class.java)
                    .putStringExtra("id", lawActivityList!![position].id)
                    .start()
            }
        }

        BannerUtil.show(this, bannerView, bannerData, BannerUtil.CENTER)
    }

    private fun getBannerData(): MutableList<BannerBean>? {
        val bannerBeanList = ArrayList<BannerBean>()
        val banner2 = BannerBean(R.drawable.banner_flfw)
        bannerBeanList.add(banner2)
        return bannerBeanList
    }

    private fun initBannerView() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val h = 572 * width / 1080 // 与后台预定
        val layoutParams = LinearLayout.LayoutParams(width, h)
        bannerView.layoutParams = layoutParams
//        showBanner(getBannerData())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("法律服务")
        presenter = AMPresenter(this)

        initBannerView()

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)
        item2Layout.setOnClickListener(this)

        refreshView()
    }

    private fun refreshView() {
        val page = PageReq<String>()
        page.pageSize = 3
        page.pageNo = 1
        presenter?.loadLawActivityList(page)
        presenter?.getLawCount()// 获得统计
        presenter?.getOnlineLawyer(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            item0Layout -> {
                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawQAListActivity::class.java).start()
            }

            item1Layout -> {
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

                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawConsultHistoryActivity::class.java).start()
            }

            item2Layout -> {
//                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawFirmMapActivity::class.java).start()
                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawFirmListActivity::class.java).start()
            }
        }
    }

//    private fun callPhone(i: Int) {
//        var num = ""
//        when (i) {
//            1 -> num = "18798021858"
//            2 -> num = "13017410920"
//            3 -> num = "13511860116"
//        }
//
//        try {
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$num"))
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ToastUtil.show("拨号功能不可用")
//        }
//    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any) {
        val result: BaseData<PageData<LawActivityBean>> = resultData as BaseData<PageData<LawActivityBean>>
        if (result.data != null && result.data != null && result.data.list.size > 0) {

            // 实时网络数据
            lawActivityList = result.data.list

            val netDataList = ArrayList<BannerBean>()

            for (pos: LawActivityBean in result.data.list) {
                if (StringUtil.isNotEmpty(pos.imgAddr)) {
                    netDataList.add(BannerBean(pos.imgAddr, ""))
                }
            }
            showBanner(netDataList)
            DataCache.setLawActivityList(netDataList)

        } else {
            // 没有网络数据，尝试加载缓存
            val bannerData: ArrayList<BannerBean>? = DataCache.getLawActivityList()
            if (bannerData != null && bannerData.size > 0) {
                showBanner(bannerData)
            } else {
                // 没有缓存显示默认图片
                val defaultDataList = ArrayList<BannerBean>()
                defaultDataList.add(BannerBean(R.drawable.banner01))
                defaultDataList.add(BannerBean(R.drawable.banner02))
                showBanner(defaultDataList)
            }
        }
    }

    override fun load2Complete(resultData: Any?) {
        if (resultData != null) {
            val resultInfo: BaseData<LawCountEntity>? = resultData as BaseData<LawCountEntity>
            QATipTv.text = "总共有${resultInfo?.data?.ansNum}个咨询问答"
            myQATipTv.text = "我咨询了${resultInfo?.data?.consultNum}个问题"
            lawFirmTipTv.text = "周边现有${resultInfo?.data?.orgNum}个法律服务机构"
        } else {
            QATipTv.text = "详细介绍咨询的问题及解答"
            myQATipTv.text = "您的所有咨询问题"
            lawFirmTipTv.text = "周边法律服务机构"
        }
    }

    fun showOnlineLawyer(resultData: BaseData<ArrayList<BizUserEntity>>?) {
        if (resultData == null) {
            return
        }

        if (resultData.data == null || resultData.data.size == 0) {
            lawyerListItemView.visibility = View.GONE

            return
        } else {
            lawyerListItemView.visibility = View.VISIBLE

            val subList: List<BizUserEntity>?
            if (resultData.data.size > 3) {
                subList = resultData.data.subList(0, 3)
            } else {
                subList = resultData.data
            }

            lawyerListItemView.setData(this, subList)
            lawyerListItemView.setOnItemClickListener { id ->
                IntentUtil.openActivity(this@AmLawHomeActivity, AmLawyerDetailActivity::class.java)
                    .putStringExtra("id",id)
                    .start()
            }
        }
    }
}
