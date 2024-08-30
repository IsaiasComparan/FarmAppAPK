package com.wamessage.plantapp_plantidentifier.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.LanguageAdapter;
import com.wamessage.plantapp_plantidentifier.models.Language;

import com.wamessage.plantapp_plantidentifier.utils.ConnectionUtils;
import com.wamessage.plantapp_plantidentifier.utils.PreferencesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class LanguageActivity extends AppSuperBase implements LanguageAdapter.OnLanguageSelectedListener {
    static boolean isDuplicatedScreen = false;
    private TextView actionNext;
    private LanguageAdapter adapter;


    boolean isFromSetting;
    private List<Language> languageList;

    private TextView tvToolbar;

    
    /* JADX WARN: Code restructure failed: missing block: B:11:0x008f, code lost:
        if (r7 == (-1)) goto L14;
     */
    @Override 
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        int i = 0;
        isDuplicatedScreen = getIntent().getBooleanExtra("isDuplicatedScreen", false);
        this.isFromSetting = getIntent().getBooleanExtra("isFromSetting", false);
        int intExtra = getIntent().getIntExtra("selectedLang", 0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.language_list);
        this.actionNext = (TextView) findViewById(R.id.lang_confirm);
        this.tvToolbar = (TextView) findViewById(R.id.tv_toolbar);


        initLanguageList();

        i = intExtra;
        LanguageAdapter languageAdapter = new LanguageAdapter(this.languageList, i, this);
        this.adapter = languageAdapter;
        recyclerView.setAdapter(languageAdapter);
        changeScreenUI(i);
        this.actionNext.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.LanguageActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                LanguageActivity.this.m913xced18d0f(view);
            }
        });

    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-LanguageActivity  reason: not valid java name */
    public  void m913xced18d0f(View view) {
        Intent intent;
        changeLanguage(this.adapter.getSelectedLang());
        if (PreferencesUtils.getInstance(this).getBoolean("first_time", true)) {
            intent = new Intent(this, BoardingActivity.class);
        } else {
            intent = new Intent(this, SettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        }
        startActivity(intent);
        finish();
    }

    private void initLanguageList() {
        ArrayList arrayList = new ArrayList();
        this.languageList = arrayList;
        arrayList.add(new Language("en", "English", getString(R.string.continue_), "Language", R.drawable.en));
        this.languageList.add(new Language("es", "Espanol", getString(R.string.continue_), "Idioma", R.drawable.es));
        this.languageList.add(new Language("pt", "Portuguese", getString(R.string.continue_), "Idioma", R.drawable.pt));
        this.languageList.add(new Language("hi", "हिन्दी", getString(R.string.continue_), "भाषा", R.drawable.hi));
        this.languageList.add(new Language("ko", "한국인", getString(R.string.continue_), "언어", R.drawable.kr));
        this.languageList.add(new Language("in", "Indonesia", getString(R.string.continue_), "Indonesia", R.drawable.id));
    }

    private void changeLanguage(int langPos) {
        String id = this.languageList.get(langPos).getId();
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(id);
        resources.updateConfiguration(configuration, displayMetrics);
        saveToLocal(id);
    }

    private void saveToLocal(String langCode) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putString("lang", langCode);
        edit.apply();
    }

    @Override // com.wamessage.plantapp_plantidentifier.adapter.LanguageAdapter.OnLanguageSelectedListener
    public void onLanguageSelected(int position) {
        changeScreenUI(position);
    }

    private void changeScreenUI(int position) {
        changeLanguage(position);
        String languageName = this.languageList.get(position).getLanguageName();
        String go = this.languageList.get(position).getGo();
        this.tvToolbar.setText(languageName);
        this.actionNext.setText(go);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.activities.LanguageActivity$$ExternalSyntheticLambda1
            @Override 
            public final void run() {
                LanguageActivity.this.finish();
            }
        }, 300L);
        super.onBackPressed();
    }
}
