package com.wira_fkom.tugas_fingerprint_sensor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wira_fkom.tugas_fingerprint_sensor.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About Me");
        }
    }
}