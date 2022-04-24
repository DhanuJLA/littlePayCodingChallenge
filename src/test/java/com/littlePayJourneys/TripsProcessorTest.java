package com.littlePayJourneys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripsProcessorTest {

    //Taps Complete and Incomplete
    List<Taps> tapsDayOne = new ArrayList<>();

    //Taps Cancelled and Complete
    List<Taps> tapsDayTwo = new ArrayList<>();

    List<Trips> expectedTripsDayOne = new ArrayList<>();
    List<Trips> expectedTripsDayTwo = new ArrayList<>();

    @BeforeEach
    void setUp() {

        //Completed trip day 1
        Taps tap1OnDay1 = new Taps(300, LocalDateTime.of(2018, 8, 20, 9, 30), "ON", "Stop1", "Company2", "Bus36", "300500200400");
        Taps tap1OffDay1 = new Taps(302, LocalDateTime.of(2018, 8, 20, 9, 50), "OFF", "Stop3", "Company2", "Bus36", "300500200400");

        //Incomplete trip day 1
        Taps tap2OnDay1 = new Taps(300, LocalDateTime.of(2018, 8, 20, 13, 30), "ON", "Stop1", "Company2", "Bus37", "233500200400");

        tapsDayOne.add(tap1OnDay1);
        tapsDayOne.add(tap1OffDay1);
        tapsDayOne.add(tap2OnDay1);

        expectedTripsDayOne.add(new Trips(tap1OnDay1, tap1OffDay1));
        expectedTripsDayOne.add(new Trips(tap2OnDay1, new Taps()));

        //Cancelled trip day 2
        Taps tap2OnDay2 = new Taps(300, LocalDateTime.of(2018, 8, 21, 6, 30), "ON", "Stop1", "Company2", "Bus37", "533500200400");
        Taps tap2OffDay2 = new Taps(300, LocalDateTime.of(2018, 8, 21, 6, 30), "OFF", "Stop1", "Company2", "Bus37", "533500200400");

        //Completed trip day 2
        Taps tap3OnDay2 = new Taps(300, LocalDateTime.of(2018, 8, 21, 8, 30), "ON", "Stop1", "Company2", "Bus38", "877500200400");
        Taps tap3OffDay2 = new Taps(300, LocalDateTime.of(2018, 8, 21, 8, 40), "OFF", "Stop1", "Company2", "Bus38", "877500200400");

        tapsDayTwo.add(tap2OnDay2);
        tapsDayTwo.add(tap2OffDay2);
        tapsDayTwo.add(tap3OnDay2);
        tapsDayTwo.add(tap3OffDay2);

        expectedTripsDayTwo.add(new Trips(tap2OnDay2, tap2OffDay2));
        expectedTripsDayTwo.add(new Trips(tap3OnDay2, tap3OffDay2));

    }

    @Test
    void itShouldReturnCompletedAndIncompleteTrips() {
        List<Trips> processedTrips = TripsProcessor.processTripsPerDay(tapsDayOne);
        assertTrue(expectedTripsDayOne.size() == processedTrips.size()
                && processedTrips.containsAll(expectedTripsDayOne)
                && expectedTripsDayOne.containsAll(processedTrips));
    }

    @Test
    void itShouldReturnCompletedAndCancelledTrips() {
        List<Trips> processedTrips = TripsProcessor.processTripsPerDay(tapsDayTwo);
        assertTrue(expectedTripsDayTwo.size() == processedTrips.size()
                && processedTrips.containsAll(expectedTripsDayTwo)
                && expectedTripsDayTwo.containsAll(processedTrips));
    }

}