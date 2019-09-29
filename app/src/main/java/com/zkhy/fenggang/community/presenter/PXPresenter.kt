package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.LoveHeartEntity
import com.zkhy.fenggang.community.model.bean.TrainEmploymentEntity
import com.zkhy.fenggang.community.model.bean.WishEntity
import com.zkhy.library.mvp.AndroidExt2View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PXPresenter(private val view: AndroidExt2View) : CommPresenter(view) {

    /**
     * 加载培训就业列表
     */
    fun loadPxJyList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadPxJyList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<TrainEmploymentEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<TrainEmploymentEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadWishTotal(vo: CommReq) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadWishTotal(vo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<CommResult>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<CommResult>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 发布心愿
     */
    fun publicWish(vo: CommReq) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .publicWish(vo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

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

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadLoveHeartList(pageParam: PageReq<String>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLoveHeartList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LoveHeartEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<LoveHeartEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })

    }
}