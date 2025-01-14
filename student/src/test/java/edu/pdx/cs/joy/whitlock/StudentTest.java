package edu.pdx.cs.joy.whitlock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
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

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\"."));
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

}
