package com.ruben.covid_19_statistics_app.network.reports.repository;

import com.ruben.covid_19_statistics_app.network.builder.RetrofitBuilder;
import com.ruben.covid_19_statistics_app.network.datasource.GlobalDataSource;
import com.ruben.covid_19_statistics_app.network.utils.NetworkUtils;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;

import retrofit2.Call;

public class ReportsRepository {

    private static ReportsRepository instance;
    private GlobalDataSource reportsDataSource;

    private ReportsRepository() {
        reportsDataSource = RetrofitBuilder.getRetrofitInstance().create(GlobalDataSource.class);
    }

    public static ReportsRepository getInstance() {
        return instance == null ? new ReportsRepository(): instance;
    }

    public Call<ApiReports> getReports(String iso, String date, String regionProvince) {
        return reportsDataSource.getReports(NetworkUtils.getHeaders(), iso, date, regionProvince);
    }

}
