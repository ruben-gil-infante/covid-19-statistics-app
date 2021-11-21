package com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;

import java.util.ArrayList;


public class ListWithFinderRecyclerViewAdapter extends RecyclerView.Adapter<ListWithFinderRecyclerViewAdapter.ListViewHolder> {

    private ArrayList<String> items;
    private IOnListWithFinderItemClicked listWithFinderItemClicked;

    public ListWithFinderRecyclerViewAdapter(ArrayList<String> items, IOnListWithFinderItemClicked listWithFinderItemClicked) {
        this.items = items;
        this.listWithFinderItemClicked = listWithFinderItemClicked;
    }

    @NonNull
    @Override
    public ListWithFinderRecyclerViewAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListWithFinderRecyclerViewAdapter.ListViewHolder holder, int position) {
        holder.setPosition(position);
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemName;
        private View root;
        int position;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tvItemName = root.findViewById(R.id.item_name);
            setListeners();
        }

        public void setPosition(int position) {
            this.position = position;
        }

        private void setListeners() {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listWithFinderItemClicked.onItemSelected(position);
                }
            });
        }

        public void bindItem(String item) {
            tvItemName.setText(item);
        }
    }
}
