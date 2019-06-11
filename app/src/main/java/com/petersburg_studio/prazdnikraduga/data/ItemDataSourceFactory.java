package com.petersburg_studio.prazdnikraduga.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.petersburg_studio.prazdnikraduga.pojo.Items;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Items>> productLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Items> create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        productLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Items>> getProductLiveDataSource() {
        return productLiveDataSource;
    }
}
