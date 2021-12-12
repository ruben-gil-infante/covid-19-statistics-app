package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.network.reports.model.ApiReportsItem;
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportsChartActivity extends AppCompatActivity {

    private ApiReportsItem reportItem;

    @BindView(R.id.activity_report_chart_wrapper)
    ConstraintLayout wrapper;
    @BindView(R.id.activity_reports_chart_error_layout)
    ErrorLayout errorLayout;
    @BindView(R.id.activity_reports_today_data_info_wrapper)
    LinearLayout todayInfoWrapper;
    @BindView(R.id.activity_reports_all_data_info_wrapper)
    LinearLayout allDataInfoWrapper;
    @BindView(R.id.activity_reports_chart_active)
    TextView tvActive;
    @BindView(R.id.activity_reports_chart_all_active)
    TextView tvAllActive;
    @BindView(R.id.activity_reports_chart_deaths)
    TextView tvDeaths;
    @BindView(R.id.activity_reports_chart_all_deaths)
    TextView tvAllDeaths;
    @BindView(R.id.activity_reports_chart_confirmed_cases)
    TextView tvConfirmed;
    @BindView(R.id.activity_reports_chart_all_confirmed_cases)
    TextView tvAllConfirmed;
    @BindView(R.id.activity_reports_chart_recovered)
    TextView tvRecovered;
    @BindView(R.id.activity_reports_chart_all_recovered)
    TextView tvAllRecovered;
    @BindView(R.id.activity_reports_chart_last_update)
    TextView tvLastUpdate;
    @BindView(R.id.activity_reports_chart_root)
    ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_chart);
        ButterKnife.bind(this);
        if(getIntent() != null) {
            reportItem = getIntent().getParcelableExtra(AppConstants.REPORT_DATA);
        }
        setData();
        animateEnter();
    }

    private void setData() {
        if(reportItem == null) {
            wrapper.setVisibility(View.GONE);
            errorLayout.hideRetryBtn();
            errorLayout.setVisibility(View.VISIBLE);
        } else {
            tvActive.setText(reportItem.getActiveDiff());
            tvAllActive.setText(reportItem.getActive());
            tvAllDeaths.setText(reportItem.getDeaths() + " (" +
                    reportItem.getFatalityRate() + "% )");
            tvConfirmed.setText(reportItem.getConfirmedDiff());
            tvAllConfirmed.setText(reportItem.getConfirmed());
            tvRecovered.setText(reportItem.getRecovered());
            tvAllRecovered.setText(reportItem.getRecoveredDiff());
            tvLastUpdate.setText(getResources().getString(R.string.last_update)
            + " " + (reportItem.getLastUpdated() == null ? " - " : reportItem.getLastUpdated()));
        }
    }

    private void animateEnter() {
        root.startAnimation(AnimationUtils.loadAnimation(
                this,
                R.anim.slide_in_right
        ));
    }
}