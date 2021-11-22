package com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ListWithFinder extends ConstraintLayout {

    private View root;
    private EditText etFinder;
    private RecyclerView itemsRecyclerView;
    private ListWithFinderRecyclerViewAdapter adapter;
    private ArrayList<ListWithFinderItem> items;
    private ArrayList<ListWithFinderItem> filteredItems;


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
        setListeners();
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();
    }

    private void bindViews() {
        etFinder = root.findViewById(R.id.list_with_finder_layout_edit_text);
        itemsRecyclerView = root.findViewById(R.id.list_with_finder_layout_recycler_view);
    }

    public void setData(ArrayList<ListWithFinderItem> items, IOnListWithFinderItemClicked listWithFinderItemClicked) {
        this.items.addAll(items);
        filteredItems.addAll(this.items);
        adapter = new ListWithFinderRecyclerViewAdapter(filteredItems, listWithFinderItemClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setListeners() {
        etFinder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterElements(etFinder.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterElements(String text) {
        filteredItems.clear();
        if(text.trim().isEmpty()) {
            filteredItems.addAll(items);
        } else {
            Iterator<ListWithFinderItem> itemIterator = items.iterator();
            while(itemIterator.hasNext()) {
                ListWithFinderItem next = itemIterator.next();
                if(next.getText().trim().toLowerCase().contains(text.trim().toLowerCase())) {
                    filteredItems.add(next);
                 }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
