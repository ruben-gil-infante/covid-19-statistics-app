package com.ruben.covid_19_statistics_app.useCases;

import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.provinces.repository.ProvinceRepository;

import retrofit2.Call;

public class GetAllProvincesUseCase {

    private ProvinceRepository provinceRepository;

    public GetAllProvincesUseCase() {
        provinceRepository = ProvinceRepository.getInstance();
    }

    public Call<ApiProvince> getAllProvincesUseCase() {
        return provinceRepository.getAllProvinces();
    }
        

}
