package dao;

import java.util.List;

public class EmployeeService {
    private AbstractDAOInterface<Employee> employeeDAO;

    public EmployeeService(AbstractDAOInterface<Employee> employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void persist(Employee employee){
        employeeDAO.persist(employee);
    }
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }
    public Employee findById(Integer id) {
        return employeeDAO.findById(id);
    }
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }
    public void deleteAll() {
        employeeDAO.deleteAll();
    }
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
