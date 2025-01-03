package com.example.finalproject;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeActivity extends AppCompatActivity {

    private TextView tvCompletedTasks, tvProgressRate, tvRemainingTime;
    private ListView lvTasks, lvNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        // Bind XML elements to Java objects
        tvCompletedTasks = findViewById(R.id.tvCompletedTasks);
        tvProgressRate = findViewById(R.id.tvProgressRate);
        tvRemainingTime = findViewById(R.id.tvRemainingTime);
        lvTasks = findViewById(R.id.lvTasks);
        lvNotifications = findViewById(R.id.lvNotifications);

        // Load data from the database
        loadAssignedTasks();
        loadNotifications();
        loadPersonalStats();
    }

    private void loadAssignedTasks() {
        // Retrieve data from the database
        ArrayList<HashMap<String, String>> tasks = new ArrayList<>();

        HashMap<String, String> task1 = new HashMap<>();
        task1.put("name", "Prepare the monthly report");
        task1.put("deadline", "2024-12-31");
        task1.put("status", "Pending");
        tasks.add(task1);

        // Set up the task list using an adapter
        // TaskAdapter adapter = new TaskAdapter(this, tasks);
        //   lvTasks.setAdapter(adapter);
    }

    private void loadNotifications() {
        // Retrieve notifications from the database
        ArrayList<String> notifications = new ArrayList<>();
        notifications.add("A new task has been assigned.");
        notifications.add("Reminder: The financial report deadline is tomorrow.");

        // NotificationAdapter adapter = new NotificationAdapter(this, notifications);
        // lvNotifications.setAdapter(adapter);
    }

    private void loadPersonalStats() {
        // Retrieve statistics from the database
        int completedTasks = 10;
        int progressRate = 80;
        int remainingDays = 5;

        tvCompletedTasks.setText("Completed Tasks: " + completedTasks);
        tvProgressRate.setText("Progress Rate: " + progressRate + "%");
        tvRemainingTime.setText("Remaining Time for Open Tasks: " + remainingDays + " days");
    }
}
