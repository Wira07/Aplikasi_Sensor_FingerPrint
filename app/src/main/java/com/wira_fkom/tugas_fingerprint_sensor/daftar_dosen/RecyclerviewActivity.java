package com.wira_fkom.tugas_fingerprint_sensor.daftar_dosen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import com.wira_fkom.tugas_fingerprint_sensor.R;
import com.wira_fkom.tugas_fingerprint_sensor.adapter.Adapter;
import com.wira_fkom.tugas_fingerprint_sensor.data.Data;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ActivityRecyclerviewBinding;
import com.wira_fkom.tugas_fingerprint_sensor.ui.AboutActivity;
import com.wira_fkom.tugas_fingerprint_sensor.ui.DeskripsiActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecyclerviewActivity extends AppCompatActivity implements View.OnClickListener {

    private Animation topAnim;
    private RecyclerView itemKu;
    private ArrayList<Data> list = new ArrayList<>();
    private ActivityRecyclerviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemKu = binding.itemKu;
        itemKu.setHasFixedSize(true);
        list.addAll(getListDokter());
        showRecyclerList();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pejabat Struktural Fkom");
        }

        topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.top_animation);
        binding.itemKu.startAnimation(topAnim);
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

    private ArrayList<Data> getListDokter() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        ArrayList<Data> listAngkringan = new ArrayList<>();
        Set<Integer> usedPhotoIds = new HashSet<>();

        for (int i = 0; i < dataName.length; i++) {
            int photoId = dataPhoto.getResourceId(i, -1);
            if (!usedPhotoIds.contains(photoId)) {
                usedPhotoIds.add(photoId);
                Data angkringan = new Data(dataName[i], dataDescription[i], photoId);
                listAngkringan.add(angkringan);
            }
        }
        dataPhoto.recycle();
        return listAngkringan;
    }

    private void showRecyclerList() {
        itemKu.setLayoutManager(new LinearLayoutManager(this));
        Adapter listHeroAdapter = new Adapter(list);
        itemKu.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new Adapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Data data) {
                Intent intent = new Intent(RecyclerviewActivity.this, DeskripsiActivity.class);
                intent.putExtra(DeskripsiActivity.DATA_ANGKRINGAN, data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        // Implementasi onClick
    }
}
