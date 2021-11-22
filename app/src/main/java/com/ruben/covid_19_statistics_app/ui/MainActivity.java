package com.ruben.covid_19_statistics_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.icu.text.Edits;
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
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinderItem;
import com.ruben.covid_19_statistics_app.useCases.GetRegionsUseCase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: id of the view to make the fragment commit transaction -> activity_main_frame_layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.
                beginTransaction().
                add(R.id.activity_main_frame_layout, RegionsFragment.class, null, "").
                commit();
    }
}