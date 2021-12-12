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
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProvinceFragment extends Fragment implements ProvinceListAdapter.IOnProvinceSelected {

    public static final String TAG = "ProvinceFragment";

    private ProvinceViewModel provinceViewModel;
    private View root;
    private String iso;

    @BindView(R.id.province_fragment_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.province_fragment_provinces_list_recycler_view)
    RecyclerView provinceListRecyclerView;
    @BindView(R.id.province_fragment_error_layout)
    ErrorLayout errorLayout;

    public static ProvinceFragment newInstance() {
        return new ProvinceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.province_fragment, container, false);
        ButterKnife.bind(this, root);
        provinceViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProvinceViewModel.class);
        setListeners();
        setObservables();
        if(getArguments() != null) {
            iso = getArguments().getString(AppConstants.ISO_KEY);
        }
        getData();
        return root;
    }

    private void setListeners() {
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provinceViewModel.getProvinceList();
            }
        });
    }

    private void setObservables() {
        provinceViewModel.getShowProgressBar().observe(getViewLifecycleOwner(), showProgressBar -> {
            if(showProgressBar) {
                progressBar.setVisibility(View.VISIBLE);
                provinceListRecyclerView.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        provinceViewModel.getProvinceList().observe(getViewLifecycleOwner(), provinceList -> {
            setProvincesRecyclerView((ArrayList<ApiProvinceItem>) provinceList);
        });

        provinceViewModel.getShowErrorLayout().observe(getViewLifecycleOwner(), showErrorLayout -> {
            if(showErrorLayout) {
                errorLayout.setVisibility(View.VISIBLE);
                provinceListRecyclerView.setVisibility(View.GONE);
            } else {
                errorLayout.setVisibility(View.GONE);
            }
        });
    }

    private void getData() {
        provinceViewModel.getAllProvincesFromCountry(iso);
    }

    private void setProvincesRecyclerView(ArrayList<ApiProvinceItem> provincesName) {
        ProvinceListAdapter adapter = new ProvinceListAdapter(provincesName, this);
        provinceListRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        provinceListRecyclerView.setLayoutManager(layoutManager);
        provinceListRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void selectedProvince(String provinceName, String latitude, String longitude) {
        Bundle args = new Bundle();
        args.putString(AppConstants.ISO_KEY, iso);
        args.putString(AppConstants.PROVINCE_NAME_KEY, provinceName);
        args.putString(AppConstants.LATITUDE_KEY, latitude);
        args.putString(AppConstants.LONGITUDE_KEY, longitude);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, ReportsFragment.class, args, ReportsFragment.TAG)
                .addToBackStack(ProvinceFragment.TAG)
                .commit();
    }
}