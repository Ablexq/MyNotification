package com.example.administrator.mynotification.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.mynotification.R;
import com.example.administrator.mynotification.activity.SecondActivity;

public class MyNotificationManager {
    public static final String TAG = "MyNotificationManager";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));
        }

        //（1）创建NotificationChannel （android 8.0 创建channel不是必须，但是发送通知必须设置channelId）
        //channelId, channelName
        NotificationChannel channel = new NotificationChannel("1", "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);//设置通知出现时的闪灯
        channel.setLightColor(Color.GREEN);//设置通知出现时的闪灯颜色
        channel.setShowBadge(true);//是否在久按桌面图标时显示此渠道的通知
        channel.enableVibration(true);//设置通知出现时的震动
        channel.setVibrationPattern(new long[]{1000, 2000, 1000,3000});//如上设置使手机：静止1秒，震动2秒，静止1秒，震动3秒
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }

        NotificationChannel channel2 = new NotificationChannel("2", "Channel2", NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.setLightColor(Color.GREEN);
        channel2.setGroup("a");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel2);
        }
    }

    /**
     * 错误示例
     */
    public static void showNotificationWrong(Context context) {
        int notificationId = 0x1234;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Integer.toString(notificationId));

        builder.setSmallIcon(android.R.drawable.stat_notify_chat);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(notificationId, builder.build());
        }
    }

    //（2）向NotificationChannel发送通知
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void showChannel1Notification(Context context) {
        int notificationId = 0x1234;
        Notification.Builder builder = new Notification.Builder(context, "1");//与channelId对应

        builder.setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentText("通知一的内容");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(notificationId, builder.build());//notificationId
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void showChannel2Notification(Context context) {
        int notificationId = 0x1235;
        Notification.Builder builder = new Notification.Builder(context);

        //icon title text必须包含，不然影响桌面图标小红点的展示
        builder.setSmallIcon(R.mipmap.ic_shortcut)
                .setDefaults(Notification.DEFAULT_SOUND)// 设置声音/震动等 //设置点击通知跳转页面后，通知消失
                .setContentTitle("通知二的标题")
                .setContentText("通知二的内容")
                .setAutoCancel(true)
                .setChannelId("2")//直接setChannelId设置一个id就能解决8.0系统通知不显示的问题，唯一与Android7.0不同
                .setNumber(3);

        /*设置通知点击事件*/
        Intent intent = new Intent(context, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(notificationId, builder.build());
        }
    }

}