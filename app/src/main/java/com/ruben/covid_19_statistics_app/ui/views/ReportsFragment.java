package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.ui.viewmodels.ReportsViewModel;
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;

public class ReportsFragment extends Fragment {

    public static final String TAG = "ReportsFragment";

    private ReportsViewModel reportsViewModel;
    private View root;
    private View headerInfoWrapper;
    private String regionProvince;
    private ProgressBar progressBar;
    private View wrapper;

    private TextView tvConfirmed;
    private TextView tvProvinceName;
    private TextView tvLastUpdated;
    private TextView tvDeaths;
    private TextView tvRecovered;
    private String iso;
    private ImageView startBtn;

    private String latitude;
    private String longitude;

    private ErrorLayout errorLayout;

    private View availableInFutureVersionsPopUp;

    private MapView googleMaps;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.report_fragment, container, false);
        reportsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ReportsViewModel.class);
        bindViews();
        setListeners();
        setObservables();
        if(getArguments() != null) {
            regionProvince = getArguments().getString(AppConstants.PROVINCE_NAME_KEY);
            iso = getArguments().getString(AppConstants.ISO_KEY);
            latitude = getArguments().getString(AppConstants.LATITUDE_KEY);
            longitude = getArguments().getString(AppConstants.LONGITUDE_KEY);
        }

        getData();
        setUpMap();
        return root;
    }

    private void bindViews() {
        headerInfoWrapper = root.findViewById(R.id.report_fragment_info_wrapper);
        tvConfirmed = root.findViewById(R.id.report_fragment_confirmed_cases);
        tvLastUpdated = root.findViewById(R.id.report_fragment_last_update);
        tvDeaths = root.findViewById(R.id.report_fragment_total_deaths);
        tvRecovered = root.findViewById(R.id.report_fragment_recovered);
        tvProvinceName = root.findViewById(R.id.report_fragment_province_name);
        googleMaps = root.findViewById(R.id.report_fragment_map_view);
        startBtn = root.findViewById(R.id.report_fragment_star_button);
        errorLayout = root.findViewById(R.id.report_fragment_error_layout);
        progressBar = root.findViewById(R.id.report_fragment_progress_bar);
        wrapper = root.findViewById(R.id.report_fragment_wrapper);
        availableInFutureVersionsPopUp = root.findViewById(R.id.not_available_function_yet_text);
    }

    private void setListeners() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProvinceAsFavourite();
            }
        });

        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportsViewModel.getReport();
            }
        });

        availableInFutureVersionsPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideNotAvailablePopUp();
            }
        });
    }

    private void setObservables() {
        reportsViewModel.getReports().observe(getViewLifecycleOwner(), reports -> {
            tvDeaths.setText(reports.getDeaths());
            tvConfirmed.setText(reports.getConfirmedDiff());
            tvRecovered.setText(reports.getRecoveredDiff());
            tvProvinceName.setText(regionProvince);
            wrapper.setVisibility(View.VISIBLE);
            // last updated --> data displayed in future versions
        });

        reportsViewModel.getProgressBar().observe(getViewLifecycleOwner(), showProgressBar -> {
            if(showProgressBar) {
                wrapper.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        reportsViewModel.getShowErrorLayout().observe(getViewLifecycleOwner(), showErrorLayout -> {
            if(showErrorLayout) {
                wrapper.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
            } else {
                errorLayout.setVisibility(View.GONE);
            }
        });

    }

    private void getData() {
        reportsViewModel.getReport(iso, "2021-11-26", regionProvince);
    }

    private void setUpMap() {
        // MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.mapstyle_night);
        googleMaps.onCreate(new Bundle());
        googleMaps.onStart();
        googleMaps.getMapAsync(google -> {
            // google.setMapStyle(mapStyleOptions);
            LatLng province = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            google.addMarker(new MarkerOptions()
                    .position(province)
                    .title("Marker in " + regionProvince));
            google.moveCamera(CameraUpdateFactory.newLatLngZoom(province, 10f));
        });
    }

    private void setProvinceAsFavourite() {
        startBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_selected));
        startBtn.startAnimation(AnimationUtils.loadAnimation(
                getContext(),
                R.anim.scale_in
        ));

        showNotAvailablePopUp();
    }

    private void showNotAvailablePopUp() {
        availableInFutureVersionsPopUp.setVisibility(View.VISIBLE);
        availableInFutureVersionsPopUp.startAnimation(AnimationUtils.loadAnimation(
                getContext(),
                R.anim.not_available_yet_pop_up_slide_in
        ));
    }

    private synchronized void hideNotAvailablePopUp() {
        availableInFutureVersionsPopUp.startAnimation(AnimationUtils.loadAnimation(
                getContext(),
                R.anim.not_available_yet_pop_up_slide_out
        ));
        startBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_not_selected));
        availableInFutureVersionsPopUp.setVisibility(View.GONE);
    }
}