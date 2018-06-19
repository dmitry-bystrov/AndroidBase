package com.example.javarunner.androidbase;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class WeatherFragment extends Fragment {
    private MyRecyclerViewAdapter adapter;

    public static WeatherFragment newInstance(SettingsParcel settingsParcel) {
        WeatherFragment weatherFragment = new WeatherFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SettingsParcel.SETTINGS_PARCEL, settingsParcel);
        weatherFragment.setArguments(bundle);

        return weatherFragment;
    }

    public SettingsParcel getSettingsParcel() {
        return (SettingsParcel) getArguments().getSerializable(SettingsParcel.SETTINGS_PARCEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        SettingsParcel settingsParcel = getSettingsParcel();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        int[] recyclerViewData = getResources().getIntArray(R.array.temperature_history);
        adapter = new MyRecyclerViewAdapter(recyclerViewData);
        recyclerView.setAdapter(adapter);

        if (settingsParcel != null) {
            updateWeather(view, settingsParcel);
        }

        return view;
    }

    private void updateWeather(View view, SettingsParcel settingsParcel) {
        TextView textViewHeader = view.findViewById(R.id.header_text);
        TextView textViewTemperature = view.findViewById(R.id.temperature_text);
        TextView textViewPressure = view.findViewById(R.id.pressure_text);
        TextView textViewHumidity = view.findViewById(R.id.humidity_text);

        textViewHeader.setText(settingsParcel.getLocationName());
        textViewTemperature.setVisibility(settingsParcel.showTemperature() ? View.VISIBLE : View.GONE);
        textViewPressure.setVisibility(settingsParcel.showPressure() ? View.VISIBLE : View.GONE);
        textViewHumidity.setVisibility(settingsParcel.showHumidity() ? View.VISIBLE : View.GONE);

        final int temperatureValue = new Random().nextInt(80) - 40;

        textViewTemperature.setText(String.format(Locale.getDefault(),
                "%s: %d°C", getString(R.string.temperature_text), temperatureValue));
        textViewPressure.setText(String.format(Locale.getDefault(),
                "%s: %d %s", getString(R.string.pressure_text), new Random().nextInt(40) + 740, getString(R.string.pressure_unit)));
        textViewHumidity.setText(String.format(Locale.getDefault(),
                "%s: %d%%", getString(R.string.humidity_text), new Random().nextInt(100) + 1));

        textViewTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addValue(temperatureValue);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
