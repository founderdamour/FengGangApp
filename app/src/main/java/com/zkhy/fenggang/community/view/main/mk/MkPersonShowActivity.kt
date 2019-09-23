package com.zkhy.fenggang.community.view.main.mk

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.MkPersonShowEntity
import com.zkhy.fenggang.community.presenter.MkPresenter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

/**
 * 人员定位系统详情
 */
class MkPersonShowActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var mkPersonShowAdapter: MkPersonShowAdapter? = null
    private var mkPresenter: MkPresenter? = null
    private var kbh: String = ""

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        kbh = intent.getStringExtra("kbh")

        setTitleBar("井下人员大屏")

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        mkPersonShowAdapter = MkPersonShowAdapter(this, ArrayList())
        loadingRecyclerView.adapter = mkPersonShowAdapter

        mkPresenter = MkPresenter(this)


        refreshData()
    }

    override fun loadData(pageNo: Int) {
        mkPresenter!!.loadMkPerson(kbh)
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.visibility = View.VISIBLE
            loadingTxtTip.text = "正在加载..."
        }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.visibility = View.VISIBLE
            loadingTxtTip.text = msg
        }
    }

    override fun loadComplete(resultData: Any?) {
        stopLoading(loadingRecyclerView, loadType)

        val result: ArrayList<MkPersonShowEntity> = resultData as ArrayList<MkPersonShowEntity>

        if (result.size > 0) {
            loadingRecyclerView.visibility = View.VISIBLE
            loadingTxtTip.visibility = View.GONE
            val deptList = ArrayList<String>()
            for (entity: MkPersonShowEntity in result) {
                deptList.add(entity.dept)
            }
            val newDeptList = deduplication(deptList)

            var data = ArrayList<MkPersonShowEntity>()

            for (dept: String in newDeptList) {
                val mkPersonShowEntity = MkPersonShowEntity()
                mkPersonShowEntity.dept = dept
                for (entity: MkPersonShowEntity in result) {
                    if (dept == entity.dept) {
                        if (TextUtils.isEmpty(mkPersonShowEntity.name)) {
                            mkPersonShowEntity.name = entity.name + ","
                        } else {
                            mkPersonShowEntity.name += entity.name + ","
                        }
                    }
                }
                data.add(mkPersonShowEntity)
            }

            mkPersonShowAdapter!!.data = data
        } else {
            showTip("暂无数据")
        }

    }

    fun deduplication(list: ArrayList<String>): ArrayList<String> {
        val set = HashSet<String>()
        val newList = ArrayList<String>()
        val iter = list.iterator()
        while (iter.hasNext()) {
            val element = iter.next()
            if (set.add(element))
                newList.add(element)
        }
        list.clear()
        list.addAll(newList)
        return newList
    }

}