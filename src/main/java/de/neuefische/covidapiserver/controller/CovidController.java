package de.neuefische.covidapiserver.controller;

import de.neuefische.covidapiserver.model.Country7DayAverage;
import de.neuefische.covidapiserver.service.CovidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("covid")
public class CovidController {

    private CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping
    public Country7DayAverage getCountry7DayAverage(@RequestParam String country) {
        return covidService.getCountrySevenDayAverage(country);
    }

    @GetMapping("realClass")
    public boolean isRealClassPossible() {
        return covidService.isRealClassPossible();
    }
}
