package com.ruben.covid_19_statistics_app.network.provinces.model;

import com.google.gson.annotations.SerializedName;

public class ApiProvinceItem {

    public static final String UNKNOWN_PROVINCE = "Unknown";

    @SerializedName("iso")
    private String iso;

    @SerializedName("name")
    private String name;
    
    @SerializedName("province")
    private String province;
    
    @SerializedName("lat")
    private String latitude;
    
    @SerializedName("long")
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
