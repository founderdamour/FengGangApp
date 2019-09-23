package com.zkhy.fenggang.community.comm.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.widget.bannerView.style1.ext.BannerBean
import com.sinothk.widget.bannerView.style1.ext.BannerUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.comm.WebPageActivity
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.BannerEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.CommPresenter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.UnitUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/9 on 13:31
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
abstract class MainBaseFragment : Fragment(), View.OnClickListener, AndroidExt2View {

    var presenter: CommPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wm = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val h = 588 * width / 1080
        val layoutParams = LinearLayout.LayoutParams(width, h)
        bannerView.layoutParams = layoutParams

        presenter = CommPresenter(this)
        initBanner()

//        showBanner(getBannerData())

        // 滚动控制
        scrollView.setOnScrollListener { y ->
            val i = UnitUtil.dip2px(activity!!, y)
            val dp = UnitUtil.px2dp(activity!!, i)

            if (dp > 100) {
                topView.setBackgroundResource(R.color.colorAccent)
            } else {
                val scale = y.toFloat() / bannerView.height
                val alpha = (255 * scale).toInt()
                topView.setBackgroundColor(Color.argb(alpha, 62, 122, 220))
            }
        }
    }

    private fun initBanner() {
        val pageReq = PageReq<String>()
        pageReq.pageNo = 1
        pageReq.pageSize = 10
        presenter?.loadHomeBannerInfo(pageReq)
    }

    /**
     * 显示BannerView
     */
    private fun showBanner(bannerData: MutableList<BannerBean>?) {
        bannerView.setOnBannerListener { position ->
            if (bannerData != null && StringUtil.isNotEmpty(bannerData[position].openUrl)) {
                val intent = Intent(activity, WebPageActivity::class.java)
                intent.putExtra("webUrl", bannerData[position].openUrl)
                intent.putExtra("webTitle", "信息详情")
                activity!!.startActivity(intent)
            }
        }

        BannerUtil.show(activity, bannerView, bannerData, BannerUtil.CENTER)
    }

    override fun loadingDismiss() {
    }

    override fun loadComplete(resultData: Any?) {
    }

    override fun loadingShow() {
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun load2Complete(resultData: Any?) {

        if (resultData == null) {
            // 没有缓存显示默认图片
            val defaultDataList = ArrayList<BannerBean>()
            defaultDataList.add(BannerBean(R.drawable.banner01))
            defaultDataList.add(BannerBean(R.drawable.banner02))
            showBanner(defaultDataList)
            return
        }

        val result = resultData as BaseData<PageData<BannerEntity>>
        if (result.data != null && result.data != null && result.data.list.size > 0) {

            // 实时网络数据
            val netDataList = ArrayList<BannerBean>()
            for (pos: BannerEntity in result.data.list) {
                if (StringUtil.isNotEmpty(pos.url)) {
                    netDataList.add(BannerBean(pos.url, pos.openUrl))
                }
            }
            showBanner(netDataList)
            DataCache.setHomeBannerInfo(netDataList)

        } else {
            // 没有网络数据，尝试加载缓存
            val bannerData: ArrayList<BannerBean>? = DataCache.getHomeBannerInfo()
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
}