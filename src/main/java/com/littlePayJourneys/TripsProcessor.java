package com.littlePayJourneys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsProcessor {

    public static List<Trips> processTripsFromTaps(Map<LocalDate, List<Taps>> listOfTapsByDate) {
        List<Trips> processedTrips = new ArrayList<>();
        //Process trips from taps for each day
        listOfTapsByDate.forEach((k, v) -> processedTrips.addAll(processTripsPerDay(v)));
        return processedTrips;
    }

    public static List<Trips> processTripsPerDay(List<Taps> tapsPerDay) {

        List<Trips> tripsListPerDay = new ArrayList<>();

        List<Taps> tapOffsList = tapsPerDay
                .stream()
                .filter(tapItem -> ("OFF").equalsIgnoreCase(tapItem.getTapType()))
                .collect(Collectors.toList());

        List<Taps> tapOnsList = tapsPerDay
                .stream()
                .filter(tapItem -> ("ON").equalsIgnoreCase(tapItem.getTapType()))
                .collect(Collectors.toList());

        for (Taps tapOn : tapOnsList) {
            //Find matching corresponding tap off for PAN and BusId
            Taps tapOff = tapOffsList
                    .stream()
                    .filter(tapItem -> tapOn.getPan().equalsIgnoreCase(tapItem.getPan()) && tapOn.getBusId().equalsIgnoreCase(tapItem.getBusId()))
                    .findFirst()
                    .orElse(new Taps());

            Trips trip;

            if (tapOn.getPan().equalsIgnoreCase(tapOff.getPan())) {
                if(tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId())){
                    //Cancelled trip
                   trip = new CancelledTrips(tapOn, tapOff);
                } else {
                    //Completed trip
                    trip = new CompletedTrips(tapOn, tapOff);
                }
                //Remove matched tapOff from list
                tapOffsList.remove(tapOff);
            } else {
                //Incomplete trip
                trip = new IncompleteTrips(tapOn, tapOff);
            }

            tripsListPerDay.add(trip);
        }
        return tripsListPerDay;
    }
}
