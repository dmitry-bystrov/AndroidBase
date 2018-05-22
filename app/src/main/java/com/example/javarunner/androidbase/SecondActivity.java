package com.example.javarunner.androidbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        TextView textViewHeader = findViewById(R.id.header_text);
        TextView textViewTemperature = findViewById(R.id.temperature_text);
        TextView textViewPressure = findViewById(R.id.pressure_text);
        TextView textViewHumidity = findViewById(R.id.humidity_text);

        textViewHeader.setText(bundle.getString(FirstActivity.EXTRA_CITY));
        textViewTemperature.setVisibility(bundle.getBoolean(FirstActivity.EXTRA_TEMPERATURE)?View.VISIBLE:View.GONE);
        textViewPressure.setVisibility(bundle.getBoolean(FirstActivity.EXTRA_PRESSURE)?View.VISIBLE:View.GONE);
        textViewHumidity.setVisibility(bundle.getBoolean(FirstActivity.EXTRA_HUMIDITY)?View.VISIBLE:View.GONE);
    }
}