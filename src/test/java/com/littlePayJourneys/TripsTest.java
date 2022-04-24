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

    Trips completedTrip;
    Trips cancelledTrip;
    Trips incompleteTrip;

    @BeforeEach
    void setUp() {
        tapOnComplete = new Taps(300, LocalDateTime.of(2018, 8, 20, 9, 30), "ON", "Stop1", "Company2", "Bus36", "300500200400");
        tapOffComplete = new Taps(302, LocalDateTime.of(2018, 8, 20, 9, 50), "OFF", "Stop3", "Company2", "Bus36", "300500200400");
        tapOffIncomplete = new Taps();
        tapOffCancelled = new Taps(300, LocalDateTime.of(2018, 8, 20, 9, 30), "OFF", "Stop1", "Company2", "Bus36", "300500200400");

        completedTrip = new CompletedTrips(tapOnComplete, tapOffComplete);
        incompleteTrip = new IncompleteTrips(tapOnComplete, tapOffIncomplete);
        cancelledTrip =  new CancelledTrips(tapOnComplete, tapOffCancelled);
    }


    @Test
    void itShouldGetDurationForCompletedTrip() {
        Long duration = completedTrip.getDurationSecs();
        assertEquals(1200, duration);
    }
    @Test
    void itShouldGetDurationForIncompleteTrip() {
        Long duration = incompleteTrip.getDurationSecs();
        assertEquals(0, duration);
    }

    @Test
    void itShouldGetDurationForCancelledTrip() {
        Long duration = cancelledTrip.getDurationSecs();
        assertEquals(0, duration);
    }

    @Test
    void itShouldGetStatusForCompletedTrip() {
        String status = completedTrip.getStatus();
        assertEquals("COMPLETED", status);
    }

    @Test
    void itShouldGetStatusForIncompleteTrip() {
        String status = incompleteTrip.getStatus();
        assertEquals("INCOMPLETE", status);
    }

    @Test
    void itShouldGetStatusForCancelledTrip() {
        String status = cancelledTrip.getStatus();
        assertEquals("CANCELLED", status);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop1ToStop2() {
        tapOnComplete.setStopId("Stop1");
        tapOffComplete.setStopId("Stop2");

        Trips completedTripStop1ToStop2 = new CompletedTrips(tapOnComplete, tapOffComplete);
        Double charge = completedTripStop1ToStop2.getChargeAmount();
        assertEquals(3.25, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop2ToStop1() {
        tapOnComplete.setStopId("Stop2");
        tapOffComplete.setStopId("Stop1");

        Trips completedTripFromStop2ToStop1 = new CompletedTrips(tapOnComplete, tapOffComplete);

        Double charge = completedTripFromStop2ToStop1.getChargeAmount();
        assertEquals(3.25, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop1ToStop3() {
        tapOnComplete.setStopId("Stop1");
        tapOffComplete.setStopId("Stop3");

        Trips completedTripFromStop1ToStop3 = new CompletedTrips(tapOnComplete, tapOffComplete);

        Double charge = completedTripFromStop1ToStop3.getChargeAmount();
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop3ToStop1() {
        tapOnComplete.setStopId("Stop3");
        tapOffComplete.setStopId("Stop1");

        Trips completedTripFromStop3ToStop1 = new CompletedTrips(tapOnComplete, tapOffComplete);

        Double charge = completedTripFromStop3ToStop1.getChargeAmount();
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop2ToStop3() {
        tapOnComplete.setStopId("Stop2");
        tapOffComplete.setStopId("Stop3");

        Trips completedTripFromStop2ToStop3 = new CompletedTrips(tapOnComplete, tapOffComplete);

        Double charge = completedTripFromStop2ToStop3.getChargeAmount();
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForCompletedTripStop3ToStop2() {
        tapOnComplete.setStopId("Stop3");
        tapOffComplete.setStopId("Stop2");

        Trips completedTripFromStop3ToStop2 = new CompletedTrips(tapOnComplete, tapOffComplete);
        Double charge = completedTripFromStop3ToStop2.getChargeAmount();
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWithStop1() {
        tapOnComplete.setStopId("Stop1");
        Trips incompleteTripStop1 = new IncompleteTrips(tapOnComplete, tapOffIncomplete);
        Double charge = incompleteTripStop1.getChargeAmount();
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWithStop2() {
        tapOnComplete.setStopId("Stop2");
        Trips incompleteTripStop2 = new IncompleteTrips(tapOnComplete, tapOffIncomplete);
        Double charge =  incompleteTripStop2.getChargeAmount();
        assertEquals(5.50, charge);
    }

    @Test
    void itShouldGetChargeForIncompleteTripWitStop3() {
        tapOnComplete.setStopId("Stop3");
        Trips incompleteTripStop3 = new IncompleteTrips(tapOnComplete, tapOffIncomplete);
        Double charge =  incompleteTripStop3.getChargeAmount();
        assertEquals(7.30, charge);
    }

    @Test
    void itShouldGetChargeForCancelledTripAtStop1() {
        tapOnComplete.setStopId("Stop1");
        tapOffCancelled.setStopId("Stop1");
        Trips cancelledTrip = new CancelledTrips(tapOnComplete, tapOffCancelled);
        Double charge = cancelledTrip.getChargeAmount();
        assertEquals(0, charge);
    }

}
