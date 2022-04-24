package com.littlePayJourneys;

import java.sql.Timestamp;
import java.time.Duration;

public abstract class AllTrips implements Trips{
    private Timestamp started;
    private Timestamp finished;
    private String fromStopId;
    private String toStopId;
    private String companyId;
    private String busId;
    private String pan;
    private Double chargeAmount;
    private Long durationSecs;

    public AllTrips (Taps tapOn, Taps tapOff){
        this.started = tapOn.getDateTimeUtc() != null ? Timestamp.valueOf(tapOn.getDateTimeUtc()) : null;
        this.finished = tapOff.getDateTimeUtc() != null ? Timestamp.valueOf(tapOff.getDateTimeUtc()) : null;
        this.fromStopId = tapOn.getStopId();
        this.toStopId = tapOff.getStopId();
        this.companyId = tapOn.getCompanyId();
        this.busId = tapOn.getBusId();
        this.pan = tapOn.getPan();
        this.chargeAmount = calculateCharge(tapOn, tapOff);
        this.durationSecs = calculateTripDuration(tapOn, tapOff);
    }

    @Override
    public Long calculateTripDuration(Taps tapOn, Taps tapOff) {
        Long duration = 0L;
        if (tapOn.getDateTimeUtc() != null && tapOff.getDateTimeUtc() != null) {
            duration = Duration.between(tapOn.getDateTimeUtc(), tapOff.getDateTimeUtc()).getSeconds();
        }
        return duration;
    }

    @Override
    public Double calculateCharge(Taps tapOn, Taps tapOff){

        Double fare = 0.0;

        Double stop1to2 = 3.25;
        Double stop1to3 = 7.30;
        Double stop2to3 = 5.50;

        String combinedStops = tapOn.getStopId() + tapOff.getStopId();
      switch (combinedStops){
          case "Stop1Stop2" :
          case "Stop2Stop1":
              fare = stop1to2;
              break;
          case "Stop1Stop3":
          case "Stop3Stop1":
              fare  = stop1to3;
              break;
              case "Stop2Stop3":
          case "Stop3Stop2":
              fare = stop2to3;
              break;
          case "Stop1null":
              fare = Double.max(stop1to2, stop1to3);
                  break;
          case "Stop2null":
              fare = Double.max(stop1to2, stop2to3);
              break;
          case "Stop3null":
              fare = Double.max(stop1to3, stop2to3);
              break;
      }
      return fare;
    }

    public Timestamp getStarted() {
        return started;
    }

    public Timestamp getFinished() {
        return finished;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBusId() {
        return busId;
    }

    public String getPan() {
        return pan;
    }

    @Override
    public Long getDurationSecs() {
        return durationSecs;
    }

    @Override
    public Double getChargeAmount() {
        return chargeAmount;
    }
}
