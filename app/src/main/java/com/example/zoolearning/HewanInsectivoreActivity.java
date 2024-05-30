package com.example.zoolearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoolearning.api.ApiService;
import com.example.zoolearning.api.CheckInternet;
import com.example.zoolearning.api.CheckInternetActivity;
import com.example.zoolearning.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanInsectivoreActivity extends AppCompatActivity {

    private LinearLayout btnKembali;
    private ApiService apiService;
    private RecyclerView rv;
    private AnimalAdapter animalAdapter;
    private List<Animal> animals;
    private List<Animal> ambilDataInsectivore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hewan_insectivore);

        rv = findViewById(R.id.rv_insectivore);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setHasFixedSize(true);
        if(CheckInternet.adaInternet(this)){
            apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<Animal>> call = apiService.getAnimals();
            call.enqueue(new Callback<List<Animal>>() {
                @Override
                public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                    if (response.isSuccessful()) {
                        List<Animal> allAnimals = response.body();
                        ambilDataInsectivore = new ArrayList<>();
                        // Buat list baru untuk menyimpan hanya hewan dengan diet "Insectivore"
                        for (Animal animal : allAnimals) {
                            if ("Insectivore".equals(animal.getDiet())) {
                                ambilDataInsectivore.add(animal);
                            }
                        }
                        // Inisialisasi adapter dengan list hewan Insectivore
                        animalAdapter = new AnimalAdapter(ambilDataInsectivore);
                        rv.setAdapter(animalAdapter);
                    }
                }
                @Override
                public void onFailure(Call<List<Animal>> call, Throwable t) {
                }
            });
        }else{
            Intent toCheckInternet = new Intent(this, CheckInternetActivity.class);
            startActivity(toCheckInternet);
            finish();
        }
        btnKembali = findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(v -> {
            finish();
        });

    }
}