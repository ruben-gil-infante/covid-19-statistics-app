package com.ruben.covid_19_statistics_app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegions;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    
    private ApiRegions apiRegions;

    public RecyclerViewAdapter(ApiRegions apiRegions) {
        this.apiRegions = apiRegions;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(apiRegions.getApiRegionItemList().get(position));
    }

    @Override
    public int getItemCount() {
        return apiRegions.getApiRegionItemList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
        }

        public void setItem(ApiRegionItem item) {
            name.setText(item.getName() + " - " + item.getIso());
        }

    }
}
