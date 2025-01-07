package com.example.finalproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import java.util.Locale;

public class MyApplication extends Application {

    private static final String PREFS_NAME = "app_settings";
    private static final String LANGUAGE_KEY = "language_key";

    @Override
    public void onCreate() {
        super.onCreate();

        // استرجاع اللغة المخزنة
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String language = prefs.getString(LANGUAGE_KEY, "en"); // القيمة الافتراضية هي الإنجليزية

        // تعيين اللغة
        setAppLanguage(language);
    }

    public void setAppLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // تحديث إعدادات التطبيق
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // حفظ اللغة في SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LANGUAGE_KEY, languageCode);
        editor.apply();
    }
}
