package com.ruben.covid_19_statistics_app.network.datasource;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GlobalDataSource {

    @GET("/provinces")
    Call<ApiProvince> getAllProvinces(@HeaderMap Map<String,String> headers, @Query("iso") String iso);

    @GET("/regions")
    Call<ApiRegions> getApiRegions(@HeaderMap Map<String, String> headers);


    @GET("/reports")
    Call<ApiReports> getReports(@HeaderMap Map<String, String> headers,
                                @Query("date") String date,
                                @Query("region_province") String regionProvince);


}
