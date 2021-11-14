package com.ruben.covid_19_statistics_app.network.reports.repository;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiReports {

    @SerializedName("data")
    private List<ApiReportsItem> apiReportsItemList;

    public List<ApiReportsItem> getApiReportsItemList() {
        return apiReportsItemList;
    }

    public void setApiReportsItemList(List<ApiReportsItem> apiReportsItemList) {
        this.apiReportsItemList = apiReportsItemList;
    }
}
