package com.ruben.covid_19_statistics_app.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ruben.covid_19_statistics_app.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: id of the view to make the fragment commit transaction -> activity_main_frame_layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.
                beginTransaction().
                add(R.id.activity_main_frame_layout, RegionsFragment.class, null, "RegionsFragment").
                commit();
    }
}