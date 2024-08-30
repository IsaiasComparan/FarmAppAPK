package com.wamessage.plantapp_plantidentifier.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.ExpertAdapter;
import com.wamessage.plantapp_plantidentifier.models.Expert;

import com.facebook.shimmer.ShimmerFrameLayout;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;


public class Botanic_ExpertActivity extends AppSuperBase {


    ExpertAdapter expertAdapter;
    List<Expert> expertList = new ArrayList();
    private boolean isInitializedBannerAd = false;
    private boolean isInitializedNativeAd = false;
    RecyclerView recyclerView;


    MaterialToolbar toolbar;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botanic_expert);
        BannerIDCardAds();


        this.toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                Botanic_ExpertActivity.this.m896xe6fc4012(view);
            }
        });

        initData();
        setRecyclerView();
    }


    public void m896xe6fc4012(View view) {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        this.expertAdapter.notifyListeners();
    }


    @Override
    public void onPause() {
        super.onPause();

    }


    private void initData() {
        this.expertList.add(new Expert(getResources().getString(R.string.expert_title_1), getResources().getString(R.string.expert_content_1)));

        this.expertList.add(new Expert(getResources().getString(R.string.expert_title_2), getResources().getString(R.string.expert_content_2)));
        this.expertList.add(new Expert(getResources().getString(R.string.expert_title_3), getResources().getString(R.string.expert_content_3)));
    }

    private void setRecyclerView() {
        this.expertAdapter = new ExpertAdapter(this, this.expertList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.expertAdapter);
    }
}
