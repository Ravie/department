package com.github.department.repo;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    Page<Employee> findByDepartment(Department department, Pageable pageable);

    Page<Employee> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    Page<Employee> findBySalaryGreaterThanEqual(Double minSalary, Pageable pageable);

    Page<Employee> findBySalaryLessThanEqual(Double maxSalary, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByName(String name);

    List<Employee> findByNameIgnoreCaseContaining(String name);

    List<Employee> findByDepartment(Department department);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    List<Employee> findBySalaryLessThanEqual(Double maxSalary);

    List<Employee> findAll();
}
