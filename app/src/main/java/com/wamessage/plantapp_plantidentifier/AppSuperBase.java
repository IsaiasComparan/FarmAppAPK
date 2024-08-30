package com.wamessage.plantapp_plantidentifier;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;


public class AppSuperBase extends AppCompatActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeLanguage(PreferenceManager.getDefaultSharedPreferences(this).getString("lang", "en"));
        hideSystemUI();

    }

    
    @Override
    public void onResume() {
        super.onResume();
    }

    private void changeLanguage(String langCode) {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(langCode);
        resources.updateConfiguration(configuration, displayMetrics);
    }





    protected void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(3842);
    }
}
