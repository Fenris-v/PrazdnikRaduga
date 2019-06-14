package com.petersburg_studio.prazdnikraduga.data.animators;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.petersburg_studio.prazdnikraduga.pojo.Items;

public class SuperheroAnimatorDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Items>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Items> create() {
        SuperheroAnimatorDataSource superheroAnimatorDataSource = new SuperheroAnimatorDataSource();
        itemLiveDataSource.postValue(superheroAnimatorDataSource);
        return superheroAnimatorDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Items>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
