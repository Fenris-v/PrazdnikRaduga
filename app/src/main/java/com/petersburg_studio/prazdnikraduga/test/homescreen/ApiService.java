package com.petersburg_studio.prazdnikraduga.test.homescreen;

import com.petersburg_studio.prazdnikraduga.test.pojo.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("db_connect.php")
    Call<ApiResponse> getProducts(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
