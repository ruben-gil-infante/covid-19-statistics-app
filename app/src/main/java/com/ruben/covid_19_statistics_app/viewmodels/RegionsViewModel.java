package com.ruben.covid_19_statistics_app.viewmodels;


import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.IOnListWithFinderItemClicked;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinderItem;
import com.ruben.covid_19_statistics_app.useCases.GetRegionsUseCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionsViewModel extends ViewModel {

    private MutableLiveData<Boolean> progressBar;
    private MutableLiveData<Boolean> errorLayout;
    private GetRegionsUseCase regionsUseCase;
    private MutableLiveData<ArrayList<ListWithFinderItem>> finderItemsList;

    public RegionsViewModel() {
        progressBar = new MutableLiveData<>();
        regionsUseCase = new GetRegionsUseCase();
        errorLayout = new MutableLiveData<>();
        finderItemsList = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getProgressBar() {
        return progressBar;
    }

    public MutableLiveData<ArrayList<ListWithFinderItem>> getFinderItemsList() {
        return finderItemsList;
    }

    public MutableLiveData<Boolean> getErrorLayout() {
        return errorLayout;
    }

    public void getAllRegions() {
        progressBar.postValue(true);
        regionsUseCase.getAllRegions().enqueue(new Callback<ApiRegions>() {
            @Override
            public void onResponse(Call<ApiRegions> call, Response<ApiRegions> response) {
                progressBar.postValue(false);
                // setUpListWithFinderComponent(response.body());
                errorLayout.postValue(true);
            }

            @Override
            public void onFailure(Call<ApiRegions> call, Throwable t) {
                progressBar.postValue(false);
                errorLayout.postValue(true);
            }
        });
    }

    private void setUpListWithFinderComponent(ApiRegions regions) {
        ArrayList<ListWithFinderItem> itemsToListBuilder = new ArrayList<>();

        for (int i = 0; i < regions.getApiRegionItemList().size(); i++) {
            ApiRegionItem regionItem = regions.getApiRegionItemList().get(i);
            itemsToListBuilder.add(ListWithFinderItem.Build(regionItem.getName(), i));
        }

        finderItemsList.postValue(itemsToListBuilder);
    }

}