package com.wamessage.plantapp_plantidentifier;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.wamessage.plantapp_plantidentifier.activities.SplashActivity;


public class MyApplication extends Application {
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static MyApplication ins;
    private AppOpenAds appOpenManager;
    @Override
    public void onCreate() {
        super.onCreate();
        ins = this;

        initChannelNotification();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        appOpenManager = new AppOpenAds(this);


    }


    private void initChannelNotification() {
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Notification", 3));
    }
}
