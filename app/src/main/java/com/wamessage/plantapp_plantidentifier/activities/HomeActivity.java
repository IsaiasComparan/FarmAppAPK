package com.wamessage.plantapp_plantidentifier.activities;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.databinding.ActivityHomeBinding;
import com.wamessage.plantapp_plantidentifier.fragment.HomeFragment;
import com.wamessage.plantapp_plantidentifier.fragment.HomeReminderFragment;
import com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.lang.reflect.Field;


public class HomeActivity extends AppSuperBase {
    ActivityHomeBinding binding;
    ImageView homeBtn;
    TextView homeTxt;
    ImageView reminderBtn;
    TextView reminderTxt;
    boolean doubleBackToExitPressedOnce = false;


    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding inflate = ActivityHomeBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        initView();
        initEvent();

        try {
            Field declaredField = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            declaredField.setAccessible(true);
            declaredField.set(null, 104857600);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setFragment(new HomeFragment());
    }



    private void initEvent() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                HomeActivity.this.m904xbddb9f6b(view);
            }
        });
        this.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                HomeActivity.this.m905xe72ff4ac(view);
            }
        });
        this.reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                HomeActivity.this.m906x108449ed(view);
            }
        });
    }


    public  void m904xbddb9f6b(View view) {
        startActivity(new Intent(this, PlantRecognitionActivity.class));
    }


    public  void m905xe72ff4ac(View view) {
        this.homeBtn.setImageResource(R.drawable.ic_home_on);
        this.homeTxt.setTextColor(Color.parseColor("#54A871"));
        this.reminderBtn.setImageResource(R.drawable.ic_bell_off);
        this.reminderTxt.setTextColor(Color.parseColor("#D2D9CE"));
        setFragment(new HomeFragment());
    }


    public  void m906x108449ed(View view) {
        this.homeBtn.setImageResource(R.drawable.ic_home_off);
        this.homeTxt.setTextColor(Color.parseColor("#D2D9CE"));
        this.reminderBtn.setImageResource(R.drawable.ic_bell_on);
        this.reminderTxt.setTextColor(Color.parseColor("#54A871"));
        setFragment(new HomeReminderFragment());
    }

    private void initView() {


        this.homeBtn = (ImageView) findViewById(R.id.nav_home);
        this.homeTxt = (TextView) findViewById(R.id.txt_home);
        this.reminderBtn = (ImageView) findViewById(R.id.nav_reminder);
        this.reminderTxt = (TextView) findViewById(R.id.txt_reminder);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("type", "plant");
        fragment.setArguments(bundle);
        beginTransaction.replace(R.id.content_frame_watering_reminder, fragment);
        beginTransaction.addToBackStack(WateringReminderFragment.TAG);
        beginTransaction.commit();
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "double tap to exit!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

    }
}
