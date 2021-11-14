package com.ruben.covid_19_statistics_app.network.provinces.datasource;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ProvinceDataSource {

    @GET("/provinces")
    @Headers() // TODO: It's necessary to add the headers
    Call<ApiProvince> getAllProvinces();

}
