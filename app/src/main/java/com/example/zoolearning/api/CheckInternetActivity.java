package com.example.zoolearning.api;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zoolearning.MainActivity;
import com.example.zoolearning.R;

public class CheckInternetActivity extends AppCompatActivity {
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_internet);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> {
            if(CheckInternet.adaInternet(this)){
                Intent toMainActivity = new Intent(this, MainActivity.class);
                startActivity(toMainActivity);
                finish();
            }
        });
    }
}