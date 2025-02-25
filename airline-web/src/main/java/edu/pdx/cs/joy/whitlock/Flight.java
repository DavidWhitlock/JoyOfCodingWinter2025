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
  public boolean equals(Object obj) {
    if (obj instanceof Flight) {
      Flight other = (Flight) obj;
      return this.flightNumber == other.flightNumber;
    }
    return false;
  }

  @Override
  public String getSource() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
