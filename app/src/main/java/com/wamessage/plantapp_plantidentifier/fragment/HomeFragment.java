package com.wamessage.plantapp_plantidentifier.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.wamessage.plantapp_plantidentifier.BuildConfig;
import com.wamessage.plantapp_plantidentifier.R;

import com.wamessage.plantapp_plantidentifier.activities.Botanic_ExpertActivity;
import com.wamessage.plantapp_plantidentifier.activities.InsectIdentifierOptionActivity;
import com.wamessage.plantapp_plantidentifier.activities.MushroomIdentifierOptionActivity;
import com.wamessage.plantapp_plantidentifier.activities.PlantHealthActivity;
import com.wamessage.plantapp_plantidentifier.activities.PlantHealthOptionActivity;
import com.wamessage.plantapp_plantidentifier.activities.PlantIdentifierOptionActivity;
import com.wamessage.plantapp_plantidentifier.activities.PlantRecognitionActivity;
import com.wamessage.plantapp_plantidentifier.activities.PrivacyPolicyActivity;
import com.wamessage.plantapp_plantidentifier.activities.SettingActivity;
import com.wamessage.plantapp_plantidentifier.activities.SplashActivity;
import com.wamessage.plantapp_plantidentifier.activities.TreeIdentifierOptionActivity;


import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    CardView cardExpert;
    CardView cardInsect;
    CardView cardMushroomIdentify;
    CardView cardPlantHealth;
    CardView cardPlantIdentify;
    CardView cardTreeIdentify;
    private ImageView[] dots;

    TextView privacypolicy;
    private int dotsCount = 0;
    private ViewPager mViewPager;
    private LinearLayout pagerIndicator;
    View rootView;

    Timer t;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        this.rootView = inflate;
        return inflate;
    }

    InterstitialAd mMobInterstitialAds;

    public void InterstitialLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getActivity(), getString(R.string.AdMob_Interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                HomeFragment.this.mMobInterstitialAds = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            }
        });
    }

    private void ShowFunUAds() {
        if (this.mMobInterstitialAds != null) {
            this.mMobInterstitialAds.show(getActivity());
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setEvents();

        Timer timer = new Timer();
        this.t = timer;
        timer.schedule(new AnonymousClass1(), 3000L, 3000L);
    }


    public class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override
        public void run() {
            HomeFragment.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    AnonymousClass1.this.m988x64270517();
                }
            });
        }


        public void m988x64270517() {
            try {
                if (HomeFragment.this.isAdded()) {
                    if (HomeFragment.this.mViewPager.getCurrentItem() != 1) {
                        HomeFragment.this.mViewPager.setCurrentItem(HomeFragment.this.mViewPager.getCurrentItem() + 1, true);
                    } else {
                        HomeFragment.this.mViewPager.setCurrentItem(0, true);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.t.cancel();
    }

    private void initView() {

        this.cardPlantIdentify = (CardView) this.rootView.findViewById(R.id.cardPlantIdentify);
        this.cardPlantHealth = (CardView) this.rootView.findViewById(R.id.cardPlantHealth);
        this.cardExpert = (CardView) this.rootView.findViewById(R.id.cardExpert);
        this.cardInsect = (CardView) this.rootView.findViewById(R.id.cardInsect);
        this.cardTreeIdentify = (CardView) this.rootView.findViewById(R.id.cardTreeIdentify);
        this.cardMushroomIdentify = (CardView) this.rootView.findViewById(R.id.cardMushroom);
        this.privacypolicy = (TextView) this.rootView.findViewById(R.id.privacypolicy);

        this.mViewPager = (ViewPager) this.rootView.findViewById(R.id.viewpager);
        this.pagerIndicator = (LinearLayout) this.rootView.findViewById(R.id.viewPagerCountDots);
    }

    private void setEvents() {

        this.cardPlantIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                HomeFragment.this.m981x4d10d95c(view);
            }
        });
        this.cardPlantHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                startActivity(new Intent(requireContext(), PlantHealthOptionActivity.class));
            }
        });
        this.cardInsect.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                HomeFragment.this.m983x67863c5e(view);
            }
        });
        this.cardExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                HomeFragment.this.m984xf4c0eddf(view);
            }
        });
        this.cardTreeIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                HomeFragment.this.m985x81fb9f60(view);
            }
        });
        this.cardMushroomIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                HomeFragment.this.m986xf3650e1(view);
            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), PrivacyPolicyActivity.class));
            }
        });

    }



    public void m981x4d10d95c(View view) {
        startActivity(new Intent(requireContext(), PlantIdentifierOptionActivity.class));
        ShowFunUAds();
    }


    public void m983x67863c5e(View view) {
        startActivity(new Intent(requireContext(), InsectIdentifierOptionActivity.class));
        ShowFunUAds();
    }


    public void m984xf4c0eddf(View view) {
        startActivity(new Intent(requireContext(), Botanic_ExpertActivity.class));
        ShowFunUAds();
    }


    public void m985x81fb9f60(View view) {
        startActivity(new Intent(requireContext(), TreeIdentifierOptionActivity.class));
        ShowFunUAds();
    }


    public void m986xf3650e1(View view) {
        startActivity(new Intent(requireContext(), MushroomIdentifierOptionActivity.class));
        ShowFunUAds();
    }

    @Override
    public void onStart() {
        super.onStart();
        InterstitialLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        InterstitialLoad();
    }
}
