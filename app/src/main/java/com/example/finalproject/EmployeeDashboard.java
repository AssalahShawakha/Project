package com.example.finalproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class EmployeeDashboard extends AppCompatActivity {

    private TextView tvCompletedTasks, tvProgressRate, tvRemainingTime;
    private ListView lvTasks, lvNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        tvCompletedTasks = findViewById(R.id.tvCompletedTasks);
        tvProgressRate = findViewById(R.id.tvProgressRate);
        tvRemainingTime = findViewById(R.id.tvRemainingTime);
        lvTasks = findViewById(R.id.lvTasks);
        lvNotifications = findViewById(R.id.lvNotifications);

        loadTasks();
        loadNotifications();
    }

    private void loadTasks() {
        String url = "http://192.168.1.106/mobile/tasks.php?action=getTasks&employee_id=1";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parse JSON and update UI
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }

    private void loadNotifications() {
        String url = "http://192.168.1.106/mobile/tasks.php?action=getNotifications&employee_id=1";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parse JSON and update UI
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
}
