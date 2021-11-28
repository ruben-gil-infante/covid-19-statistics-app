package com.ruben.covid_19_statistics_app.ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.constants.AppConstants;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.IOnListWithFinderItemClicked;
import com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.ListWithFinder;
import com.ruben.covid_19_statistics_app.uicomponents.networkError.ErrorLayout;
import com.ruben.covid_19_statistics_app.ui.viewmodels.RegionsViewModel;

public class RegionsFragment extends Fragment implements IOnListWithFinderItemClicked {

    private static final String TAG = "RegionsFragment";

    private ListWithFinder listWithFinder;
    private RegionsViewModel regionsViewModel;
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
        regionsViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(RegionsViewModel.class);
        bindViews();
        setListeners();
        prepareErrorLayout();
        regionsViewModel.getAllRegions();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void bindViews() {
        listWithFinder = root.findViewById(R.id.regions_fragment_list_with_finder);
        progressBar = root.findViewById(R.id.regions_fragment_progress_bar);
        errorLayout = root.findViewById(R.id.regions_fragment_error_layout);
    }

    private void setListeners() {
        regionsViewModel.getFinderItemsList().observe((LifecycleOwner) this, list -> {
            listWithFinder.setData(list, this);
            listWithFinder.setVisibility(View.VISIBLE);
        });

        regionsViewModel.getProgressBar().observe((LifecycleOwner) this, showPrgoressBar -> {
            if(showPrgoressBar) {
                listWithFinder.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        regionsViewModel.getErrorLayout().observe((LifecycleOwner) this, showErrorLayout -> {
            if(showErrorLayout) {
                listWithFinder.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
            } else {
                errorLayout.setVisibility(View.GONE);
            }
        });
    }

    private void prepareErrorLayout() {
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regionsViewModel.getAllRegions();
            }
        });
    }

    @Override
    public void onItemSelected(int position) {
        String iso = regionsViewModel.countrySelected(position);
        Bundle args = new Bundle();
        args.putString(AppConstants.ISO_KEY, iso);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, ProvinceFragment.class, args, ProvinceFragment.TAG)
                .addToBackStack(RegionsFragment.TAG)
                .commit();
    }
}