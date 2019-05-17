package com.petersburg_studio.prazdnikraduga.test;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static final String BASE_URL = "http://cx45074.tmweb.ru/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient() {
        String baseUrl;
        retrofit = new Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    Api getApi() {
        return retrofit.create(Api.class);
    }
}
