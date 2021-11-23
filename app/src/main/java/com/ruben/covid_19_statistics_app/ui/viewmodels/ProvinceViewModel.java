package com.ruben.covid_19_statistics_app.ui.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvinceItem;
import com.ruben.covid_19_statistics_app.useCases.GetAllProvincesUseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceViewModel extends ViewModel {

    private GetAllProvincesUseCase provincesUseCase;
    private MutableLiveData<Boolean> showProgressBar;
    private MutableLiveData<List<ApiProvinceItem>> provincesList;
    private List<ApiProvinceItem> noMutableProvinceList;
    private String iso;

    public ProvinceViewModel() {
        provincesUseCase = new GetAllProvincesUseCase();
        showProgressBar = new MutableLiveData<>();
        provincesList = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }

    public MutableLiveData<List<ApiProvinceItem>> getProvinceList() {
        return provincesList;
    }

    public String getIso() { return iso; }

    public void setIso(String iso) { this.iso = iso; }

    public void getAllProvincesFromCountry(String iso) {
        showProgressBar.postValue(true);
        provincesUseCase.getAllProvincesUseCase(iso).enqueue(new Callback<ApiProvince>() {
            @Override
            public void onResponse(Call<ApiProvince> call, Response<ApiProvince> response) {
                showProgressBar.postValue(false);
                provincesList.postValue(response.body().getApiProvinceList());
            }

            @Override
            public void onFailure(Call<ApiProvince> call, Throwable t) {
                showProgressBar.postValue(false);
                // TODO: Display the error screen
            }
        });
    }
}