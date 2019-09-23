package com.zkhy.community.view.main.ts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.view.main.ts.fragments.ComplaintHistoryList4DoingFragment
import com.zkhy.community.view.main.ts.fragments.ComplaintHistoryList4DoneFragment
import com.zkhy.community.view.main.ts.fragments.ComplaintHistoryList4AllFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_meet_read_status.*
import kotlin.collections.ArrayList

class ComplaintHistoryListActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_complaint_history_list_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("我的咨询投诉")

        initView()
    }

    private fun initView() {
        initTab(stab_tab00, pager0, listOf("全部", "待处理", "已处理"))
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()
        fragments.add(ComplaintHistoryList4AllFragment())
        fragments.add(ComplaintHistoryList4DoingFragment())
        fragments.add(ComplaintHistoryList4DoneFragment())

        val fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(arg0: Int): Fragment {
                return fragments[arg0]
            }
        }

//        pager.offscreenPageLimit = titles.size - 1
        pager.adapter = fragmentPagerAdapter

        tab.setTitles(titles)
        // tab.setNumber(1, "9", View.VISIBLE)// 设置数字红点
        tab.setViewPager(pager)
        tab.setOnTabListener { index, _ -> pager.setCurrentItem(index, true) }
    }
}
