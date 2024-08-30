package com.wamessage.plantapp_plantidentifier.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.cardview.widget.CardView;

import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.google.android.material.appbar.MaterialToolbar;


public class SettingActivity extends AppSuperBase {
    CardView cardAboutUs;
    CardView cardLanguage;
    CardView cardPolicy;
    CardView cardRateApp;
    CardView cardShareApp;
    MaterialToolbar toolbar;

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        setEvents();
    }

    private void initView() {
        this.toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.cardLanguage = (CardView) findViewById(R.id.cardLanguage);
        this.cardPolicy = (CardView) findViewById(R.id.cardPolicy);
        this.cardShareApp = (CardView) findViewById(R.id.cardShareApp);
        this.cardRateApp = (CardView) findViewById(R.id.cardRateApp);
        this.cardAboutUs = (CardView) findViewById(R.id.cardAboutUs);
    }

    private void setEvents() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m933x6029a6b1(view);
            }
        });
        this.cardLanguage.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m934xed16bdd0(view);
            }
        });
        this.cardPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m935x7a03d4ef(view);
            }
        });
        this.cardShareApp.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m936x6f0ec0e(view);
            }
        });
        this.cardRateApp.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda4
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m937x93de032d(view);
            }
        });
        this.cardAboutUs.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.SettingActivity$$ExternalSyntheticLambda5
            @Override 
            public final void onClick(View view) {
                SettingActivity.this.m938x20cb1a4c(view);
            }
        });
    }

    
    /* renamed from: lambda$setEvents$0$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m933x6029a6b1(View view) {
        finish();
    }

    
    /* renamed from: lambda$setEvents$1$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m934xed16bdd0(View view) {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra("isFromSetting", true);
        startActivity(intent);
    }

    
    /* renamed from: lambda$setEvents$2$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m935x7a03d4ef(View view) {

        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://firebasestorage.googleapis.com/v0/b/exoriumplantrecognition.appspot.com/o/Privacy%20Policy.html?alt=media&token=f6fa6d38-263e-42af-a1d1-65e7241e00f6")));
    }

    
    /* renamed from: lambda$setEvents$3$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m936x6f0ec0e(View view) {
        String packageName = getPackageName();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + packageName);
        intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.app_name)));
    }

    
    /* renamed from: lambda$setEvents$4$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m937x93de032d(View view) {

        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    
    /* renamed from: lambda$setEvents$5$com-exorium-plant_recognition-activities-SettingActivity  reason: not valid java name */
    public  void m938x20cb1a4c(View view) {

        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Exorium.io")));
        } catch (ActivityNotFoundException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    
     @Override
    public void onResume() {

        super.onResume();
    }
}
