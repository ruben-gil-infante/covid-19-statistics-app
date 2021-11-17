package com.ruben.covid_19_statistics_app.network.regions.repository;

import com.ruben.covid_19_statistics_app.network.builder.RetrofitBuilder;
import com.ruben.covid_19_statistics_app.network.utils.NetworkUtils;
import com.ruben.covid_19_statistics_app.network.regions.datasource.RegionsDataSource;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;

import retrofit2.Call;

public class RegionsRepository {

    private static RegionsRepository instance;
    private RegionsDataSource regionsDataSource;

    private RegionsRepository() {
        regionsDataSource = RetrofitBuilder.getRetrofitInstance().create(RegionsDataSource.class);
    }

    public static RegionsRepository getInstance() {
        return instance == null ? new RegionsRepository() : instance;
    }

    public Call<ApiRegions> getAllRegions() {
        return regionsDataSource.getApiRegions(NetworkUtils.getHeaders());
    }

}
