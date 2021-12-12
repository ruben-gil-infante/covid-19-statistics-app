package com.ruben.covid_19_statistics_app.ui.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReports;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReportsItem;
import com.ruben.covid_19_statistics_app.useCases.GetReportsUseCase;
import com.ruben.covid_19_statistics_app.utils.DateUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsViewModel extends ViewModel {

    private GetReportsUseCase reportsUseCase;
    private MutableLiveData<ApiReportsItem> report;
    private MutableLiveData<Boolean> progressBar;
    private MutableLiveData<Boolean> showErrorLayout;

    // Saved data to make the second call
    private String iso;
    private String date;
    private String regionProvince;

    boolean savedData = false;

    public ReportsViewModel() {
        reportsUseCase = new GetReportsUseCase();
        report = new MutableLiveData<>();
        progressBar = new MutableLiveData<>();
        showErrorLayout = new MutableLiveData<>();
        date = DateUtils.getParsedTodayDate();
    }

    public MutableLiveData<ApiReportsItem> getReports() {
        return report;
    }

    public MutableLiveData<Boolean> getProgressBar() {
        return progressBar;
    }

    public MutableLiveData<Boolean> getShowErrorLayout() { return showErrorLayout; }

    public void getReport() {
        getReport(iso, regionProvince);
    }

    public void getReport(String iso, String regionProvince) {
        if(!savedData) {
            this.iso = iso;
            this.date = date;
            this.regionProvince = regionProvince;
        }
        showErrorLayout.postValue(false);
        progressBar.postValue(true);
        reportsUseCase.getReports(iso, date, regionProvince).enqueue(new Callback<ApiReports>() {
            @Override
            public void onResponse(Call<ApiReports> call, Response<ApiReports> response) {
                // TODO: Add null pointer check
                progressBar.postValue(false);
                if(response.body() == null || response.body().getApiReportsItemList().isEmpty()) {
                    showErrorLayout.postValue(true);
                } else {
                    report.postValue(response.body().getApiReportsItemList().get(0));
                }
            }

            @Override
            public void onFailure(Call<ApiReports> call, Throwable t) {
                progressBar.postValue(false);
                showErrorLayout.postValue(true);
            }
        });
    }

    public ApiReportsItem getReportItem() {
        return report.getValue();
    }

}