package com.wira_fkom.tugas_fingerprint_sensor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityFormUserPreferenceBinding;

public class FormUserPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFormUserPreferenceBinding binding;

    public static final String EXTRA_TYPE_FORM = "extra_type_form";
    public final static String EXTRA_RESULT = "extra_result";
    public static final int RESULT_CODE = 101;

    public static final int TYPE_ADD = 1;
    public static final int TYPE_EDIT = 2;

    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik";
    private final String FIELD_IS_NOT_VALID = "Email tidak valid";

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormUserPreferenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Halaman Jadwal");
        }

        binding.btnSave.setOnClickListener(this);

        userModel = getIntent().getParcelableExtra("USER");
        int formType = getIntent().getIntExtra(EXTRA_TYPE_FORM, 0);

        String actionBarTitle = "";
        String btnTitle = "";

        switch (formType) {
            case TYPE_ADD:
                actionBarTitle = "Tambah Baru";
                btnTitle = "Simpan";
                break;
            case TYPE_EDIT:
                actionBarTitle = "Ubah";
                btnTitle = "Update";
                showPreferenceInForm();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.btnSave.setText(btnTitle);
    }

    private void showPreferenceInForm() {
        binding.edtName.setText(userModel.getName());
        binding.edtEmail.setText(userModel.getEmail());
        binding.edtAge.setText(String.valueOf(userModel.getAge()));
        binding.edtPhone.setText(userModel.getPhoneNumber());
        if (userModel.isLove()) {
            binding.rbYes.setChecked(true);
        } else {
            binding.rbNo.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            String name = binding.edtName.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();
            String age = binding.edtAge.getText().toString().trim();
            String phoneNo = binding.edtPhone.getText().toString().trim();
            boolean isLoveMU = binding.rgLoveMu.getCheckedRadioButtonId() == R.id.rb_yes;

            if (TextUtils.isEmpty(name)) {
                binding.edtName.setError(FIELD_REQUIRED);
                return;
            }

            if (TextUtils.isEmpty(email)) {
                binding.edtEmail.setError(FIELD_REQUIRED);
                return;
            }

            if (!isValidEmail(email)) {
                binding.edtEmail.setError(FIELD_IS_NOT_VALID);
                return;
            }

            if (TextUtils.isEmpty(age)) {
                binding.edtAge.setError(FIELD_REQUIRED);
                return;
            }

            if (TextUtils.isEmpty(phoneNo)) {
                binding.edtPhone.setError(FIELD_REQUIRED);
                return;
            }

            if (!TextUtils.isDigitsOnly(phoneNo)) {
                binding.edtPhone.setError(FIELD_DIGIT_ONLY);
                return;
            }

            saveUser(name, email, age, phoneNo, isLoveMU);

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT, userModel);
            setResult(RESULT_CODE, resultIntent);

            finish();
        }
    }

    private void saveUser(String name, String email, String age, String phoneNo, boolean isLoveMU) {
        UserPreference userPreference = new UserPreference(this);

        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setAge(Integer.parseInt(age));
        userModel.setPhoneNumber(phoneNo);
        userModel.setLove(isLoveMU);

        userPreference.setUser(userModel);
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
