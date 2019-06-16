package com.petersburg_studio.prazdnikraduga.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.petersburg_studio.prazdnikraduga.Constants.BASE_URL;

public class RetrofitClient {

    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);
    }
}
