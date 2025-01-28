package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Disabled
  @Test
  void printOptionPrintsValidFlightToStringToStandardOut() {
    MainMethodResult result = invokeMain("-print", "Airline Name", "123", "PDX", "1/27/2025", "18:00", "LAV", "1/27/2025", "20:00");
    String expected = "Flight 123 departs PDX at 1/27/2025 18:00 arrives LAV at 1/27/2025 20:00";
    assertThat(result.getTextWrittenToStandardOut(), containsString(expected));
  }

}