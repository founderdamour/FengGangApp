package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.DjThreeMeetMemDTO
import com.zkhy.fenggang.community.view.main.dj.fragments.ReadListFragment
import com.zkhy.fenggang.community.view.main.dj.fragments.UnreadListFragment
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
class MeetReadStatusActivity : TitleBarBaseActivity() {

    var index = 0
    private var receivedList: ArrayList<DjThreeMeetMemDTO>? = null
    private var unreceivedList: ArrayList<DjThreeMeetMemDTO>? = null

    override fun getLayout(): Int = R.layout.activity_meet_read_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("接收人")

        initView()
    }

    private fun initView() {
        index = intent.getIntExtra("index", 0)
        receivedList = MeetingDetailsActivity.receivedList
        unreceivedList = MeetingDetailsActivity.unreceivedList

        initTab(stab_tab00, pager0, Arrays.asList("未读 (${unreceivedList!!.size}人)", "已读 (${receivedList!!.size}人)"))//
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()

        val unreadListFragment = UnreadListFragment()
        val unreadBundle = Bundle()
        unreadBundle.putSerializable("data", unreceivedList)
        unreadListFragment.arguments = unreadBundle
        fragments.add(unreadListFragment)

        val readListFragment = ReadListFragment()
        val bundle = Bundle()
        bundle.putSerializable("data", receivedList)
        readListFragment.arguments = bundle
        fragments.add(readListFragment)

        val fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(arg0: Int): Fragment {
                return fragments[arg0]
            }
        }

        pager.adapter = fragmentPagerAdapter

        tab.setTitles(titles, index)
            .setViewPager(pager)
            .setOnTabListener { index, _ -> pager.setCurrentItem(index, true) }
    }
}