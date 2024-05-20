package com.wira_fkom.tugas_fingerprint_sensor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import com.bumptech.glide.Glide;
import com.wira_fkom.tugas_fingerprint_sensor.R;
import com.wira_fkom.tugas_fingerprint_sensor.data.Data;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityDeskripsiBinding;

public class DeskripsiActivity extends AppCompatActivity {

    private Animation topAnim;
    private Animation bottomAnim;
    public static final String DATA_ANGKRINGAN = "extra_data";
    private ActivityDeskripsiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeskripsiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        binding.imgItemPhoto.startAnimation(topAnim);
        binding.tvItemName.startAnimation(bottomAnim);
        binding.tvItemDescription.startAnimation(bottomAnim);
        binding.btnShare.startAnimation(bottomAnim);


        Data data = getIntent().getParcelableExtra(DATA_ANGKRINGAN);

        if (data != null) {
            // Use Glide to load the image
            Glide.with(this)
                    .load(data.getPhoto())
                    .into(binding.imgItemPhoto);

            binding.tvItemDescription.setText(data.getDescription());
            binding.tvItemName.setText(data.getName());

            binding.btnShare.setOnClickListener(view -> shareProduct(data.getName(), data.getDescription()));
        }
    }
    private void shareProduct(String productName, String productDescription) {
        // Create an Intent to share text
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this product: " + productName);
        shareIntent.putExtra(Intent.EXTRA_TEXT, productDescription);
        // Start the activity to show the share options
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}