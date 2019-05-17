package com.petersburg_studio.prazdnikraduga.test;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class ProductDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Product>> productLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        ProductDataSource productDataSource = new ProductDataSource();
        productLiveDataSource.postValue(productDataSource);
        return productDataSource;
    }

    MutableLiveData<PageKeyedDataSource<Integer, Product>> getProductLiveDataSource() {
        return productLiveDataSource;
    }
}
