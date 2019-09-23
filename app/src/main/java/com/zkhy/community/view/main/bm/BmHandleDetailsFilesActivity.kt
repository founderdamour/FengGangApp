package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_bm_handle_details_file_list.*


/**
 * <pre>
 *  创建:  梁玉涛 2019/1/10 on 14:20
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleDetailsFilesActivity : StatusViewBaseActivity() {

    private var titleTxt = "资料信息"
    private var fileList: Array<String?>? = null

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener { }
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, titleTxt)

    override fun getContentLayoutId(): Int = R.layout.activity_bm_handle_details_file_list

    override fun onCreate(savedInstanceState: Bundle?) {
        titleTxt = intent.getStringExtra("title")
        fileList = intent.getSerializableExtra("fileList") as Array<String?>
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        if (fileList == null || fileList!!.isEmpty()) {
            StatusView.showEmptyData("暂无内容")
        } else {

            mallDetailView.setImgUrls(fileList)
            StatusView.showContent()
        }
    }
}