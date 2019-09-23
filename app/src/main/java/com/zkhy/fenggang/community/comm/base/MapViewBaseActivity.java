package com.zkhy.fenggang.community.comm.base;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.*;
import com.zkhy.fenggang.community.R;
import com.zkhy.library.base.activity.TitleBarBaseActivity;

import java.util.ArrayList;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/16 on 16:23
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public abstract class MapViewBaseActivity extends TitleBarBaseActivity {
    MapView mMapView = null;
    // 初始化AMap对象
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
            initLocMap();
        }
    }

    private void initLocMap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.interval(24 * 60 * 60 * 1000); //10 * 1000 设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.push_pin_72)));
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        setMapSetting();
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    private void setMapSetting() {
        UiSettings mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
//        aMap.setLocationSource(this);//通过aMap对象设置定位数据源的监听

//        mUiSettings.setCompassEnabled(true); // 指南针用于向 App 端用户展示地图方向，默认不显示。通过如下接口控制其显示：
        mUiSettings.setZoomControlsEnabled(false);
//        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
    }

    protected void setMultiPointOverlay(ArrayList<MultiPointItem> pointList) {
        // ====================================================================================
        MultiPointOverlayOptions overlayOptions = new MultiPointOverlayOptions();
        overlayOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.location_24)));//设置图标
        overlayOptions.anchor(0.5f, 0.5f); //设置锚点
        MultiPointOverlay multiPointOverlay = aMap.addMultiPointOverlay(overlayOptions);
        multiPointOverlay.setItems(pointList);//将规范化的点集交给海量点管理对象设置，待加载完毕即可看到海量点信息
        multiPointOverlay.setEnable(true);

        // 定义海量点点击事件
//        AMap.OnMultiPointClickListener multiPointClickListener = new AMap.OnMultiPointClickListener() {
//            // 海量点中某一点被点击时回调的接口
//            // 返回 true 则表示接口已响应事件，否则返回false
//            @Override
//            public boolean onPointClick(MultiPointItem pointItem) {
//                Toast.makeText(MapViewBaseActivity.this, "id = " + pointItem.getCustomerId(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        };

        // 绑定海量点点击事件
        aMap.setOnMultiPointClickListener(setMapMultiPointClickListener());
    }

    protected abstract AMap.OnMultiPointClickListener setMapMultiPointClickListener();

//    protected abstract ArrayList<MultiPointItem> getMapPointLatLngList();

//    private List<MultiPointItem> getPointLatLngList() {
//        List<MultiPointItem> list = new ArrayList<MultiPointItem>();
//        list.add(new MultiPointItem(new LatLng(26.5989651565, 106.6947555542)));
//        list.add(new MultiPointItem(new LatLng(26.6331890046, 106.6358757019)));
//        list.add(new MultiPointItem(new LatLng(26.6385595303, 106.5975952148)));
//        list.add(new MultiPointItem(new LatLng(26.6256698444, 106.7017936707)));
//        list.add(new MultiPointItem(new LatLng(26.6024954442, 106.6906356812)));
//        return list;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
