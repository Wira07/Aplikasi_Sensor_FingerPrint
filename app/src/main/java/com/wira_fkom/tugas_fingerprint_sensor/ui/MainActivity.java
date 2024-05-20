package com.wira_fkom.tugas_fingerprint_sensor.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.biometric.BiometricPrompt;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.wira_fkom.tugas_fingerprint_sensor.JadwalActivity;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityMainBinding;
import com.wira_fkom.tugas_fingerprint_sensor.map.MapsActivity;
import com.wira_fkom.tugas_fingerprint_sensor.R;
import com.wira_fkom.tugas_fingerprint_sensor.daftar_dosen.RecyclerviewActivity;
import com.wira_fkom.tugas_fingerprint_sensor.SecretActivity;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Halaman Utama");
        }

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Tolong Verifikasi Dulu")
                        .setDescription("Otentikasi Pengguna diperlukan untuk melanjutkan")
                        .setNegativeButtonText("Batalkan")
                        .build();
                getPrompt().authenticate(promptInfo);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerviewActivity.class);
                startActivity(intent);
            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JadwalActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication Berhasil!!");
                Intent intent = new Intent(MainActivity.this, SecretActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Gagal!!");
            }
        };

        return new BiometricPrompt(this, executor, callback);
    }

    private void notifyUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
