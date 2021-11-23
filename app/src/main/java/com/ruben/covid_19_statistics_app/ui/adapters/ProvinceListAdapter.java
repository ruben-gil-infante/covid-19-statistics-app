package com.ruben.covid_19_statistics_app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruben.covid_19_statistics_app.R;

import java.util.ArrayList;


public class ProvinceListAdapter extends RecyclerView.Adapter<ProvinceListAdapter.ViewHolder> {

    private ArrayList<String> provincesNameList;

    public ProvinceListAdapter(ArrayList<String> provincesNameList) {
        this.provincesNameList = provincesNameList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_list_item, parent, false);
        ViewHolder vh = new ViewHolder(root);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(provincesNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return provincesNameList != null ? provincesNameList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProvinceName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProvinceName = itemView.findViewById(R.id.province_item_province_name);
        }

        public void bindItem(String provinceName) {
            tvProvinceName.setText(provinceName);
        }
    }
}
