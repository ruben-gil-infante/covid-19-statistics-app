package com.ruben.covid_19_statistics_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvince;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.ui.adapters.RecyclerViewAdapter;
import com.ruben.covid_19_statistics_app.useCases.GetAllProvincesUseCase;
import com.ruben.covid_19_statistics_app.useCases.GetRegionsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button requestBtn;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setListeners();
    }

    private void bindViews() {
        requestBtn = findViewById(R.id.activity_main_request_btn);
        progressBar = findViewById(R.id.activity_main_progress_bar);
        recyclerView = findViewById(R.id.activity_main_recycler_view);
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
        /* Get Provinces
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

         */

        // Get Regions
        requestBtn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        GetRegionsUseCase getRegionsUseCase = new GetRegionsUseCase();
        getRegionsUseCase.getAllRegions().enqueue(new Callback<ApiRegions>() {
            @Override
            public void onResponse(Call<ApiRegions> call, Response<ApiRegions> response) {
                int stopToDebug = 0;
                progressBar.setVisibility(View.GONE);
                setUpRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ApiRegions> call, Throwable t) {
                int stopToDebug = 0;
            }
        });

    }

    private void setUpRecyclerView(ApiRegions apiRegions) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(apiRegions);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setVisibility(View.VISIBLE);
    }
}