package Lesson_7.project;

import Lesson_7.project.enums.Periods;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    //private static final String FORECAST_PERIOD_1 = "1day";
    private static final String FORECAST_PERIOD_5 = "5day";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private static final String LANGUAGE = "ru-ru";
    private static final String METRIC = "true";

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            HttpUrl nowUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    //.addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    //.addPathSegment(FORECAST_TYPE)
                    //.addPathSegment(FORECAST_PERIOD_1)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    //.addQueryParameter("language", LANGUAGE);
                    .build();

            Request request1 = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(nowUrl)
                    .build();

            Response response1 = client.newCall(request1).execute();
            String response1Body = response1.body().string();

            if(!response1Body.equals("[]")) {
                String localData = objectMapper.readTree(response1Body).get(0).at("/LocalObservationDateTime").asText();
                String weatherText = objectMapper.readTree(response1Body).get(0).at("/WeatherText").asText();
                String temperature = objectMapper.readTree(response1Body).get(0).at("/Temperature/Metric/Value").asText();
                System.out.println("В городе на дату " + localData.substring(0,10) + " ожидается " + weatherText +
                        ", температура " + temperature + "C");
            }

        }if(periods.equals(Periods.FIVE_DAYS)){
            HttpUrl fiveUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD_5)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", LANGUAGE)
                    .addQueryParameter("metric", METRIC)
                    .build();
            Request request2 = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(fiveUrl)
                    .build();
            Response response2 = client.newCall(request2).execute();
            //System.out.println(response2.body().string());
            String response2Body = response2.body().string();
            if(!response2Body.equals("[]")){
                WeatherResponse fiveDaysForecast = objectMapper.readValue(response2Body, WeatherResponse.class);
                printWeather(fiveDaysForecast);
            }
        }

    }
    public void printWeather(WeatherResponse forecast) {
        ArrayList<DailyForecasts> dailyForecasts = new ArrayList<>(forecast.getDailyForecasts());
        System.out.println("Прогноз на 5 дней:");
        System.out.println();
        Iterator<DailyForecasts> iterator = dailyForecasts.iterator();
        while (iterator.hasNext()) {
            DailyForecasts dataIter = iterator.next();
            System.out.println("Дата " + dataIter.getDate().substring(0, 10) + " ожидается минимальная температура "
                    + dataIter.getTemperatureObject().getMinimumObject().getValue() +
                    dataIter.getTemperatureObject().getMinimumObject().getUnit());
            System.out.println("Максимальная температура " + dataIter.getTemperatureObject().getMaximumObject().getValue() +
                    dataIter.getTemperatureObject().getMaximumObject().getUnit());
        }
        System.out.println("*****");
        System.out.println("Самое важное за пять дней: " + forecast.getHeadline().getText() + ". Дата прогноза: " +
                forecast.getHeadline().getEffectiveDate().substring(0,10));

    }
    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
            .scheme("http")
            .host(BASE_HOST)
            .addPathSegment("locations")
            .addPathSegment(API_VERSION)
            .addPathSegment("cities")
            .addPathSegment("autocomplete")
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("q", selectedCity)
            .build();

        Request request = new Request.Builder()
            .addHeader("accept", "application/json")
            .url(detectLocationURL)
            .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
