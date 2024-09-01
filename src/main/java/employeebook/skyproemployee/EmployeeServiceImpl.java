package employeebook.skyproemployee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int maxListSize = 10;

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov"),
            new Employee("Oleg", "Ivanov"),
            new Employee("Ivan", "Fedorov"),
            new Employee("Oleg", "Fedorov"),
            new Employee("Ivan", "Sidorov"),
            new Employee("Oleg", "Sidorov"),
            new Employee("Ivan", "Petrenko"),
            new Employee("Oleg", "Petrenko"),
            new Employee("Roman", "Polyakov"),
            new Employee("Gennadiy", "Polyakov")
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxListSize) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (!employees.contains(findEmployee)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        for (Employee employee : employees)
            if (employee.equals(findEmployee)) {
                return employee;
            }
        return findEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(null)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}