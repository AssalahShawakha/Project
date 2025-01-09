package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditEmployeeProfileActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, phoneNumber, salary, position;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_profile);

        // Initialize UI components
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone_number);
        salary = findViewById(R.id.salary);
        position = findViewById(R.id.position);
        //saveButton = findViewById(R.id.save_button); // Ensure this matches the ID from XML

        // Get the employee data passed from the profile activity
        Intent intent = getIntent();
        firstName.setText(intent.getStringExtra("FIRST_NAME"));
        lastName.setText(intent.getStringExtra("LAST_NAME"));
        email.setText(intent.getStringExtra("EMAIL"));
        phoneNumber.setText(intent.getStringExtra("PHONE"));
        salary.setText(intent.getStringExtra("SALARY"));
        position.setText(intent.getStringExtra("POSITION"));

        // Handle save button click to save the changes
        saveButton.setOnClickListener(v -> {
            // Save the updated employee profile
            // Example: saveEmployeeProfile(firstName.getText().toString(), ...);
            finish();  // Close the edit activity
        });
    }
}
