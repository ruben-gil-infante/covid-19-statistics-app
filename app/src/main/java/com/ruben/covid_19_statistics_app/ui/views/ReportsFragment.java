package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.ui.viewmodels.ReportsViewModel;

public class ReportsFragment extends Fragment {

    private ReportsViewModel reportsViewModel;
    private View root;
    private String regionProvince;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.region_fragment, container, false);
        reportsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ReportsViewModel.class);
        bindViews();
        setListeners();
        setObservables();
        if(getArguments() != null) {
            regionProvince = getArguments().getString(AppConstants.PROVINCE_NAME_KEY);
        }
        getData();
        return root;
    }

    private void bindViews() {

    }

    private void setListeners() {

    }

    private void setObservables() {

    }

    private void getData() {
        reportsViewModel.getReport("2021-11-23", regionProvince);
    }

}