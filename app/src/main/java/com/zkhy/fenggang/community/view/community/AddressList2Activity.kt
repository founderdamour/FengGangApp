package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.AreaDTO
import com.zkhy.fenggang.community.presenter.BMPresenter
import com.zkhy.fenggang.community.view.comm.AddressCommunityListActivity
import com.zkhy.fenggang.community.view.main.bm.adapters.AreaTownListAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_address_list_2.*

class AddressList2Activity : TitleBarBaseActivity(), AndroidExt2View {

    private var id = ""

    private var presenter: BMPresenter? = null
    private var adapter: AreaTownListAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_address_list_2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("社区列表")
        init()
        setListener()
    }

    private fun setListener() {
        adapter!!.setClickCallBack(ItemClickCallBack<AreaDTO> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }
        })
    }

    fun init() {
        id = intent.getStringExtra("id")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = AreaTownListAdapter(ArrayList())
        recyclerView.adapter = adapter

        presenter = BMPresenter(this)
        presenter!!.loadTownList("")

        adapter!!.setClickCallBack(ItemClickCallBack<AreaDTO> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this, AddressCommunityListActivity::class.java)
                .putStringExtra("id", entity.id.toString())
                .start()
        })

    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<ArrayList<AreaDTO>>

        if (result.errcode != 0) {
            showTip(result.errmsg)
        } else {
            if (result.data != null && result.data.isNotEmpty()) {
                adapter?.setData(result.data)
            } else {
                LoadingDialog.show(this@AddressList2Activity, "暂无数据")
            }
        }
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        LoadingDialog.show(this@AddressList2Activity, "正在获取数据...")
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}