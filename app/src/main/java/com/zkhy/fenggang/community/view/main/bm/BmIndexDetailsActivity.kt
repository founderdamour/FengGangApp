package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.FlowInfoEntity
import com.zkhy.fenggang.community.presenter.BizHandlePresenter
import com.zkhy.fenggang.community.view.main.bm.fragments.BmIndexDataFragment
import com.zkhy.fenggang.community.view.main.bm.fragments.BmIndexOrgFragment
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_biz_index_details.*
import com.sinothk.comm.utils.IntentUtil
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.library.mvp.AndroidExt2View


class BmIndexDetailsActivity : StatusViewBaseActivity(), View.OnClickListener, AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var itemId: String? = ""
    var itemTitle: String? = ""

    private var presenter: BizHandlePresenter? = null

    private fun getIntentData() {
        itemId = intent.getStringExtra("itemId")
        itemTitle = intent.getStringExtra("itemTitle")
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener { }
    }

    override fun getTitleBarView(): View =
        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(itemTitle, "办事指南"))

    override fun getContentLayoutId(): Int = R.layout.activity_biz_index_details

    override fun onCreate(savedInstanceState: Bundle?) {
        getIntentData()
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        presenter = BizHandlePresenter(this)
        presenter!!.loadHandleInfo(itemId)
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {

        val result = resultData as BaseData<FlowInfoEntity>
        if (result.errcode == 0) {
            if (result.data == null) {
                StatusView.showEmptyData("暂无数据")
            } else {
                initView(result.data)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun initView(data: FlowInfoEntity) {

        // 子标题
        topTitle0Layout.setOnClickListener(this)
        topTitle1Layout.setOnClickListener(this)
        setTab(0)

        // 内容
        val fragments = ArrayList<Fragment>()
        fragments.add(BmIndexOrgFragment.newInstance(data))
        fragments.add(BmIndexDataFragment.newInstance(data))

        val fragmentAdapter = FragmentAdapter(supportFragmentManager, fragments)
        viewPager.adapter = fragmentAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                setTab(position)
            }
        })

        if (data.isNetHandle && StringUtil.isNotEmpty(data.netHandleUrl)) {
            handleBtn.visibility = View.VISIBLE

            handleBtn.setOnClickListener {
//                val uri = Uri.parse(data.netHandleUrl)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)

                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", data.netHandleUrl)
                    .putStringExtra("webTitle", "申请办事")
                    .start()
            }

        } else {
            handleBtn.visibility = View.GONE
        }
    }

    private fun setTab(position: Int) {
        if (position == 0) {
            topTitle0Tv.setTextColor(resources.getColor(R.color.colorAccent))
            topTitle0Bg.setBackgroundResource(R.color.colorAccent)

            topTitle1Tv.setTextColor(resources.getColor(R.color.tc_list_title))
            topTitle1Bg.setBackgroundResource(R.color.white)

            viewPager.currentItem = 0
        } else {
            topTitle0Tv.setTextColor(resources.getColor(R.color.tc_list_title))
            topTitle0Bg.setBackgroundResource(R.color.white)

            topTitle1Tv.setTextColor(resources.getColor(R.color.colorAccent))
            topTitle1Bg.setBackgroundResource(R.color.colorAccent)

            viewPager.currentItem = 1
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            topTitle0Layout -> {
                setTab(0)
            }
            topTitle1Layout -> {
                setTab(1)
            }
        }
    }

    private inner class FragmentAdapter(fm: FragmentManager?, fList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

        var fragments: ArrayList<Fragment> = fList

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}
