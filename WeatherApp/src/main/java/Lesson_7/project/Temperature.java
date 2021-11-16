package Lesson_7.project;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Temperature {
    private TemperatureUnit MinimumObject;
    private TemperatureUnit MaximumObject;

    public Temperature() {
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "MinimumObject=" + MinimumObject +
                ", MaximumObject=" + MaximumObject +
                '}';
    }

    @JsonGetter("Minimum")
    public TemperatureUnit getMinimumObject() {
        return MinimumObject;
    }

    @JsonSetter("Minimum")
    public void setMinimumObject(TemperatureUnit minimumObject) {
        MinimumObject = minimumObject;
    }

    @JsonGetter("Maximum")
    public TemperatureUnit getMaximumObject() {
        return MaximumObject;
    }

    @JsonSetter("Maximum")
    public void setMaximumObject(TemperatureUnit maximumObject) {
        MaximumObject = maximumObject;
    }

}
