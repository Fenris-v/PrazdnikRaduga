package com.petersburg_studio.prazdnikraduga.data.others;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.petersburg_studio.prazdnikraduga.pojo.Items;

public class MastersDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Items>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Items> create() {
        MastersDataSource itemsDataSource = new MastersDataSource();
        itemLiveDataSource.postValue(itemsDataSource);
        return itemsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Items>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
