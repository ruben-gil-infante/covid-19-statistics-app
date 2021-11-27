package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.ui.viewmodels.ReportsViewModel;

public class ReportsFragment extends Fragment {

    // TODO: ADD the progress bar
    private ReportsViewModel reportsViewModel;
    private View root;
    private String regionProvince;

    private TextView tvConfirmed;
    private TextView tvProvinceName;
    private TextView tvLastUpdated;
    private TextView tvDeaths;
    private TextView tvRecovered;
    private String iso;

    private String latitude;
    private String longitude;

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
        tvConfirmed = root.findViewById(R.id.report_fragment_confirmed_cases);
        tvLastUpdated = root.findViewById(R.id.report_fragment_last_update);
        tvDeaths = root.findViewById(R.id.report_fragment_total_deaths);
        tvRecovered = root.findViewById(R.id.report_fragment_recovered);
        tvProvinceName = root.findViewById(R.id.report_fragment_province_name);
        googleMaps = root.findViewById(R.id.report_fragment_map_view);
    }

    private void setListeners() {

    }

    private void setObservables() {
        reportsViewModel.getReports().observe(getViewLifecycleOwner(), reports -> {
            tvDeaths.setText(reports.getDeaths());
            tvConfirmed.setText(reports.getConfirmedDiff());
            tvRecovered.setText(reports.getRecoveredDiff());
            tvProvinceName.setText(regionProvince);
            // last updated --> data displayed in future versions
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
            google.moveCamera(CameraUpdateFactory.newLatLngZoom(province, 5f));
        });
    }

}