package de.neuefische.covidapiserver.apiService;

import de.neuefische.covidapiserver.model.ApiModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service
public class CoronaApiService {

    private RestTemplate restTemplate = new RestTemplate();
    private final static String apiUrl= "https://api.covid19api.com/total/country/";

    public ApiModel[] getApiModel(String country) {
        return restTemplate.getForEntity(apiUrl + country + getDateRange(), ApiModel[].class).getBody();
    }

    private String getDateRange() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate sevenDaysAgo = yesterday.minusDays(7);
        String dateRange = "/status/confirmed?from=" + sevenDaysAgo + "T00:00:00Z&to=" + yesterday +"T00:00:00Z";
        return dateRange;
    }

//    private String getRequestUrl(String country) {
//        LocalDate yesterday = LocalDate.now().minusDays(1);
//        LocalDate sevenDaysAgo = yesterday.minusDays(7);
//        String dateRange = "/status/confirmed?from=" + sevenDaysAgo + "T00:00:00Z&to=" + yesterday +"T00:00:00Z";
//        return dateRange;
//    }

}
