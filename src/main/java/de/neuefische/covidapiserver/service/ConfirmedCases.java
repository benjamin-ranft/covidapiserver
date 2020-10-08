package de.neuefische.covidapiserver.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmedCases {

    private String country;
    private int cases;
    private String date;

}
