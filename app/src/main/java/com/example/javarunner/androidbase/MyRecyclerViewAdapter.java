package com.example.javarunner.androidbase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private int[] dataSource;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.history_of_temperature);
        }
    }

    public MyRecyclerViewAdapter(int[] dataSource) {
        this.dataSource = dataSource;
    }

    public void addValue(int value) {
        int[] newRecyclerViewData = new int[dataSource.length + 1];
        newRecyclerViewData[0] = value;
        for (int i = 1; i < newRecyclerViewData.length; i++) {
            newRecyclerViewData[i] = dataSource[i - 1];
        }

        dataSource = newRecyclerViewData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.format(Locale.getDefault(), "%d°C", dataSource[position]));
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
