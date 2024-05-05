package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.UserConverter;
import com.d2d.grh.grhBackend.dto.UserDto;
import com.d2d.grh.grhBackend.entity.Department;
import com.d2d.grh.grhBackend.entity.Role;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.exception.UserNotFoundException;
import com.d2d.grh.grhBackend.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public List<User>findAllUsers(){
        return this.userRepository.findAll();
    }

    public String addNewUser(User user){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        this.userRepository.save(user);
        System.out.println("added successfully");
        return "userCreated successfully";
    }


    public UserDto findUserByUsername(String username){
        User user = this.userRepository.findByUsername(username);
        return this.userConverter.entityToDto(user);
    }
    public User findUserByUsername2(String username){
        return this.userRepository.findByUsername(username);
    }

    public UserDto updateUserInfo(Long userId, String user, String department) throws JsonProcessingException {
        Department department1 = new ObjectMapper().readValue(department, Department.class);
        User userProfile = new ObjectMapper().readValue(user, User.class);
        User userToUpdate = this.userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("No user match with this ID"));
        userToUpdate.setFirstname(userProfile.getFirstname());
        userToUpdate.setLastname(userProfile.getLastname());
        userToUpdate.setAddress(userProfile.getAddress());
        userToUpdate.setCity(userProfile.getCity());
        userToUpdate.setAboutMe(userProfile.getAboutMe());
        userToUpdate.setPostalCode(userProfile.getPostalCode());
        userToUpdate.setCountry(userProfile.getCountry());
        userToUpdate.setDepartment(department1);
        return this.userConverter.entityToDto(this.userRepository.save(userToUpdate));
    }

    public void newUser(String newUser, String department) throws JsonProcessingException {
        User user = new ObjectMapper().readValue(newUser, User.class);
        Department department1 = new ObjectMapper().readValue(department, Department.class);
        System.out.println(department);
        this.emailService.sendMail(user.getEmail(),
                "GRH ACCOUNT",
                "Welcome "+user.getFirstname()+ " "+ user.getLastname()+"."+"\nYour credentials to access in our recruitment platform is: \n" +
                        user.getUsername()+ "\n"+user.getPassword());
        user.setDepartment(department1);
        addNewUser(user);
    }

    public List<UserDto> getAllUser(String username){
        List<UserDto> allUsers = this.userConverter.entityToDto(this.userRepository.findAll());
        System.out.println(allUsers);
        List<UserDto> usersWithoutMe = allUsers.stream()
                .filter(u-> !u.getUsername().equals(username))
                .collect(Collectors.toList());
        System.out.println(usersWithoutMe);
        return usersWithoutMe;

    }

    public List<UserDto> getOnlyRhUsers(String username){
        List<UserDto> users = this.getAllUser(username);
        return users.stream().
                filter(user-> user.getRoleName().equals("RH"))
                .collect(Collectors.toList());

    }

}
