package com.ruben.covid_19_statistics_app.ui.viewmodels;


import androidx.lifecycle.ViewModel;

import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;
import com.ruben.covid_19_statistics_app.network.reports.repository.ReportsRepository;
import com.ruben.covid_19_statistics_app.useCases.GetReportsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsViewModel extends ViewModel {

    private GetReportsUseCase reportsUseCase;

    public ReportsViewModel() {
        reportsUseCase = new GetReportsUseCase();
    }

    public void getReport(String date, String regionProvince) {
        reportsUseCase.getReports(date, regionProvince).enqueue(new Callback<ApiReports>() {
            @Override
            public void onResponse(Call<ApiReports> call, Response<ApiReports> response) {
                int stopToDebug = 0;
            }

            @Override
            public void onFailure(Call<ApiReports> call, Throwable t) {
                int stopToDebug = 0;
            }
        });
    }

}