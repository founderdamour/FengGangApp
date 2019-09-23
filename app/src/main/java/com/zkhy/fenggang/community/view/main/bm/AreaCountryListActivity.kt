package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.AreaDTO
import com.zkhy.fenggang.community.presenter.BMPresenter
import com.zkhy.fenggang.community.view.main.bm.adapters.AreaTownListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_status_view_list.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  镇列表
 *  更新:
 * <pre>
 */
class AreaCountryListActivity : StatusViewBaseActivity(), AndroidExt2View {

    private var id = ""

    private var presenter: BMPresenter? = null
    private var adapter: AreaTownListAdapter? = null


    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "社区列表")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_status_view_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            presenter!!.loadTownList(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("id")

        StatusBarUtil.transparencyBar(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = AreaTownListAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<AreaDTO> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@AreaCountryListActivity, BmHomeListActivity::class.java).start()
        })

        presenter = BMPresenter(this)
        presenter!!.loadTownList(id)
    }

    override fun loadingShow() {
        StatusView.showLoading("正在获取数据...")
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        StatusView.showError(msg)
    }

    override fun loadComplete(resultData: Any?) {

        val result = resultData as BaseData<ArrayList<AreaDTO>>

        if (result.errcode != 0) {
            showTip(result.errmsg)
        } else {
            if (result.data != null && result.data.isNotEmpty()) {
                adapter?.setData(result.data)
                StatusView.showContent()
            } else {
                StatusView.showEmptyData("暂无数据")
            }
        }
    }

    override fun load2Complete(resultData: Any?) {

    }
}
