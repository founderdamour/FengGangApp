package com.zkhy.community.view.main.km

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView
import com.sinothk.widget.loadingRecyclerView.extend.LoadingRecycleViewHeader
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.*
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.KMPresenter
import com.zkhy.community.view.main.am.AmLawConsultReplyActivity
import com.zkhy.community.view.main.am.adapter.LawQuestionReplyAdapter
import com.zkhy.community.view.main.km.adpaters.DocQuestionReplyAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.base.activity.TitleBarWhitRecycleViewBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_am_law_consult_details.*
import java.io.Serializable

/**
 * 发起咨询
 */
class DocConsultDetailsActivity : TitleBarWhitRecycleViewBaseActivity(), AndroidExt2View {
    private var docQuestionDetails: DocQADetailsEntity? = null

    private var timeTv: TextView? = null
    private var contentTv: TextView? = null
    private var totalTv: TextView? = null
    private var statusTv: TextView? = null
    private var consId = ""

    var item: HouseDocQAEntity? = null
    private var presenter: KMPresenter? = null
    private var mAdapter: DocQuestionReplyAdapter? = null

    override fun getLayout(): Int = R.layout.activity_am_law_consult_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = intent.getSerializableExtra("item") as HouseDocQAEntity?
        presenter = KMPresenter(this)

        initView()
    }

    private fun initView() {
        initRecycleLinearView(recyclerView)
        recyclerView.addItemDecoration(recyclerView.getListViewLine(this, R.drawable.list_divider_max))
        // 设置头部
        val header: View = LoadingRecycleViewHeader.getViewByLayoutId(
            this@DocConsultDetailsActivity,
            R.layout.law_consult_details_top
        )
        timeTv = header.findViewById(R.id.timeTv)
        contentTv = header.findViewById(R.id.contentTv)
        totalTv = header.findViewById(R.id.totalTv)
        statusTv = header.findViewById(R.id.statusTv)

        recyclerView.addHeaderView(header)
        recyclerView.setNoMore(true)
        recyclerView.setLoadingMoreEnabled(false)
        recyclerView.setLoadingListener(object : LoadingRecyclerView.LoadingListener {
            override fun onRefresh() {
                refreshData()
            }

            override fun onLoadMore() {
                recyclerView?.loadMoreComplete()
                mAdapter?.notifyDataSetChanged()
            }
        })

        mAdapter = DocQuestionReplyAdapter(this, ArrayList())
        mAdapter!!.setClickCallBack { _, itemEntity ->

            if (docQuestionDetails!!.answerStatus == 3) {
                ToastUtil.show("已解决问题不能追问，请重新咨询")
                return@setClickCallBack
            }

            val entity: DocQAReplyBean = itemEntity as DocQAReplyBean
            val user: WmUser? = DataCache.getUserInfo()

            if (user != null && StringUtil.isNotEmpty(user.userId)
                && StringUtil.isNotEmpty(entity.answerUserId)
                && user.userId != entity.answerUserId // 不是自己发出，才进行回复
            ) {
                IntentUtil.openActivity(this@DocConsultDetailsActivity, DocConsultReplyActivity::class.java)
                    .putStringExtra("consId", consId)
                    .putSerializableExtra("item", entity as Serializable?)
                    .start()
            } else {
                ToastUtil.show("不能回复自己的咨询")
            }
        }

        recyclerView.adapter = mAdapter
    }

    override fun refreshData() {
        if (item != null) {
            presenter?.getDocConsultAnswerDetailsById(item!!.id)
        }
    }

    override fun loadMoreData() {
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在处理")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
        recyclerView.refreshComplete()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        loadingDismiss()
    }

    @SuppressLint("SetTextI18n")
    override fun loadComplete(result: Any?) {
        loadingDismiss()

        val baseData = result as BaseData<DocQADetailsEntity>
        if (baseData.errcode == 0) {

            docQuestionDetails = baseData.data

            consId = docQuestionDetails!!.id
            timeTv?.text = docQuestionDetails!!.quesTime
            contentTv?.text = docQuestionDetails!!.consultQuestion
            totalTv?.text = "${docQuestionDetails!!.ansNum}条"

            if (docQuestionDetails!!.answerStatus == 3) {
                statusTv?.text = "已解决"
                setTitleBar("我的咨询")
            } else {
                statusTv?.text = "解答中"

                setTitleBar("我的咨询", "已解决") {

                    if (StringUtil.isNotEmpty(item!!.id)) {
                        CommonDialog.Builder(this)
                            .setTitle("重要提示")
                            .setCanceledOnTouchOutside(false)
                            .setMessage("\n确定问题已解决了吗？\n")
                            .setPositiveButton("确定") {

                                presenter?.closeDocConsultAnswer(item!!.id)

                            }.setNegativeButton("取消") {
                            }.show()
                    } else {
                        ToastUtil.show("问题信息不全")
                    }
                }
            }

            if (baseData.data.replyList != null && baseData.data.replyList.size > 0) {
                mAdapter?.setData(baseData.data.replyList)

            } else {

            }
            recyclerView.refreshComplete()
        } else {
            showTip(baseData.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {
        loadingDismiss()

        val result = resultData as BaseData<Boolean>
        if (result.data) {
            refreshData()
        } else {
            showTip(result.errmsg)
        }
    }
}
