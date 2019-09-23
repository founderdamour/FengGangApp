package com.zkhy.fenggang.community.presenter

import android.util.Log
import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.MainActivity
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.LocationEntity
import com.zkhy.fenggang.community.model.bean.NoticeTipEntity
import com.zkhy.fenggang.community.model.cache.DataCache
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
class MainPresenter(private val view: AndroidView) {

    /**
     * 检查用户信息完整性
     */
    fun checkUserInfo(userId: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .checkUserInfo(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

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

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadDictionary(typeCode: Array<String>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadDictionary(typeCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Map<String, Map<String, String>>>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                }

                override fun onNext(resultData: BaseData<Map<String, Map<String, String>>>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            DataCache.saveDictionary(resultData.data)
                        } else {
                            Log.i("loadDictionary", resultData.errmsg)
                        }
                    } else {
                        Log.i("loadDictionary", "loadDictionary返回为空")
                    }
                }
            })
    }

    fun uploadLocationInfo(locEntity: LocationEntity?) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .uploadLocationInfo(locEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            Log.i("uploadLocationInfo", resultData.errmsg)
                        } else {
                            Log.i("uploadLocationInfo", resultData.errmsg)
                        }
                    } else {
                        Log.i("uploadLocationInfo", "loadDictionary返回为空")
                    }
                }
            })
    }

    fun loadSysNotice(currView: MainActivity, userId: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadSysNotice(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<NoticeTipEntity>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    Log.d("loadSysNotice", e.message)
                }

                override fun onNext(resultData: BaseData<NoticeTipEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0 && resultData.data != null) {
                            currView.showMsgTip(resultData.data)
                        } else {
                            Log.d("loadSysNotice", resultData.errmsg)
                        }
                    } else {
                        Log.d("loadSysNotice", "服务器异常")
                    }
                }
            })
    }
}