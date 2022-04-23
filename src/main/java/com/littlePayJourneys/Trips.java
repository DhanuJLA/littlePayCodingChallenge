package com.littlePayJourneys;

import java.time.Duration;
import java.time.LocalDateTime;

public class Trips {
    private LocalDateTime started;
    private  LocalDateTime finished;
    private  Long durationSecs;
    private  String fromStopId;
    private  String toStopId;
    private  Double chargeAmount;
    private  String companyId;
    private  String busId;
    private  String pan;
    private  String status;


    public Trips(LocalDateTime started, LocalDateTime finished, Long durationSecs, String fromStopId, String toStopId, Double chargeAmount, String companyId, String busId, String pan, String status) {
        this.started = started;
        this.finished = finished;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.status = status;
    }

    public Trips(Taps tapOn, Taps tapOff){
        this.started = tapOn.getDateTimeUtc();
        this.finished = tapOff.getDateTimeUtc();
        this.durationSecs = getTripDuration(tapOn, tapOff);
        this.fromStopId = tapOn.getStopId();
        this.toStopId = tapOff.getStopId();
        this.chargeAmount = getCharge(tapOn, tapOff);
        this.companyId = tapOn.getCompanyId();
        this.busId = tapOn.getBusId();
        this.pan = tapOn.getPan();
        this.status = getTripStatus(tapOn, tapOff);
    }

    public Trips() {

    }

    public static Long getTripDuration(Taps tapOn, Taps tapOff){
        Long duration  = 0L;
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        if(Boolean.FALSE.equals(isIncompleteTrip)){
          duration = Duration.between(tapOn.getDateTimeUtc(), tapOff.getDateTimeUtc()).getSeconds();
        }
        return duration;
    }

    public static String getTripStatus(Taps tapOn, Taps tapOff){
        String status = "COMPLETED";
        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());

        if(Boolean.TRUE.equals(isIncompleteTrip)){
            status = "INCOMPLETE";
        } else {
            Boolean isTripCancelled = tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId());
            if(Boolean.TRUE.equals(isTripCancelled)){
                status = "CANCELLED";
            }
        }
        return status;
    }

    public static Double getCharge(Taps tapOn, Taps tapOff){
        Double charge = 0.0;

        Boolean isIncompleteTrip = !tapOn.getPan().equalsIgnoreCase(tapOff.getPan());
        Boolean isTripCancelled = tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId());

        String fromStop = tapOn.getStopId();
        String toStop = tapOff.getStopId();

        if(Boolean.FALSE.equals(isTripCancelled)){
            if(Boolean.TRUE.equals(isIncompleteTrip)){
                if((("Stop1").equalsIgnoreCase(fromStop) || ("Stop1").equalsIgnoreCase(toStop))){
                    charge = Double.max(TripsProcessor.stop1ToStop2, TripsProcessor.stop1ToStop3);
                } else if((("Stop2").equalsIgnoreCase(fromStop) || ("Stop2").equalsIgnoreCase(toStop))){
                    charge = Double.max(TripsProcessor.stop1ToStop2, TripsProcessor.stop2ToStop3);
                } else{
                    charge = Double.max(TripsProcessor.stop1ToStop3, TripsProcessor.stop2ToStop3);
                }
            } else {
                if((("Stop1").equalsIgnoreCase(fromStop) || ("Stop2").equalsIgnoreCase(fromStop)) && (("Stop1").equalsIgnoreCase(toStop) || ("Stop2").equalsIgnoreCase(toStop))){
                    charge = TripsProcessor.stop1ToStop2;
                } else if((("Stop1").equalsIgnoreCase(fromStop) || ("Stop3").equalsIgnoreCase(fromStop)) && (("Stop1").equalsIgnoreCase(toStop) || ("Stop3").equalsIgnoreCase(toStop))){
                    charge = TripsProcessor.stop1ToStop3;
                } else if((("Stop2").equalsIgnoreCase(fromStop) || ("Stop3").equalsIgnoreCase(fromStop)) && (("Stop2").equalsIgnoreCase(toStop) || ("Stop3").equalsIgnoreCase(toStop))){
                    charge = TripsProcessor.stop2ToStop3;
                }
            }

        }

        return charge;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public Double getChargeAmount() {
        return chargeAmount;
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

    public String getStatus() {
        return status;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }

    public void setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trips{" +
                "started=" + started +
                ", finished=" + finished +
                ", durationSecs=" + durationSecs +
                ", fromStopId='" + fromStopId + '\'' +
                ", toStopId='" + toStopId + '\'' +
                ", chargeAmount='" + chargeAmount + '\'' +
                ", companyId='" + companyId + '\'' +
                ", busId='" + busId + '\'' +
                ", pan='" + pan + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
