package com.example.finalproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ManagerProfile extends AppCompatActivity {

    private ListView profileListView;
    private ProfileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager_profile);

        profileListView = findViewById(R.id.profileListView);

        List<profileItem> profileItems = new ArrayList<>();
        profileItems.add(new profileItem("Password", "")); // Empty for password
        profileItems.add(new profileItem("Mobile", "1234-123-9874"));
        profileItems.add(new profileItem("Tell", "1234-123-9874"));
        profileItems.add(new profileItem("Address", "NY- Street 21-no 34"));
        profileItems.add(new profileItem("PostalCode", "9871234567"));

        adapter = new ProfileListAdapter(this, profileItems);
        profileListView.setAdapter(adapter);
    }
}