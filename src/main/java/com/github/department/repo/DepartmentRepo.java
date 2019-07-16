package com.github.department.repo;

import com.github.department.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CRUD refers Create, Read, Update, Delete

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    List<Department> findByName(String name);
}