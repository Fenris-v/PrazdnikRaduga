package com.petersburg_studio.prazdnikraduga.adapters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.petersburg_studio.prazdnikraduga.data.ItemDataSource;
import com.petersburg_studio.prazdnikraduga.data.ItemDataSourceFactory;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

public class AnimatorViewModel extends ViewModel {

    public LiveData<PagedList<Items>> animatorPagedList;

    public AnimatorViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        LiveData<PageKeyedDataSource<Integer, Items>> liveDataSource = itemDataSourceFactory.getProductLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE).build();

        animatorPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
}
