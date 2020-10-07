package de.neuefische.covidapiserver.apiService;

import de.neuefische.covidapiserver.model.ApiModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service
public class CoronaApiService {

    private final RestTemplate restTemplate = new RestTemplate();


    public ApiModel[] getApiModel(String country) {
        return restTemplate.getForEntity(getRequestUrl(country), ApiModel[].class).getBody();
    }

    private String getRequestUrl(String country) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate sevenDaysAgo = yesterday.minusDays(7);
        String url = "https://api.covid19api.com/total/country/" + country
                + "/status/confirmed?from=" + sevenDaysAgo + "T00:00:00Z&to="
                + yesterday +"T00:00:00Z";
        return url;
    }

}
