package com.ruben.covid_19_statistics_app.network.provinces.model;

import com.google.gson.annotations.SerializedName;

public class ApiProvinceItem {

    @SerializedName("iso")
    private String iso;

    @SerializedName("name")
    private String name;
    
    @SerializedName("province")
    private String province;
    
    @SerializedName("lat")
    private String lat;
    
    @SerializedName("long")
    private String logt;

    public String getIso() { return iso; }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLogt() {
        return logt;
    }

    public void setLogt(String logt) {
        this.logt = logt;
    }
}
