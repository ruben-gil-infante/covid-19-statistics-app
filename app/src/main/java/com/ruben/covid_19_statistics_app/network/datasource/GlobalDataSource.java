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
    @Headers({
            "x-rapidapi-host: covid-19-statistics.p.rapidapi.com",
            "x-rapidapi-key: 27aa06345fmsh8f4bfc85f8d8f7dp1ba148jsn9b86ffbf73f8"
    })
    Call<ApiProvince> getAllProvinces(@HeaderMap Map<String,String> headers, @Query("iso") String iso);

    @GET("/regions")
    Call<ApiRegions> getApiRegions(@HeaderMap Map<String, String> headers);


    @GET("/reports")
    Call<ApiReports> getReports(@HeaderMap Map<String, String> headers,
                                @Query("date") String date,
                                @Query("q") String q,
                                @Query("region_name") String regionName,
                                @Query("iso") String iso,
                                @Query("region_province") String regionProvince,
                                @Query("city_name") String cityName);


}
