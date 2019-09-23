package com.zkhy.community.view.community

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.AreaDTO
import com.zkhy.community.presenter.BMPresenter
import com.zkhy.community.view.comm.AddressCommunityListActivity
import com.zkhy.community.view.main.bm.adapters.AreaTownListAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.decorations.RecyclerLineView
import kotlinx.android.synthetic.main.activity_address_list_1.*

class AddressList1Activity : TitleBarBaseActivity(), AndroidExt2View {

    private var presenter: BMPresenter? = null
    private var adapter: AreaTownListAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_address_list_1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("街道办列表")
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

            IntentUtil.openActivity(this, AddressList2Activity::class.java)
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
                LoadingDialog.show(this@AddressList1Activity, "暂无数据")
            }
        }
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        LoadingDialog.show(this@AddressList1Activity, "正在获取数据...")
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}