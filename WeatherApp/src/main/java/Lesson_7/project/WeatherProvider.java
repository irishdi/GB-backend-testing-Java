package Lesson_7.project;

import Lesson_7.project.enums.Periods;


import java.io.IOException;

public interface WeatherProvider {

    public void getWeather(Periods period) throws IOException;
}
