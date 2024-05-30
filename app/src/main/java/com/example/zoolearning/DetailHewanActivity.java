package com.example.zoolearning;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zoolearning.database.DBConfig;

public class DetailHewanActivity extends AppCompatActivity {
    public static final String PARCEL_DATA = "parcel_data";
    TextView tvName, tvDesc, tvSpecies, tvFamily, tvHabitat, tvPlaceFound, tvDiet, tvWeight, tvHeight;
    Button btnFavorite;
    LinearLayout btnKembali;
    boolean adaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_hewan);
        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvSpecies = findViewById(R.id.tvSpecies);
        tvFamily = findViewById(R.id.tvFamily);
        tvHabitat = findViewById(R.id.tvHabitat);
        tvPlaceFound = findViewById(R.id.tvPlaceFound);
        tvDiet = findViewById(R.id.tvDiet);
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnKembali = findViewById(R.id.btnKembali);

        // Mendapatkan data hewan dari Intent
        Animal animal = getIntent().getParcelableExtra(PARCEL_DATA); // Menggunakan konstanta PARCEL_DATA

        // Menampilkan data hewan di dalam views
        tvName.setText(animal.getName());
        tvDesc.setText(animal.getDescription());
        tvSpecies.setText("Species: " + animal.getSpecies());
        tvFamily.setText("Family: " + animal.getFamily());
        tvHabitat.setText("Habitat: " + animal.getHabitat());
        tvPlaceFound.setText("Place of Found: " + animal.getPlace_of_found());
        tvDiet.setText("Diet: " + animal.getDiet());
        tvWeight.setText("Weight: " + animal.getWeight_kg() + " kg");
        tvHeight.setText("Height: " + animal.getHeight_cm() + " cm");

        btnKembali.setOnClickListener(v -> finish());

        btnFavorite.setOnClickListener(v -> {
            DBConfig db = new DBConfig(this);
            adaData = false;

            // Cek apakah hewan ini sudah ada di database
            Cursor cursor = db.getRecordById(animal.getId());
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    adaData = true;
                }
                cursor.close();
            }

            if (adaData) {
                Toast.makeText(this, "Anda Sudah Menambahkan Hewan Ini Ke Favorit", Toast.LENGTH_SHORT).show();
            } else {
                // Tambahkan hewan ke database
                db.insertRecord(
                        animal.getId(),
                        animal.getName(),
                        animal.getSpecies(),
                        animal.getFamily(),
                        animal.getHabitat(),
                        animal.getPlace_of_found(),
                        animal.getDiet(),
                        animal.getDescription(),
                        animal.getWeight_kg(),
                        animal.getHeight_cm());
                Toast.makeText(this, "Berhasil Menambahkan Hewan Ini Ke Favorit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
