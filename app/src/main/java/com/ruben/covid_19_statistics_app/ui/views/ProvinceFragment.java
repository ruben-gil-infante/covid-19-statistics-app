package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvinceItem;
import com.ruben.covid_19_statistics_app.ui.adapters.ProvinceListAdapter;
import com.ruben.covid_19_statistics_app.ui.viewmodels.ProvinceViewModel;

import java.util.ArrayList;

public class ProvinceFragment extends Fragment {

    private ProvinceViewModel provinceViewModel;
    private ProgressBar progressBar;
    private View root;
    private String iso;
    private RecyclerView provinceListRecyclerView;

    public static ProvinceFragment newInstance() {
        return new ProvinceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.province_fragment, container, false);
        provinceViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProvinceViewModel.class);
        bindViews();
        setListeners();
        setObservables();
        if(getArguments() != null) {
            iso = getArguments().getString(AppConstants.ISO_KEY);
        }
        getData();
        return root;
    }

    private void bindViews() {
        progressBar = root.findViewById(R.id.province_fragment_progress_bar);
        provinceListRecyclerView = root.findViewById(R.id.province_fragment_provinces_list_recycler_view);
    }

    private void setListeners() {

    }

    private void setObservables() {
        provinceViewModel.getShowProgressBar().observe(getViewLifecycleOwner(), showProgressBar -> {
            if(showProgressBar) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        provinceViewModel.getProvinceList().observe(getViewLifecycleOwner(), provinceList -> {
            setProvincesRecyclerView((ArrayList<ApiProvinceItem>) provinceList);
        });
    }

    private void getData() {
        provinceViewModel.getAllProvincesFromCountry(iso);
    }

    private void setProvincesRecyclerView(ArrayList<ApiProvinceItem> provincesName) {
        ProvinceListAdapter adapter = new ProvinceListAdapter(provincesName);
        provinceListRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        provinceListRecyclerView.setLayoutManager(layoutManager);
    }



}