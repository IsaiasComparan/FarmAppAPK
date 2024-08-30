package com.wamessage.plantapp_plantidentifier.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.wamessage.plantapp_plantidentifier.R;
import java.util.Date;


public class AlarmNhan extends BroadcastReceiver {
    final String CHANNEL_ID = "205";

    @Override 
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("MyAction")) {
            String stringExtra = intent.getStringExtra("time");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT > 26) {
                NotificationChannel notificationChannel = new NotificationChannel("205", "ChannelTest", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Mieu ta cho ken ChannelTest");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            RingtoneManager.getDefaultUri(2);
            notificationManager.notify(getNotificationID(), new NotificationCompat.Builder(context, "205").setContentTitle("BAO THUC" + stringExtra).setContentText("Day di toi gio roi").setSmallIcon(R.drawable.baseline_notifications_active_24).setColor(-65536).setCategory(NotificationCompat.CATEGORY_ALARM).build());
        }
    }

    private int getNotificationID() {
        return (int) new Date().getTime();
    }
}
