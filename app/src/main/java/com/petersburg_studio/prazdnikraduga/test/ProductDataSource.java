package com.petersburg_studio.prazdnikraduga.test;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(FIRST_PAGE)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
//                        progressBar;
                        if (response.body() != null) {
                            callback.onResult(response.body().products, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(params.key)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().products, key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(params.key)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        if (response.body() != null) {
                            Integer key = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().products, key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }
}
