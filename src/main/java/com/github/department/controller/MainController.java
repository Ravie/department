package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.Employee;
import com.github.department.entity.User;
import com.github.department.repo.DepartmentRepo;
import com.github.department.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public String index(@RequestParam(required = false, defaultValue = "") String name,
                        @RequestParam(required = false) Integer searchDepId,
                        @RequestParam(required = false, defaultValue = "") Double minSalary,
                        @RequestParam(required = false, defaultValue = "") Double maxSalary,
                        Model model
    ) {
        List<Employee> employeesByName;
        List<Employee> employeesByDep;
        List<Employee> employeesBySalary;
        Iterable<Department> departments = depRepo.findAll();

        if (name != null && !name.isEmpty()) {
            employeesByName = employeeRepo.findByName(name);
        } else {
            employeesByName = StreamSupport
                    .stream(employeeRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }

        if (searchDepId != null && depRepo.findById(searchDepId).isPresent()) {
            employeesByDep = employeeRepo.findByDepartment(depRepo.findById(searchDepId).get());
        } else {
            employeesByDep = StreamSupport
                    .stream(employeeRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }

        employeesByDep.retainAll(employeesByName);

        if (minSalary != null && maxSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryBetween(minSalary, maxSalary);
        } else if (minSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryGreaterThanEqual(minSalary);
        } else if (maxSalary != null) {
            employeesBySalary = employeeRepo.findBySalaryLessThanEqual(maxSalary);
        } else {
            employeesBySalary = StreamSupport
                    .stream(employeeRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }

        employeesBySalary.retainAll(employeesByDep);

        model.addAttribute("employees", employeesBySalary);
        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        model.addAttribute("searchDepId", searchDepId);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        return "index";
    }

    @PostMapping("/index")
    public String addEmployee(@AuthenticationPrincipal User user, @RequestParam String name, @RequestParam Integer depId, @RequestParam Double salary, Model model) {
        List<Employee> employeeName = employeeRepo.findByName(name);

        if (employeeName.isEmpty()) {
            Employee employee = new Employee(name, depRepo.findById(depId).get(), salary, user);
            employeeRepo.save(employee);
        }

        Iterable<Employee> employeeRepoAll = employeeRepo.findAll();
        model.addAttribute("employees", employeeRepoAll);
        return "redirect:/index";
    }
}