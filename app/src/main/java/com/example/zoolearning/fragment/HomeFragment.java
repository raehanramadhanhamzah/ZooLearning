package com.example.zoolearning.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zoolearning.HewanCarnivoreActivity;
import com.example.zoolearning.HewanInsectivoreActivity;
import com.example.zoolearning.HewanOmnivoreActivity;
import com.example.zoolearning.HewanHerbivoreActivity;
import com.example.zoolearning.R;

public class HomeFragment extends Fragment {
    Button btnHewanCarnivore,btnHewanHerbivore,btnHewanOmnivore,btnHewanInsectivore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnHewanCarnivore = view.findViewById(R.id.btnHewanCarnivore);
        btnHewanHerbivore = view.findViewById(R.id.btnHewanHerbivore);
        btnHewanOmnivore = view.findViewById(R.id.btnHewanOmnivore);
        btnHewanInsectivore = view.findViewById(R.id.btnHewanInsectivore);

        btnHewanCarnivore.setOnClickListener(v -> {
            Intent toHewanCarnivoreActivity = new Intent(view.getContext(), HewanCarnivoreActivity.class);
            startActivity(toHewanCarnivoreActivity);

        });
        btnHewanHerbivore.setOnClickListener(v -> {
            Intent toHewanHerbivoreActivity = new Intent(view.getContext(), HewanHerbivoreActivity.class);
            startActivity(toHewanHerbivoreActivity);
        });
        btnHewanOmnivore.setOnClickListener(v -> {
            Intent toHewanOmnivoreActivity = new Intent(view.getContext(), HewanOmnivoreActivity.class);
            startActivity(toHewanOmnivoreActivity);
        });
        btnHewanInsectivore.setOnClickListener(v -> {
            Intent toHewanInsectivoreActivity = new Intent(view.getContext(), HewanInsectivoreActivity.class);
            startActivity(toHewanInsectivoreActivity);
        });

    }
}