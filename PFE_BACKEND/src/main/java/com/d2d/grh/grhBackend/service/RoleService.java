package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.Role;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.models.UserRoleObject;
import com.d2d.grh.grhBackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public void addRoleToUser(UserRoleObject userRoleObject){
        User user = this.userService.findUserByUsername2(userRoleObject.getUsername());
        Role role = this.roleRepository.findByRoleName(userRoleObject.getRoleName());
        user.getRoles().add(role);
    }

    public void addRole(Role role){
        this.roleRepository.save(role);
    }
}
