package de.neuefische.covidapiserver.service;

import de.neuefische.covidapiserver.model.ApiModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CovidCalcUtils {

    public static List<ConfirmedCases> mapApiModel(List<ApiModel> values){
        return values.stream()
                .map(value -> new ConfirmedCases(value.getCountry(),(int)value.getConfirmedCases(), value.getDate()))
                .collect(Collectors.toList());
    }

    public static List<ConfirmedCases> weekdayFilter (List<ConfirmedCases> values){
        return values.stream()
                .filter(confirmedCase -> isDayOfTheWeek(confirmedCase))
                .collect(Collectors.toList());

    }


    public static boolean isThereADayExceedingTwoThousandCases(List<ConfirmedCases> values){
        return values.stream()
                .filter(value -> areThereMoreThanTwoThousandCases(value))
                .findAny()
                .isPresent();
    }

    public static boolean isDayOfTheWeek(ConfirmedCases confirmedCase){
        DayOfWeek dayOfWeek = LocalDate.parse(confirmedCase.getDate(), DateTimeFormatter.ISO_DATE_TIME).getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY) && (dayOfWeek != DayOfWeek.SUNDAY);
    }

    public static boolean areThereMoreThanTwoThousandCases(ConfirmedCases cases) {
        return cases.getCases() >= 2000;
    }

}
