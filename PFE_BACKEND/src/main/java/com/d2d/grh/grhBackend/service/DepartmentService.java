package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.Department;
import com.d2d.grh.grhBackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department){
       return this.departmentRepository.save(department);
    }

    public Department getDepartmentByDepartmentName(String departmentName){
        return this.departmentRepository.findByDepartmentName(departmentName);
    }
}
