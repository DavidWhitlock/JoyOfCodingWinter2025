package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class TextDumper implements AirlineDumper<Airline> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(Airline airline) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
      for (Flight flight : airline.getFlights()) {
        pw.println(airline.getName() + " : " + flight.getNumber());
      }

      pw.flush();
    }
  }
}
