package Lesson_7HW;


import Lesson_7HW.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {

    public void getWeather(Periods period) throws IOException;
}
