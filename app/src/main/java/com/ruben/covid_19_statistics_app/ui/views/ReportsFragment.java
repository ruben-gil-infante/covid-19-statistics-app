package com.ruben.covid_19_statistics_app.ui.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
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
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReportsItem;
import com.ruben.covid_19_statistics_app.ui.viewmodels.ReportsViewModel;
import com.ruben.covid_19_statistics_app.uicomponents.datepicker.CustomDatePicker;
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;
import com.ruben.covid_19_statistics_app.utils.DateUtils;
import com.ruben.covid_19_statistics_app.utils.NumberUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportsFragment extends Fragment {

    public static final String TAG = "ReportsFragment";

    private ReportsViewModel reportsViewModel;
    private View root;
    private String latitude;
    private String longitude;
    private String regionProvince;
    private ApiReportsItem reportItem;
    String iso;

    @BindView(R.id.report_fragment_info_wrapper)
    View headerInfoWrapper;
    @BindView(R.id.province_fragment_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.report_fragment_wrapper)
    View wrapper;
    @BindView(R.id.report_fragment_map_load_error_layout)
    View mapErrorLayout;
    @BindView(R.id.report_fragment_confirmed_cases)
    TextView tvConfirmed;
    @BindView(R.id.report_fragment_province_name)
    TextView tvProvinceName;
    @BindView(R.id.report_fragment_last_update)
    TextView tvLastUpdated;
    @BindView(R.id.report_fragment_total_deaths)
    TextView tvDeaths;
    @BindView(R.id.report_fragment_recovered)
    TextView tvRecovered;
    @BindView(R.id.report_fragment_star_button)
    ImageView startBtn;
    @BindView(R.id.report_fragment_error_layout)
    ErrorLayout errorLayout;
    @BindView(R.id.not_available_function_yet_text)
    View availableInFutureVersionsPopUp;
    @BindView(R.id.report_fragment_map_view)
    MapView googleMaps;
    @BindView(R.id.report_fragment_see_more_data_btn)
    TextView seeMoreData;
    @BindView(R.id.report_fragment_calendar_btn)
    ImageView calendarBtn;
    @BindView(R.id.report_fragment_data_title)
    TextView dataTitle;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.report_fragment, container, false);
        ButterKnife.bind(this, root);
        reportsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ReportsViewModel.class);
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
                calendarBtn.callOnClick();
            }
        });

        availableInFutureVersionsPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideNotAvailablePopUp();
            }
        });

        seeMoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreData();
            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDatePicker customDatePicker = new CustomDatePicker();
                customDatePicker.setOnDataSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String parsedDate = DateUtils.parseDate(year, month, day);
                        reportsViewModel.getReport(parsedDate);
                    }
                });
                customDatePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });
    }

    private void setObservables() {
        reportsViewModel.getReports().observe(getViewLifecycleOwner(), reports -> {
            reportItem = reports;
            tvDeaths.setText(reports.getDeaths() + " " + NumberUtils.makeThePercentage(reports.getFatalityRate()));
            tvConfirmed.setText(reports.getConfirmedDiff());
            tvRecovered.setText(reports.getRecoveredDiff());
            tvProvinceName.setText(regionProvince);
            wrapper.setVisibility(View.VISIBLE);
            dataTitle.setText(getResources().getString(R.string.data_from) + " " + reports.getDate());
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
        reportsViewModel.getReport(iso, regionProvince);
    }

    private void setUpMap() {
        // MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.mapstyle_night);
        try {
            googleMaps.setVisibility(View.VISIBLE);
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
        } catch (Exception e) {
            googleMaps.setVisibility(View.GONE);
            mapErrorLayout.setVisibility(View.VISIBLE);
        }
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

    private void seeMoreData() {
        Intent intent = new Intent(getActivity(), ReportsChartActivity.class);
        intent.putExtra(AppConstants.REPORT_DATA, reportItem);
        getActivity().startActivity(intent);
    }
}