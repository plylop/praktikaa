package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MedicinesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        TextView textView = findViewById(R.id.medicines_info);
        textView.setText("Лекарство: Парацетамол\nЦена: 50 руб.\nКоличество: 100");
    }
}