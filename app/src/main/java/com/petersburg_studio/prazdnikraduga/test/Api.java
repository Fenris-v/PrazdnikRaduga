package com.petersburg_studio.prazdnikraduga.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("db_connect_new.php")
    Call<ApiResponse> getProducts(
            @Query("page") int page
    );
}
