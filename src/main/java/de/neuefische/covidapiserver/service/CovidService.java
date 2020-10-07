package de.neuefische.covidapiserver.service;

import de.neuefische.covidapiserver.apiService.CoronaApiService;
import de.neuefische.covidapiserver.model.ApiModel;
import de.neuefische.covidapiserver.model.Country7DayAverage;
import org.springframework.stereotype.Service;

@Service
public class CovidService {

    private CoronaApiService coronaApiService;

    public CovidService(CoronaApiService coronaApiService) {
        this.coronaApiService = coronaApiService;
    }

    public Country7DayAverage getCountrySevenDayAverage(String country) {
        ApiModel[] apiModels = coronaApiService.getApiModel(country);
        float average = (apiModels[apiModels.length-1].getConfirmedCases() - apiModels[0].getConfirmedCases()) / 7f;
        return new Country7DayAverage(country, average);
    }

    public boolean isRealClassPossible() {
        float averageCases = getCountrySevenDayAverage("germany").getSevenDayAverage();
        return averageCases <= 100;
    }
}
