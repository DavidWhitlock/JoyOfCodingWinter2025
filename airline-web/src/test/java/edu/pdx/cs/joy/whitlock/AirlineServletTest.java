package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.ParserException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class AirlineServletTest {

  @Test
  void addAirlineWithOneFlight() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    String airlineName = "Airline";
    String flightNumber = "123";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINE_PARAMETER)).thenReturn(airlineName);
    when(request.getParameter(AirlineServlet.FLIGHT_NUMBER_PARAMETER)).thenReturn(flightNumber);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    assertThat(stringWriter.toString(), containsString(Messages.prettyPrintFlight(airlineName, flightNumber)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    Airline airline = servlet.getAirline(airlineName);
    assertThat(airline.getName(), equalTo(airlineName));
    assertThat(airline.getFlights().size(), equalTo(1));
    assertThat(airline.getFlights().iterator().next().getNumber(), equalTo(Integer.parseInt(flightNumber)));
  }

  @Test
  void getAirlineWithOneFlight() throws IOException, ParserException {
    AirlineServlet servlet = new AirlineServlet();

    String airlineName = "Airline";
    int flightNumber = 123;

    Flight flight = new Flight(flightNumber);
    Airline airline = new Airline(airlineName);
    airline.addFlight(flight);

    servlet.addAirline(airline);

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINE_PARAMETER)).thenReturn(airlineName);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    String airlineText = stringWriter.toString();
    TextParser parser = new TextParser(new StringReader(airlineText));
    Airline parsedAirline = parser.parse();
    assertThat(parsedAirline.getName(), equalTo(airlineName));
    assertThat(parsedAirline.getFlights().size(), equalTo(1));
    assertThat(parsedAirline.getFlights().iterator().next().getNumber(), equalTo(flightNumber));
  }

}
