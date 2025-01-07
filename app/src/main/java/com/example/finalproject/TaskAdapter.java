package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the task_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Get the task object for the current position
        Task task = taskList.get(position);

        // Bind data to the ViewHolder
        holder.taskTitle.setText(task.getTitle());

        // Format deadline display, assuming it's a String
        holder.taskDeadline.setText("Deadline: " + task.getTime()); // You can add more date formatting if needed

        // Display task progress
        holder.taskProgress.setText("Progress: " + task.getProgress() + "%");
    }

    @Override
    public int getItemCount() {
        // Return the total number of tasks in the list
        return taskList.size();
    }

    // ViewHolder class to hold references to the views
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle, taskDeadline, taskProgress;

        public TaskViewHolder(View itemView) {
            super(itemView);
            // Initialize the views
            taskTitle = itemView.findViewById(R.id.task_title);
            taskDeadline = itemView.findViewById(R.id.task_deadline);
            taskProgress = itemView.findViewById(R.id.task_progress);
        }
    }
}
