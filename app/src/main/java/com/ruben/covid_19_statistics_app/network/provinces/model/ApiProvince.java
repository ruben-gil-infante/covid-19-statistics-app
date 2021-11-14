package com.ruben.covid_19_statistics_app.network.provinces.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiProvince {
    
    @SerializedName("data")
    private List<ApiProvince> apiProvinceList;

    public List<ApiProvince> getApiProvinceList() {
        return apiProvinceList;
    }

    public void setApiProvinceList(List<ApiProvince> apiProvinceList) {
        this.apiProvinceList = apiProvinceList;
    }
}


