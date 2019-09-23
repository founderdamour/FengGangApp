package com.zkhy.community.presenter

import com.sinothk.rxretrofit.RetrofitFactory
import com.zkhy.community.model.api.*
import com.zkhy.community.model.bean.*
import com.zkhy.library.mvp.AndroidView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 17:01
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BasePresenter(private val view: AndroidView) {

    /**
     * 获得问答列表
     */
    fun loadLawQAList(pageParam: PageReq<CommReq>) {
        RetrofitFactory.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLawQAList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LawQuestionEntity>>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<PageData<LawQuestionEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }
}