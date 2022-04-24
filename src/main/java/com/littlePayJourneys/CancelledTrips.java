package com.littlePayJourneys;

import java.util.Objects;

public class CancelledTrips extends AllTrips {
    private Long durationSecs;
    private Double chargeAmount;
    private String status;

    public CancelledTrips(Taps tapOn, Taps tapOff) {
        super(tapOn, tapOff);
        this.durationSecs = getTripDuration(tapOn, tapOff);
        this.chargeAmount = getCharge(tapOn, tapOff);
        this.status = getTripStatus();
    }

    @Override
    public Long getTripDuration(Taps tapOn, Taps tapOff) {
        return 0L;
    }

    @Override
    public String getTripStatus() {
        return "CANCELLED";
    }

    @Override
    public Double getCharge(Taps tapOn, Taps tapOff) {
        return 0.0;
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
    public Double getChargeAmount() {
        return chargeAmount;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancelledTrips trips = (CancelledTrips) o;
        return Objects.equals(super.getStarted(), trips.getStarted()) && Objects.equals(super.getFinished(), trips.getFinished()) && Objects.equals(durationSecs, trips.durationSecs) && Objects.equals(super.getFromStopId(), trips.getFromStopId()) && Objects.equals(super.getToStopId(), trips.getToStopId()) && Objects.equals(chargeAmount, trips.chargeAmount) && Objects.equals(super.getCompanyId(), trips.getCompanyId()) && Objects.equals(super.getBusId(), trips.getBusId()) && Objects.equals(super.getPan(), trips.getPan()) && Objects.equals(status, trips.status);
    }
}
