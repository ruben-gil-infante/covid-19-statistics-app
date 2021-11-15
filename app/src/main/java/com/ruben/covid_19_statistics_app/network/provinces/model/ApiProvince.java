package com.ruben.covid_19_statistics_app.network.provinces.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiProvince {
    
    @SerializedName("data")
    private List<ApiProvinceItem> apiProvinceList;

    public List<ApiProvinceItem> getApiProvinceList() {
        return apiProvinceList;
    }

    public void setApiProvinceList(List<ApiProvinceItem> apiProvinceList) {
        this.apiProvinceList = apiProvinceList;
    }
}


