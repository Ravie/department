package com.github.department.repo;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    Page<Employee> findByDepartment(Department department, Pageable pageable);

    Page<Employee> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    Page<Employee> findBySalaryGreaterThanEqual(Double minSalary, Pageable pageable);

    Page<Employee> findBySalaryLessThanEqual(Double maxSalary, Pageable pageable);

    @Query("select empl.id, empl.name, dep.name, empl.salary " +
            "from Employee empl " +
            "left join Department dep " +
            "on dep.id = empl.department")
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByName(String name);

    List<Employee> findByNameIgnoreCaseContaining(String name);

    List<Employee> findByDepartment(Department department);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    List<Employee> findBySalaryLessThanEqual(Double maxSalary);

    List<Employee> findAll();

    @Query("select empl.id, empl.name, dep.name, empl.salary " +
            "from Employee empl " +
            "left join Department dep " +
            "on dep.id = empl.department " +
            "where (empl.name like %:name% or :name = '') " +
            "and (empl.department = :department or :department is null) " +
            "and ((empl.salary <= :maxSalary and :minSalary is null) " +
            "or (empl.salary >= :minSalary and :maxSalary is null) " +
            "or (empl.salary >= :minSalary and empl.salary <= :maxSalary) " +
            "or (:minSalary is null and :maxSalary is null))")
    Page<Employee> filter(
            @Param("name") String name,
            @Param("department") Department department,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary,
            Pageable pageable
    );
}
