package com.ruben.covid_19_statistics_app.network.provinces.repository;


import com.ruben.covid_19_statistics_app.network.builder.RetrofitBuilder;
import com.ruben.covid_19_statistics_app.network.networkUtils.NetworkUtils;
import com.ruben.covid_19_statistics_app.network.provinces.datasource.ProvinceDataSource;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;

import retrofit2.Call;

public class ProvinceRepository {

    private static ProvinceRepository instance;
    private ProvinceDataSource provinceDataSource;

    private ProvinceRepository() {
        provinceDataSource = RetrofitBuilder.getRetrofitInstance().create(ProvinceDataSource.class);
    }

    public static ProvinceRepository getInstance() {
        return instance == null ? new ProvinceRepository() : instance;
    }

    public Call<ApiProvince> getAllProvinces() {
        // TODO: Avoid to force the province to be spain
        return provinceDataSource.getAllProvinces(NetworkUtils.getHeaders(), "ESP");
    }
}
