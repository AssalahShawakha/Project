package com.example.finalproject;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date date;
    private String status;

    Task(String name, String description, Date date, String status){
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
