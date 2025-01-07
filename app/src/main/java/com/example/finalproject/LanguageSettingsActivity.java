package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageSettingsActivity extends AppCompatActivity {

    private Button englishButton;
    private Button arabicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);

        // تعريف الأزرار
        englishButton = findViewById(R.id.english_button);
        arabicButton = findViewById(R.id.arabic_button);

        // تعيين مستمع للأزرار
        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en"); // تعيين اللغة الإنجليزية
            }
        });

        arabicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("ar"); // تعيين اللغة العربية
            }
        });
    }

    // دالة لتغيير اللغة
    private void setLanguage(String languageCode) {
        // استخدام MyApplication لتغيير اللغة
        MyApplication app = (MyApplication) getApplication();
        app.setAppLanguage(languageCode);

        // إظهار رسالة للمستخدم
        Toast.makeText(this, "Language changed to " + (languageCode.equals("en") ? "English" : "Arabic"), Toast.LENGTH_SHORT).show();

        // إعادة تشغيل النشاط لتطبيق اللغة الجديدة
        Intent intent = new Intent(LanguageSettingsActivity.this, SettingActivity.class);
        startActivity(intent);
        finish(); // إغلاق النشاط الحالي
    }
}
