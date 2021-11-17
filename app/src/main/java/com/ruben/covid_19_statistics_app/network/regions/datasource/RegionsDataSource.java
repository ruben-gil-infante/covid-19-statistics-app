package com.ruben.covid_19_statistics_app.network.regions.datasource;

import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;

public interface RegionsDataSource {

    @GET("/regions")
    Call<ApiRegions> getApiRegions(@HeaderMap Map<String, String> headers);
}
