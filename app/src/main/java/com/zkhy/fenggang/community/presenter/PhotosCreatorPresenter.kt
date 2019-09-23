package com.zkhy.fenggang.community.presenter

import com.sinothk.comm.utils.ToastUtil
import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.BmShotQuestionInfoDTO
import com.zkhy.fenggang.community.model.bean.PhotoShotEntity
import com.zkhy.fenggang.community.model.bean.PhotosCreatorVo
import com.zkhy.fenggang.community.view.photos.ShotPhotoDetailActivity
import com.zkhy.fenggang.community.view.photos.ShotPhotoHandleDetailActivity
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
class PhotosCreatorPresenter(private val view: AndroidView) {

    fun submit(vo: PhotosCreatorVo) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .submitPhotosCreatorInfo(vo)
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
                    view.loadingDismiss()
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

    fun loadMyAllShotPhotoList(pageParam: PageReq<PhotosCreatorVo>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadMyAllShotPhotoList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<PhotoShotEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<PhotoShotEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })

    }

    fun loadShotPhotoDetailData(id: String?) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadShotPhotoDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<BmShotQuestionInfoDTO>>() {

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

                override fun onNext(resultData: BaseData<BmShotQuestionInfoDTO>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun submitHandle(vo: PhotosCreatorVo) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .submitPhotosHandle(vo)
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
                    view.loadingDismiss()
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

    fun evaluateService(view: ShotPhotoDetailActivity, id: Long, stars: Int) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .bmShotQuestionEvaluate(id.toString(), stars.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingEvaluateShow()
                }

                override fun onCompleted() {
                    view.loadingEvaluateDismiss()
                }

                override fun onError(e: Throwable) {
                    view.loadingEvaluateDismiss()
                    ToastUtil.show("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        view.loadEvaluateComplete(resultData)
                    } else {
                        ToastUtil.show("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadingVideoUrl(view: ShotPhotoHandleDetailActivity) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadingVideoUrl()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    ToastUtil.show("访问服务器报错")
                    e.printStackTrace()
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadingVideoUrlEnd(resultData.data)
                        } else {
                            ToastUtil.show(resultData.errmsg)
                        }
                    } else {
                        ToastUtil.show("服务器数据异常")
                    }
                }
            })
    }
}