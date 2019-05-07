package com.example.bakingapplication.API;

import com.example.bakingapplication.Utils.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceInterface {
    @GET("/topher/2017/May/59121517_baking/{json}")
    Call<List<Recipe>> getRecipes(@Path("json") String path);
}
