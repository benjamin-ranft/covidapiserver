package de.neuefische.covidapiserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiModel {

    @JsonProperty("Country")
    private String country;
    @JsonProperty("Cases")
    private double confirmedCases;

}
