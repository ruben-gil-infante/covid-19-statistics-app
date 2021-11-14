package com.ruben.covid_19_statistics_app.network.reports.repository;

import com.google.gson.annotations.SerializedName;

public class ApiReportsItem {

    @SerializedName("date")
    private String date;

    @SerializedName("confirmed")
    private String confirmed;

    @SerializedName("deaths")
    private String deaths;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("confirmed_diff")
    private String confirmedDiff;

    @SerializedName("recoered_diff")
    private String recoveredDiff;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("active")
    private String active;

    @SerializedName("active_diff")
    private String activeDiff;

    @SerializedName("fatality_rate")
    private String fatalityRate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getConfirmedDiff() {
        return confirmedDiff;
    }

    public void setConfirmedDiff(String confirmedDiff) {
        this.confirmedDiff = confirmedDiff;
    }

    public String getRecoveredDiff() {
        return recoveredDiff;
    }

    public void setRecoveredDiff(String recoveredDiff) {
        this.recoveredDiff = recoveredDiff;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveDiff() {
        return activeDiff;
    }

    public void setActiveDiff(String activeDiff) {
        this.activeDiff = activeDiff;
    }

    public String getFatalityRate() {
        return fatalityRate;
    }

    public void setFatalityRate(String fatalityRate) {
        this.fatalityRate = fatalityRate;
    }
}
