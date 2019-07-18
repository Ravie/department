package com.github.department.repo;

import com.github.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Page<Department> findByName(String name, Pageable pageable);

    Page<Department> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    Page<Department> findAll(Pageable pageable);

    List<Department> findByName(String name);

    List<Department> findByNameIgnoreCaseContaining(String name);

    List<Department> findAll();
}