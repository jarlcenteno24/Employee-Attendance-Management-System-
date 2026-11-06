import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class AttendanceManager {
  private final List<Employee> employees;
  private final List<AttendanceRecord> attendanceRecords;

  public AttendanceManager() {
    this.employees = new ArrayList<>();
    this.attendanceRecords = new ArrayList<>();
  }

  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee registeredEmployee : employees) {
      if (registeredEmployee.employeeId.equals(employee.employeeId)) {
        throw new EmployeeAlreadyExistsException(
            "Employee ID " + employee.employeeId + " is already registered.");
      }
    }
    employees.add(employee);
  }

  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee employee : employees) {
      if (employee.employeeId.equals(employeeId)) {
        return employee;
      }
    }
    throw new EmployeeNotFoundException("Employee ID " + employeeId + " was not found.");
  }

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

  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);
    return attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}
