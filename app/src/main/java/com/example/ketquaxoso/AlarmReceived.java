package com.example.ketquaxoso;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceived extends BroadcastReceiver {
    private static final int ID_NOTIFICATION = 0;
    private static final String ID_CHANNEL = "kenhthongbao";
    private NotificationManager mNotificationManager ;
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }
    public void deliverNotification(Context context){
            Intent contentIntent = new Intent(context, KetQua.class);
            PendingIntent contentPendingIntent = PendingIntent.getActivity(context, ID_NOTIFICATION, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID_CHANNEL).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("XỔ SỐ MIỀN BẮC").setContentText("Đã trúng thưởng hôm nay. Vào kiểm tra nào!").setContentIntent(contentPendingIntent).setPriority(NotificationCompat.PRIORITY_HIGH).setDefaults(NotificationCompat.DEFAULT_ALL).setAutoCancel(true);

            mNotificationManager.notify(ID_NOTIFICATION, builder.build());
    }
}
