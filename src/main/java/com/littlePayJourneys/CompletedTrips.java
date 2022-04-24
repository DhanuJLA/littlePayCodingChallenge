package com.littlePayJourneys;

import java.util.Objects;

public class CompletedTrips extends AllTrips {
    private String status;

    public CompletedTrips(Taps tapOn, Taps tapOff) {
        super(tapOn, tapOff);
        this.status = assignTripStatus();
    }

    @Override
    public String assignTripStatus() {
        return "COMPLETED";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedTrips trips = (CompletedTrips) o;
        return Objects.equals(super.getStarted(), trips.getStarted()) && Objects.equals(super.getFinished(), trips.getFinished()) && Objects.equals(super.getDurationSecs(), trips.getDurationSecs()) && Objects.equals(super.getFromStopId(), trips.getFromStopId()) && Objects.equals(super.getToStopId(), trips.getToStopId()) && Objects.equals(super.getChargeAmount(), trips.getChargeAmount()) && Objects.equals(super.getCompanyId(), trips.getCompanyId()) && Objects.equals(super.getBusId(), trips.getBusId()) && Objects.equals(super.getPan(), trips.getPan()) && Objects.equals(status, trips.status);
    }

    @Override
    public String toString() {
        return
                super.getStarted() +
                        ", " + super.getFinished() +
                        ", " + super.getDurationSecs() +
                        ", " + super.getFromStopId() +
                        ", " + super.getToStopId() +
                        ", " + super.getChargeAmount() +
                        ", " + super.getCompanyId() +
                        ", " + super.getBusId() +
                        ", " + super.getPan() +
                        ", " + status;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
