package com.github.department.repo;

import com.github.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CRUD refers Create, Read, Update, Delete

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    Page<Department> findByName(String name, Pageable pageable);

    Page<Department> findByNameLike(String name, Pageable pageable);

    Page<Department> findAll(Pageable pageable);

    List<Department> findAll();
}