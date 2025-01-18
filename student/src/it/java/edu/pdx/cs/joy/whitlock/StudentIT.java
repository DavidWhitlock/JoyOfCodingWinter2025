package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
class StudentIT extends InvokeMainTestCase {

  @Test
  void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Disabled
  @Test
  void daveStudent() {
    String[] args = { "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java" };
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, args);

    String toString = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\".";
    assertThat(result.getTextWrittenToStandardOut(), equalTo(toString));
  }

  @Test
  void negativeGpaPrintsErrorMessageToStandardError() {
    // Adam male -3.64 Intro to Database
    String[] args = { "Adam", "male", "-3.64", "Intro to Database" };
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, args);

    assertThat(result.getTextWrittenToStandardError(), containsString("Please enter a valid GPA."));
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));

  }
}
