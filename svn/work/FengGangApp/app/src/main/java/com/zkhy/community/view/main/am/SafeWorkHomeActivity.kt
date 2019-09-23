package com.zkhy.community.view.main.am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.webkit.WebView
import com.sinothk.switchTabView.style1.ScrollTab
import com.zkhy.community.R
import com.zkhy.community.view.main.am.fragment.SafeWorkListFragment
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_safe_work_home.*
import java.lang.Exception
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
class SafeWorkHomeActivity : TitleBarBaseActivity() {
    var currPageNo = 0
    val fragments = ArrayList<SafeWorkListFragment>()

    override fun getLayout(): Int = R.layout.activity_safe_work_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("安全生产")

        initView()
    }

    private fun initView() {
        initTab(stab_tab00, pager0, listOf("六级六覆盖", "工作动态", "案例+常识", "法律法规"))
    }

    private fun initTab(tab: ScrollTab, pager: ViewPager, titles: List<String>) {

        val fragment1: SafeWorkListFragment =
            SafeWorkListFragment().getInstance("http://58.16.181.23:7070/site/index.html#/ljlfg")
        val fragment2: SafeWorkListFragment =
            SafeWorkListFragment().getInstance("http://58.16.181.23:7070/site/index.html#/gzdt")
        val fragment3: SafeWorkListFragment =
            SafeWorkListFragment().getInstance("http://58.16.181.23:7070/site/index.html#/alcs")
        val fragment4: SafeWorkListFragment =
            SafeWorkListFragment().getInstance("http://58.16.181.23:7070/site/index.html#/flfg")

        fragments.add(fragment1)
        fragments.add(fragment2)
        fragments.add(fragment3)
        fragments.add(fragment4)

        val fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(arg0: Int): Fragment {
                return fragments[arg0]
            }
        }

        pager.offscreenPageLimit = titles.size - 1
        pager.adapter = fragmentPagerAdapter

        tab.setTitles(titles)
        // tab.setNumber(1, "9", View.VISIBLE)// 设置数字红点
        tab.setViewPager(pager)
        tab.setOnTabListener { index, _ ->
            pager.setCurrentItem(index, true)
            currPageNo = index
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        try {
            val webView: WebView? = fragments[currPageNo].getLocWebView()
            if (keyCode == KEYCODE_BACK && webView != null && webView.canGoBack()) {
                webView.goBack()
                return true
            }
        } catch (e: Exception) {
        }

        return super.onKeyDown(keyCode, event)
    }
}