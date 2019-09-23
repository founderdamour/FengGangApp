package com.zkhy.comm.plugin.version;

import android.app.Activity;
import android.view.View;
import com.autonavi.amap.mapcore.FileUtil;
import com.sinothk.comm.utils.LogUtil;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.comm.utils.ToastUtil;
import com.sinothk.dialog.loading.LoadingDialog;
import com.sinothk.rxretrofit.RxRetrofit;
import com.sinothk.rxretrofit.callback.DownloadCallback;
import com.zkhy.community.model.api.AllApi;
import com.zkhy.community.model.api.BaseData;
import com.zkhy.community.model.api.ServerConfig;
import com.zkhy.community.model.cache.DataCache;
import com.zkhy.library.utils.AppUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.io.File;
import java.util.concurrent.Executors;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/14/014 on 16:46
 *  项目: gxqptAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class VersionDialog {

    public static void show(final Activity mActivity, final boolean needTip) {

        final int currVersionCode = AppUtil.getAppVersionCode();

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
                .create(AllApi.class)
                .getLastVersionInfo("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseData<VersionEntity>>() {

                    @Override
                    public void onStart() {
                        if (needTip) {
                            LoadingDialog.show(mActivity, "正在获取");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (needTip) {
                            LoadingDialog.hidden();
                            ToastUtil.show("服务器异常");
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (needTip) {
                            LoadingDialog.hidden();
                        }
                    }

                    @Override
                    public void onNext(BaseData<VersionEntity> result) {

                        if (result.getErrcode() == 0) {
                            if (result.getData() != null) {
                                final VersionEntity version = result.getData();

                                boolean haveNew = version.getVerNumber() > currVersionCode;
                                if (haveNew) {
                                    DataCache.setNewVersion(true);

                                    int isMustUpdate = version.getPwUpdate() == null ? 1 : version.getPwUpdate();

                                    UpdateManagerDialog.create(mActivity, isMustUpdate == 2)
                                            .setContent("升级提示",
                                                    version.getVerName(),
                                                    StringUtil.getNotNullValue(version.getApkFileSize(), "未知大小"),
                                                    StringUtil.getNotNullValue(version.getUpdateContent(), "优化App性能..."),
                                                    new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DataCache.setNewVersion(false);

                                                            UpdateManagerDialog.setLoading(true, "正在下载");
                                                            UpdateManagerDialog.hideCancelBtn(true);

                                                            downloadFile(mActivity, version);

//                                                            String apkFilePath = version.getApkFilePath();
//                                                            String apkFileName = version.getApkFileName();
//                                                            String allUrl = ServerUrl.getFileDown2Url(apkFilePath, apkFileName);

//                                                            String url = version.getApkFilePath();
//
//                                                            if (StringUtil.isNotEmpty(url)) {
//                                                                Uri uri = Uri.parse(url);
//                                                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                                                mActivity.startActivity(intent);
//                                                            } else {
//                                                                ToastUtil.show("下载地址为空");
//                                                            }
//                                                            UpdateManagerDialog.closeDialog();
                                                        }
                                                    }, new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            UpdateManagerDialog.closeDialog();
                                                        }
                                                    });
                                } else {
                                    DataCache.setNewVersion(false);

                                    if (needTip) {
                                        ToastUtil.show("当前已是最新版本");
                                    }
                                }
                            } else {
                                if (needTip) {
                                    ToastUtil.show("无版本信息");
                                }
                            }
                        } else {
                            if (needTip) {
                                ToastUtil.show(result.getErrmsg());
                            }
                        }
                    }
                });
    }

    /**
     * 下载文件
     *
     * @param mActivity
     * @param versionInfo
     */
    private static void downloadFile(Activity mActivity, final VersionEntity versionInfo) {

        if (versionInfo != null && StringUtil.isNotEmpty(versionInfo.getApkFilePath())) {
            // 文件下载
            String url = versionInfo.getApkFilePath().replace(ServerConfig.baseUrl + "api/admin", "api/admin");
            String path = FileUtil.getExternalStroragePath(mActivity) + "/download/" + versionInfo.getApkFileName();

            final long fileSize = versionInfo.getApkFileSize() == null ? 0 : Long.parseLong(versionInfo.getApkFileSize());

            RxRetrofit
                    .init(ServerConfig.baseUrl, Executors.newSingleThreadExecutor())
                    .create(AllApi.class)
                    .downloadFile(url)
                    .enqueue(new DownloadCallback(mActivity, path) {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onProgress(long currSize, long totalSize, int progress) {
                            UpdateManagerDialog.updateProgress(currSize, fileSize, progress);
                        }

                        @Override
                        public void onFinish(String path) {
                            UpdateManagerDialog.closeDialog();
                            if (path != null) {
                                UpdateManagerDialog.downloadSuccess(new File(path));
                            }
                        }

                        @Override
                        public void onFail(String errorInfo) {
                            UpdateManagerDialog.closeDialog();
                        }
                    });

            // 更新下载数量
            updateDownLoadNum(versionInfo);
        } else {
            ToastUtil.show("下载信息不可用");
        }
    }

    private static void updateDownLoadNum(VersionEntity versionInfo) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
                .create(AllApi.class)
                .updateDownLoadNum("" + versionInfo.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseData<Boolean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(RxRetrofit.class, e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<Boolean> booleanBaseData) {
                        LogUtil.e(RxRetrofit.class, booleanBaseData.getData().toString());
                    }
                });
    }
}
