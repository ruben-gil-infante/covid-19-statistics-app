package com.ruben.covid_19_statistics_app.network.reports.repository;

import com.ruben.covid_19_statistics_app.network.builder.RetrofitBuilder;
import com.ruben.covid_19_statistics_app.network.utils.NetworkUtils;
import com.ruben.covid_19_statistics_app.network.reports.datasource.ReportsDataSource;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;

import retrofit2.Call;

public class ReportsRepository {

    private static ReportsRepository instance;
    private ReportsDataSource reportsDataSource;

    private ReportsRepository() {
        reportsDataSource = RetrofitBuilder.getRetrofitInstance().create(ReportsDataSource.class);
    }

    public static ReportsRepository getInstance() {
        return instance == null ? new ReportsRepository(): instance;
    }

    public Call<ApiReports> getReports(String date, String q, String regionName,
                                          String iso, String regionProvince, String cityName) {
        return reportsDataSource.getReports(NetworkUtils.getHeaders(), date, q, regionName, iso, regionProvince, cityName);
    }

}
