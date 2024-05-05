package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.models.UserRoleObject;
import com.d2d.grh.grhBackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody UserRoleObject userRoleObject){
        this.roleService.addRoleToUser(userRoleObject);
    }
}


