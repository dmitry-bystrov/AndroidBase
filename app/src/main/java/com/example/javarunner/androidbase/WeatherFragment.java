package com.example.javarunner.androidbase;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherFragment extends Fragment {
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
    }
}
