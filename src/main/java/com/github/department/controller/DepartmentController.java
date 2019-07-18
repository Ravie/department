package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.User;
import com.github.department.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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
    public String addDepartment(@AuthenticationPrincipal User user, @Valid Department department, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.addAttribute("department", department);
            model.mergeAttributes(errors);
        } else {
            List<Department> depRepo = departmentRepo.findByName(department.getName());

            if (depRepo.isEmpty()) {
                department.setAuthor(user);
                departmentRepo.save(department);
                model.addAttribute("nameError", null);
            } else {
                model.addAttribute("nameError", "Отдел с таким именем уже существует!");
            }
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
    public String departmentDelete(@PathVariable Department department) {
        departmentRepo.deleteById(department.getId());

        return "redirect:/department";
    }

    @PostMapping("/edit")
    public String saveChanges(@ModelAttribute Department currentDep) {
        departmentRepo.save(currentDep);

        return "redirect:/department";
    }
}
