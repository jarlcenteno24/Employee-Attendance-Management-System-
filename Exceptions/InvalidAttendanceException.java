/**
 * Exception thrown when attendance data is invalid.
 */
public class InvalidAttendanceException extends Exception {
  /**
   * Creates an exception describing why the attendance data is invalid.
   *
   * @param message details about the invalid attendance data
   */
  public InvalidAttendanceException(String message) {
    super(message);
  }
}
