package com.wamessage.plantapp_plantidentifier.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.BuildConfig;
import com.wamessage.plantapp_plantidentifier.R;

import com.facebook.shimmer.ShimmerFrameLayout;



public class BoardingActivity extends AppSuperBase implements ViewPager.OnPageChangeListener {

    private ImageView[] dots;
    private CustomPagerAdapter mAdapter;
    private String[] mDesc;
    private String[] mTitle;
    private ViewPager mViewPager;
    private LinearLayout pagerIndicator;


    private final int[] mResources = {R.drawable.ic_board1, R.drawable.ic_board2, R.drawable.ic_board3};
    private int dotsCount = 0;
    private boolean isInitializedBannerAd = false;

    @Override 
    public void onPageScrollStateChanged(int state) {
    }

    @Override 
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_2);



        this.mTitle = new String[]{getString(R.string.title_boarding_1), getString(R.string.title_boarding_2), getString(R.string.title_boarding_3)};
        this.mDesc = new String[]{getString(R.string.content_boarding_1), getString(R.string.content_boarding_2), getString(R.string.content_boarding_3)};
        this.mViewPager = (ViewPager) findViewById(R.id.viewpager);
        this.pagerIndicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(this, this.mResources);
        this.mAdapter = customPagerAdapter;
        this.mViewPager.setAdapter(customPagerAdapter);
        this.mViewPager.setCurrentItem(0);
        setPageViewIndicator();
        this.mViewPager.addOnPageChangeListener(this);
        ((RelativeLayout) findViewById(R.id.actionNextOrFinish)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.BoardingActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                BoardingActivity.this.m894xab614cf3(view);
            }
        });
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-BoardingActivity  reason: not valid java name */
    public  void m894xab614cf3(View view) {
        if (this.mViewPager.getCurrentItem() == 2) {
            startResumeActivity();
            return;
        }
        this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() + 1, true);
    }

    @Override 
    public void onPageSelected(int position) {
        for (int i = 0; i < this.dotsCount; i++) {
            this.dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.not_selected_item_thumb, null));
        }
        this.dots[position].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));

    }

    
    
    public class CustomPagerAdapter extends PagerAdapter {
        private final LayoutInflater mLayoutInflater;
        private final int[] mResources;

        @Override 
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public CustomPagerAdapter(Context context, int[] resources) {
            this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mResources = resources;
        }

        @Override 
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = this.mLayoutInflater.inflate(R.layout.item_introduction, container, false);
            ((TextView) inflate.findViewById(R.id.tvTitle)).setText(BoardingActivity.this.mTitle[position]);
            ((TextView) inflate.findViewById(R.id.tvDesc)).setText(BoardingActivity.this.mDesc[position]);
            ((ImageView) inflate.findViewById(R.id.ivBanner)).setImageResource(this.mResources[position]);
            if (position == 0) {
                ((ImageView) inflate.findViewById(R.id.ivBanner)).setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else if (position == 1 || position == 2) {
                ((ImageView) inflate.findViewById(R.id.ivBanner)).setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            container.addView(inflate);
            return inflate;
        }

        @Override 
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override 
        public int getCount() {
            return this.mResources.length;
        }
    }

    private void setPageViewIndicator() {
        int count = this.mAdapter.getCount();
        this.dotsCount = count;
        this.dots = new ImageView[count];
        for ( int i = 0; i < this.dotsCount; i++) {
            this.dots[i] = new ImageView(this);
            this.dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.not_selected_item_thumb, null));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(12, 0, 12, 0);
            int finalI = i;
            this.dots[i].setOnTouchListener(new View.OnTouchListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.BoardingActivity$$ExternalSyntheticLambda2
                @Override 
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return BoardingActivity.this.m895xe0f58bd2(finalI, view, motionEvent);
                }
            });
            this.pagerIndicator.addView(this.dots[i], layoutParams);
        }
        this.dots[0].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));
    }

    
    /* renamed from: lambda$setPageViewIndicator$1$com-exorium-plant_recognition-activities-BoardingActivity  reason: not valid java name */
    public  boolean m895xe0f58bd2(int i, View view, MotionEvent motionEvent) {
        this.mViewPager.setCurrentItem(i);
        return true;
    }

    @Override 
    protected void onRestart() {
        super.onRestart();

    }

    
    @Override 
    public void onStop() {
        super.onStop();

    }



    private void startResumeActivity() {
        startActivity(new Intent(this, PermissionActivity.class));
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.activities.BoardingActivity$$ExternalSyntheticLambda1
            @Override 
            public final void run() {
                BoardingActivity.this.finish();
            }
        }, 300L);
        super.onBackPressed();
    }
}
