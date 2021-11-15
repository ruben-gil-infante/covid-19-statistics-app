package com.ruben.covid_19_statistics_app.network.provinces.datasource;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ProvinceDataSource {

    @GET("/provinces")
    @Headers({
            "x-rapidapi-host: covid-19-statistics.p.rapidapi.com",
            "x-rapidapi-key: 27aa06345fmsh8f4bfc85f8d8f7dp1ba148jsn9b86ffbf73f8"
    }) // TODO: It"s necessary to add the headers dinamically
    Call<ApiProvince> getAllProvinces(@Query("iso") String iso);

}
