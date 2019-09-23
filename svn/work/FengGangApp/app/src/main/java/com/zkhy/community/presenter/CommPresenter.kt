package com.zkhy.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.sinothk.rxretrofit.param.RetrofitParam
import com.zkhy.community.model.api.*
import com.zkhy.community.model.bean.AttachmentEntity
import com.zkhy.community.model.bean.BannerEntity
import com.zkhy.community.model.bean.NoticeMsgEntity
import com.zkhy.library.mvp.AndroidExt2View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/4 on 13:55
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
open class CommPresenter(private val view: AndroidExt2View) {

    fun uploadFile(bizId: String, bizType: String, filePath: String) {

        // 传单文件文件和键值对
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap()).create(AllApi::class.java)
            .uploadFile(bizId, bizType, RetrofitParam.createFileParam("file", File(filePath)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<AttachmentEntity>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onNext(result: BaseData<AttachmentEntity>?) {
                    if (result != null) {
                        if (result.errcode == 0) {
                            view.load2Complete(result)
                        } else {
                            view.showTip(result.errmsg)
                        }
                    } else {
                        view.showTip("文件上传失败")
                    }
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable?) {
                    view.showTip("文件上传失败")
                    e?.printStackTrace()
                }
            })
    }

    /**
     * 加载轮播图信息
     */
    fun loadHomeBannerInfo(pageReq: PageReq<String>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadHomeBannerInfo(pageReq)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<BannerEntity>>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.load2Complete(null)
                }

                override fun onNext(resultData: BaseData<PageData<BannerEntity>>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.load2Complete(resultData)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器无数据返回")
                    }
                }
            })
    }

    fun loadNoticeMsgList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadNoticeMsgList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<NoticeMsgEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<NoticeMsgEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获取通知消息
     */
    fun loadNoticeMsgStatus(ids: ArrayList<String>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadNoticeMsgStatus(ids)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    if (e == null) {

                    }
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
//                        currActivity.loadComplete(resultData)
                    } else {
                    }
                }
            })

    }
}