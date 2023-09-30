package ru.sh.weatherforecastrestapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sh.weatherforecastrestapp.models.WeatherForecast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherForecastRepository {

    private final List<WeatherForecast> weatherRepository;

    @Autowired
    public WeatherForecastRepository() {
        weatherRepository = new ArrayList<>();
        weatherRepository.add(WeatherForecast.of(LocalDateTime.of(2023, 9, 30, 18, 0), 20));
        weatherRepository.add(WeatherForecast.of(LocalDateTime.of(2023, 9, 30, 19, 0), 15));
        weatherRepository.add(WeatherForecast.of(LocalDateTime.of(2023, 9, 30, 22, 0), 10));
    }

    /**
     * метод добавления данных о погоде
     * @param weatherForecast
     */
    public void addData(WeatherForecast weatherForecast) {
        weatherRepository.add(weatherForecast);
    }

    /**
     * метод обновления данных о погоде
     * @param id - ID
     * @param dateTime - дата измерения
     * @param temperatureC - температура в градусах по Цельсию
     * @return
     */
    public boolean updateData(int id, LocalDateTime dateTime, int temperatureC) {
        for (WeatherForecast weather : weatherRepository) {
            if (weather.getId() == id) {
                weather.setDateTime(dateTime);
                weather.setTemperatureC(temperatureC);
                return true;
            }
        }
        return false;
    }

    /**
     * метод возвращает список всех данных о погоде
     * @return
     */
    public List<WeatherForecast> getAllData() {
        return weatherRepository;
    }

    /**
     * метод возвращает список данных о погоде по указанным датам
     * @param dateFrom - начальная дата(включительно)
     * @param dateTo - конечная дата(включительно)
     * @return
     */
    public List<WeatherForecast> getDataBetweenOfDates(String dateFrom, String dateTo) {
        List<WeatherForecast> list = new ArrayList<>();

        var localDateFrom = LocalDate.parse(dateFrom);
        var localDateTo = LocalDate.parse(dateTo);

        for (WeatherForecast weather : weatherRepository) {

            var currentDate = weather.getDateTime().toLocalDate();

            if ((currentDate.isAfter(localDateFrom) || currentDate.isEqual(localDateFrom)) && (currentDate.isBefore(localDateTo)) || currentDate.isEqual(localDateTo)) {
                    list.add(weather);
            }
        }
        return list;
    }

    /**
     * метод удаления данных о погоде
     * @param dateTime - нужно передать дату
     */
    public void deleteData(LocalDateTime dateTime) {
        weatherRepository.removeIf(weatherForecast -> weatherForecast.getDateTime().equals(dateTime));
    }
}
