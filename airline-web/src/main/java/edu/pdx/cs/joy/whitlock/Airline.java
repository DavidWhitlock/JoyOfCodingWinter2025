package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Airline extends AbstractAirline<Flight> {
  private final String name;
  private final List<Flight> flights = new ArrayList<>();

  public Airline(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    this.flights.add(flight);
  }

  @Override
  public Collection<Flight> getFlights() {
    return this.flights;
  }

}
