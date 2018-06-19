package com.example.javarunner.androidbase;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class SettingsFragment extends Fragment {
    private boolean weatherFramePresent;
    private SettingsParcel settingsParcel;

    private Spinner spinner;
    private Button button;
    private CheckBox checkBoxTemperature;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxHumidity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Locations,
                android.R.layout.simple_spinner_dropdown_item);

        spinner = getActivity().findViewById(R.id.spinnerLocation);
        spinner.setAdapter(adapter);

        View weatherFrame = getActivity().findViewById(R.id.fragment_weather);
        weatherFramePresent = weatherFrame != null && weatherFrame.getVisibility() == View.VISIBLE;


        if (savedInstanceState != null) {
            settingsParcel = (SettingsParcel) savedInstanceState.getSerializable(SettingsParcel.SETTINGS_PARCEL);
        }

        if (settingsParcel == null) {
            settingsParcel = new SettingsParcel(0,
                    spinner.getItemAtPosition(0).toString(),
                    true,
                    false,
                    false);
        }

        button = getActivity().findViewById(R.id.buttonShow);
        checkBoxTemperature = getActivity().findViewById(R.id.checkBoxTemperature);
        checkBoxPressure = getActivity().findViewById(R.id.checkBoxPressure);
        checkBoxHumidity = getActivity().findViewById(R.id.checkBoxHumidity);

        updateControls();
        setupListeners();
        updateWeatherFragment();
    }

    private void updateControls() {
        spinner.setSelection(settingsParcel.getLocationIndex());
        button.setVisibility(weatherFramePresent ? View.GONE : View.VISIBLE);
        checkBoxTemperature.setChecked(settingsParcel.showTemperature());
        checkBoxPressure.setChecked(settingsParcel.showPressure());
        checkBoxHumidity.setChecked(settingsParcel.showHumidity());
    }

    private void updateSettingsParcel() {
        settingsParcel = new SettingsParcel(spinner.getSelectedItemPosition(),
                spinner.getSelectedItem().toString(),
                checkBoxTemperature.isChecked(),
                checkBoxPressure.isChecked(),
                checkBoxHumidity.isChecked());
    }

    private void setupListeners() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSettingsParcel();
                updateWeatherFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherActivity();
            }
        });

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSettingsParcel();
                updateWeatherFragment();
            }
        };

        checkBoxTemperature.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxPressure.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxHumidity.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SettingsParcel.SETTINGS_PARCEL, settingsParcel);
    }

    private void updateWeatherFragment() {
        if (!weatherFramePresent) return;

        WeatherFragment weatherFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.fragment_weather);
        if (weatherFragment == null || !weatherFragment.getSettingsParcel().equals(settingsParcel)) {
            weatherFragment = WeatherFragment.newInstance(settingsParcel);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_weather, weatherFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
    }

    private void showWeatherActivity() {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra(SettingsParcel.SETTINGS_PARCEL, settingsParcel);
        startActivity(intent);
    }
}
