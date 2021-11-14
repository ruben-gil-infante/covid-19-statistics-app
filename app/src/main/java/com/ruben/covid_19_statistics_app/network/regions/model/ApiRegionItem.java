package com.ruben.covid_19_statistics_app.network.regions.model;

import com.google.gson.annotations.SerializedName;

public class ApiRegionItem {

    @SerializedName("iso")
    private String iso;

    @SerializedName("name")
    private String name;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
