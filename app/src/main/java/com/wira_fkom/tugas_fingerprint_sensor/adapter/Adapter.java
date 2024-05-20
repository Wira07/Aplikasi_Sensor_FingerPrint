package com.wira_fkom.tugas_fingerprint_sensor.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.wira_fkom.tugas_fingerprint_sensor.data.Data;
import com.wira_fkom.tugas_fingerprint_sensor.ui.DeskripsiActivity;
import com.wira_fkom.tugas_fingerprint_sensor.databinding.ItemLayoutBinding;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {

    private ArrayList<Data> listDokter;
    private OnItemClickCallback onItemClickCallback;

    public Adapter(ArrayList<Data> listDokter) {
        this.listDokter = listDokter;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Data data = listDokter.get(position);

        // Gunakan Glide untuk memuat gambar
        Glide.with(holder.itemView.getContext())
                .load(data.getPhoto())
                .into(holder.binding.imgItemPhoto);

        holder.binding.tvItemName.setText(data.getName());
        holder.binding.tvItemDetail.setText(data.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickCallback != null) {
                    onItemClickCallback.onItemClicked(listDokter.get(holder.getAdapterPosition()));
                }

                Intent intent = new Intent(v.getContext(), DeskripsiActivity.class);

                // Tambahkan animasi transisi
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) v.getContext(),
                        Pair.create((View) holder.binding.imgItemPhoto, "profileTransition"),
                        Pair.create((View) holder.binding.tvItemName, "nameTransition"),
                        Pair.create((View) holder.binding.tvItemDetail, "descriptionTransition")
                );

//                v.getContext().startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;

        public ListViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Data data);
    }
}
