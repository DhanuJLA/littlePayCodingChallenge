package com.littlePayJourneys;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Objects;

public class Trips {
    private Timestamp started;
    private Timestamp finished;
    private Long durationSecs;
    private String fromStopId;
    private String toStopId;
    private Double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private String status;

    public Trips(Taps tapOn, Taps tapOff) {
        this.started = tapOn.getDateTimeUtc() != null ? Timestamp.valueOf(tapOn.getDateTimeUtc()) : null;
        this.finished = tapOff.getDateTimeUtc() != null ? Timestamp.valueOf(tapOff.getDateTimeUtc()) : null;
        this.durationSecs = getTripDuration(tapOn, tapOff);
        this.fromStopId = tapOn.getStopId();
        this.toStopId = tapOff.getStopId();
        this.chargeAmount = getCharge(tapOn, tapOff);
        this.companyId = tapOn.getCompanyId();
        this.busId = tapOn.getBusId();
        this.pan = tapOn.getPan();
        this.status = getTripStatus(tapOn, tapOff);
    }

    public Trips(){}

     public static Long getTripDuration(Taps tapOn, Taps tapOff) {
        Long duration = 0L;
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        if (Boolean.FALSE.equals(isIncompleteTrip)) {
            duration = Duration.between(tapOn.getDateTimeUtc(), tapOff.getDateTimeUtc()).getSeconds();
        }
        return duration;
    }

    public static String getTripStatus(Taps tapOn, Taps tapOff) {
        String status = "COMPLETED";
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        if (Boolean.TRUE.equals(isIncompleteTrip)) {
            status = "INCOMPLETE";
        } else {
            Boolean isTripCancelled = tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId());
            if (Boolean.TRUE.equals(isTripCancelled)) {
                status = "CANCELLED";
            }
        }
        return status;
    }

    public static Double getCharge(Taps tapOn, Taps tapOff) {
        Double stop1ToStop2 = 3.25;
        Double stop1ToStop3 = 7.30;
        Double stop2ToStop3 = 5.50;

        Double charge = 0.0;

        //Trip is incomplete if the tap off pan doesn't match tap on pan as this would be an empty tap off object
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        //Trips is considered cancelled if both tap on and tap off was at the same stop
        Boolean isTripCancelled = tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId());

        String fromStop = tapOn.getStopId();
        String toStop = tapOff.getStopId();

        if (Boolean.FALSE.equals(isTripCancelled)) {
            if (Boolean.TRUE.equals(isIncompleteTrip)) {
                if ((("Stop1").equalsIgnoreCase(fromStop) || ("Stop1").equalsIgnoreCase(toStop))) {
                    charge = Double.max(stop1ToStop2, stop1ToStop3);
                } else if ((("Stop2").equalsIgnoreCase(fromStop) || ("Stop2").equalsIgnoreCase(toStop))) {
                    charge = Double.max(stop1ToStop2, stop2ToStop3);
                } else {
                    charge = Double.max(stop1ToStop3, stop2ToStop3);
                }
            } else {
                if ((("Stop1").equalsIgnoreCase(fromStop) || ("Stop2").equalsIgnoreCase(fromStop)) && (("Stop1").equalsIgnoreCase(toStop) || ("Stop2").equalsIgnoreCase(toStop))) {
                    charge = stop1ToStop2;
                } else if ((("Stop1").equalsIgnoreCase(fromStop) || ("Stop3").equalsIgnoreCase(fromStop)) && (("Stop1").equalsIgnoreCase(toStop) || ("Stop3").equalsIgnoreCase(toStop))) {
                    charge = stop1ToStop3;
                } else if ((("Stop2").equalsIgnoreCase(fromStop) || ("Stop3").equalsIgnoreCase(fromStop)) && (("Stop2").equalsIgnoreCase(toStop) || ("Stop3").equalsIgnoreCase(toStop))) {
                    charge = stop2ToStop3;
                }
            }

        }
        return charge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trips trips = (Trips) o;
        return Objects.equals(started, trips.started) && Objects.equals(finished, trips.finished) && Objects.equals(durationSecs, trips.durationSecs) && Objects.equals(fromStopId, trips.fromStopId) && Objects.equals(toStopId, trips.toStopId) && Objects.equals(chargeAmount, trips.chargeAmount) && Objects.equals(companyId, trips.companyId) && Objects.equals(busId, trips.busId) && Objects.equals(pan, trips.pan) && Objects.equals(status, trips.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(started, finished, durationSecs, fromStopId, toStopId, chargeAmount, companyId, busId, pan, status);
    }

    @Override
    public String toString() {
        return
                started +
                        ", " + finished +
                        ", " + durationSecs +
                        ", " + fromStopId +
                        ", " + toStopId +
                        ", " + chargeAmount +
                        ", " + companyId +
                        ", " + busId +
                        ", " + pan +
                        ", " + status;
    }
}
