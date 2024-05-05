package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.UserDto;
import com.d2d.grh.grhBackend.entity.Department;
import com.d2d.grh.grhBackend.entity.Role;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private DepartmentRepository departmentRepository;

    @Autowired
    public UserConverter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        String roleName = "";
        for (int i = 0; i < user.getRoles().toArray().length; i++) {
            roleName =user.getRoles().toArray()[i].toString().substring(user.getRoles().toArray()[i].toString().indexOf("roleName=")+9, user.getRoles().toArray()[i].toString().length()-1);
        }
        dto.setUserId(user.getUserId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setAboutMe(user.getAboutMe());
        dto.setCreationDate(user.getCreationDate());
        dto.setRoleName(roleName);
        dto.setAboutMe(user.getAboutMe());
        dto.setDepartment(user.getDepartment() != null ? user.getDepartment().getDepartmentName():null);
        return dto;
    }

    public List<UserDto> entityToDto(List<User> users){
        return users.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }

    public User dtoEntity(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setPostalCode(userDto.getPostalCode());
        user.setAboutMe(userDto.getAboutMe());
        user.setDepartment(this.departmentRepository.findByDepartmentName(userDto.getDepartment()));
        return user;
    }

    public User customDtoEntity(UserDto userDto, Department dep){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setPostalCode(userDto.getPostalCode());
        user.setAboutMe(userDto.getAboutMe());
        user.setDepartment(dep);
        return user;
    }

    public List<User> dtoToEntity(List<UserDto> userDtoList ){
        return userDtoList.stream().map(x -> dtoEntity(x)).collect(Collectors.toList());
    }
}
