package com.ruben.covid_19_statistics_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.useCases.GetAllProvincesUseCase;
import com.ruben.covid_19_statistics_app.useCases.GetRegionsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setListeners();
        makeTheRequestsExample();
    }

    private void bindViews() {
        requestBtn = findViewById(R.id.activity_main_request_btn);
    }

    private void setListeners() {
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeTheRequestsExample();
            }
        });
    }

    private void makeTheRequestsExample() {
        // Get Provinces
        GetAllProvincesUseCase getAllProvincesUseCase = new GetAllProvincesUseCase();
        getAllProvincesUseCase.getAllProvincesUseCase().enqueue(new Callback<ApiProvince>() {
            @Override
            public void onResponse(Call<ApiProvince> call, Response<ApiProvince> response) {
                int stopToDebug = 0;
            }

            @Override
            public void onFailure(Call<ApiProvince> call, Throwable t) {
                int stopToDebug = 0;
            }
        });

        // Get Regions
        GetRegionsUseCase getRegionsUseCase = new GetRegionsUseCase();
        getRegionsUseCase.getAllRegions().enqueue(new Callback<ApiRegions>() {
            @Override
            public void onResponse(Call<ApiRegions> call, Response<ApiRegions> response) {
                int stopToDebug = 0;
            }

            @Override
            public void onFailure(Call<ApiRegions> call, Throwable t) {
                int stopToDebug = 0;
            }
        });

    }
}