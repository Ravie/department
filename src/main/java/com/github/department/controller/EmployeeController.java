package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import com.github.department.repo.DepartmentRepo;
import com.github.department.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    DepartmentRepo departmentRepo;

    @GetMapping("/edit/{employee}")
    public String employeeEditorForm(@PathVariable Employee employee, Model model) {
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentRepo.findAll());
        return "employee";
    }

    @GetMapping("delete/{employee}")
    public String employeeDelete(@PathVariable Employee employee, Model model) {
        employeeRepo.deleteById(employee.getId());
        model.addAttribute("departments", departmentRepo.findAll());
        return "redirect:/index";
    }

    @PostMapping
    public String saveChanges(@RequestParam("employeeId") Employee employee,
                              @RequestParam String name,
                              @RequestParam("depId") Department department,
                              @RequestParam Double salary
    ) {
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);

        employeeRepo.save(employee);

        return "redirect:/index";
    }
}