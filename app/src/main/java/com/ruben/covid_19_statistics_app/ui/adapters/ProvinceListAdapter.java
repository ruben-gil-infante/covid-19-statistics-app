package com.ruben.covid_19_statistics_app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;
import com.ruben.covid_19_statistics_app.network.provinces.model.ApiProvinceItem;
import com.ruben.covid_19_statistics_app.network.regions.model.ApiRegionItem;

import java.util.ArrayList;


public class ProvinceListAdapter extends RecyclerView.Adapter<ProvinceListAdapter.ViewHolder> {

    private ArrayList<ApiProvinceItem> provinceItemList;
    private IOnProvinceSelected onProvinceSelected;

    public ProvinceListAdapter(ArrayList<ApiProvinceItem> provinceItemList, IOnProvinceSelected onProvinceSelected) {
        this.provinceItemList = provinceItemList;
        this.onProvinceSelected = onProvinceSelected;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_list_item, parent, false);
        ViewHolder vh = new ViewHolder(root, onProvinceSelected);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(provinceItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return provinceItemList != null ? provinceItemList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View root;
        private TextView tvProvinceName;
        private TextView tvLatitude;
        private TextView tvLongitude;
        private IOnProvinceSelected onProvinceSelected;

        public ViewHolder(@NonNull View itemView, IOnProvinceSelected provinceSelected) {
            super(itemView);
            root = itemView;
            tvProvinceName = itemView.findViewById(R.id.province_item_province_name);
            tvLatitude = itemView.findViewById(R.id.province_list_item_latitude);
            tvLongitude = itemView.findViewById(R.id.province_list_item_logintude);
            onProvinceSelected = provinceSelected;
            setListeners();
        }

        public void bindItem(ApiProvinceItem apiProvinceItem) {
            tvProvinceName.setText(apiProvinceItem.getProvince());
            tvLatitude.setText(apiProvinceItem.getLatitude());
            tvLongitude.setText(apiProvinceItem.getLongitude());
            animate();
        }

        private void animate() {
            root.startAnimation(AnimationUtils.loadAnimation(
                    root.getContext(),
                    R.anim.slide_in_top
            ));
        }

        private void setListeners() {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProvinceSelected.selectedProvince(tvProvinceName.getText().toString());
                }
            });
        }
    }

    public interface IOnProvinceSelected {
        void selectedProvince(String provinceName);
    }
}
