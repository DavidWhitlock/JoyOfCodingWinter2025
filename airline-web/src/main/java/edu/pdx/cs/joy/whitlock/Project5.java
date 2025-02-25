package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.ParserException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {
        String hostName = null;
        String portString = null;
        String airlineName = null;
        String flightNumberString = null;

        for (String arg : args) {
            if (hostName == null) {
                hostName = arg;

            } else if ( portString == null) {
                portString = arg;

            } else if (airlineName == null) {
                airlineName = arg;

            } else if (flightNumberString == null) {
                flightNumberString = arg;

            } else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );
            return;

        } else if ( portString == null) {
            usage( "Missing port" );
            return;
        }

        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        AirlineRestClient client = new AirlineRestClient(hostName, port);

        String message;
        try {
            if (airlineName == null) {
                System.err.println("Missing airline");
                return;

            } else if (flightNumberString == null) {
                // Pretty print the airline
                Airline airline = client.getAirline(airlineName);
                StringWriter sw = new StringWriter();
                new PrettyPrinter(sw).dump(airline);
                message = sw.toString();

            } else {
                // Post the airline/flightNumber pair
                int flightNumber = Integer.parseInt(flightNumberString);
                Flight flight = new Flight(flightNumber);
                client.addFlight(airlineName, flight);
                message = Messages.prettyPrintFlight(airlineName, flightNumberString);
            }

        } catch (IOException | ParserException ex ) {
            error("While contacting server: " + ex.getMessage());
            return;
        }

        System.out.println(message);
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project5 host port airline [flightNumber]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  airline      Name of airline");
        err.println("  flightNumber Flight number");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();
    }
}