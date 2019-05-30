package com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.petersburg_studio.prazdnikraduga.itemsActivityTest.data.ProductDataSource;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.data.ProductDataSourceFactory;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.pojo.Product;

public class ProductViewModel extends ViewModel {

    public LiveData<PagedList<Product>> productPagedList;

    public ProductViewModel() {
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory();
        LiveData<PageKeyedDataSource<Integer, Product>> liveDataSource = productDataSourceFactory.getProductLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ProductDataSource.PAGE_SIZE).build();

        productPagedList = (new LivePagedListBuilder(productDataSourceFactory, pagedListConfig))
                .build();
    }
}
