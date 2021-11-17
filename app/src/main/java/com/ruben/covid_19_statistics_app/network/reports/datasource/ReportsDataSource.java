package com.ruben.covid_19_statistics_app.network.reports.datasource;

import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface ReportsDataSource {

    @GET("/reports")
    Call<ApiReports> getReports(@HeaderMap Map<String, String> headers,
                                @Query("date") String date,
                                @Query("q") String q,
                                @Query("region_name") String regionName,
                                @Query("iso") String iso,
                                @Query("region_province") String regionProvince,
                                @Query("city_name") String cityName);

}
