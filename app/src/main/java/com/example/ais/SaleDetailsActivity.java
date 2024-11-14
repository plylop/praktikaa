package com.example.ais;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SaleDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_details);

        TextView textView = findViewById(R.id.sale_details_info);
        textView.setText("ID продажи: 1\nID лекарства: 1001\nКоличество: 2");
    }
}