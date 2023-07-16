package ru.mikhailov.employeescompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mikhailov.employeescompany.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
