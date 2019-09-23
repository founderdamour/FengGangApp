package com.zkhy.fenggang.comm.plugin.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.plugin.adapter.KeyValueListAdapter
import com.zkhy.fenggang.comm.plugin.entity.KValueEntity
import com.zkhy.fenggang.community.R
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
class KeyValueListActivity : StatusViewBaseActivity() {

    private var kValueList: ArrayList<KValueEntity>? = null
    private var title = ""

    companion object {
        const val CODE = "CODE"
        const val VALUE = "VALUE"

        const val TITLE = "TITLE"
        const val DATA = "DATA"
    }

    private var adapter: KeyValueListAdapter? = null

    override fun getTitleBarView(): View =
        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(title, "选择列表"))

    override fun getContentLayoutId(): Int {
        return R.layout.activity_status_view_list
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = intent.getStringExtra(TITLE)
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        kValueList = intent.getSerializableExtra(DATA) as ArrayList<KValueEntity>?

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerLineView(this, R.color.app_bg))

        adapter = KeyValueListAdapter(kValueList)
        recyclerView.adapter = adapter
        StatusView.showContent()

        adapter!!.setClickCallBack(ItemClickCallBack<KValueEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            val intent = intent
            intent.putExtra(DATA, entity)
            setResult(200, intent)
            finish()
        })
    }
}
