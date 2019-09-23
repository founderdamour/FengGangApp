package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.view.community.adapter.GridReviewAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_grid_review.*

class GridReviewActivity : TitleBarBaseActivity(), AndroidView {

    private var gridReviewAdapter: GridReviewAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_grid_review
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("网格查看")
        init()
        initData()
    }

    private fun initData() {
        val list:MutableList<String> = ArrayList()
        for (i in 1 .. 10){
            list.add("表格$i")
        }
        gridReviewAdapter!!.setData(list)
    }

    fun init() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        gridReviewAdapter = GridReviewAdapter(this)
        recyclerView.adapter = gridReviewAdapter


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