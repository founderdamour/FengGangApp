package com.zkhy.fenggang.community.view.main.km

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.DictionaryEntity
import com.zkhy.fenggang.community.model.bean.HouseDocQAEntity
import com.zkhy.fenggang.community.presenter.KMPresenter
import com.zkhy.fenggang.community.view.main.km.adpaters.KMHouseDocQAListAdapter
import com.zkhy.fenggang.community.widget.LawClassListItemPopupWindow
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_law_q_a_list.*

class HouseDocQAListActivity : LoadingRecyclerViewBaseActivity(), AndroidExt2View {

    override fun load2Complete(resultData: Any?) {
    }

    private var searchViewShow = false
    var question = ""
    var quesClass = ""
    var questType = ""

    private var presenter: KMPresenter? = null
    private var adapter: KMHouseDocQAListAdapter? = null

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

        adapter = KMHouseDocQAListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        loadingRecyclerView.emptyView = noDataView

        presenter = KMPresenter(this)

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
            val mWindow = LawClassListItemPopupWindow(this@HouseDocQAListActivity, "wumin_health")

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

            val mWindow = LawClassListItemPopupWindow(this@HouseDocQAListActivity, quesClass)
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
        comm.question = question
        comm.quesClass = quesClass
        comm.questType = questType
        pageParam.data = comm
        presenter!!.loadHouseDocQAList(pageParam)
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

        val result: BaseData<PageData<HouseDocQAEntity>> = resultData as BaseData<PageData<HouseDocQAEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                noDataView.text = "暂无数据"
                adapter?.setData(ArrayList())
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<HouseDocQAEntity>
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


//    override fun load2Complete(resultData: Any?) {
//    }
//
//    private var pageNo: Int = 0
//
//    private var presenter: KMPresenter? = null
//    private var adapter: KMHouseDocQAListAdapter? = null
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "问题解答库")
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.activity_law_q_a_list
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        StatusBarUtil.transparencyBar(this)
//
//        initRecycleLinearView(loadingRecyclerView)
//
//        adapter = KMHouseDocQAListAdapter(ArrayList())
//        loadingRecyclerView.adapter = adapter
//
//        presenter = KMPresenter(this)
//
//        // ================================
//        refreshData()
//    }
//
//    private fun loadData(pageNo: Int) {
//        val pageParam = PageReq<CommReq>()
//        pageParam.pageNo = pageNo
//        pageParam.pageSize = 10
//
//        val comm = CommReq()
//        comm.question = ""
//        comm.quesClass = ""
//        comm.questType = ""
//        pageParam.data = comm
//        presenter!!.loadHouseDocQAList(pageParam)
//    }
//
//    override fun refreshData() {
//        loadType = LoadType.REFRESH
//        loadData(1)
//    }
//
//    override fun loadMoreData() {
//        loadType = LoadType.LOAD_MORE
//        loadData(pageNo)
//    }
//
//    override fun loadingShow() {
//        if (loadType == LoadType.REFRESH) {
//            StatusView.showLoading("正在获取数据...")
//        }
//    }
//
//    override fun loadingDismiss() {
//    }
//
//    override fun showTip(msg: String?) {
//        // 设置：通用
//        stopLoading(loadingRecyclerView, loadType)
//        if (loadType == LoadType.REFRESH) {
//            StatusView.showError(msg)
//        } else {
//            ToastUtil.show(msg)
//        }
//    }
//
//    override fun loadComplete(resultData: Any?) {
//        // 设置：通用
//        stopLoading(loadingRecyclerView, loadType)
//
//        val result: BaseData<PageData<HouseDocQAEntity>> = resultData as BaseData<PageData<HouseDocQAEntity>>
//
//        if (result.errcode == 0) {
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                // 页
//                val pageData = result.data
//                // 数据
//                val listData = pageData.list as ArrayList<HouseDocQAEntity>
//                for (index in listData.indices) {
//                    listData[index].isSelected = false
//                }
//
//                if (loadType == LoadType.REFRESH) {
//                    adapter?.setData(listData)
//                } else {
//                    adapter?.addData(listData)
//                }
//                StatusView.showContent()
//
//                // 设置：通用
//                pageNo = pageData.nextPage
//                if (pageData.isHasNextPage) {
//                    loadingRecyclerView.setNoMore(false)
//                } else {
//                    loadingRecyclerView.setNoMore(true)
//                }
//            }
//        } else {
//            showTip(result.errmsg)
//        }
//    }
}


//    private var presenter: KMPresenter? = null
//    private var adapter: KMHouseDocQAListAdapter? = null
//
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "问题解答库")
//
//    override fun getContentLayoutId(): Int {
//        return R.layout.activity_lm_stadium_list
//    }
//
//    override fun getContentRetryListener(): View.OnClickListener {
//        return View.OnClickListener {
//            refreshData()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        StatusBarUtil.transparencyBar(this)
//
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))
//
//        adapter = KMHouseDocQAListAdapter(ArrayList())
//        recyclerView.adapter = adapter
//
//        presenter = KMPresenter(this)
//
//        // ================================
//        refreshData()
//    }
//
//    private fun refreshData() {
//        val pageParam = PageReq<CommReq>()
//        pageParam.pageNo = 1
//        pageParam.pageSize = 100
//
//        val comm = CommReq()
//        comm.question = ""
//        comm.quesClass = ""
//        comm.questType = ""
//
//        pageParam.data = comm
//        presenter!!.loadHouseDocQAList(pageParam)
//    }
//
//    override fun loadingShow() {
//        StatusView.showLoading("正在获取数据...")
//    }
//
//    override fun loadingDismiss() {
//    }
//
//    override fun showTip(msg: String?) {
//        StatusView.showError(msg)
//    }
//
//    override fun loadComplete(resultData: Any?) {
//        val result: BaseData<PageData<HouseDocQAEntity>> = resultData as BaseData<PageData<HouseDocQAEntity>>
//
//        if (result.errcode == 0) {
//
//            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//                showTip("暂无数据")
//            } else {
//                val pageData = result.data
//                val listData = pageData.list as ArrayList<HouseDocQAEntity>
//                adapter?.setData(listData)
//                StatusView.showContent()
//            }
//        } else {
//            StatusView.showError(result.errmsg)
//        }
//    }
//}
