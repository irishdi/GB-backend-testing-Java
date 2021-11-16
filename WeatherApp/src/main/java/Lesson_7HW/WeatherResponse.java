package Lesson_7HW;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WeatherResponse {
    private Lesson_7HW.Headline Headline;
    private ArrayList<Lesson_7HW.DailyForecasts> DailyForecasts = new ArrayList<>();

    public WeatherResponse() {
    }

    @JsonGetter("Headline")
    public Lesson_7HW.Headline getHeadline() {
        return Headline;
    }

    @JsonSetter("Headline")
    public void setHeadline(Lesson_7HW.Headline Headline) {
        this.Headline = Headline;
    }

    @JsonGetter("DailyForecasts")
    public ArrayList<Lesson_7HW.DailyForecasts> getDailyForecasts() {
        return DailyForecasts;
    }

    @JsonSetter("DailyForecasts")
    public void setDailyForecasts(ArrayList<Lesson_7HW.DailyForecasts> DailyForecasts) {
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
