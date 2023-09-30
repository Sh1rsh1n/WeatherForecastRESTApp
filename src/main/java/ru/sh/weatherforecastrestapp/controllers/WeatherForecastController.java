package ru.sh.weatherforecastrestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sh.weatherforecastrestapp.models.WeatherForecast;
import ru.sh.weatherforecastrestapp.repository.WeatherForecastRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Погода", description = "Прогноз погоды")
public class WeatherForecastController {

    private final WeatherForecastRepository repository;

    @Autowired
    public WeatherForecastController(WeatherForecastRepository repository) {
        this.repository = repository;
    }

    /**
     * добавление данных о погоде
     * @param weatherForecast
     * @return
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавление данных о погоде", description = "Позволяет добавить данные о погоде")
    public HttpStatus addWeatherForecastData(@RequestBody WeatherForecast weatherForecast) {
        repository.addData(weatherForecast);
        return HttpStatus.OK;
    }

    /**
     * изменение данных о погоде
     * @param weatherForecast
     * @return
     */
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение данных о погоде", description = "Позволяет изменять данные о погоде")
    public HttpStatus updateWeatherForecastData(@RequestBody WeatherForecast weatherForecast) {
        // если таких данных о погоде нет в списке, вернем ответ NOT_FOUND
        if (!repository.getAllData().contains(weatherForecast))
            return HttpStatus.NOT_FOUND;

        repository.addData(weatherForecast);
        return HttpStatus.OK;
    }

    /**
     * получаем данные между указанными датами(включая сами даты)
     * @param dateFrom - начало
     * @param dateTo - конец
     * @return
     */
    @GetMapping(value = "{dateFrom},{dateTo}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить данные о погоде", description = "Позволяет получить данные о погоде")
    public ResponseEntity<List<WeatherForecast>> getWeatherForecastData(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {
        return ResponseEntity.ok(repository.getDataBetweenOfDates(dateFrom, dateTo));
    }

    /**
     * получаем все данные которые есть в списке
     * @return
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить данные о погоде", description = "Позволяет получить данные о погоде")
    public ResponseEntity<List<WeatherForecast>> getWeatherForecastData() {
        return ResponseEntity.ok(repository.getAllData());
    }

    /**
     * удаляем данные
     * @param weatherForecast
     * @return
     */
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Удаление данных о погоде", description = "Позволяет Удалять данные о погоде")
    public HttpStatus deleteWeatherForecastData(@RequestBody WeatherForecast weatherForecast) {
        repository.deleteData(weatherForecast.getDateTime());
        return HttpStatus.OK;
    }
}
