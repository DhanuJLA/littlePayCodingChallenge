package com.littlePayJourneys;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVProcessor {

    public static Map<LocalDate, List<Taps>> readTapsByDate() throws IOException {

        Path path = Path.of("src", "main", "resources", "taps.csv");

        //Taps read from file, sorted by date time and grouped by date
        Map<LocalDate, List<Taps>> listOfTapsGroupedByDate = Files.lines(path)
                .skip(1)
                .map(CSVProcessor::getTaps)
                .sorted(Comparator.comparing(Taps::getDateTimeUtc))
                .collect(Collectors.groupingBy(Taps::getDate));

        return listOfTapsGroupedByDate;
    }

    public static void writeTripsCsv(List<Trips> processedTrips) {
        try {
            File tripsCsvFile = new File("trips.csv");
            PrintWriter out = new PrintWriter(tripsCsvFile);
            out.println("Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status");
            for (Trips trip : processedTrips) {
                out.println(trip);
            }
            out.close();
            System.out.println("Trips written to file successfully!");
        } catch (Exception e) {
            System.out.println("Unable to write to the file.");
        }
    }

    private static Taps getTaps(String line) {
        String[] fields = line.split(",");
        return new Taps(Integer.parseInt(fields[0]), formatToLocalDateTime(fields[1]), fields[2], fields[3], fields[4], fields[5], fields[6]);
    }

    private static LocalDateTime formatToLocalDateTime(String dateTimeUtcString) {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTimeUtcString, formatDateTime);
    }


}
