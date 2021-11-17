package com.ruben.covid_19_statistics_app.network.reports.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiRegionReport {

    @SerializedName("iso")
    private String iso;

    @SerializedName("name")
    private String name;

    @SerializedName("province")
    private String province;

    @SerializedName("lat")
    private String lat;

    @SerializedName("long")
    private String longt;

    @SerializedName("cities")
    private List<ApiRegionCity> apiRegionCityList;

}
