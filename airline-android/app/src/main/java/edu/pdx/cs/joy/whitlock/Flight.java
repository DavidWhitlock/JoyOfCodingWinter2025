package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AbstractFlight;

public class Flight extends AbstractFlight {
    private final int flightNumber;

    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public int getNumber() {
        return this.flightNumber;
    }

    @Override
    public String getSource() {
        return "SOURCE";
    }

    @Override
    public String getDepartureString() {
        return "Departure Time";
    }

    @Override
    public String getDestination() {
        return "DESTINATION";
    }

    @Override
    public String getArrivalString() {
        return "Arrival Time";
    }
}
