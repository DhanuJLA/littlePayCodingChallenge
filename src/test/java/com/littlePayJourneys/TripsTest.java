package com.littlePayJourneys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TripsTest {
    Taps tapOnComplete;
    Taps tapOffComplete;
    Taps tapOffIncomplete;
    Taps tapOffCancelled;

    @BeforeEach
    void setUp() {
        tapOnComplete = new Taps(300, LocalDateTime.of(2018, 8, 20, 9, 30), "ON", "Stop1", "Company2", "Bus36", "300500200400");
        tapOffComplete = new Taps(302, LocalDateTime.of(2018, 8, 20, 9, 50), "OFF", "Stop3", "Company2", "Bus36", "300500200400");
        tapOffIncomplete = new Taps();
        tapOffCancelled = new Taps(300, LocalDateTime.of(2018, 8, 20, 9, 30), "OFF", "Stop1", "Company2", "Bus36", "300500200400");
    }


    @Test
    void itShouldGetDurationForCompletedTrip() {
        Long duration = Trips.getTripDuration(tapOnComplete, tapOffComplete);
        assertEquals(1200, duration);
    }

    @Test
    void itShouldGetDurationForIncompleteTrip() {
        Long duration = Trips.getTripDuration(tapOnComplete, tapOffIncomplete);
        assertEquals(0, duration);
    }

    @Test
    void itShouldGetDurationForCancelledTrip() {
        Long duration = Trips.getTripDuration(tapOnComplete, tapOffCancelled);
        assertEquals(0, duration);
    }

    @Test
    void itShouldGetStatusForCompletedTrip() {
        String status = Trips.getTripStatus(tapOnComplete, tapOffComplete);
        assertEquals("COMPLETED", status);
    }

    @Test
    void itShouldGetStatusForIncompleteTrip() {
        String status = Trips.getTripStatus(tapOnComplete, tapOffIncomplete);
        assertEquals("INCOMPLETE", status);
    }

    @Test
    void itShouldGetStatusForCancelledTrip() {
        String status = Trips.getTripStatus(tapOnComplete, tapOffCancelled);
        assertEquals("CANCELLED", status);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop1ToStop2() {
        tapOnComplete.setStopId("Stop1");
        tapOffComplete.setStopId("Stop2");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(3.25, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop2ToStop1() {
        tapOnComplete.setStopId("Stop2");
        tapOffComplete.setStopId("Stop1");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(3.25, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop1ToStop3() {
        tapOnComplete.setStopId("Stop1");
        tapOffComplete.setStopId("Stop3");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop3ToStop1() {
        tapOnComplete.setStopId("Stop3");
        tapOffComplete.setStopId("Stop1");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop2ToStop3() {
        tapOnComplete.setStopId("Stop2");
        tapOffComplete.setStopId("Stop3");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop3ToStop2() {
        tapOnComplete.setStopId("Stop3");
        tapOffComplete.setStopId("Stop2");

        Double charge = Trips.getCharge(tapOnComplete, tapOffComplete);
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWithStop1() {
        tapOnComplete.setStopId("Stop1");
        Double charge = Trips.getCharge(tapOnComplete, tapOffIncomplete);
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWithStop2() {
        tapOnComplete.setStopId("Stop2");
        Double charge = Trips.getCharge(tapOnComplete, tapOffIncomplete);
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWitStop3() {
        tapOnComplete.setStopId("Stop3");
        Double charge = Trips.getCharge(tapOnComplete, tapOffIncomplete);
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCancelledTripAtStop1() {
        tapOnComplete.setStopId("Stop1");
        tapOffCancelled.setStopId("Stop1");
        Double charge = Trips.getCharge(tapOnComplete, tapOffCancelled);
        assertEquals(0, charge);
    }

}