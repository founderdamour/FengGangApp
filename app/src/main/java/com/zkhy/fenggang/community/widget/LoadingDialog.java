package com.zkhy.fenggang.community.widget;

import android.content.Context;
import android.util.Log;

import com.sinothk.dialog.loading.LoadDialog;

public class LoadingDialog {

    private static LoadDialog loadingDialog;

    public LoadingDialog() {
    }

    public static void show(Context context, String msg) {
        LoadingDialog.show(context, msg, false);
    }

    public static void show(Context context, boolean isCancel) {
        LoadingDialog.show(context, "", isCancel);
    }

    public static void show(Context context, String msg, boolean isCancel) {
        try {
            if (loadingDialog != null) {
                loadingDialog = null;
            }

            loadingDialog = new LoadDialog(context);
            LoadDialog.SetText(msg);
            loadingDialog.setCanceledOnTouchOutside(isCancel);
            loadingDialog.show();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void hidden() {
        try {
            Log.d("LmiotDialog", "销毁");
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                LoadDialog var10000 = loadingDialog;
                LoadDialog.setTextViewNull();
                loadingDialog = null;
            }
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
