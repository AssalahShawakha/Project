package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private Switch notificationsSwitch;
    private TextView languageOption;
    private TextView accountOption;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Find the views by their IDs
        notificationsSwitch = findViewById(R.id.notifications_switch);
        languageOption = findViewById(R.id.language_option);
        accountOption = findViewById(R.id.account_option);
        backButton = findViewById(R.id.back_button);

        // Set click listeners for the views
        languageOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the language settings page
                openLanguageSettings();
            }
        });

        accountOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the account management page
                openAccountManagement();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                onBackPressed();
            }
        });

        // Set listeners for the switch
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle notification toggle
            if (isChecked) {
                // Enable notifications
                enableNotifications();
            } else {
                // Disable notifications
                disableNotifications();
            }
        });
    }

    // Method to handle the language settings
    private void openLanguageSettings() {
        Intent intent = new Intent(SettingActivity.this, LanguageSettingsActivity.class);
        startActivity(intent);
    }

    // Method to handle account management
    private void openAccountManagement() {
        Intent intent = new Intent(SettingActivity.this, AccountManagementActivity.class);
        startActivity(intent);
    }

    // Method to enable notifications
    private void enableNotifications() {
        // Implement logic to enable notifications
    }

    // Method to disable notifications
    private void disableNotifications() {
        // Implement logic to disable notifications
    }
}
