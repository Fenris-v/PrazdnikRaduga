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
            @Query("per_page") int perPage,
            @Query("item_type") int type
    );

    @GET("db_connect_additional.php")
    Call<ApiResponse> getAdds(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("db_connect_master.php")
    Call<ApiResponse> getMasters(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("db_connect_season.php")
    Call<ApiResponse> getSeasons(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("db_connect_show.php")
    Call<ApiResponse> getShows(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("db_connect_thematic.php")
    Call<ApiResponse> getThematic(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
