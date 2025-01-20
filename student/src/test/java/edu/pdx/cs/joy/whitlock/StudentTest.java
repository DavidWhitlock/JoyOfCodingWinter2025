package edu.pdx.cs.joy.whitlock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = createStudentNamed(name);
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  void allStudentSayThisClassIsTooMuchWork() {
    var student = createStudentNamed("ANYTHING");
    assertThat(student.says(), equalTo("This class is too much work"));
  }

  private static Student createStudentNamed(String name) {
    return new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
  }

  @Disabled
  @Test
  void daveStudent() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("Algorithms");
    classes.add("Operation Systems");
    classes.add("Java");
    Student dave = new Student("Dave", classes, 3.64, "male");

    String toString = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\".";
    assertThat(dave.toString(), equalTo(toString));
  }

  @Test
  void studentToStringContainsThisClassIsTooMuchWork() {
    Student student = createStudentNamed("Student");
    assertThat(student.toString(), containsString(" \"This class is too much work\"."));
  }

  @Test
  void studentWithNameThatIsANumberThrowsIllegalArgumentException() {
    IllegalArgumentException exception =
      assertThrows(IllegalArgumentException.class, () -> createStudentNamed("123"));
    assertThat(exception.getMessage(), equalTo("Name cannot be a number"));
  }

  @Test
  void negativeGpaThrowsNegativeGpaException() {
    // Adam male -3.64 Intro to Database

    ArrayList<String> classes = new ArrayList<>();
    classes.add("Intro to Database");

    assertThrows(Student.NegativeGpaException.class, () -> {
      new Student("Adam", classes, -3.64, "male");
    });

  }

  @Test
  void aboutDatesKoanUsingDateTimeFormatterToFormatDate() {
    LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(100010001000L), ZoneId.of("-07:00"));

    // assert with and without non-breaking space to handle Java 17 and 21+

    String shortFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(date);
    assertThat(shortFormat, anyOf(equalTo("3/3/73, 5:33\u202FAM"), equalTo("3/3/73, 5:33 AM")));

    String mediumFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(date);
    assertThat(mediumFormat, anyOf(equalTo("Mar 3, 1973, 5:33:21\u202FAM"), equalTo("Mar 3, 1973, 5:33:21 AM")));
  }

}
