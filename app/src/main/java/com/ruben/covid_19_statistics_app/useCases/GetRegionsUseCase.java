package com.ruben.covid_19_statistics_app.useCases;

import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.network.regions.repository.RegionsRepository;

import retrofit2.Call;

public class GetRegionsUseCase {

    private RegionsRepository regionsRepository;

    public GetRegionsUseCase() {
        regionsRepository = RegionsRepository.getInstance();
    }

    public Call<ApiRegions> getAllRegions() {
        return regionsRepository.getAllRegions();
    }

}

