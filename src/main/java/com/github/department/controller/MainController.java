package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import com.github.department.entity.User;
import com.github.department.repo.DepartmentRepo;
import com.github.department.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo depRepo;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/index")
    public String index(@PageableDefault(sort= {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String name,
                        @RequestParam(required = false) Department depId,
                        @RequestParam(required = false, defaultValue = "") Double minSalary,
                        @RequestParam(required = false, defaultValue = "") Double maxSalary,
                        Model model
    ) {
        /*
        List<Employee> employeesByName;
        List<Employee> employeesByDep;
        List<Employee> employeesBySalary;

        if (name != null && !name.isEmpty()) {
            employeesByName = employeeRepo.findByNameIgnoreCaseContaining(name);
        } else {
            employeesByName = employeeRepo.findAll();
        }

        if (depId != null) {
            employeesByDep = employeeRepo.findByDepartment(depId);
        } else {
            employeesByDep = employeeRepo.findAll();
        }

        employeesByDep.retainAll(employeesByName);

        if (minSalary != null && maxSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryBetween(minSalary, maxSalary);
        } else if (minSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryGreaterThanEqual(minSalary);
        } else if (maxSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryLessThanEqual(maxSalary);
        } else {
            employeesBySalary = employeeRepo.findAll();
        }

        employeesBySalary.retainAll(employeesByDep);
        */
        Iterable<Department> departments = depRepo.findAll();
        Page<Employee> empsPage = employeeRepo.filter(name, depId, minSalary, maxSalary, pageable);

        model.addAttribute("employees", empsPage);
        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        model.addAttribute("depId", depId);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        return "index";
    }

    // BindingResult must be before Model for correct work
    @PostMapping("/index")
    public String addEmployee(@PageableDefault(sort= {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                              @AuthenticationPrincipal User user,
                              @Valid Employee employee,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("employee", employee);
        } else {
            List<Employee> employeeName = employeeRepo.findByName(employee.getName());

            if (employeeName.isEmpty()) {
                employee.setAuthor(user);
                model.addAttribute("employee", null);
                employeeRepo.save(employee);
            } else {
                model.addAttribute("nameError", "Сотрудник с данным именем уже существует!");
            }
        }

        Page<Employee> employeeRepoAll = employeeRepo.findAll(pageable);
        model.addAttribute("employees", employeeRepoAll);

        Iterable<Department> departments = depRepo.findAll();
        model.addAttribute("departments", departments);

        return "index";
    }

}