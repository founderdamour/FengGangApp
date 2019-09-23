package com.zkhy.fenggang.community.view.main.am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.view.main.am.fragment.ImpeachList4AllFragment
import com.zkhy.fenggang.community.view.main.am.fragment.ImpeachList4DoingFragment
import com.zkhy.fenggang.community.view.main.am.fragment.ImpeachList4DoneFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_meet_read_status.*
import java.util.*
import kotlin.collections.ArrayList

class ImpeachHistoryListActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_meet_read_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("我的信访")

        initView()
    }

    private fun initView() {
        initTab(stab_tab00, pager0, Arrays.asList("全部", "待处理", "已处理"))
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()
        fragments.add(ImpeachList4AllFragment())
        fragments.add(ImpeachList4DoingFragment())
        fragments.add(ImpeachList4DoneFragment())

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
