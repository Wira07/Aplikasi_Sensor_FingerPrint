package com.wira_fkom.tugas_fingerprint_sensor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityJadwalBinding;

public class JadwalActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityJadwalBinding binding;
    private UserPreference mUserPreference;
    private boolean isPreferenceEmpty = false;
    private UserModel userModel;

    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null && result.getResultCode() == FormUserPreferenceActivity.RESULT_CODE) {
                    userModel = result.getData().getParcelableExtra(FormUserPreferenceActivity.EXTRA_RESULT);
                    populateView(userModel);
                    checkForm(userModel);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJadwalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Jadwal Mengajar");
        }

        mUserPreference = new UserPreference(this);
        showExistingPreference();
        binding.btnSave.setOnClickListener(this);
    }

    private void showExistingPreference() {
        userModel = mUserPreference.getUser();
        populateView(userModel);
        checkForm(userModel);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            Intent intent = new Intent(JadwalActivity.this, FormUserPreferenceActivity.class);
            if (isPreferenceEmpty) {
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_ADD);
            } else {
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_EDIT);
            }
            intent.putExtra("USER", userModel);
            resultLauncher.launch(intent);
        }
    }

    private void checkForm(UserModel userModel) {
        if (!userModel.getName().isEmpty()) {
            binding.btnSave.setText(getString(R.string.change));
            isPreferenceEmpty = false;
        } else {
            binding.btnSave.setText(getString(R.string.save));
            isPreferenceEmpty = true;
        }
    }

    private void populateView(UserModel userModel) {
        binding.tvName.setText(userModel.getName().isEmpty() ? "Tidak Ada" : userModel.getName());
        binding.tvAge.setText(String.valueOf(userModel.getAge()).isEmpty() ? "Tidak Ada" : String.valueOf(userModel.getAge()));
        binding.tvIsLoveMu.setText(userModel.isLove() ? "Ya" : "Tidak");
        binding.tvEmail.setText(userModel.getEmail().isEmpty() ? "Tidak Ada" : userModel.getEmail());
        binding.tvPhone.setText(userModel.getPhoneNumber().isEmpty() ? "Tidak Ada" : userModel.getPhoneNumber());
    }
}
