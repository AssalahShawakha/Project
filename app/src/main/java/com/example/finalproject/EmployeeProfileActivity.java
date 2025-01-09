package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeProfileActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, phoneNumber, salary, position;
    private TextView profileImageText;
    private ImageView profileImage;
    private Button editButton;
    private int employeeId = 1;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://192.168.1.106/project";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        // Initialize UI components
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone_number);
        salary = findViewById(R.id.salary);
        position = findViewById(R.id.position);
        profileImageText = findViewById(R.id.profile_image_text);
        profileImage = findViewById(R.id.profile_image);
        editButton = findViewById(R.id.edit_button);

        // Fetch employee data from the database
        fetchEmployeeData(employeeId);

        // Set up the Edit button click listener
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Allow editing employee details
                updateEmployeeData(employeeId);
            }
        });
    }

    private void fetchEmployeeData(int employeeId) {
        // Database connection in a separate thread (because it's a network operation)
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                ResultSet rs = null;
                try {
                    conn = DriverManager.getConnection(DB_URL);

                    // Query to get employee details
                    String query = "SELECT first_name, last_name, email, phone_number, salary, position " +
                            "FROM employees WHERE employee_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, employeeId);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Set values to the UI components
                        ResultSet finalRs = rs;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    firstName.setText(finalRs.getString("first_name"));
                                    lastName.setText(finalRs.getString("last_name"));
                                    email.setText(finalRs.getString("email"));
                                    phoneNumber.setText(finalRs.getString("phone_number"));
                                    salary.setText(String.valueOf(finalRs.getDouble("salary")));
                                    position.setText(finalRs.getString("position"));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    Toast.makeText(EmployeeProfileActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EmployeeProfileActivity.this, "No data found for this employee", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EmployeeProfileActivity.this, "Error fetching profile data", Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {
                    // Close resources
                    try {
                        if (rs != null) rs.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void updateEmployeeData(int employeeId) {
        // Get updated values from the EditText fields
        String updatedFirstName = firstName.getText().toString();
        String updatedLastName = lastName.getText().toString();
        String updatedEmail = email.getText().toString();
        String updatedPhoneNumber = phoneNumber.getText().toString();
        String updatedSalary = salary.getText().toString();
        String updatedPosition = position.getText().toString();

        // Database connection for update operation
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DB_URL);

                    // SQL update query
                    String updateQuery = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, " +
                            "phone_number = ?, salary = ?, position = ? WHERE employee_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(updateQuery);

                    // Set the parameters for the query
                    stmt.setString(1, updatedFirstName);
                    stmt.setString(2, updatedLastName);
                    stmt.setString(3, updatedEmail);
                    stmt.setString(4, updatedPhoneNumber);
                    stmt.setDouble(5, Double.parseDouble(updatedSalary));
                    stmt.setString(6, updatedPosition);
                    stmt.setInt(7, employeeId);

                    // Execute the update
                    int rowsAffected = stmt.executeUpdate();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (rowsAffected > 0) {
                                Toast.makeText(EmployeeProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EmployeeProfileActivity.this, "No changes made", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (SQLException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EmployeeProfileActivity.this, "Error updating profile", Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {
                    // Close the connection
                    try {
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
