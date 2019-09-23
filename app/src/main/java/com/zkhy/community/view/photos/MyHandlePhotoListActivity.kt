package com.zkhy.community.view.photos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.view.main.dj.fragments.MeetClass4UnreadListFragment
import com.zkhy.community.view.main.dj.fragments.MeetClassAllListFragment
import com.zkhy.community.view.photos.fragment.MyHandleShotPhotoAllListFragment
import com.zkhy.community.view.photos.fragment.MyHandleShotPhotoDoingListFragment
import com.zkhy.community.view.photos.fragment.MyHandleShotPhotoEndListFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_meet_read_status.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * <pre>
 *  创建:  梁玉涛 2019/2/28 on 16:51
 *  项目:  AMorAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class MyHandlePhotoListActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_meet_read_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("随手拍处理")

        initView()
    }

    private fun initView() {
        initTab(stab_tab00, pager0, listOf("全部", "待处理", "已处理"))
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()
        fragments.add(MyHandleShotPhotoAllListFragment())
        fragments.add(MyHandleShotPhotoDoingListFragment())
        fragments.add(MyHandleShotPhotoEndListFragment())

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