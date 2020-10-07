package de.neuefische.covidapiserver.controller;

import de.neuefische.covidapiserver.apiService.CoronaApiService;
import de.neuefische.covidapiserver.model.ApiModel;
import de.neuefische.covidapiserver.model.Country7DayAverage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CovidControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CoronaApiService coronaApiService;

    @Test
    @DisplayName("getCountry7DayAverage should return a Country7DayAverage object")
    void getCountry7DayAverageTest() {
        //GIVEN
        ApiModel[] apiModels = new ApiModel[]{
                new ApiModel("germany", 1000f),
                new ApiModel("germany",15000f)
        };
        String url = "http://localhost:" + port + "/covid?country=germany";
        Country7DayAverage expected = new Country7DayAverage("germany", 2000f);
        when(coronaApiService.getApiModel("germany")).thenReturn(apiModels);
        //WHEN
        ResponseEntity<Country7DayAverage> response = restTemplate.getForEntity(url, Country7DayAverage.class);

        //THEN
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
        assertThat(response.getBody(), is(expected));
    }


    @Test
    @DisplayName("isRealClassPossible should return false when there are more than 100 cases")
    void isRealClassNotPossibleTest() {
        //GIVEN
        ApiModel[] apiModels = new ApiModel[]{
                new ApiModel("germany", 0f),
                new ApiModel("germany",701f)
        };
        String url = "http://localhost:" + port + "/covid/realClass";
        when(coronaApiService.getApiModel("germany")).thenReturn(apiModels);
        //WHEN
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(false));
    }

    @Test
    @DisplayName("isRealClassPossible should return true when there are less than 100 cases")
    void isRealClassPossibleTest() {
        //GIVEN
        ApiModel[] apiModels = new ApiModel[]{
                new ApiModel("germany", 0f),
                new ApiModel("germany",700f)
        };
        String url = "http://localhost:" + port + "/covid/realClass";
        when(coronaApiService.getApiModel("germany")).thenReturn(apiModels);
        //WHEN
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(true));
    }
}