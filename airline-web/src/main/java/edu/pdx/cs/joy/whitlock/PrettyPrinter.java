package edu.pdx.cs.joy.whitlock;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs.joy.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class PrettyPrinter implements AirlineDumper<Airline> {
  private final Writer writer;

  @VisibleForTesting
  static String formatWordCount(int count )
  {
    return String.format( "Dictionary on server contains %d words", count );
  }

  @VisibleForTesting
  static String formatFlightDictionaryEntry(String word, String definition )
  {
    return String.format("  %s -> %s", word, definition);
  }


  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(Airline airline) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {

      for (Flight flight : airline.getFlights()) {
        String airlineName = airline.getName();
        int flightNumber = flight.getNumber();
        pw.println(formatFlightDictionaryEntry(airlineName, String.valueOf(flightNumber)));
      }

      pw.flush();
    }

  }
}
