package com.github.department.repo;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByDepartment(Department department);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    List<Employee> findBySalaryLessThanEqual(Double maxSalary);
}
