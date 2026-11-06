import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates employee registration and attendance tracking operations.
 */
public class Main {
  /**
   * Runs the attendance management examples and prints their results.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    AttendanceManager attendanceManager = new AttendanceManager();
    String employeeId = "E001";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    try {
      attendanceManager.registerEmployee(new Employee(employeeId, "Riri Williams", "IT"));
      attendanceManager.registerEmployee(new Employee("E002", "Nick Fury", "HR"));
      System.out.println("Employees E001 and E002 successfully registered.");

      try {
        attendanceManager.registerEmployee(
            new Employee("E001", "Thor Son of Odin", "Electrical"));
      } catch (EmployeeAlreadyExistsException e) {
        System.out.println("Employee registration rejected: " + e.getMessage());
      }

      Employee employee = attendanceManager.findEmployeeById(employeeId);
      System.out.println(
          "Found employee: ID=" + employee.employeeId
              + ", Name=" + employee.name
              + ", Department=" + employee.department);

      try {
        attendanceManager.findEmployeeById("E999");
      } catch (EmployeeNotFoundException e) {
        System.out.println("Employee search failed: " + e.getMessage());
      }

      AttendanceRecord record = attendanceManager.timeIn(employeeId);
      System.out.println(
          "Time-in recorded for " + employeeId
              + ": Time in: " + record.getTimeIn().format(formatter)
              + ", Time out: " + record.getTimeOut());

      try {
        attendanceManager.timeIn(employeeId);
      } catch (InvalidAttendanceException e) {
        System.out.println("Additional time-in rejected: " + e.getMessage());
      }

      attendanceManager.timeOut(employeeId);
      System.out.println(
          "Time-out recorded for " + employeeId
              + ": Time in: " + record.getTimeIn().format(formatter)
              + ", Time out: " + record.getTimeOut().format(formatter));

      AttendanceRecord nextRecord = attendanceManager.timeIn(employeeId);
      System.out.println(
          "Previous completed record remains: Time in: " + record.getTimeIn().format(formatter)
              + ", Time out: " + record.getTimeOut().format(formatter));
      System.out.println(
          "New time-in recorded for " + employeeId
              + ": Time in: " + nextRecord.getTimeIn().format(formatter)
              + ", Time out: " + nextRecord.getTimeOut());

      List<AttendanceRecord> completedRecords =
          attendanceManager.getCompletedAttendanceRecords(employeeId);
      System.out.println("Completed attendance records for " + employeeId + ":");
      for (AttendanceRecord completedRecord : completedRecords) {
        System.out.println(
            "Time in: " + completedRecord.getTimeIn().format(formatter)
                + ", Time out: " + completedRecord.getTimeOut().format(formatter));
      }
    } catch (EmployeeAlreadyExistsException | EmployeeNotFoundException
        | InvalidAttendanceException e) {
      System.err.println("Attendance operation failed: " + e.getMessage());
    }
  }
}
