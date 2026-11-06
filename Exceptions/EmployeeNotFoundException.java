/**
 * Exception thrown when an employee cannot be found.
 */
public class EmployeeNotFoundException extends Exception {
  /**
   * Creates an exception with a descriptive message.
   *
   * @param message details about the missing employee
   */
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
