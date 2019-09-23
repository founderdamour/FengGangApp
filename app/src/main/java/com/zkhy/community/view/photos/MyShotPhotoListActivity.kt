package com.zkhy.community.view.photos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.view.photos.fragment.MyShotPhotoAllListFragment
import com.zkhy.community.view.photos.fragment.MyShotPhoto4DoingListFragment
import com.zkhy.community.view.photos.fragment.MyShotPhoto4EndListFragment
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
class MyShotPhotoListActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_meet_read_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("我的随手拍")

        initView()
    }

    private fun initView() {
        initTab(stab_tab00, pager0, Arrays.asList("全部", "处理中", "已处理"))
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()
        fragments.add(MyShotPhotoAllListFragment())
        fragments.add(MyShotPhoto4DoingListFragment())
        fragments.add(MyShotPhoto4EndListFragment())

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