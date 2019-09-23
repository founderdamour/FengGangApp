package com.zkhy.community.view.main.dj

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.view.main.dj.fragments.DjPartyCostPayHistoryFragment
import com.zkhy.community.view.main.dj.fragments.DjPartyCostUnpaidFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_party_cost_home.*

/**
 * 党费缴纳主页
 */
class PartyCostHomeActivity : TitleBarBaseActivity(), View.OnClickListener {

    private fun initTopView() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val h = 694 * width / 1080 // 与后台预定
        val layoutParams = LinearLayout.LayoutParams(width, h)
        topView.layoutParams = layoutParams
    }

    override fun getLayout(): Int = R.layout.activity_party_cost_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("党员缴费", "党费介绍") {
            IntentUtil.openActivity(this@PartyCostHomeActivity, PartyCostPayDescribeActivity::class.java).start()
        }

        initTopView()
        initBottomView()
    }

    private fun initBottomView() {
        // 子标题
        topTitle0Layout.setOnClickListener(this)
        topTitle1Layout.setOnClickListener(this)

        setTab(0)

        // 内容
        val fragments = ArrayList<Fragment>()
        fragments.add(DjPartyCostUnpaidFragment())
        fragments.add(DjPartyCostPayHistoryFragment())

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
    }

    private fun setTab(position: Int) {
        if (position == 0) {
            topTitle0Bg.setBackgroundResource(R.color.dj_theme_color)
            topTitle1Bg.setBackgroundResource(R.color.transparent)

            viewPager.currentItem = 0
        } else {
            topTitle0Bg.setBackgroundResource(R.color.transparent)
            topTitle1Bg.setBackgroundResource(R.color.dj_theme_color)

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

    fun updatePayInfo(unpaidNum: Int) {
        if (unpaidNum == 0) {
            djTipWarningIv.setImageResource(R.drawable.wwcc)
            updatePayInfoTv.text = "党费已缴清"
        } else {
            djTipWarningIv.setImageResource(R.drawable.wwc)
            updatePayInfoTv.text = "党费未缴清"
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
