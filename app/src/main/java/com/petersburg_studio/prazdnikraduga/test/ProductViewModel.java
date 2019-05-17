package com.petersburg_studio.prazdnikraduga.test;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ProductViewModel extends ViewModel {

    LiveData<PagedList<Product>> productPagedList;
    private LiveData<PageKeyedDataSource<Integer, Product>> liveDataSource;

    public ProductViewModel() {

        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory();
        liveDataSource = productDataSourceFactory.getProductLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ProductDataSource.PAGE_SIZE)
                        .build();

        productPagedList = (new LivePagedListBuilder(productDataSourceFactory, config)).build();
    }
}
