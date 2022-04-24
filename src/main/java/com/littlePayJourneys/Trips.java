package com.littlePayJourneys;

public interface Trips {

    Long calculateTripDuration(Taps tapOn, Taps tapOff);

    String assignTripStatus();

    Double calculateCharge(Taps tapOn, Taps tapOff);

    Long getDurationSecs();

    Double getChargeAmount();

    String getStatus();

    String toString();

}
