package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        TextView textView = findViewById(R.id.employees_info);
        textView.setText("ФИО: Иванов Иван Иванович\nДолжность: Провизор");
    }
}