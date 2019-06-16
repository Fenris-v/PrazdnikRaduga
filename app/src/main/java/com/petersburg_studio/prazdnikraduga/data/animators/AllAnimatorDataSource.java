package com.petersburg_studio.prazdnikraduga.data.animators;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.petersburg_studio.prazdnikraduga.api.RetrofitClient;
import com.petersburg_studio.prazdnikraduga.pojo.ApiResponse;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.petersburg_studio.prazdnikraduga.Constants.API_KEY;
import static com.petersburg_studio.prazdnikraduga.Constants.PAGE_SIZE;

public class AllAnimatorDataSource extends PageKeyedDataSource<Integer, Items> {

    private static final int FIRST_PAGE = 1;
    private static final int ITEM_TYPE = 0;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Items> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getItems(API_KEY, FIRST_PAGE, PAGE_SIZE, ITEM_TYPE)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {

                        if (response.body() != null) {
                            Integer key = response.body().isHas_more() ? FIRST_PAGE + 1 : null;
                            callback.onResult(
                                    response.body().getItems(),
                                    null,
                                    key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Items> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Items> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getItems(API_KEY, params.key, PAGE_SIZE, ITEM_TYPE)
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
                            callback.onResult(response.body().getItems(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {

                    }
                });
    }
}
