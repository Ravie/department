package com.github.department.repo;

import com.github.department.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
    List<Employee> findByName(String name);

    List<Employee> findByDepId(Integer depId);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    List<Employee> findBySalaryLessThanEqual(Double maxSalary);
}
