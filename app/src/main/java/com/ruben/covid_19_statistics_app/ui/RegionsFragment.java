package com.ruben.covid_19_statistics_app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.IOnListWithFinderItemClicked;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinder;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinderItem;
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;
import com.ruben.covid_19_statistics_app.viewmodels.RegionsViewModel;

public class RegionsFragment extends Fragment implements IOnListWithFinderItemClicked {

    private static final String TAG = "RegionsFragment";

    private ListWithFinder listWithFinder;
    private RegionsViewModel mViewModel;
    private ProgressBar progressBar;
    private ErrorLayout errorLayout;
    private View root;

    public static RegionsFragment newInstance() {
        return new RegionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.regions_fragment, container, false);
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(RegionsViewModel.class);
        bindViews();
        setListeners();
        prepareErrorLayout();
        mViewModel.getAllRegions();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void bindViews() {
        listWithFinder = root.findViewById(R.id.regions_fragment_list_with_fander);
        progressBar = root.findViewById(R.id.regions_fragment_progress_bar);
        errorLayout = root.findViewById(R.id.regions_fragment_error_layout);
    }

    private void setListeners() {
        mViewModel.getFinderItemsList().observe((LifecycleOwner) this, list -> {
            listWithFinder.setData(list, this);
            listWithFinder.setVisibility(View.VISIBLE);
        });

        mViewModel.getProgressBar().observe((LifecycleOwner) this, showPrgoressBar -> {
            if(showPrgoressBar) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        mViewModel.getErrorLayout().observe((LifecycleOwner) this, showErrorLayout -> {
            if(showErrorLayout) {
                errorLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void prepareErrorLayout() {
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Retry call button pressed");
            }
        });
    }

    @Override
    public void onItemSelected(int position) {

    }
}