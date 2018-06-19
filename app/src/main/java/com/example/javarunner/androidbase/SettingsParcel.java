package com.example.javarunner.androidbase;

import java.io.Serializable;
import java.util.Objects;

public class SettingsParcel implements Serializable {
    public static final String SETTINGS_PARCEL = "settings_parcel";

    private int locationIndex;
    private String locationName;
    private boolean showTemperature;
    private boolean showPressure;
    private boolean showHumidity;

    public SettingsParcel(int locationIndex, String locationName, boolean showTemperature, boolean showPressure, boolean showHumidity) {
        this.locationIndex = locationIndex;
        this.locationName = locationName;
        this.showTemperature = showTemperature;
        this.showPressure = showPressure;
        this.showHumidity = showHumidity;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public String getLocationName() {
        return locationName;
    }

    public boolean showTemperature() {
        return showTemperature;
    }

    public boolean showPressure() {
        return showPressure;
    }

    public boolean showHumidity() {
        return showHumidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsParcel that = (SettingsParcel) o;
        return locationIndex == that.locationIndex &&
                showTemperature == that.showTemperature &&
                showPressure == that.showPressure &&
                showHumidity == that.showHumidity &&
                Objects.equals(locationName, that.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationIndex, locationName, showTemperature, showPressure, showHumidity);
    }
}
