package com.ruben.covid_19_statistics_app.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ruben.covid_19_statistics_app.R;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.
                beginTransaction().
                add(R.id.activity_main_frame_layout, RegionsFragment.class, null, "RegionsFragment").
                commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}