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

    private ArrayList<ListWithFinderItem> items;
    private IOnListWithFinderItemClicked listWithFinderItemClicked;

    public ListWithFinderRecyclerViewAdapter(ArrayList<ListWithFinderItem> items, IOnListWithFinderItemClicked listWithFinderItemClicked) {
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
        holder.setListWithFinderItem(items.get(position));
        holder.setInfo();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemName;
        private View root;
        private ListWithFinderItem item;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tvItemName = root.findViewById(R.id.item_name);
            setListeners();
        }

        public void setListWithFinderItem(ListWithFinderItem item) {
            this.item = item;
        }

        private void setListeners() {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listWithFinderItemClicked.onItemSelected(item.getOriginalPosition());
                }
            });
        }

        public void setInfo() {
            tvItemName.setText(item.getText());
        }
    }
}
