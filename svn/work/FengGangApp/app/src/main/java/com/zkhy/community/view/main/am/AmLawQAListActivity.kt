package com.zkhy.community.view.main.am

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.DictionaryEntity
import com.zkhy.community.model.bean.LawQuestionEntity
import com.zkhy.community.presenter.AMPresenter
import com.zkhy.community.view.main.am.adapter.AMLawQAListAdapter
import com.zkhy.community.widget.LawClassListItemPopupWindow
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_law_q_a_list.*

class AmLawQAListActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    override fun load2Complete(resultData: Any?) {
    }

    private var searchViewShow = false
    var question = ""
    var quesClass = ""
    var questType = ""

    private var presenter: AMPresenter? = null
    private var adapter: AMLawQAListAdapter? = null

    override fun getLayout(): Int = R.layout.activity_law_q_a_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("问题解答库", R.drawable.sx) {
            if (!searchViewShow) {
                searchViewShow = true
                searchView.visibility = View.VISIBLE
            } else {
                searchViewShow = false
                searchView.visibility = View.GONE
            }
        }

        StatusBarUtil.transparencyBar(this)

        initRecycleLinearView(loadingRecyclerView)

        adapter = AMLawQAListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        loadingRecyclerView.emptyView = noDataView

        presenter = AMPresenter(this)

        // ================================
        refreshData()

        initSearch()
    }

    private fun initSearch() {
        searchBtn.setOnClickListener {

            val questionInput: String = searchValue.text.toString()

            if (StringUtil.isNotEmpty(questionInput) && !StringUtilExt.isHanNumLetterChar(questionInput)) {
                ToastUtil.show("关键字不能包含特殊字符")
                return@setOnClickListener
            }

            question = questionInput
            refreshData()
        }

        class1View.setOnClickListener {
            val mWindow = LawClassListItemPopupWindow(this@AmLawQAListActivity, "wumin_stable_problem")

            mWindow.setItemClickCallBack { _, obj ->

                mWindow.dismiss()

                val entity: DictionaryEntity = obj as DictionaryEntity

                if (entity.code == DictionaryEntity.ALL_CODE) {
                    quesClass = ""
                    quesClassTv.text = "问题分类"
                } else {
                    quesClass = entity.code
                    quesClassTv.text = entity.name
                }
            }

            mWindow.showAsDropDown(class1View)
        }

        class2View.setOnClickListener {

            if (StringUtil.isEmpty(quesClass)) {
                ToastUtil.show("请先选择问题分类")
                return@setOnClickListener
            }

            val mWindow = LawClassListItemPopupWindow(this@AmLawQAListActivity, quesClass)
            mWindow.setItemClickCallBack { _, obj ->

                mWindow.dismiss()

                val entity: DictionaryEntity = obj as DictionaryEntity
                if (entity.code == DictionaryEntity.ALL_CODE) {
                    questType = ""
                    questTypeTv.text = "问题名称"
                } else {
                    questType = entity.code
                    questTypeTv.text = entity.name
                }
            }

            mWindow.showAsDropDown(class1View)
        }
    }

    override fun loadData(pageNo: Int) {
        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 10

        val comm = CommReq()
        comm.question = question // 搜索内容
        comm.quesClass = quesClass
        comm.questType = questType
        pageParam.data = comm
        presenter!!.loadLawQAList(pageParam)
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
            noDataView.text = msg
        } else {
            ToastUtil.show(msg)
        }
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<LawQuestionEntity>> = resultData as BaseData<PageData<LawQuestionEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                noDataView.text = "暂无数据"
                adapter?.setData(ArrayList())
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<LawQuestionEntity>
                for (index in listData.indices) {
                    listData[index].isSelected = false
                }

                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(listData)
                } else {
                    adapter?.addData(listData)
                }

                // 设置：通用
                pageNo = pageData.nextPage
                if (pageData.isHasNextPage) {
                    loadingRecyclerView.setLoadingMoreEnabled(true)
                } else {
                    loadingRecyclerView.setLoadingMoreEnabled(false)
                }
            }
        } else {
            showTip(result.errmsg)
        }
    }
}
