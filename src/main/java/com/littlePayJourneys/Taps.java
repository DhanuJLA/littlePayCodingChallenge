package com.littlePayJourneys;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Taps {
    private int id;
    private LocalDateTime dateTimeUtc;
    private String tapType;
    private String stopId;
    private String companyId;
    private String busId;
    private String pan;

    public Taps() {
    }

    public Taps(int id, LocalDateTime dateTimeUtc, String tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        this.dateTimeUtc = dateTimeUtc;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTimeUtc() {
        return dateTimeUtc;
    }

    public LocalDate getDate(){
        return this.dateTimeUtc.toLocalDate();
    }

    public String getTapType() {
        return tapType;
    }

    public String getStopId() {
        return stopId;
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
    public String toString() {
        return "Taps{" +
                "id=" + id +
                ", dateTimeUtc=" + dateTimeUtc +
                ", tapType='" + tapType + '\'' +
                ", stopId='" + stopId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", busId='" + busId + '\'' +
                ", pan='" + pan + '\'' +
                '}';
    }
}
