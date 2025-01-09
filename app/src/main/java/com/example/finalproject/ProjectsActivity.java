package com.example.finalproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView projectsRecyclerView;
    private ProjectsAdapter projectsAdapter;
    private List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        // ربط RecyclerView
        projectsRecyclerView = findViewById(R.id.projects_recycler_view);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // إنشاء قائمة المشاريع
        projectList = new ArrayList<>();
        projectsAdapter = new ProjectsAdapter(projectList);
        projectsRecyclerView.setAdapter(projectsAdapter);

        // جلب المشاريع من قاعدة البيانات
        fetchProjectsFromDatabase();
    }

    private void fetchProjectsFromDatabase() {
        String url = "http://192.168.1.106/mobile/projects.php"; // رابط API الذي يعيد المشاريع

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            projectList.clear(); // تنظيف القائمة قبل الإضافة
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject projectObject = response.getJSONObject(i);

                                // جلب البيانات من JSON
                                String name = projectObject.getString("name");
                                String description = projectObject.getString("description");
                                String startDate = projectObject.getString("start_date");
                                String endDate = projectObject.getString("end_date");
                                String imageUrl = projectObject.getString("image");

                                // أضف المشروع إلى القائمة
                                projectList.add(new Project(name, description, startDate, endDate, imageUrl));
                            }
                            // تحديث RecyclerView
                            projectsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProjectsActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProjectsActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // إضافة الطلب إلى قائمة الطلبات
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
