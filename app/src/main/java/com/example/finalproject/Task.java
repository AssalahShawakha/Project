package com.example.finalproject;

import java.util.Date;
public class Task {
    private final String title;
    private final String description;
    private final String time;
    private final int progress;

    public Task(String title, String description, String time, int progress) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public int getProgress() {
        return progress;
    }
}
