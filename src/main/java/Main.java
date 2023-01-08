import dao.Employee;
import dao.EmployeeDAODB;
import dao.EmployeeService;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService(new EmployeeDAODB());

        int randId = (new Random()).nextInt(99);
        Employee employee = new Employee("Peter" + randId, new Date());
        employeeService.persist(employee);

        List<Employee> employees = employeeService.findAll();
        employees.stream().forEach(p -> System.out.println(p));

        Employee emplyeeFromDB = employeeService.findById(employees.get(0).getId());
        System.out.println("\nEmployee from DB: " + emplyeeFromDB);

//        employeeService.delete( emplyeeFromDB );
//        System.out.println("Employee id: " + emplyeeFromDB.getId() + " deleted");

//        System.out.println("\nEmployees list after delete:");
//        employees = employeeService.findAll();
//        employees.stream().forEach(p -> System.out.println(p));
    }
}
