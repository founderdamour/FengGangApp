package com.zkhy.community.view.main.dw

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.view.main.dw.fragment.DWOpenTabFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_zw_open.*
import java.util.*

/**
 *
 * 党务公开界面
 *
 */
class DWOpenActivity : TitleBarBaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_zw_open
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("党务公开")
        initView()
    }

    private fun initView() {
        initTab(stabTab, viewPager, listOf("班子分工", "重点工作", "年度计划总结", "党风廉政", "党员发展", "党费缴纳", "评先评优"))
    }

    private fun initTab(stabTab: ScrollTab, viewPager: ViewPager, titles: List<String>) {
        val fragments = ArrayList<Fragment>()
        // %BZFG=班子分工 %ZDGZ=重点工作 %NDJHZJ=年度计划总结 %DFLZ=党风廉政 %DYFZ=党员发展 %DFJN=党费缴纳 %PYPX=评优评先
        fragments.add(DWOpenTabFragment.newInstance("BZFG"))
        fragments.add(DWOpenTabFragment.newInstance("ZDGZ"))
        fragments.add(DWOpenTabFragment.newInstance("NDJHZJ"))
        fragments.add(DWOpenTabFragment.newInstance("DFLZ"))
        fragments.add(DWOpenTabFragment.newInstance("DYFZ"))
        fragments.add(DWOpenTabFragment.newInstance("DFJN"))
        fragments.add(DWOpenTabFragment.newInstance("PYPX"))

        val fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(arg0: Int): Fragment {
                return fragments[arg0]
            }
        }

        viewPager.adapter = fragmentPagerAdapter

        stabTab.setTitles(titles)
        stabTab.setViewPager(viewPager)
        stabTab.setOnTabListener { index, _ ->
            viewPager.setCurrentItem(index, true)
        }

        leftBtn.visibility = View.INVISIBLE
        rightBtn.visibility = View.VISIBLE

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(index: Int) {
                when (index) {
                    0 -> {
                        leftBtn.visibility = View.INVISIBLE
                        rightBtn.visibility = View.VISIBLE
                    }
                    fragments.size - 1 -> {
                        leftBtn.visibility = View.VISIBLE
                        rightBtn.visibility = View.INVISIBLE
                    }
                    else -> {
                        leftBtn.visibility = View.VISIBLE
                        rightBtn.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}