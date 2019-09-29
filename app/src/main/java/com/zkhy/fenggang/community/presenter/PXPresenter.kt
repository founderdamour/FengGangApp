package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.FgRentingEntity
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

    /**
     * 加载培训就业列表
     */
    fun loadFgRentingList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
                .create(AllApi::class.java)
                .loadFgRentingList(pageParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<BaseData<PageData<FgRentingEntity>>>() {

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

                    override fun onNext(resultData: BaseData<PageData<FgRentingEntity>>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

}