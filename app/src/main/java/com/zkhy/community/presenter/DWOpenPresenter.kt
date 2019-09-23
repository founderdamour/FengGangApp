package com.zkhy.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.community.model.api.*
import com.zkhy.community.model.bean.DWOpenListEntity
import com.zkhy.community.model.bean.ThreeMeetEntity
import com.zkhy.library.mvp.AndroidExt2View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 党务公开
 */
class DWOpenPresenter(var view: AndroidExt2View?) {

    /**
     * 党务公开列表
     */
    fun loadDWOpenAllList(pageParam: PageReq<CommReq>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadDWOpenAllList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<DWOpenListEntity>>>() {

                override fun onStart() {
                    super.onStart()
                    view?.loadingShow()
                }

                override fun onCompleted() {
                    view?.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view?.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<PageData<DWOpenListEntity>>?) {
                    if (resultData != null) {
                        view?.loadComplete(resultData)
                    } else {
                        view?.showTip("服务器无数据返回...")
                    }
                }
            })
    }
}