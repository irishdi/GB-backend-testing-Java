package Lesson_7.project;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WeatherResponse {
    private Headline Headline;
    private ArrayList<DailyForecasts> DailyForecasts = new ArrayList<>();

    public WeatherResponse() {
    }

    @JsonGetter("Headline")
    public Headline getHeadline() {
        return Headline;
    }

    @JsonSetter("Headline")
    public void setHeadline(Headline Headline) {
        this.Headline = Headline;
    }

    @JsonGetter("DailyForecasts")
    public ArrayList<DailyForecasts> getDailyForecasts() {
        return DailyForecasts;
    }

    @JsonSetter("DailyForecasts")
    public void setDailyForecasts(ArrayList<DailyForecasts> DailyForecasts) {
        this.DailyForecasts = DailyForecasts;
    }

    @Override
    public String toString() {
        return "OneDayForecast{" +
                "Headline=" + Headline +
                ", DailyForecasts=" + DailyForecasts +
                '}';
    }

}
