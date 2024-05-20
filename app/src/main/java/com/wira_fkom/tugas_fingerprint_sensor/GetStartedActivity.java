package com.wira_fkom.tugas_fingerprint_sensor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityGetStartedBinding;
import com.wira_fkom.tugas_fingerprint_sensor.ui.MainActivity;

public class GetStartedActivity extends AppCompatActivity {

    private ActivityGetStartedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Halaman Start");
        }

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStartedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
