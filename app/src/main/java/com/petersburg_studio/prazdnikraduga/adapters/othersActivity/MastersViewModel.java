package com.petersburg_studio.prazdnikraduga.adapters.othersActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.petersburg_studio.prazdnikraduga.data.others.MastersDataSourceFactory;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

import static com.petersburg_studio.prazdnikraduga.Constants.PAGE_SIZE;

public class MastersViewModel extends ViewModel {

    public LiveData<PagedList<Items>> itemsPagedList;

    public MastersViewModel() {
        MastersDataSourceFactory additionalDataSourceFactory = new MastersDataSourceFactory();
        LiveData<PageKeyedDataSource<Integer, Items>> liveDataSource = additionalDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE).build();

        itemsPagedList = (new LivePagedListBuilder(additionalDataSourceFactory, pagedListConfig))
                .build();
    }
}
