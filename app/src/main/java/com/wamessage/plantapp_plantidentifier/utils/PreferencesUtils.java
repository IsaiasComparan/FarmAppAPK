package com.wamessage.plantapp_plantidentifier.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesUtils {
    private static PreferencesUtils instance;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    public String getString(String key, String defValue) {
        return this.sharedPreferences.getString(key, defValue);
    }

    public PreferencesUtils setString(String key, String value) {
        this.editor.putString(key, value);
        this.editor.apply();
        return this;
    }

    public PreferencesUtils setStatus(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return this.sharedPreferences.getInt(key, defValue);
    }

    public PreferencesUtils setInt(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.apply();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.sharedPreferences.getBoolean(key, defValue);
    }

    public void setBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.apply();
    }

    public PreferencesUtils setLong(String key, long value) {
        this.editor.putLong(key, value);
        this.editor.apply();
        return this;
    }

    public long getLong(String key, long defValue) {
        return this.sharedPreferences.getLong(key, defValue);
    }

    public void clearData() {
        this.editor.clear();
        this.editor.apply();
    }

    private PreferencesUtils(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preference", 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public static PreferencesUtils getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesUtils(context);
        }
        return instance;
    }
}
