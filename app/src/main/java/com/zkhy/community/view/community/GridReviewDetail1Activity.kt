package com.zkhy.community.view.community

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.zkhy.community.R
import com.zkhy.community.view.community.adapter.GridReviewDetail1Adapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_grid_review_detail_1.*

class GridReviewDetail1Activity : TitleBarBaseActivity(), AndroidView {

    private var gridReviewDetail1Adapter: GridReviewDetail1Adapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_grid_review_detail_1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("网格详情(楼栋)")
        init()
        initData()
    }

    private fun initData() {
        val list: MutableList<String> = ArrayList()
        for (i in 1..10) {
            list.add("楼栋单元$i")
        }
        gridReviewDetail1Adapter!!.setData(list)
    }

    fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        gridReviewDetail1Adapter = GridReviewDetail1Adapter(this)
        recyclerView.adapter = gridReviewDetail1Adapter

    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {
    }
}