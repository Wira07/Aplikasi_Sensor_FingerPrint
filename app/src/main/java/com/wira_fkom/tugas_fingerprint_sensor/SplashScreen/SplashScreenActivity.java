package com.wira_fkom.tugas_fingerprint_sensor.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wira_fkom.tugas_fingerprint_sensor.GetStartedActivity;
import com.wira_fkom.tugas_fingerprint_sensor.R;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivitySplashScreenBinding;


public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    private static final long SPLASH_TIMEOUT = 3000;
    private Animation topAnim;
    private Animation bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SplashScreen");
        }

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        binding.gambar.startAnimation(topAnim);
        binding.textView2.startAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, GetStartedActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}