package com.example.finalproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class managerDashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        RecyclerView RecycleView = findViewById(R.id.RecycleView);
        RecycleView.setLayoutManager(new LinearLayoutManager(this));

        // Sample Data
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Team Meeting", "Group discussion for the new product", "10:00 AM", 48));
        taskList.add(new Task("UI Design", "Make a homepage for the app", "11:00 AM", 30));

        TaskAdapter adapter = new TaskAdapter(taskList);
        RecycleView.setAdapter(adapter);
    }
}