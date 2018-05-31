package com.example.javarunner.androidbase;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class WeatherActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                SettingsParcel settingsParcel = (SettingsParcel) bundle.getSerializable(SettingsParcel.SETTINGS_PARCEL);
                WeatherFragment weatherFragment = WeatherFragment.newInstance(settingsParcel);
                getFragmentManager().beginTransaction().add(android.R.id.content, weatherFragment).commit();
            }
        }
    }


}
