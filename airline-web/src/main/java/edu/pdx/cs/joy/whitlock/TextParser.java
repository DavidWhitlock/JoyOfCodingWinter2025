package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AirlineParser;
import edu.pdx.cs.joy.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements AirlineParser<Airline> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  @Override
  public Airline parse() throws ParserException {
    Pattern pattern = Pattern.compile("(.*) : (.*)");

    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      for (String line = br.readLine(); line != null; line = br.readLine()) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
          throw new ParserException("Unexpected text: " + line);
        }

        String airlineName = matcher.group(1);
        String flightNumber = matcher.group(2);

        Airline airline = new Airline(airlineName);
        airline.addFlight(new Flight(Integer.parseInt(flightNumber)));
        return airline;
      }

      return null;

    } catch (IOException e) {
      throw new ParserException("While parsing dictionary", e);
    }
  }
}
