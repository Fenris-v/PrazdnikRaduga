package com.petersburg_studio.prazdnikraduga.api;

import com.petersburg_studio.prazdnikraduga.pojo.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("db_connect_animators_all.php")
    Call<ApiResponse> getItems(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
