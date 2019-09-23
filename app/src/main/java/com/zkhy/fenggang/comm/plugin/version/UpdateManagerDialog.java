package com.zkhy.fenggang.comm.plugin.version;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zkhy.fenggang.community.R;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.DecimalFormat;


/**
 * 自定义download对话框
 *
 * @author DengBin
 */
public class UpdateManagerDialog extends Dialog {

    private static UpdateManagerDialog dialog;
    private static TextView cancelTv;
    private static TextView okTV;

    private TextView titleTV;
    private TextView versionNameTv;
    private static TextView fileSizeTv;
    private TextView describeTv;
    private static LinearLayout btnLayout;

    private static Context mContext;

    private static boolean isNeedUpdate;

    private UpdateManagerDialog(Context context) {
        super(context, R.style.myDialog);
        this.setCancelable(true);
    }

    @NotNull
    public static UpdateManagerDialog create(Context context, boolean isMustUpdate) {
        isNeedUpdate = isMustUpdate;
        mContext = context;

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        // onCreate中
        dialog = new UpdateManagerDialog(context);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    public void setContent(String titleStr, String versionNameStr, String fSize, String versionDescribeStr,
                           View.OnClickListener onOKClick,
                           View.OnClickListener onCancelClick) {
        titleTV.setText(titleStr);
        versionNameTv.setText(versionNameStr);

        describeTv.setText(Html.fromHtml(versionDescribeStr));

        okTV.setOnClickListener(onOKClick);
        cancelTv.setOnClickListener(onCancelClick);

        try {
            long fSizeValue = Long.valueOf(fSize);

            fileSizeTv.setText(getSize(fSizeValue));
        } catch (Exception e) {
            fileSizeTv.setText(fSize);
        }
    }

    public static void closeDialog() {
        try {
            if (null != dialog && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_progress);

        titleTV = (TextView) findViewById(R.id.title_tick);
        versionNameTv = (TextView) findViewById(R.id.versionName);
        fileSizeTv = (TextView) findViewById(R.id.fileSizeTv);
        describeTv = (TextView) findViewById(R.id.describeTv);

        btnLayout = (LinearLayout) findViewById(R.id.btnLayout);
        cancelTv = (TextView) findViewById(R.id.cancel_down);
        okTV = (TextView) findViewById(R.id.ok_down);

        if (isNeedUpdate) {
            cancelTv.setVisibility(View.GONE);
        } else {
            cancelTv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 下载进度
     *
     * @param currentSize
     * @param fileSize
     * @param networkSpeed
     */
    public static void updateProgress(long currentSize, long fileSize, long networkSpeed) {
        if (fileSizeTv != null) {
//            fileSizeTv.setText(getSize(currentSize) + "/" + getSize(fileSize));
            fileSizeTv.setText(getSize(currentSize) + "/" + getSize(fileSize));
        }
    }

    /**
     * 下载成功
     *
     * @param file
     */
    public static void downloadSuccess(File file) {
        if (file != null && file.exists()) {
            installNormal(file.getAbsolutePath());
        }
    }

    /**
     * ================ 安装
     *
     * @param apkPath
     */
    private static void installNormal(String apkPath) {///storage/emulated/0/download/com.qihoo.appstore_300070181.apk
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String packageName = mContext.getApplicationInfo().packageName + ".fileProvider";
            Uri uriForFile = FileProvider.getUriForFile(mContext, packageName, new File(apkPath));

            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, mContext.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(new File(apkPath)), getMIMEType(new File(apkPath)));
        }
        try {
            mContext.startActivity(var2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMIMEType(File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileType);
    }

    public static String getSize(long size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }

    public static void hideBtnLayout(boolean isHide) {
        if (isHide) {
            btnLayout.setVisibility(View.GONE);
        } else {
            btnLayout.setVisibility(View.VISIBLE);
        }
    }

    public static void setDisable(boolean disable) {
        okTV.setEnabled(!disable);
    }

    public static void setLoading(boolean disable, String msg) {
        okTV.setText(msg);
        okTV.setEnabled(!disable);
    }

    public static void hideCancelBtn(boolean hide) {
        if (hide) {
            cancelTv.setVisibility(View.GONE);
        } else {
            cancelTv.setVisibility(View.VISIBLE);
        }
    }

}
