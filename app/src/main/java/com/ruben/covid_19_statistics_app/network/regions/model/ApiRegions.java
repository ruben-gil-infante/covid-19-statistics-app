package com.ruben.covid_19_statistics_app.network.regions.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiRegions {

    @SerializedName("data")
    private List<ApiRegionItem> apiRegionItemList;


    public List<ApiRegionItem> getApiRegionItemList() {
        return apiRegionItemList;
    }

    public void setApiRegionItemList(List<ApiRegionItem> apiRegionItemList) {
        this.apiRegionItemList = apiRegionItemList;
    }
}
