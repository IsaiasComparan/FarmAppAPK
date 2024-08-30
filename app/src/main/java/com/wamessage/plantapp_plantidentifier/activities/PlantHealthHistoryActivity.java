package com.wamessage.plantapp_plantidentifier.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.fragment.HistoryPlantHealthFragment;
import com.wamessage.plantapp_plantidentifier.models.PlantHealth;
import com.google.android.material.appbar.MaterialToolbar;
import java.io.Serializable;
import java.util.List;


public class PlantHealthHistoryActivity extends AppSuperBase {
    MaterialToolbar toolbar_material;

    LinearLayout adContainerView;
    AdView adViewone;
    public void BannerIDCardAds() {
        adContainerView = findViewById(R.id.adsmultyViews);
        adViewone = new AdView(getApplicationContext());
        adViewone.setAdUnitId(getString(R.string.AdMob_Banner));
        adContainerView.addView(adViewone);
        BannerLoad();
        adViewone.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

            }
        });
    }
    private void BannerLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = BannerGetSize();
        adViewone.setAdSize(adSize);
        adViewone.loadAd(adRequest);
    }
    private AdSize BannerGetSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }


    @Override 
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.clear();
    }

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_health_history);
        BannerIDCardAds();
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.toolbar_material = materialToolbar;
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                PlantHealthHistoryActivity.this.m923xd49140a4(view);
            }
        });
        if (AppDatabase.getInstance(this).plantHealthDao().getPlantHealths().isEmpty()) {
            return;
        }
        List<PlantHealth> plantHealths = AppDatabase.getInstance(this).plantHealthDao().getPlantHealths();
        HistoryPlantHealthFragment historyPlantHealthFragment = new HistoryPlantHealthFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("plantHealths", (Serializable) plantHealths);
        historyPlantHealthFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content_frame, historyPlantHealthFragment);
        beginTransaction.commit();
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-PlantHealthHistoryActivity  reason: not valid java name */
    public  void m923xd49140a4(View view) {
        finish();
    }
}
