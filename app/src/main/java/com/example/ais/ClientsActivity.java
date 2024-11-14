package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ClientsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        TextView textView = findViewById(R.id.clients_info);
        textView.setText("ФИО: Петрова Анна Сергеевна\nТелефон: +7 (123) 456-78-91\nEmail: petrova@example.com");
    }
}
