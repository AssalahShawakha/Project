package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class adminDashboard extends AppCompatActivity {

    private TextView adminNameTextView, employeesCountTextView, projectsCountTextView, reportsCountTextView;
    private ImageView adminLogoImageView,settings,projects;
    private Button addManagerButton, addEmployeeButton, addProjectButton, viewReportsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // ربط عناصر الواجهة
        adminNameTextView = findViewById(R.id.admin_title);
        adminLogoImageView = findViewById(R.id.admin_logo);
        employeesCountTextView = findViewById(R.id.employees_count);
        projectsCountTextView = findViewById(R.id.projects_count);
        reportsCountTextView = findViewById(R.id.reports_count);

        addManagerButton = findViewById(R.id.add_manager_button);
        addEmployeeButton = findViewById(R.id.add_employee_button);
        addProjectButton = findViewById(R.id.add_project_button);
        viewReportsButton = findViewById(R.id.view_reports_button);
        settings = findViewById(R.id.setting);
        projects = findViewById(R.id.projects);

        // استلام البيانات من LoginActivity
        Intent intent = getIntent();
        String adminName = intent.getStringExtra("name");
        String adminImage = intent.getStringExtra("profile_image");

        // تحديث واجهة المستخدم بالاسم والصورة
        adminNameTextView.setText(adminName);

        // جلب بيانات لوحة التحكم
        fetchDashboardData();

        // إضافة التنقل عند الضغط على الأزرار
        addManagerButton.setOnClickListener(v -> {
            Intent addManagerIntent = new Intent(adminDashboard.this, addManager.class);
            startActivity(addManagerIntent);
        });

        addEmployeeButton.setOnClickListener(v -> {
            Intent addEmployeeIntent = new Intent(adminDashboard.this, AddEmployeeActivity.class);
            startActivity(addEmployeeIntent);
        });
        projects.setOnClickListener(v->{
            Intent showproj = new Intent(adminDashboard.this, ProjectsActivity.class);
            startActivity(showproj);
        });
//
//        addProjectButton.setOnClickListener(v -> {
//            Intent addProjectIntent = new Intent(adminDashboard.this, AddProjectActivity.class);
//            startActivity(addProjectIntent);
//        });
//
//        viewReportsButton.setOnClickListener(v -> {
//            Intent viewReportsIntent = new Intent(adminDashboard.this, ReportsActivity.class);
//            startActivity(viewReportsIntent);
//        });

        settings.setOnClickListener(v -> {
            try {
                Intent settingsIntent = new Intent(adminDashboard.this, SettingActivity.class);
                startActivity(settingsIntent);
            } catch (Exception e) {
                Toast.makeText(adminDashboard.this, "Error starting settings: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        adminLogoImageView.setOnClickListener(v -> {
            Intent pofile = new Intent(adminDashboard.this, ManagerProfile.class);
            startActivity(pofile);
        });

    }

    private void fetchDashboardData() {
        // رابط API الذي يعيد البيانات
        String url = "http://192.168.1.106/mobile/admin.php";

        // إنشاء طلب JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // استخراج البيانات من JSON
                            int totalEmployees = response.getInt("total_employees");
                            int totalProjects = response.getInt("total_projects");
                            int totalReports = response.getInt("total_reports");

                            // تحديث واجهة المستخدم
                            employeesCountTextView.setText(String.valueOf(totalEmployees));
                            projectsCountTextView.setText(String.valueOf(totalProjects));
                            reportsCountTextView.setText(String.valueOf(totalReports));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(adminDashboard.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // معالجة الأخطاء
                        Toast.makeText(adminDashboard.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // إضافة الطلب إلى قائمة الطلبات
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
