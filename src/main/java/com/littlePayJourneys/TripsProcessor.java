package com.littlePayJourneys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsProcessor {

    public static List<Trips> processTripsFromTaps(Map<LocalDate, List<Taps>> listOfTapsByDate) {
        List<Trips> processedTrips = new ArrayList<>();
        //Process trips grouped by day
        listOfTapsByDate.forEach((k, v) -> processedTrips.addAll(processTripsPerDay(v)));
        return processedTrips;
    }

    public static List<Trips> processTripsPerDay(List<Taps> tapsPerDay) {
        Map<String, List<Taps>> tapsGroupedByPan = tapsPerDay
                .stream()
                .collect(Collectors.groupingBy(Taps::getPan));

        List<Trips> tripsListPerDay = new ArrayList<>();

        tapsGroupedByPan.forEach((k, v) -> tripsListPerDay.addAll(processTapsGroupedByPanPerDay(v)));

        return tripsListPerDay;
    }

    public static List<Trips> processTapsGroupedByPanPerDay(List<Taps> tapsPerPan) {
        List<Taps> tapOnsListPerPan = new ArrayList<>();
        List<Taps> tapOffsListPerPan = new ArrayList<>();

        //Segregate into lists of tapOns and tapOffs for pan per day
        for (Taps tap : tapsPerPan) {
            if (("ON").equalsIgnoreCase(tap.getTapType())) {
                tapOnsListPerPan.add(tap);
            } else {
                tapOffsListPerPan.add(tap);
            }
        }

        //create Trips with Taps ons and Tap offs
        List<Trips> tripsListPerPan = new ArrayList<>();

        for (int tapOnItemIndex = 0; tapOnItemIndex < tapOnsListPerPan.size(); tapOnItemIndex++) {
            Taps tapOnItem = tapOnsListPerPan.get(tapOnItemIndex);
            Taps tapOffItem = new Taps();
            if (tapOnItemIndex < tapOffsListPerPan.size()) {
                tapOffItem = tapOffsListPerPan.get(tapOnItemIndex);
            }
            //construct trips
            Trips trip = new Trips(tapOnItem, tapOffItem);
            tripsListPerPan.add(trip);
        }
        return tripsListPerPan;
    }
}
