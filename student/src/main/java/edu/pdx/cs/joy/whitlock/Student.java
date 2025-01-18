package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {                                                
                                                                                    
  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);
    throwIllegalArgumentExceptionIfNumber(name);
    throwNegativeGpaExceptionIfNegative(gpa);
  }

  private void throwNegativeGpaExceptionIfNegative(double gpa) {
    if (gpa < 0.0) {
      throw new NegativeGpaException();
    }

  }

  private void throwIllegalArgumentExceptionIfNumber(String string) {
    try {
      Integer.parseInt(string);
      throw new IllegalArgumentException("Name cannot be a number");

    } catch (NumberFormatException ex) {
      // Name is not a valid number.  That's good.
    }

  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    return "This class is too much work";
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    return " \"" + says() + "\".";
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("** Missing command line arguments");
      return;
    }

    String gpaString = args[2];
    double gpa;
    try {
      gpa = Double.parseDouble(gpaString);

    } catch (NumberFormatException ex) {
      System.err.println("GPA \"" + gpaString + "\" is not a number between 0.0 and 4.0");
      return;
    }

    ArrayList<String> classes = new ArrayList<>();
    try {
      new Student("Name", classes, gpa, "GENDER");

    } catch (NegativeGpaException ex) {
      System.err.println("Please enter a valid GPA.");
    }

  }

  public static class NegativeGpaException extends RuntimeException {
  }
}