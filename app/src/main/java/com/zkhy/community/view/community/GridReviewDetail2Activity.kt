package com.zkhy.community.view.community

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.zkhy.community.R
import com.zkhy.community.view.community.adapter.GridReviewAdapter
import com.zkhy.community.view.community.adapter.GridReviewDetail2Adapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_grid_review.*

class GridReviewDetail2Activity : TitleBarBaseActivity(), AndroidView {

    private var gridReviewDetail2Adapter: GridReviewDetail2Adapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_grid_review_detail_2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("网格详情(单元)")
        init()
        initData()
    }

    private fun initData() {
        val list: MutableList<String> = ArrayList()
        for (i in 1..10) {
            list.add("10$i")
        }
        gridReviewDetail2Adapter!!.setData(list)
    }

    fun init() {
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        gridReviewDetail2Adapter = GridReviewDetail2Adapter(this)
        recyclerView.adapter = gridReviewDetail2Adapter

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