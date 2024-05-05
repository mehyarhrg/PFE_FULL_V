package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.dto.UserDto;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> FindAllUsers(){
        return this.userService.findAllUsers();
    }

    @PostMapping("/addUser")
    public void addNewUser(@RequestBody User user){
        this.userService.addNewUser(user);
    }

    @GetMapping("/user/{username}")
    public UserDto findUserByUsername(@PathVariable String username){
        return this.userService.findUserByUsername(username);
    }

    @PostMapping("/updateProfile/{userId}")
    public UserDto updateProfile(@PathVariable Long userId, @RequestParam("profile") String user, @RequestParam("department") String department) throws JsonProcessingException {
       return this.userService.updateUserInfo(userId, user, department);
    }

    @PostMapping("/addNewUser")
    public void newUser(@RequestParam("user") String newUser, @RequestParam("department") String department) throws JsonProcessingException {
        this.userService.newUser(newUser, department);
    }

    @GetMapping("/allUsers/{username}")
    public List<UserDto> getAllUsers(@PathVariable String username){
        return this.userService.getAllUser(username);
    }

    @GetMapping("onlyRhUsers/{currentUsername}")
    public List<UserDto> getOnlyRhUsers(@PathVariable String currentUsername){
        return this.userService.getOnlyRhUsers(currentUsername);
    }
}
