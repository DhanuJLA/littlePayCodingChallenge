package com.littlePayJourneys;

import java.time.Duration;
import java.util.Objects;

public class CompletedTrips extends AllTrips{
    private Long durationSecs;
    private String status;

    public CompletedTrips(Taps tapOn, Taps tapOff) {
        super(tapOn, tapOff);
        this.durationSecs = getTripDuration(tapOn, tapOff);
        this.status = getTripStatus();
    }

     public Long getTripDuration(Taps tapOn, Taps tapOff) {
        Long duration = 0L;
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        if (Boolean.FALSE.equals(isIncompleteTrip)) {
            duration = Duration.between(tapOn.getDateTimeUtc(), tapOff.getDateTimeUtc()).getSeconds();
        }
        return duration;
    }

    @Override
    public String getTripStatus(){
        return "COMPLETED";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedTrips trips = (CompletedTrips) o;
        return Objects.equals(super.getStarted(), trips.getStarted()) && Objects.equals(super.getFinished(), trips.getFinished()) && Objects.equals(durationSecs, trips.durationSecs) && Objects.equals(super.getFromStopId(), trips.getFromStopId()) && Objects.equals(super.getToStopId(), trips.getToStopId()) && Objects.equals(super.getChargeAmount(), trips.getChargeAmount()) && Objects.equals(super.getCompanyId(), trips.getCompanyId()) && Objects.equals(super.getBusId(), trips.getBusId()) && Objects.equals(super.getPan(), trips.getPan()) && Objects.equals(status, trips.status);
    }

    @Override
    public String toString() {
        return
                super.getStarted() +
                        ", " + super.getFinished() +
                        ", " + durationSecs +
                        ", " + super.getFromStopId() +
                        ", " + super.getToStopId() +
                        ", " + super.getChargeAmount() +
                        ", " + super.getCompanyId() +
                        ", " + super.getBusId() +
                        ", " + super.getPan() +
                        ", " + status;
    }

@Override
    public Long getDurationSecs() {
        return durationSecs;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
