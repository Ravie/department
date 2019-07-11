package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.User;
import com.github.department.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo departmentRepo;

    @GetMapping
    public String findByDepartment(@RequestParam(required = false, defaultValue = "") String name, Model model) {
        Iterable<Department> departments;

        if (name != null && !name.isEmpty()) {
            departments = departmentRepo.findByName(name);
        } else {
            departments = StreamSupport
                    .stream(departmentRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }

        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        return "departmentList";
    }

    @PostMapping
    public String addDepartment(@AuthenticationPrincipal User user, @RequestParam String name, Model model) {
        List<Department> depRepo = departmentRepo.findByName(name);

        if (depRepo.isEmpty()) {
            Department department = new Department(name, user);
            departmentRepo.save(department);
        }

        Iterable<Department> departments = departmentRepo.findAll();
        model.addAttribute("departments", departments);
        return "departmentList";
    }

    @GetMapping("/edit/{department}")
    public String departmentEditorForm(@PathVariable Department department, Model model) {
        model.addAttribute("currentDep", department);
        return "departmentEditor";
    }

    @GetMapping("/delete/{department}")
    public String departmentDelete(@PathVariable Department department, Model model) {
        departmentRepo.deleteById(department.getId());
        model.addAttribute("departments", departmentRepo.findAll());
        return "redirect:/department";
    }

    @PostMapping("/edit")
    public String saveChanges(@RequestParam("depId") Department department,
                              @RequestParam("depName") String depName
    ) {
        department.setName(depName);

        departmentRepo.save(department);

        return "redirect:/department";
    }
}
