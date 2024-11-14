package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SalesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        TextView textView = findViewById(R.id.sales_info);
        textView.setText("Дата: 2023-10-23\nСумма: 500 руб.");
    }
}