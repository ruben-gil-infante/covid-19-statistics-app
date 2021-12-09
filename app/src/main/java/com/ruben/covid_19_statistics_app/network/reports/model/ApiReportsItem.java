package com.ruben.covid_19_statistics_app.network.reports.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ApiReportsItem implements Parcelable {

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

    @SerializedName("recovered_diff")
    private String recoveredDiff;

    @SerializedName("last_update")
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(confirmed);
        dest.writeString(deaths);
        dest.writeString(recovered);
        dest.writeString(confirmedDiff);
        dest.writeString(recoveredDiff);
        dest.writeString(lastUpdated);
        dest.writeString(active);
        dest.writeString(activeDiff);
        dest.writeString(fatalityRate);
    }

    protected ApiReportsItem(Parcel in) {
        date = in.readString();
        confirmed = in.readString();
        deaths = in.readString();
        recovered = in.readString();
        confirmedDiff = in.readString();
        recoveredDiff = in.readString();
        lastUpdated = in.readString();
        active = in.readString();
        activeDiff = in.readString();
        fatalityRate = in.readString();
    }

    public static final Creator<ApiReportsItem> CREATOR = new Creator<ApiReportsItem>() {
        @Override
        public ApiReportsItem createFromParcel(Parcel in) {
            return new ApiReportsItem(in);
        }

        @Override
        public ApiReportsItem[] newArray(int size) {
            return new ApiReportsItem[size];
        }
    };
}
