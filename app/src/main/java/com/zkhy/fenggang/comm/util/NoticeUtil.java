package com.zkhy.fenggang.comm.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import com.zkhy.fenggang.community.model.bean.NoticeMsgEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/7 on 10:56
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class NoticeUtil {


//    public static void showTip(Activity activity, String title, String content, int iconId, Class<?> targetCls) {
//
////        //第一步：实例化通知栏构造器Notification.Builder：
////        Notification.Builder builder = new Notification.Builder(activity);//实例化通知栏构造器Notification.Builder，参数必填（Context类型），为创建实例的上下文
////
////        //第二步：获取状态通知栏管理：
////        NotificationManager mNotifyMgr = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);//获取状态栏通知的管理类（负责发通知、清除通知等操作）
////
//////        //第三步：设置通知栏PendingIntent（点击动作事件等都包含在这里）:
//////        Intent push = new Intent(activity.getApplicationContext(), targetCls);//新建一个显式意图，第一个参数 Context 的解释是用于获得 package name，以便找到第二个参数 Class 的位置
//////        //PendingIntent可以看做是对Intent的包装，通过名称可以看出PendingIntent用于处理即将发生的意图，而Intent用来用来处理马上发生的意图
//////        //本程序用来响应点击通知的打开应用,第二个参数非常重要，点击notification 进入到activity, 使用到pendingIntent类方法，PengdingIntent.getActivity()的第二个参数，即请求参数，实际上是通过该参数来区别不同的Intent的，如果id相同，就会覆盖掉之前的Intent了
//////        PendingIntent contentIntent = PendingIntent.getActivity(activity, 0, push, 0);
////
////        //设置点击通知后执行的动作
////        Intent intent = new Intent(activity, targetCls);
//////        intent.putExtra("message","今天中午一点在报告厅一开部门会议,请大家准参加，通知发布时间:"+sdf.format(new Date()));
////        //用当前时间充当通知的id，这里是为了区分不同的通知，如果是同一个id，前者就会被后者覆盖
////        int requestId = (int) new Date().getTime();
////        //第一个参数连接上下文的context
////        // 第二个参数是对PendingIntent的描述，请求值不同Intent就不同
////        // 第三个参数是一个Intent对象，包含跳转目标
////        // 第四个参数有4种状态
////        PendingIntent contentIntent = PendingIntent.getActivity(activity, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
////
////        //第四步：对Builder进行配置：
////        builder.setContentTitle(title)//标题
////                .setContentText(content)// 详细内容
////                .setContentIntent(contentIntent)//设置点击意图
////                .setAutoCancel(true)
////                .setTicker("New message")//第一次推送，角标旁边显示的内容
////                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), iconId))//设置大图标
////                .setDefaults(Notification.DEFAULT_ALL);//打开呼吸灯，声音，震动，触发系统默认行为
////
////       /*Notification.DEFAULT_VIBRATE  //添加默认震动提醒 需要VIBRATE permission
////       Notification.DEFAULT_SOUND  //添加默认声音提醒
////       Notification.DEFAULT_LIGHTS//添加默认三色灯提醒
////       Notification.DEFAULT_ALL//添加默认以上3种全部提醒*/
////        //.setLights(Color.YELLOW, 300, 0)//单独设置呼吸灯，一般三种颜色：红，绿，蓝，经测试，小米支持黄色
////        //.setSound(url)//单独设置声音
////        //.setVibrate(new long[] { 100, 250, 100, 250, 100, 250 })//单独设置震动
////
////        //比较手机sdk版本与Android 5.0 Lollipop的sdk
////        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
////            builder
////                    /*android5.0加入了一种新的模式Notification的显示等级，共有三种：
////                    VISIBILITY_PUBLIC只有在没有锁屏时会显示通知
////                    VISIBILITY_PRIVATE任何情况都会显示通知
////                    VISIBILITY_SECRET在安全锁和没有锁屏的情况下显示通知*/
////                    .setVisibility(Notification.VISIBILITY_PUBLIC)
////                    .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
////                    .setCategory(Notification.CATEGORY_MESSAGE)//设置通知类别
////                    //.setColor(context.getResources().getColor(R.color.small_icon_bg_color))//设置smallIcon的背景色
////                    .setFullScreenIntent(contentIntent, true)//将Notification变为悬挂式Notification
////                    .setSmallIcon(iconId);//设置小图标
////        } else {
////            builder.setSmallIcon(iconId);//设置小图标
////        }
////
////        //第五步：发送通知请求：
////        Notification notify = builder.build();//得到一个Notification对象
////        mNotifyMgr.notify(requestId, notify);//发送通知请求
//
//    }

    //===========================发通知==============================//
    //定义一个NotifactionManager对象
    private static NotificationManager manager;
    //设置日期格式
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void showTip(Activity activity, String title, String content, int iconId, Class<?> targetCls, int num) {
        //获取NotifactionManager对象
        manager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
        //构建一个Notifaction的Builder对象
        Notification.Builder builder = new Notification.Builder(activity);
        //设置通知相关信息
        builder.setTicker("新消息提醒");//设置信息提示
        builder.setSmallIcon(iconId);//设置通知提示图标
        builder.setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), iconId));//设置图标
        builder.setContentTitle(title);//设置标题
        builder.setContentText(content);//设置文本
        builder.setAutoCancel(true);//查看后自动取消
        builder.setWhen(SystemClock.currentThreadTimeMillis());//什么时候发出的通知
        builder.setDefaults(Notification.DEFAULT_ALL);//消息提示模式

