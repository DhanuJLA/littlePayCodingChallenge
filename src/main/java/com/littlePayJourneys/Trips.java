package com.littlePayJourneys;

public interface Trips {

    Long getTripDuration(Taps tapOn, Taps tapOff);

    String getTripStatus();

    Double getCharge(Taps tapOn, Taps tapOff);

    Long getDurationSecs();

    Double getChargeAmount();

    String getStatus();

    String toString();



}
