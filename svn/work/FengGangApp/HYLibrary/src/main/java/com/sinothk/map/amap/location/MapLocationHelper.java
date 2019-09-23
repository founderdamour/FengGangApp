package com.sinothk.map.amap.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/31 on 23:17
 *  项目:  MapLocationByAMapLib
 *  描述:
 *  更新:
 * <pre>
 */
public class MapLocationHelper {

    @SuppressLint("StaticFieldLeak")
    private volatile static MapLocationHelper singleton;

    public static MapLocationHelper with(@NonNull Context context) {
        if (singleton == null) {
            synchronized (MapLocationHelper.class) {
                if (singleton == null) {
                    singleton = new MapLocationHelper(context);
                }
            }
        }
        return singleton;
    }

    private Context mContext;
    //声明mlocationClient对象
    AMapLocationClient locationClient;

    private MapLocationHelper(Context context) {
        mContext = context;
    }

    /**
     * 单次定位
     *
     * @param callback
     */
    public void location(final AMapLocationCallback callback) {

        //声明mlocationClient对象
        locationClient = new AMapLocationClient(mContext);

        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

//        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
//
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //设置定位参数
        locationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

        //设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(com.amap.api.location.AMapLocation amapLocation) {
                callback.complete(AMapLocationEntity.format(amapLocation));
//                if (amapLocation != null) {
//                    if (amapLocation.getErrorCode() == 0) {
//
//                        //定位成功回调信息，设置相关消息
//                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                        amapLocation.getLatitude();//获取纬度
//                        amapLocation.getLongitude();//获取经度
//                        amapLocation.getAccuracy();//获取精度信息
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(amapLocation.getTime());
//                        df.format(date);//定位时间
//
//                    } else {
//                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                        Log.e("AmapError", "location Error, ErrCode:"
//                                + amapLocation.getErrorCode() + ", errInfo:"
//                                + amapLocation.getErrorInfo());
//                    }
//                }
            }
        });

        //启动定位
        locationClient.startLocation();
    }

    /**
     * 连续定位
     *
     * @param second 单位：秒
     */
    public void locateContinue(int second, final AMapLocationCallback callback) {

        //声明mlocationClient对象
        locationClient = new AMapLocationClient(mContext);

        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(second * 1000);
//
//        // ===================== 获取一次定位结果：
//        //该方法默认为false。
//        mLocationOption.setOnceLocation(true);
//        //获取最近3s内精度最高的一次定位结果：
//        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);

        //设置定位参数
        locationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

        //设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(com.amap.api.location.AMapLocation amapLocation) {
                callback.complete(AMapLocationEntity.format(amapLocation));

//                if (amapLocation != null) {
//                    if (amapLocation.getErrorCode() == 0) {
//                        //定位成功回调信息，设置相关消息
//                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                        amapLocation.getLatitude();//获取纬度
//                        amapLocation.getLongitude();//获取经度
//                        amapLocation.getAccuracy();//获取精度信息
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(amapLocation.getTime());
//                        df.format(date);//定位时间
//
//                        Log.e("AmapSuccess",
//                                "Latitude:" + amapLocation.getLatitude()
//                                        + ", Longitude:" + amapLocation.getLongitude()
//                        );
//                    } else {
//                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                        Log.e("AmapError", "location Error, ErrCode:"
//                                + amapLocation.getErrorCode() + ", errInfo:"
//                                + amapLocation.getErrorInfo());
//                    }
//                }
            }
        });

        //启动定位
        locationClient.startLocation();
    }

    public void locateStop() {
        if (locationClient != null) {
            if (locationClient.isStarted()) {
                locationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            }
            locationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

}
