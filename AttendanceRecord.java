import java.time.LocalDateTime;

/**
 * Represents an employee's attendance period, including its start and end times.
 */
public class AttendanceRecord {
  private final String employeeId;
  private final LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Creates an attendance record with the employee's clock-in time.
   *
   * @param employeeId the employee's identifier
   * @param timeIn the date and time the employee clocked in
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  /**
   * Returns the identifier of the employee associated with this record.
   *
   * @return the employee identifier
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Returns the employee's clock-in date and time.
   *
   * @return the clock-in date and time
   */
  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  /**
   * Returns the employee's clock-out date and time, if set.
   *
   * @return the clock-out date and time, or {@code null} if not set
   */
  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  /**
   * Sets the employee's clock-out date and time.
   *
   * @param timeOut the date and time the employee clocked out
   */
  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }
}
