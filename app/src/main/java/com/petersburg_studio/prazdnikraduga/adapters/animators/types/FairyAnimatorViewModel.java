package com.petersburg_studio.prazdnikraduga.adapters.animators.types;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.petersburg_studio.prazdnikraduga.data.animators.FairyAnimatorDataSourceFactory;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

import static com.petersburg_studio.prazdnikraduga.Constans.PAGE_SIZE;

public class FairyAnimatorViewModel extends ViewModel {

    public LiveData<PagedList<Items>> animatorPagedList;

    public FairyAnimatorViewModel() {
        FairyAnimatorDataSourceFactory fairyAnimatorDataSourceFactory = new FairyAnimatorDataSourceFactory();
        LiveData<PageKeyedDataSource<Integer, Items>> liveDataSource = fairyAnimatorDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE).build();

        animatorPagedList = (new LivePagedListBuilder(fairyAnimatorDataSourceFactory, pagedListConfig))
                .build();
    }
}
