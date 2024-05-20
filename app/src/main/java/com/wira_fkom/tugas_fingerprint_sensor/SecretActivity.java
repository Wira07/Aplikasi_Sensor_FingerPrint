package com.wira_fkom.tugas_fingerprint_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityScretBinding;
public class SecretActivity extends AppCompatActivity {

    private ActivityScretBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using View Binding
        binding = ActivityScretBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Halaman Absen");
        }
        setContentView(view);

        // Set an OnClickListener on the button using View Binding
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity to go back
                finish();
            }
        });
    }


}

