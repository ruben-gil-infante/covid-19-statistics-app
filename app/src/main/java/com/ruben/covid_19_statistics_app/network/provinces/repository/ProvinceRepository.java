package com.ruben.covid_19_statistics_app.network.provinces.repository;


import com.ruben.covid_19_statistics_app.network.builder.RetrofitBuilder;
import com.ruben.covid_19_statistics_app.network.utils.NetworkUtils;
import com.ruben.covid_19_statistics_app.network.datasource.GlobalDataSource;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;

import retrofit2.Call;

public class ProvinceRepository {

    private static ProvinceRepository instance;
    private GlobalDataSource globalDataSource;

    private ProvinceRepository() {
        globalDataSource = RetrofitBuilder.getRetrofitInstance().create(GlobalDataSource.class);
    }

    public static ProvinceRepository getInstance() {
        return instance == null ? new ProvinceRepository() : instance;
    }

    public Call<ApiProvince> getAllProvinces(String iso) {
        return globalDataSource.getAllProvinces(NetworkUtils.getHeaders(), iso);
    }
}
