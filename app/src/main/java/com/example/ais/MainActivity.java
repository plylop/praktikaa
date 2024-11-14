package com.example.ais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPharmacy(View view) {
        startActivity(new Intent(this, PharmacyActivity.class));
    }

    public void openMedicines(View view) {
        startActivity(new Intent(this, MedicinesActivity.class));
    }

    public void openEmployees(View view) {
        startActivity(new Intent(this, EmployeesActivity.class));
    }

    public void openClients(View view) {
        startActivity(new Intent(this, ClientsActivity.class));
    }

    public void openSales(View view) {
        startActivity(new Intent(this, SalesActivity.class));
    }

    public void openSaleDetails(View view) {
        startActivity(new Intent(this, SaleDetailsActivity.class));
    }
}
