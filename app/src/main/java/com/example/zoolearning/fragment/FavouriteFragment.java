package com.example.zoolearning.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zoolearning.Animal;
import com.example.zoolearning.FavoriteAdapter;
import com.example.zoolearning.R;
import com.example.zoolearning.api.CheckInternet;
import com.example.zoolearning.api.CheckInternetActivity;
import com.example.zoolearning.database.DBConfig;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    int id;
    String name,species,family,habitat,place_of_found,diet,description,weight_kg,height_cm;
    ArrayList<Animal> dataAnimalFav;
    RecyclerView rv_fav ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBConfig db = new DBConfig(view.getContext());
        Cursor cursor = db.getAllRecord();
        if(CheckInternet.adaInternet(getContext())){
            dataAnimalFav = new ArrayList<>();
            //Buat perulangan untuk mengambil semua datany
            //Dicek apakah cursor bisa bergerak, kalau bisa berarti ada datanya(tidak kosong),
            // Makany pakai do while agar ketika datanya cuman datanya 1 akan tetap berjalan terus akan berulangan ketika cursor masih bisa move
            if(cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    species = cursor.getString(cursor.getColumnIndexOrThrow("species"));
                    family = cursor.getString(cursor.getColumnIndexOrThrow("family"));
                    habitat= cursor.getString(cursor.getColumnIndexOrThrow("habitat"));
                    place_of_found= cursor.getString(cursor.getColumnIndexOrThrow("place_of_found"));
                    diet= cursor.getString(cursor.getColumnIndexOrThrow("diet"));
                    description= cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    weight_kg= cursor.getString(cursor.getColumnIndexOrThrow("weight_kg"));
                    height_cm= cursor.getString(cursor.getColumnIndexOrThrow("height_cm"));

                    dataAnimalFav.add(0,new Animal(id,name,species,family,habitat,place_of_found,diet,description,weight_kg,height_cm));
                } while (cursor.moveToNext());
            }

            rv_fav = view.findViewById(R.id.rv_fav);
            rv_fav.setHasFixedSize(true);
            FavoriteAdapter adapterFav= new FavoriteAdapter(dataAnimalFav);
            rv_fav.setAdapter(adapterFav);
        }else{
            Intent toCheckInternet = new Intent(getContext(), CheckInternetActivity.class);
            startActivity(toCheckInternet);
            getActivity().finish();
        }

    }
}