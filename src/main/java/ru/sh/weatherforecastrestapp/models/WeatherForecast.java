package ru.sh.weatherforecastrestapp.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * класс описывает данные о погоде
 */
public class WeatherForecast {

    private static int counter;

    private int id;

    private LocalDateTime dateTime;

    private int temperatureC;

    private int temperatureF;

    public WeatherForecast() {
        id = ++counter;
    }

    public WeatherForecast(LocalDateTime dateTime, int temperatureC) {
        this.dateTime = dateTime;
        this.temperatureC = temperatureC;
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(int temperatureC) {
        this.temperatureC = temperatureC;
    }

    public int getTemperatureF() {
        return 32 + (int) (temperatureC / 0.5556);
    }

    public static WeatherForecast of(LocalDateTime dateTime, int temperatureC) {
        return new WeatherForecast(dateTime, temperatureC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherForecast that = (WeatherForecast) o;

        if (id != that.id) return false;
        if (temperatureC != that.temperatureC) return false;
        if (temperatureF != that.temperatureF) return false;
        return Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + temperatureC;
        result = 31 * result + temperatureF;
        return result;
    }
}
