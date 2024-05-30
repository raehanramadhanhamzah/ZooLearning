package com.example.zoolearning;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.zoolearning.fragment.GamesFragment;
import com.example.zoolearning.fragment.HomeFragment;
import com.example.zoolearning.fragment.FavouriteFragment;

public class MainActivity extends AppCompatActivity {
    private TextView btnHome,btnGames,btnFavourite;
    private LinearLayout backgroundMenuHome,backgroundMenuGames,backgroundMenuFavourite;
    private CardView navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if(!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout,homeFragment)
                    .commit();
        }
        btnHome = findViewById(R.id.btnHome);
        btnGames = findViewById(R.id.btnGames);
        btnFavourite = findViewById(R.id.btnFavourite);

        backgroundMenuHome = findViewById(R.id.background_menu_home);

        backgroundMenuGames = findViewById(R.id.background_menu_games);
        ColorGreyGames();

        backgroundMenuFavourite = findViewById(R.id.background_menu_favourite);
        ColorGreyFavourite();


        btnHome.setOnClickListener(v -> {
            ColorGreenHome();
            ColorGreyGames();
            ColorGreyFavourite();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,homeFragment)
                    .commit();
        });

        btnGames.setOnClickListener(v -> {
            btnHome.setTextColor(Color.LTGRAY);
            btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(Color.LTGRAY));

            btnFavourite.setTextColor(Color.LTGRAY);
            btnFavourite.setCompoundDrawableTintList(ColorStateList.valueOf(Color.LTGRAY));

            ColorGreenGames();
            ColorGreyHome();
            ColorGreyFavourite();
            GamesFragment gamesFragment = new GamesFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,gamesFragment)
                    .commit();
        });
        btnFavourite.setOnClickListener(v -> {
            ColorGreenFavourite();
            ColorGreyGames();
            ColorGreyHome();
            FavouriteFragment favouriteFragment = new FavouriteFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, favouriteFragment)
                    .commit();
        });
    }
    public void ColorGreenHome(){
        backgroundMenuHome.setBackgroundResource(R.drawable.rounded_button_menu);
        btnHome.setTextColor(getColor(R.color.hijau));
        btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(getColor(R.color.hijau)));
    }
    public void ColorGreenGames(){
        backgroundMenuGames.setBackgroundResource(R.drawable.rounded_button_menu);
        btnGames.setTextColor(getColor(R.color.hijau));
        btnGames.setCompoundDrawableTintList(ColorStateList.valueOf(getColor(R.color.hijau)));
    }
    public void ColorGreenFavourite(){
        backgroundMenuFavourite.setBackgroundResource(R.drawable.rounded_button_menu); // Mengatur kembali latar belakang
        btnFavourite.setTextColor(getColor(R.color.hijau));
        btnFavourite.setCompoundDrawableTintList(ColorStateList.valueOf(getColor(R.color.hijau)));

    }
    public  void ColorGreyHome(){
        btnHome.setTextColor(Color.LTGRAY);
        btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(Color.LTGRAY));
    }
    public  void ColorGreyGames(){
        btnGames.setTextColor(Color.LTGRAY);
        btnGames.setCompoundDrawableTintList(ColorStateList.valueOf(Color.LTGRAY));
    }
    public  void ColorGreyFavourite(){
        btnFavourite.setTextColor(Color.LTGRAY);
        btnFavourite.setCompoundDrawableTintList(ColorStateList.valueOf(Color.LTGRAY));
    }
    
    public void setNavigatorOff() {
        navigator = findViewById(R.id.navigator);
        navigator.setVisibility(View.GONE);
    }


}