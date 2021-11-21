package com.ruben.covid_19_statistics_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.IOnListWithFinderItemClicked;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinder;
import com.ruben.covid_19_statistics_app.useCases.GetRegionsUseCase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button requestBtn;
    private ProgressBar progressBar;
    private ListWithFinder listWithFinder;

    public static final String TAG = "MainActivity";

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
        listWithFinder = findViewById(R.id.activity_main_list_with_fander);
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
        // Get Regions
        requestBtn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        GetRegionsUseCase getRegionsUseCase = new GetRegionsUseCase();
        getRegionsUseCase.getAllRegions().enqueue(new Callback<ApiRegions>() {
            @Override
            public void onResponse(Call<ApiRegions> call, Response<ApiRegions> response) {
                int stopToDebug = 0;
                progressBar.setVisibility(View.GONE);
                setUpListWithFinderComponent(response.body());
            }

            @Override
            public void onFailure(Call<ApiRegions> call, Throwable t) {
                int stopToDebug = 0;
            }
        });
    }

    private void setUpListWithFinderComponent(ApiRegions regions) {
        ArrayList<String> itemsToList = new ArrayList<>();
        for(ApiRegionItem item : regions.getApiRegionItemList()) {
            itemsToList.add(item.getName());
        }
        listWithFinder.setData(itemsToList, new IOnListWithFinderItemClicked() {
            @Override
            public void onItemSelected(int position) {
                Log.d(TAG, "Element selected + " + itemsToList.get(position));
            }
        });
        listWithFinder.setVisibility(View.VISIBLE);
    }
}