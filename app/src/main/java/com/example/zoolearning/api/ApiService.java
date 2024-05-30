package com.example.zoolearning.api;

import com.example.zoolearning.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v1/animals")
    Call<List<Animal>> getAnimals();


}

