package com.wamessage.plantapp_plantidentifier.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.WateringReminderActivity;
import java.util.Date;


public class AlarmReceiver extends BroadcastReceiver {
    final String CHANNEL_ID = "202";

    @Override 
    public void onReceive(Context context, Intent intent) {
        PendingIntent activity;
        if (intent.getAction().equals("MyAction")) {
            intent.getStringExtra("time");
            String stringExtra = intent.getStringExtra("plant_name");
            String alarm_Name=  intent.getStringExtra("alarm_Name");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("202", "Channel 2", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Plant Identification APP");
            notificationManager.createNotificationChannel(notificationChannel);
            Intent intent2 = new Intent(context, WateringReminderActivity.class);
            if (Build.VERSION.SDK_INT >= 33) {
                activity = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            } else if (Build.VERSION.SDK_INT >= 31) {
                activity = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
            } else {
                activity = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            Notification build = new NotificationCompat.Builder(context, "202").setContentTitle(alarm_Name).setContentText(context.getResources().getString(R.string.time_to_watering) + " " + stringExtra + "! " + context.getResources().getString(R.string.dont_let)).setSmallIcon(R.drawable.baseline_notifications_active_24).setContentIntent(activity).setColor(-16777216).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).build();
            if (build != null) {
                notificationManager.notify(getNotificationId(), build);
            }
        }
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}
