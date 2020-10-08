package de.neuefische.covidapiserver.service;

import de.neuefische.covidapiserver.model.ApiModel;

import static de.neuefische.covidapiserver.service.CovidCalcUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CovidCalcUtilsTest {

    @Test
    void mapApiModelTest() {
        //GIVEN
        List<ApiModel> givenList = List.of(new ApiModel("germany", 2000, "2020-09-30T00:00:00Z"),
                new ApiModel("france", 12000, "2020-09-30T00:00:00Z"));

        //WHEN
        List<ConfirmedCases> expectedList = List.of(new ConfirmedCases("germany", 2000, "2020-09-30T00:00:00Z"),
                new ConfirmedCases("france", 12000, "2020-09-30T00:00:00Z"));
        List<ConfirmedCases> actualList = mapApiModel(givenList);
        //THEN
        assertThat(actualList, is(expectedList));
        
    }

    @Test
    void isDayOfTheWeekShouldReturnTrue() {
        //GIVEN
        ConfirmedCases givenCase = new ConfirmedCases("germany", 2000, "2020-09-30T00:00:00Z");
        //WHEN
        Boolean actualCase = isDayOfTheWeek(givenCase);

        //THEN
        assertTrue(actualCase);
    }

    @Test
    void weekdayFilterShouldReturnListOfOnlyWeekdayConfirmedCases(){
        //GIVEN
        List<ConfirmedCases> givenList = List.of(new ConfirmedCases("germany", 2000, "2020-09-30T00:00:00Z"),
                new ConfirmedCases("france", 12000, "2020-10-03T00:00:00Z"));

        List<ConfirmedCases> expectedList = List.of(new ConfirmedCases("germany", 2000, "2020-09-30T00:00:00Z"));

        //WHEN
        List<ConfirmedCases> actualList = weekdayFilter(givenList);

        //THEN
        assertEquals(expectedList,actualList);
    }

    @Test
    void areThereMoreThanTwoThousandCasesIsTrue (){
        ConfirmedCases givenCase = new ConfirmedCases("germany", 2000, "2020-09-30T00:00:00Z");
        //WHEN
        Boolean actualCase = areThereMoreThanTwoThousandCases(givenCase);

        //THEN
        assertTrue(actualCase);
    }

    @Test
    void areThereMoreThanTwoThousandCasesIsFalse (){
        ConfirmedCases givenCase = new ConfirmedCases("germany", 100, "2020-09-30T00:00:00Z");
        //WHEN
        Boolean actualCase = areThereMoreThanTwoThousandCases(givenCase);

        //THEN
        assertFalse(actualCase);
    }

    @Test
    void isThereADayExceedingTwoThousandCasesIsTrue (){
        //GIVEN
        List<ConfirmedCases> givenList = List.of(new ConfirmedCases("germany", 1000, "2020-09-30T00:00:00Z"),
                new ConfirmedCases("france", 12000, "2020-10-03T00:00:00Z"));

        //WHEN
        boolean actualList = isThereADayExceedingTwoThousandCases(givenList);

        //THEN
        assertTrue(actualList);
    }

    @Test
    void isThereADayExceedingTwoThousandCasesIsFalse (){
        //GIVEN
        List<ConfirmedCases> givenList = List.of(new ConfirmedCases("germany", 1000, "2020-09-30T00:00:00Z"),
                new ConfirmedCases("france", 1000, "2020-10-03T00:00:00Z"));

        //WHEN
        boolean actualList = isThereADayExceedingTwoThousandCases(givenList);

        //THEN
        assertFalse(actualList);
    }
}