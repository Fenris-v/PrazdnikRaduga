package com.petersburg_studio.prazdnikraduga.test.homescreen;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.petersburg_studio.prazdnikraduga.test.pojo.ApiResponse;
import com.petersburg_studio.prazdnikraduga.test.pojo.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.petersburg_studio.prazdnikraduga.test.APIKey.API_KEY;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(API_KEY, FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getProducts(), null, FIRST_PAGE + 1);
//                        } else {
//                            TestActivity.class.
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(API_KEY, params.key, PAGE_SIZE)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getProducts(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(API_KEY, params.key, PAGE_SIZE)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        if (response.body() != null) {

//                            Integer key;
//                            if (response.body().isHas_more()) {
//                                key = params.key + 1;
//                            } else {
//                                key = null;
//                            }
//                            ^ IT'S LONG CODE OF NEXT STRING:

                            Integer key = response.body().isHas_more() ? params.key + 1 : null;
                            callback.onResult(response.body().getProducts(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }
}
