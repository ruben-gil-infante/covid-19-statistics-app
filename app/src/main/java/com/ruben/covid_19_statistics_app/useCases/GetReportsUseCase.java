package com.ruben.covid_19_statistics_app.useCases;


import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;
import com.ruben.covid_19_statistics_app.network.reports.repository.ReportsRepository;

import retrofit2.Call;

public class GetReportsUseCase {

    private ReportsRepository reportsRepository;

    public GetReportsUseCase() {
        reportsRepository = ReportsRepository.getInstance();
    }

    public Call<ApiReports> getReports(String date, String q, String regionName,
                                       String iso, String regionProvince, String cityName) {
        return reportsRepository.getReports(date, q,
            regionName, iso, regionProvince, cityName); }

}
