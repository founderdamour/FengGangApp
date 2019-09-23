package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.AllApi
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.ServerConfig
import com.zkhy.fenggang.community.model.bean.RegisterEntity
import com.zkhy.fenggang.community.model.bean.TerminalLoginDTO
import com.zkhy.fenggang.community.model.bean.UserEntity
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.library.mvp.AndroidExt2View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserPresenter(private val view: AndroidExt2View) : CommPresenter(view) {

    /**
     * 用户注册
     */
    fun registerUser(dto: CommReq) {
        RxRetrofit.init(ServerConfig.baseUrl)
            .create<AllApi>(AllApi::class.java)
            .registerUser(dto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<RegisterEntity>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.loadingDismiss()
                    view.showTip("访问服务器失败")
                }

                override fun onNext(resultData: BaseData<RegisterEntity>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("返回数为空")
                    }
                }
            })
    }

    /**
     * 登录
     */
    fun login(account: String, pwd: String) {

        RxRetrofit.init(ServerConfig.baseUrl)
            .create<AllApi>(AllApi::class.java)
            .login(account, pwd)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<TerminalLoginDTO>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.loadingDismiss()
                    view.showTip("访问服务器失败")
                }

                override fun onNext(resultData: BaseData<TerminalLoginDTO>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("返回数为空")
                    }
                }
            })
    }

    /**
     * 加载用户信息
     */
    fun loadingUserInfo() {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadingUserInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<WmUser>>() {

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.loadingDismiss()
                    view.showTip("访问服务器失败")
                }

                override fun onNext(resultData: BaseData<WmUser>?) {
                    if (resultData != null) {
                        view.load2Complete(resultData)
                    } else {
                        view.showTip("返回数为空")
                    }
                }
            })
    }

    /**
     * 更新用户信息
     */
    fun updateInfo(userEntity: UserEntity) {

    }

    /**
     * 修改密码
     */
    fun changePwd(oldPwd: String, newPwd: String, confirmPwd: String) {

    }

    fun registerCode(userPhone: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .registerCode(userPhone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showTip("已发送，请注意查收")
                            view.load2Complete("")
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 更新用户信息
     */
    fun updateUserInfo(user: WmUser) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .updateUserInfo(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<WmUser>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<WmUser>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 根据账号找手机号
     */
    fun getUserPhoneByAccount(account: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .getUserAccountInfoByAccountOrIDCard(account)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<WmUser>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<WmUser>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            if (resultData.data != null) {
                                view.loadComplete(resultData.data)
                            } else {
                                view.showTip("无用户信息")
                            }
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 修改密码验证码获取
     */
    fun resetPwdPhoneCode(userPhone: String) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .resetPwdPhoneCode(userPhone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showTip("访问出错")
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showTip("已发送，请注意查收")
                            view.load2Complete("")
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 修改用户密码
     */
    fun changeUserPwd(commReq: CommReq) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .changeUserPwd(commReq)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showTip("访问出错")
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showTip("处理成功")
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 验证找回密码验证码正确性
     */
    fun findPwdShortMsgCheck(phone: String?, shortMsgCode: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .findPwdShortMsgCheck(phone, shortMsgCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }
}