//        //设置震动规律，（第一个参数: 振动前等待的时间，第二个参数： 第一次振动的时长、以此类推  ）
//        builder.setVibrate(new long[]{1000, 2000, 1000, 3000});
//        builder.setS
//        //自定义声音
//        //builder.setSound(Uri.parse("file:///sdcard/notification/ringer.mp3"));
//        //设置灯
//        builder.setLights(Color.GREEN, 1000, 1000);

        //设置点击通知后执行的动作
        Intent intent = new Intent(activity, targetCls);
//        intent.putExtra(title, "今天中午一点在报告厅一开部门会议,请大家准参加，通知发布时间:" + sdf.format(new Date()));
        //用当前时间充当通知的id，这里是为了区分不同的通知，如果是同一个id，前者就会被后者覆盖
        int requestId = (int) new Date().getTime() + num;
        //第一个参数连接上下文的context
        // 第二个参数是对PendingIntent的描述，请求值不同Intent就不同
        // 第三个参数是一个Intent对象，包含跳转目标
        // 第四个参数有4种状态
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        //发出通知，参数是（通知栏的id，设置内容的对象）
        manager.notify(requestId, builder.build());


    }

    public static void showTip(Activity activity, String title, String content, int iconId, Class<?> targetCls, int num, NoticeMsgEntity centerInfo) {
        //获取NotifactionManager对象
        manager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
        //构建一个Notifaction的Builder对象
        Notification.Builder builder = new Notification.Builder(activity);
        //设置通知相关信息
        builder.setTicker("新消息提醒");//设置信息提示
        builder.setSmallIcon(iconId);//设置通知提示图标
        builder.setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), iconId));//设置图标
        builder.setContentTitle(title);//设置标题
        builder.setContentText(content);//设置文本
        builder.setAutoCancel(true);//查看后自动取消
        builder.setWhen(SystemClock.currentThreadTimeMillis());//什么时候发出的通知
        builder.setDefaults(Notification.DEFAULT_ALL);//消息提示模式

//        //设置震动规律，（第一个参数: 振动前等待的时间，第二个参数： 第一次振动的时长、以此类推  ）
//        builder.setVibrate(new long[]{1000, 2000, 1000, 3000});
//        builder.setS
//        //自定义声音
//        //builder.setSound(Uri.parse("file:///sdcard/notification/ringer.mp3"));
//        //设置灯
//        builder.setLights(Color.GREEN, 1000, 1000);

        //设置点击通知后执行的动作
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity", centerInfo);

        Intent intent = new Intent(activity, targetCls);
        intent.putExtras(bundle);

        //用当前时间充当通知的id，这里是为了区分不同的通知，如果是同一个id，前者就会被后者覆盖
        int requestId = (int) new Date().getTime() + num;
        //第一个参数连接上下文的context
        // 第二个参数是对PendingIntent的描述，请求值不同Intent就不同
        // 第三个参数是一个Intent对象，包含跳转目标
        // 第四个参数有4种状态
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        //发出通知，参数是（通知栏的id，设置内容的对象）
        manager.notify(requestId, builder.build());


    }
}
