package com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;

import java.util.ArrayList;

public class ListWithFinder extends ConstraintLayout {

    private View root;
    private EditText etFinder;
    private RecyclerView itemsRecyclerView;


    public ListWithFinder(@NonNull Context context) {
        super(context);
        initViews();
    }

    public ListWithFinder(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public ListWithFinder(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    public ListWithFinder(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    private void initViews() {
        root = inflate(getContext(), R.layout.list_with_finder_layout, this);
        bindViews();
    }

    private void bindViews() {
        etFinder = root.findViewById(R.id.list_with_finder_layout_edit_text);
        itemsRecyclerView = root.findViewById(R.id.list_with_finder_layout_recycler_view);
    }

    public void setData(ArrayList<String> items, IOnListWithFinderItemClicked listWithFinderItemClicked) {
        ListWithFinderRecyclerViewAdapter adapter = new ListWithFinderRecyclerViewAdapter(items, listWithFinderItemClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
