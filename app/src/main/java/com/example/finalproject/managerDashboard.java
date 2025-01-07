package com.example.finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class managerDashboard extends AppCompatActivity {

    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private TextView taskNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        // ربط RecyclerView
        RecyclerView recyclerView = findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ربط TextView لعدد المهام
        taskNumber = findViewById(R.id.TaskNumber);

        // إعداد المحول
        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        // استدعاء البيانات
        new FetchTasks().execute();
    }

    private class FetchTasks extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // استخدم عنوان IP بدلاً من localhost
                URL url = new URL("http://192.168.1.106/mobile/getTasks.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    taskList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject taskObject = jsonArray.getJSONObject(i);
                        String title = taskObject.getString("TaskTitle");
                        String description = taskObject.getString("TaskDescription");
                        String time = taskObject.getString("TaskTime");
                        int progress = taskObject.getInt("TaskProgress");

                        taskList.add(new Task(title, description, time, progress));
                    }

                    // تحديث عدد المهام
                    taskNumber.setText("Total Tasks: " + taskList.size());

                    // تحديث المحول
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
