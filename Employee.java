/**
 * Represents an employee in the attendance management system.
 */
public class Employee {
  String employeeId;
  String name;
  String department;

  /**
   * Creates an employee with the specified ID, name, and department.
   *
   * @param employeeId the employee's unique identifier
   * @param name the employee's name
   * @param department the employee's department
   */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
  }
}
