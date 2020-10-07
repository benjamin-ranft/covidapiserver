package de.neuefische.covidapiserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country7DayAverage {

    private String country;
    private float sevenDayAverage;

}
