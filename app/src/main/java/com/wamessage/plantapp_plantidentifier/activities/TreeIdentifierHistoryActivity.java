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
import com.wamessage.plantapp_plantidentifier.fragment.HistoryTreeFragment;
import com.wamessage.plantapp_plantidentifier.models.Tree;
import com.google.android.material.appbar.MaterialToolbar;
import java.io.Serializable;
import java.util.List;


public class TreeIdentifierHistoryActivity extends AppSuperBase {
    MaterialToolbar toolbar_material;

    
    @Override 
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.clear();
    }

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
        setContentView(R.layout.activity_tree_identifier_history);
        BannerIDCardAds();
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.toolbar_material = materialToolbar;
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.TreeIdentifierHistoryActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                TreeIdentifierHistoryActivity.this.m944xf6a6e854(view);
            }
        });
        if (AppDatabase.getInstance(this).treeDao().getTrees().isEmpty()) {
            return;
        }
        List<Tree> trees = AppDatabase.getInstance(this).treeDao().getTrees();
        HistoryTreeFragment historyTreeFragment = new HistoryTreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("trees", (Serializable) trees);
        historyTreeFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content_frame, historyTreeFragment);
        beginTransaction.commit();
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-TreeIdentifierHistoryActivity  reason: not valid java name */
    public  void m944xf6a6e854(View view) {
        finish();
    }
}
