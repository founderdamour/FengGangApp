package com.sinothk.map.amap.navigate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;

import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/2/28 on 10:29
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class MapNavigateUtil {

    public static final int DATA_FROM_BAIDU = 1; // 百度数据
    public static final int DATA_FROM_GAODE = 2; // 高德数据

    /**
     * BD-09 坐标转换成 GCJ-02 坐标
     */
    public static LatLng BD2GCJ(LatLng bd) {
        double x = bd.longitude - 0.0065, y = bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);

        double lng = z * Math.cos(theta);//lng
        double lat = z * Math.sin(theta);//lat
        return new LatLng(lat, lng);
    }

    /**
     * GCJ-02 坐标转换成 BD-09 坐标
     */
    public static LatLng GCJ2BD(LatLng bd) {
        double x = bd.longitude, y = bd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new LatLng(tempLat, tempLon);

    }

    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @return
     */
    private static boolean isInstalled(Context mContext, String packageName) {

        PackageManager manager = mContext.getPackageManager();

        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

    /**
     * 跳转百度地图
     */
    public static void goToBaiduMap(Activity mActivity, String mAddressStr, double mLng, double mLat, int dataFromType) {
        // 百度数据->中建华府F区: 26.6094876522,106.7336647792
        if (!isInstalled(mActivity, "com.baidu.BaiduMap")) {
            Toast.makeText(mActivity, "请先安装百度地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }


        LatLng endPoint;
        if (dataFromType == DATA_FROM_GAODE) {
            // 如果是高德数据，需要转换
            endPoint = GCJ2BD(new LatLng(mLat, mLng));//坐标转换
        } else {
            endPoint = new LatLng(mLat, mLng);//坐标转换
        }


        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + endPoint.latitude + "," + endPoint.longitude + "|name:" + mAddressStr + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=andr.baidu.openAPIdemo"));
        mActivity.startActivity(intent); // 启动调用
    }

    /**
     * 跳转高德地图
     */
    public static void goToGaodeMap(Activity mActivity, String mAddressStr, double mLng, double mLat, int dataFromType) {
        // 百度数据->中建华府F区: 26.6094876522,106.7336647792

        if (!isInstalled(mActivity, "com.autonavi.minimap")) {
            Toast.makeText(mActivity, "请先安装高德地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }

        LatLng endPoint;
        if (dataFromType == DATA_FROM_BAIDU) {
            // 如果是百度数据，需要转换
            endPoint = BD2GCJ(new LatLng(mLat, mLng));//坐标转换
        } else {
            endPoint = new LatLng(mLat, mLng);//坐标转换
        }

        String stringBuffer = "androidamap://navi?sourceApplication=" + "amap" +
                "&lat=" + endPoint.latitude +
                "&lon=" + endPoint.longitude + "&keywords=" + mAddressStr +
                "&dev=" + 0 +
                "&style=" + 2;

        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer));
        intent.setPackage("com.autonavi.minimap");
        mActivity.startActivity(intent);
    }

    /**
     * 跳转腾讯地图
     *
     * @param mActivity
     * @param mAddressStr
     * @param mLng
     * @param mLat
     * @param dataFromType
     */
    public static void goToTencentMap(Activity mActivity, String mAddressStr, double mLng, double mLat, int dataFromType) {
        if (!isInstalled(mActivity, "com.tencent.map")) {
            Toast.makeText(mActivity, "请先安装腾讯地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        LatLng endPoint;
        if (dataFromType == DATA_FROM_BAIDU) {
            // 如果是百度数据，需要转换
            endPoint = BD2GCJ(new LatLng(mLat, mLng));//坐标转换
        } else {
            endPoint = new LatLng(mLat, mLng);//坐标转换
        }

        String stringBuffer = "qqmap://map/routeplan?type=drive" +
                "&tocoord=" + endPoint.latitude + "," + endPoint.longitude + "&to=" + mAddressStr;

        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer));
        mActivity.startActivity(intent);
    }
}
