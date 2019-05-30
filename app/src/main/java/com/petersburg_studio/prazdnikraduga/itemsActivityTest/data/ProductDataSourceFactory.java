package com.petersburg_studio.prazdnikraduga.itemsActivityTest.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.petersburg_studio.prazdnikraduga.itemsActivityTest.pojo.Product;

public class ProductDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Product>> productLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Product> create() {
        ProductDataSource productDataSource = new ProductDataSource();
        productLiveDataSource.postValue(productDataSource);
        return productDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Product>> getProductLiveDataSource() {
        return productLiveDataSource;
    }
}
