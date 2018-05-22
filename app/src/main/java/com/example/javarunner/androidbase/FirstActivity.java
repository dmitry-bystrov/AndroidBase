package com.example.javarunner.androidbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    public static final String EXTRA_HUMIDITY = "ExtraHumidity";
    public static final String EXTRA_PRESSURE = "ExtraPressure";
    public static final String EXTRA_TEMPERATURE = "ExtraTemperature";
    public static final String EXTRA_CITY = "ExtraCity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonShow:
                Intent intent = new Intent(this, SecondActivity.class);
                EditText editTextCity = findViewById(R.id.editTextCity);
                CheckBox chkBoxTemperature = findViewById(R.id.checkBoxTemperature);
                CheckBox chkBoxPressure = findViewById(R.id.checkBoxPressure);
                CheckBox chkBoxHumidity = findViewById(R.id.checkBoxHumidity);

                intent.putExtra(EXTRA_CITY, editTextCity.getText().toString());
                intent.putExtra(EXTRA_TEMPERATURE, chkBoxTemperature.isChecked());
                intent.putExtra(EXTRA_PRESSURE, chkBoxPressure.isChecked());
                intent.putExtra(EXTRA_HUMIDITY, chkBoxHumidity.isChecked());
                startActivity(intent);
                break;
        }
    }
}
