package com.ruben.covid_19_statistics_app.useCases;


import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;
import com.ruben.covid_19_statistics_app.network.reports.repository.ReportsRepository;

import retrofit2.Call;

public class GetReportsUseCase {

    private ReportsRepository reportsRepository;

    public GetReportsUseCase() {
        reportsRepository = ReportsRepository.getInstance();
    }

    public Call<ApiReports> getReports(String iso, String date, String regionProvince) {
        return reportsRepository.getReports(iso, date, regionProvince); }

}
