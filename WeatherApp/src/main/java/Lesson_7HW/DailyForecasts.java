package Lesson_7HW;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)

public class DailyForecasts {
    @JsonProperty (value = "Date")
    public String Date;
    @JsonProperty (value = "Temperature")
    private Temperature TemperatureObject;

    public DailyForecasts(){

    }
    @Override
    public String toString(){
        return "DailyForecasts{" + "Date='" + Date +'\'' +", TemperatureObject=" + TemperatureObject +'\''+ '}';
    }
    @JsonGetter ("Date")
    public String getDate(){
        return Date;
    }
    @JsonSetter("Date")
    public void setDate(String date) {
        Date = date;
    }
    @JsonGetter
    public Temperature getTemperatureObject() {
        return TemperatureObject;
    }
    @JsonSetter
    public void setTemperatureObject(Temperature temperatureObject) {
        TemperatureObject = temperatureObject;
    }
}
