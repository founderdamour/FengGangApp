package com.zkhy.community.view.main.dj

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.model.bean.DjThreeMeetMemDTO
import com.zkhy.community.view.main.dj.fragments.ReadListFragment
import com.zkhy.community.view.main.dj.fragments.UnreadListFragment
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
class MeetAttendInfoActivity : TitleBarBaseActivity() {

    var index = 0
    private var joinsMeetMemDTO: ArrayList<DjThreeMeetMemDTO>? = null
    private var leaveMeetMemDTO: ArrayList<DjThreeMeetMemDTO>? = null

    override fun getLayout(): Int = R.layout.activity_meet_read_status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("参会人")

        initView()
    }

    private fun initView() {
        index = intent.getIntExtra("index", 0)
        joinsMeetMemDTO = MeetingDetailsActivity.joinsMeetMemDTO
        leaveMeetMemDTO = MeetingDetailsActivity.leaveMeetMemDTO

        initTab(
            stab_tab00,
            pager0,
            Arrays.asList("已签到(${joinsMeetMemDTO!!.size}人)", "请假(${leaveMeetMemDTO!!.size}人)")
        )//
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragments = ArrayList<Fragment>()

        val readListFragment = ReadListFragment()
        val bundle = Bundle()
        bundle.putSerializable("data", joinsMeetMemDTO)
        readListFragment.arguments = bundle
        fragments.add(readListFragment)

        val unreadListFragment = UnreadListFragment()
        val unreadBundle = Bundle()
        unreadBundle.putSerializable("data", leaveMeetMemDTO)
        unreadListFragment.arguments = unreadBundle
        fragments.add(unreadListFragment)

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