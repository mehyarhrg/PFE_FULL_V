package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.entity.Department;
import com.d2d.grh.grhBackend.repository.DepartmentRepository;
import com.d2d.grh.grhBackend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{name}")
    public Department getDepartmentByName(@PathVariable String name){
        return this.departmentService.getDepartmentByDepartmentName(name);
    }
}
