package com.ruben.covid_19_statistics_app.network.provinces.repository;

import android.os.Bundle;

import com.ruben.covid_19_statistics_app.network.constants;
import com.ruben.covid_19_statistics_app.network.provinces.datasource.ProvinceDataSource;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ProvinceRepository {

    private static ProvinceRepository instance;
    private ProvinceDataSource provinceDataSource;

    private ProvinceRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL) // TODO: Create newtorkConstants.class instead of constants
                .build();

        provinceDataSource = retrofit.create(ProvinceDataSource.class);
    }

    public static ProvinceRepository getInstance() {
        if(instance == null) {
            instance = new ProvinceRepository();
        }
        return instance;
    }

    public Call<ApiProvince> getAllProvinces() {
        return provinceDataSource.getAllProvinces();
    }
}
