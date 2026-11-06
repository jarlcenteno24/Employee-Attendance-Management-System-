import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Manages employee registration and attendance records, including time-in and
 * time-out operations.
 */
public class AttendanceManager {
  private final List<Employee> employees;
  private final List<AttendanceRecord> attendanceRecords;

  /** Creates an attendance manager with no employees or attendance records. */
  public AttendanceManager() {
    this.employees = new ArrayList<>();
    this.attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers an employee unless an employee with the same ID is already registered.
   *
   * @param employee the employee to register
   * @throws EmployeeAlreadyExistsException if the employee ID is already registered
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee registeredEmployee : employees) {
      if (registeredEmployee.employeeId.equals(employee.employeeId)) {
        throw new EmployeeAlreadyExistsException(
            "Employee ID " + employee.employeeId + " is already registered.");
      }
    }
    employees.add(employee);
  }

  /**
   * Finds a registered employee by ID.
   *
   * @param employeeId the ID of the employee to find
   * @return the matching employee
   * @throws EmployeeNotFoundException if no employee has the given ID
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee employee : employees) {
      if (employee.employeeId.equals(employeeId)) {
        return employee;
      }
    }
    throw new EmployeeNotFoundException("Employee ID " + employeeId + " was not found.");
  }

  /**
   * Starts an attendance record for a registered employee.
   *
   * @param employeeId the ID of the employee clocking in
   * @return the newly created attendance record
   * @throws EmployeeNotFoundException if no employee has the given ID
   * @throws InvalidAttendanceException if the employee already has an active record
   */
  public AttendanceRecord timeIn(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);

    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        throw new InvalidAttendanceException(
            "Employee ID " + employeeId + " already has an active attendance record.");
      }
    }

    AttendanceRecord record = new AttendanceRecord(employeeId, LocalDateTime.now());
    attendanceRecords.add(record);
    return record;
  }

  /**
   * Completes the active attendance record for a registered employee.
   *
   * @param employeeId the ID of the employee clocking out
   * @throws EmployeeNotFoundException if no employee has the given ID
   * @throws InvalidAttendanceException if the employee has no active record
   */
  public void timeOut(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);

    for (int i = attendanceRecords.size() - 1; i >= 0; i--) {
      AttendanceRecord record = attendanceRecords.get(i);
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        record.setTimeOut(LocalDateTime.now());
        return;
      }
    }

    throw new InvalidAttendanceException(
        "Employee ID " + employeeId + " has no active attendance record to complete.");
  }

  /**
   * Returns completed attendance records for a registered employee.
   *
   * @param employeeId the ID of the employee whose records are requested
   * @return the employee's completed attendance records
   * @throws EmployeeNotFoundException if no employee has the given ID
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);
    return attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}
