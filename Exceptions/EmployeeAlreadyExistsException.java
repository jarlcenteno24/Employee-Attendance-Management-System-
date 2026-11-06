/**
 * Thrown when attempting to add an employee that already exists in the system.
 *
 * @author jarl.centeno
 * @since 1.0
 */
public class EmployeeAlreadyExistsException extends Exception {
  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message explaining why the employee already exists
   */
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }
}
