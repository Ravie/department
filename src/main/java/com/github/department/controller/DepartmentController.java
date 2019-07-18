package com.github.department.controller;

import com.github.department.entity.Department;
import com.github.department.entity.User;
import com.github.department.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo departmentRepo;

    @GetMapping
    public String loadAll(@RequestParam(required = false, defaultValue = "") String name,
                          @PageableDefault(sort= {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                          Model model) {
        Page<Department> departments;

        if (name != null && !name.isEmpty()) {
            departments = departmentRepo.findByNameLike(name, pageable);
        } else {
            departments = departmentRepo.findAll(pageable);
        }

        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        return "departmentList";
    }

    @PostMapping
    public String addDepartment(@PageableDefault(sort= {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                                @AuthenticationPrincipal User user,
                                @Valid Department department,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.addAttribute("department", department);
            model.mergeAttributes(errors);
        } else {
            Page<Department> depRepo = departmentRepo.findByName(department.getName(), pageable);

            if (depRepo.isEmpty()) {
                department.setAuthor(user);
                departmentRepo.save(department);
                model.addAttribute("nameError", null);
            } else {
                model.addAttribute("nameError", "Отдел с таким именем уже существует!");
            }
        }

        Page<Department> departments = departmentRepo.findAll(pageable);
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
