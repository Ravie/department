package com.github.department.controller;

import com.github.department.entity.Employee;
import com.github.department.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Iterable<Employee> employeeRepoAll = employeeRepo.findAll();
        model.addAttribute("employees", employeeRepoAll);
        return "index";
    }

    @PostMapping("/index")
    public String addEmployee(@RequestParam String name, @RequestParam Integer depId, @RequestParam Double salary, Model model) {
        Employee employee = new Employee(name, depId, salary);
        employeeRepo.save(employee);
        Iterable<Employee> employeeRepoAll = employeeRepo.findAll();
        model.addAttribute("employees", employeeRepoAll);
        return "index";
    }

    @PostMapping("filterByName")
    public String filterByName(@RequestParam String name, Model model) {
        Iterable<Employee> employees;

        if (name != null && !name.isEmpty()) {
            employees = employeeRepo.findByName(name);
        } else {
            employees = employeeRepo.findAll();
        }

        model.addAttribute("employees", employees);

        return "index";
    }

    @PostMapping("filterByDepId")
    public String filterByDepId(@RequestParam Integer depId, Model model) {
        Iterable<Employee> employees;

        if (depId != null) {
            employees = employeeRepo.findByDepId(depId);
        } else {
            employees = employeeRepo.findAll();
        }

        model.addAttribute("employees", employees);

        return "index";
    }

    @PostMapping("filterBySalary")
    public String filterBySalary(@RequestParam Double minSalary, @RequestParam Double maxSalary, Model model) {
        Iterable<Employee> employees;

        if (minSalary != null && maxSalary != null) {
            employees = employeeRepo.findBySalaryBetween(minSalary, maxSalary);
        } else if (minSalary != null) {
            employees = employeeRepo.findBySalaryGreaterThanEqual(minSalary);
        } else if (maxSalary != null) {
            employees = employeeRepo.findBySalaryLessThanEqual(maxSalary);
        } else {
            employees = employeeRepo.findAll();
        }

        model.addAttribute("employees", employees);

        return "index";
    }
}