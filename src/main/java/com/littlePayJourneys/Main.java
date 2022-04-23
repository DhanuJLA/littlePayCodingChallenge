package com.littlePayJourneys;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        Map<LocalDate, List<Taps>> listOfTapsByDate = CSVProcessor.readTapsByDate();

        List<Trips> processedTrips = TripsProcessor.processTripsFromTaps(listOfTapsByDate);

        CSVProcessor.writeTripsCsv(processedTrips);
    }
}
