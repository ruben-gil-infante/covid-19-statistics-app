package com.ruben.covid_19_statistics_app.ui.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReportsItem;
import com.ruben.covid_19_statistics_app.useCases.GetReportsUseCase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsViewModel extends ViewModel {

    private GetReportsUseCase reportsUseCase;
    private MutableLiveData<ApiReportsItem> report;

    public ReportsViewModel() {
        reportsUseCase = new GetReportsUseCase();
        report = new MutableLiveData<>();
    }

    public MutableLiveData<ApiReportsItem> getReports() {
        return report;
    }

    public void getReport(String iso, String date, String regionProvince) {
        reportsUseCase.getReports(iso, date, regionProvince).enqueue(new Callback<ApiReports>() {
            @Override
            public void onResponse(Call<ApiReports> call, Response<ApiReports> response) {
                // TODO: Add null pointer check
                report.postValue(response.body().getApiReportsItemList().get(0));
            }

            @Override
            public void onFailure(Call<ApiReports> call, Throwable t) {
                int stopToDebug = 0;
            }
        });
    }

}