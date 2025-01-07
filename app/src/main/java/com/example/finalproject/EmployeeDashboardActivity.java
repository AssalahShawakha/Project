package com.example.finalproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);  // Your layout file

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set LayoutManager

        // Fetch tasks from the database or use mock data
        fetchTasksFromDatabase();
    }

    private void fetchTasksFromDatabase() {
        // For now, we will use mock data (you will replace this with actual database fetching logic)
        taskList = new ArrayList<>();
        taskList.add(new Task("UI Design", "Make a homepage", "2025-01-03", 48));
        taskList.add(new Task("Develop Profile Page", "Create profile page for users", "2025-02-08", 45));

        // Initialize the adapter with the task list
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);  // Set adapter to the RecyclerView
    }
}
