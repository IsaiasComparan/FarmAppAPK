package com.wamessage.plantapp_plantidentifier.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.BuildConfig;
import com.wamessage.plantapp_plantidentifier.R;

import com.wamessage.plantapp_plantidentifier.utils.ConnectionUtils;
import com.wamessage.plantapp_plantidentifier.utils.PreferencesUtils;
import com.facebook.shimmer.ShimmerFrameLayout;


public class PermissionActivity extends AppSuperBase {
    private static final int READ_CODE_MEDIA_IMAGES = 200;
    private static final int STORAGE_REQUEST_CODE = 400;


    private SwitchCompat switchCompat;



    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        final CardView cardView = (CardView) findViewById(R.id.actionLetStart);


        this.switchCompat = (SwitchCompat) findViewById(R.id.switchWidget);
        cardView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.PermissionActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                PermissionActivity.this.m919x872dd686(view);
            }
        });
        this.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.PermissionActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PermissionActivity.this.m920x41a37707(compoundButton, z);
            }
        });

        new Handler().postDelayed(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.activities.PermissionActivity$$ExternalSyntheticLambda3
            @Override 
            public final void run() {
                cardView.setVisibility(View.VISIBLE);
            }
        }, 3000L);
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-PermissionActivity  reason: not valid java name */
    public  void m919x872dd686(View view) {
        startActivity();
    }

    
    /* renamed from: lambda$onCreate$1$com-exorium-plant_recognition-activities-PermissionActivity  reason: not valid java name */
    public  void m920x41a37707(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.switchCompat.setChecked(false);
            getPermissionStorage();
        }
    }

    public void getPermissionStorage() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (checkSelfPermission("android.permission.READ_MEDIA_IMAGES") != PackageManager.PERMISSION_GRANTED) {
                requestStoragePermission2();
            } else {
                this.switchCompat.setChecked(true);
            }
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission();
        } else {
            this.switchCompat.setChecked(true);
        }
    }

    @Override 
    protected void onRestart() {
        super.onRestart();

    }

    
     @Override
    public void onResume() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (checkSelfPermission("android.permission.READ_MEDIA_IMAGES") == PackageManager.PERMISSION_GRANTED) {
                this.switchCompat.setChecked(true);
            }
        } else if (checkStoragePermission()) {
            this.switchCompat.setChecked(true);
        }
        super.onResume();
    }

    public void requestStoragePermission() {
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 400);
    }

    public void requestStoragePermission2() {
        requestPermissions(new String[]{"android.permission.READ_MEDIA_IMAGES"}, 200);
    }

    public boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }





    @Override 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 || requestCode == 400) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                this.switchCompat.setChecked(true);
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startActivity() {
        PreferencesUtils.getInstance(this).setBoolean("first_time", false);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.activities.PermissionActivity$$ExternalSyntheticLambda4
            @Override 
            public final void run() {
                PermissionActivity.this.finish();
            }
        }, 300L);
        super.onBackPressed();
    }
}
