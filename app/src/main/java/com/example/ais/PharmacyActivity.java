package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PharmacyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);

        TextView textView = findViewById(R.id.pharmacy_info);
        textView.setText("Название: Аптека 1\nАдрес: ул. Примерная, 1\nТелефон: +7 (123) 456-78-90");
    }
}
